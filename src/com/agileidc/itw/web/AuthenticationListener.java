package com.agileidc.itw.web;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;

import com.agileidc.itw.model.ItwUser;
import com.agileidc.itw.service.ItwUserService;

public class AuthenticationListener implements
		ApplicationListener<AbstractAuthenticationEvent> {
	@Override
	public void onApplicationEvent(AbstractAuthenticationEvent appEvent) {
		if (appEvent instanceof AuthenticationSuccessEvent) {
			AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;
			//do nothing
			
		}

		if (appEvent instanceof AuthenticationFailureBadCredentialsEvent) {
			AuthenticationFailureBadCredentialsEvent event = (AuthenticationFailureBadCredentialsEvent) appEvent;
			System.out.println("login failed...... Puneet.....12345");
			String userDetails = (String) event.getAuthentication()
					.getPrincipal();
			System.out.println("Failed login:" + userDetails);

			ApplicationContext ctx = ApplicationContextProvider1
					.getApplicationContext();

			ItwUserService itwUserService = (ItwUserService) ctx
					.getBean("itwUserService");

			List<ItwUser> itwUsers = itwUserService.getAllUsers(userDetails);

			int failedcount = 0;
			int count = 0;
			int enabled=1;
			if (itwUsers != null && !itwUsers.isEmpty()) {

				for (ItwUser itwUser : itwUsers) {

					failedcount = itwUser.getFailedcount();
					
					System.out.println("Failed Count=========="+failedcount);
				}
			}

			if (failedcount < 3) {

				count = failedcount + 1;
				
			}else{
				
				count=0;
				enabled=0;
			}
			ItwUser itwUser = new ItwUser();
            
			itwUser.setFailedcount(count);
			itwUser.setUserid(userDetails);
			itwUser.setEnabled(enabled);
			System.out.println("Count value============"+itwUser.getFailedcount());
			itwUserService.updateItwUser(itwUser);

			

		}
	}
}

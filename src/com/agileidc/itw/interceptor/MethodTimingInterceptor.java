package com.agileidc.itw.interceptor;

import java.math.BigDecimal;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MethodTimingInterceptor implements MethodInterceptor {

	private final Log log = LogFactory.getLog(getClass());

	public Object invoke(final MethodInvocation methodInvocation)
			throws Throwable {
		final StopWatch stopWatch = new StopWatch();
		log.info("MethodTimingInterceptor START stopwatch");
		stopWatch.start();
		Object methodResult = null;
		try {
			methodResult = methodInvocation.proceed();
		} finally {
			log.info("MethodTimingInterceptor STOP stopwatch");
			stopWatch.stop();
		}
		final long millis = stopWatch.getTime();
		final BigDecimal seconds = new BigDecimal(millis).divide(new BigDecimal(
				DateUtils.MILLIS_PER_SECOND), BigDecimal.ROUND_HALF_UP);

		log.info("Method Invocation ["
					+ methodInvocation.getThis().getClass().getName() + "."
					+ methodInvocation.getMethod().getName() + "] Total Time: " + seconds
					+ "(seconds) " + millis + "(millis)");
	
		return methodResult;
	}

}

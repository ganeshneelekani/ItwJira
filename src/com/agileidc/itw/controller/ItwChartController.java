package com.agileidc.itw.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.agileidc.itw.model.ItwChartStatus;
import com.agileidc.itw.model.ItwSeverity;
import com.agileidc.itw.model.ItwStatusTypes;
import com.agileidc.itw.model.ItwTaskBug;
import com.agileidc.itw.service.ItwChartStatusService;
import com.agileidc.itw.service.ItwSeverityService;
import com.agileidc.itw.service.ItwStatusTypesService;
import com.agileidc.itw.service.ItwTaskBugService;

@Controller
public class ItwChartController {

	@Autowired
	private ItwTaskBugService itwTaskBugService;

	@Autowired
	private ItwStatusTypesService itwStatusTypesService;

	@Autowired
	private ItwChartStatusService itwChartStatusService;

	@Autowired
	private ItwSeverityService itwSeverityService;

	@RequestMapping(value = "/piaChartDemo", method = RequestMethod.GET)
	public ModelAndView piaChartDemo(HttpServletRequest request,
			HttpServletResponse response) {

		DefaultPieDataset pieDataset = new DefaultPieDataset();

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		List<ItwChartStatus> itwChartStatusList = itwChartStatusService
				.listItwChartStatus(langId);

		Integer totalBugs = itwTaskBugService.listItwTaskBugs().size();

		for (ItwChartStatus itwChartStatus : itwChartStatusList) {

			List<ItwStatusTypes> itwStatusTypesList = itwStatusTypesService
					.listItwStatusIDTypes(langId, itwChartStatus.getId());

			int tempcount = 0;

			for (ItwStatusTypes itwStatusTypes : itwStatusTypesList) {

				Integer closedBugs = itwTaskBugService
						.getItwTaskBugCount(itwStatusTypes.getId());
				tempcount += closedBugs;

			}

			double numerator = tempcount;
			double denom = totalBugs;
			double percent = (numerator / denom) * 100;

			int intPercent = (int) Math.round(percent);

			if (intPercent != 0)

				pieDataset.setValue(itwChartStatus.getShortname(), intPercent);

		}

		// Integer closedBugs = itwTaskBugService.getItwTaskBugCount(langId);

		JFreeChart chart = ChartFactory.createPieChart(
				"Bug Status Chart (All parameters in %)", pieDataset, true,
				true, false);

		// chart.setBackgroundPaint(Color.blue);
		// chart.setBackgroundImageAlpha(0.5f);
		// chart.setBorderVisible(false);

		/*
		 * PiePlot3D chart3D= (PiePlot3D) chart.getPlot();
		 * 
		 * chart3D.setStartAngle(290); chart3D.setDirection(Rotation.CLOCKWISE);
		 * chart3D.setForegroundAlpha(0.5f);
		 */

		try {
			ChartUtilities.saveChartAsJPEG(new File(request.getRealPath("")
					+ "/images/chart.jpg"), chart, 900, 700);
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
		}

		// Bar chart for Raised Vs Closed Bugs
		// =======================================

		Date today = new Date();
		Calendar cal = new GregorianCalendar();

		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");

		cal.setTime(today);

		cal.add(Calendar.DAY_OF_MONTH, -30);

		String today30 = format.format(cal.getTime());

		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -60);

		String today60 = format.format(cal.getTime());

		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -15);

		String today15 = format.format(cal.getTime());

		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -7);

		String today7 = format.format(cal.getTime());

		Integer totalBugsBefor7Days = itwTaskBugService
				.getItwTaskBugForDate(today7);
		Integer totalBugsBefor15Days = itwTaskBugService
				.getItwTaskBugForDate(today15);
		Integer totalBugsBefor30Days = itwTaskBugService
				.getItwTaskBugForDate(today30);
		Integer totalBugsBefor60Days = itwTaskBugService
				.getItwTaskBugForDate(today60);

		List<ItwStatusTypes> itwClosedStatusTypesList = itwStatusTypesService
				.listItwStatusIDTypes(langId, 2);

		int closedBugsWithin7Days = 0;
		int closedBugsWithin15Days = 0;
		int closedBugsWithin30Days = 0;
		int closedBugsWithin60Days = 0;

		for (ItwStatusTypes itwStatusTypes : itwClosedStatusTypesList) {

			Integer closedBugs7Days = itwTaskBugService
					.getItwTaskClosedBugForDate(today7, itwStatusTypes.getId());
			closedBugsWithin7Days += closedBugs7Days;

			Integer closedBugs15Days = itwTaskBugService
					.getItwTaskClosedBugForDate(today15, itwStatusTypes.getId());
			closedBugsWithin15Days += closedBugs15Days;

			Integer closedBugs30Days = itwTaskBugService
					.getItwTaskClosedBugForDate(today30, itwStatusTypes.getId());
			closedBugsWithin30Days += closedBugs30Days;

			Integer closedBugs60Days = itwTaskBugService
					.getItwTaskClosedBugForDate(today60, itwStatusTypes.getId());
			closedBugsWithin60Days += closedBugs60Days;

		}

		DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();

		categoryDataset.setValue(totalBugsBefor60Days, "Raised", "60");
		categoryDataset.setValue(totalBugsBefor30Days, "Raised", "30");
		categoryDataset.setValue(totalBugsBefor15Days, "Raised", "15");
		categoryDataset.setValue(totalBugsBefor7Days, "Raised", "7");

		categoryDataset.setValue(closedBugsWithin60Days, "Closed", "60");
		categoryDataset.setValue(closedBugsWithin30Days, "Closed", "30");
		categoryDataset.setValue(closedBugsWithin15Days, "Closed", "15");
		categoryDataset.setValue(closedBugsWithin7Days, "Closed", "7");

		// Enrollment in Masters level

		JFreeChart barChart = ChartFactory.createBarChart3D(
				"Raised Vs Closed Bugs Chart", // Title
				"Days", // X-Axis label
				"Number of Bugs",// Y-Axis label
				categoryDataset, // Dataset
				PlotOrientation.VERTICAL, true, // Show legend
				true, false);

		barChart.setBackgroundImageAlpha(0.5f);
		barChart.setBorderVisible(true);
		try {
			/**
			 * This utility saves the JFreeChart as a JPEG First Parameter:
			 * FileName Second Parameter: Chart To Save Third Parameter: Height
			 * Of Picture Fourth Parameter: Width Of Picture
			 */
			ChartUtilities.saveChartAsJPEG(new File(request.getRealPath("")
					+ "/images/Barchart.jpg"), barChart, 900, 700);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Problem occurred creating chart.");
		}

		// Bar chart for Criticality =======================================

		List<ItwSeverity> itwSeverityList = itwSeverityService
				.listItwSeveritys(langId);

		DefaultCategoryDataset categoryDataset1 = new DefaultCategoryDataset();

		for (ItwSeverity itwSeverity : itwSeverityList) {

			int temp7 = 0;
			int temp15 = 0;

			for (ItwStatusTypes itwStatusTypes : itwClosedStatusTypesList) {

				Integer closedBugs7Days = itwTaskBugService
						.getItwTaskSeverityClosedBugForDate(today7,
								itwSeverity.getId(), itwStatusTypes.getId());
				temp7 += closedBugs7Days;
				Integer closedBugs15Days = itwTaskBugService
						.getItwTaskSeverityClosedBugForDate(today15,
								itwSeverity.getId(), itwStatusTypes.getId());

				temp15 += closedBugs15Days;

			}
			
			

			String todayString = format.format(today);

			Integer raisedBugs7Days = itwTaskBugService
					.getItwTaskSeverityBugForDate(today7, todayString,
							itwSeverity.getId());

			Integer raisedBugs15Days = itwTaskBugService
					.getItwTaskSeverityBugForDate(today15, today7,
							itwSeverity.getId());

			categoryDataset1.setValue(raisedBugs15Days,
					itwSeverity.getShortname(), "15-7");
			categoryDataset1.setValue(raisedBugs7Days,
					itwSeverity.getShortname(), "7-1");
			categoryDataset1.setValue(temp15,
					"Closed" + itwSeverity.getShortname(), "15-7");
			categoryDataset1.setValue(temp7,
					"Closed" + itwSeverity.getShortname(), "7-1");

		}

		JFreeChart barChart1 = ChartFactory.createBarChart3D(
				"Criticality Chart ", // Title
				"Days", // X-Axis label
				"Number of Bugs",// Y-Axis label
				categoryDataset1, // Dataset
				PlotOrientation.VERTICAL, true, // Show legend
				true, false);

		//barChart1.setBackgroundImageAlpha(1.0f);
		//barChart1.setBorderVisible(true);
		try {
			
			ChartUtilities.saveChartAsJPEG(new File(request.getRealPath("")
					+ "/images/CriticalityBarchart.jpg"), barChart1, 900, 700);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Problem occurred creating chart.");
		}

		// Bar chart for SLA ===============================

		DefaultCategoryDataset slaBarDataset = new DefaultCategoryDataset();

		for (ItwSeverity itwSeverity : itwSeverityList) {

			SimpleDateFormat formatForSeverity = new SimpleDateFormat(
					"dd-MMM-yy HH:mm:ss aaa");

			cal.setTime(today);

			cal.add(Calendar.DAY_OF_MONTH, -30);

			String last30days = formatForSeverity.format(cal.getTime());

			int severityCount = itwTaskBugService.getItwTaskBugSeverityCount(
					itwSeverity.getId(), last30days);

			slaBarDataset.setValue(severityCount, itwSeverity.getShortname(),
					"30");

			int temp = 0;

			for (ItwStatusTypes itwStatusTypes : itwClosedStatusTypesList) {

				List<ItwTaskBug> itwTaskBugList = itwTaskBugService
						.getItwTaskClosedBugForSeverity(itwSeverity.getId(),
								itwStatusTypes.getId(), last30days);

				
				int sla = itwSeverity.getSla();

				for (ItwTaskBug itwTaskBug : itwTaskBugList) {

					long milliseconds1 = itwTaskBug.getCreatedtime().getTime();
					long milliseconds2 = itwTaskBug.getLastupdatedtime()
							.getTime();

					long diff = milliseconds2 - milliseconds1;

					long diffHours = diff / (60 * 60 * 1000);

					Date d1 = itwTaskBug.getCreatedtime();
					Date d2 = itwTaskBug.getLastupdatedtime();
					long elapseHours = 0;

					try {
						long diff1 = d2.getTime() - d1.getTime();
						long diffDays = diff1 / (24 * 60 * 60 * 1000);

						Calendar cal2 = Calendar.getInstance();

						cal2.setTime(d1);
						int hour = cal2.get(Calendar.HOUR_OF_DAY);
						int day = cal2.get(Calendar.DAY_OF_MONTH);
						int month = cal2.get(Calendar.MONTH);

						cal2.setTime(d2);

						int hour1 = cal2.get(Calendar.HOUR_OF_DAY);
						int day1 = cal2.get(Calendar.DAY_OF_MONTH);
						int month1 = cal2.get(Calendar.MONTH);

						cal2.setTime(d2);

						int days = day1 - day;
						int months = month1 - month;

						if (days == 0 && months == 0) {

							elapseHours = diffHours;

						}

						long temp1;
						long temp2;

						if (days == 1 && months == 0) {

							if (hour < 22) {

								temp1 = 22 - hour;
							} else {
								temp1 = 0;
							}
							if (hour1 > 10) {

								temp2 = hour1 - 10;
							} else {
								temp2 = hour1;
							}

							elapseHours = temp2 + temp1;

						}

						if (diffDays > 1) {

							Calendar cal1 = Calendar.getInstance();

							int loopVar = (int) diffDays;

							int betweenhours = 0;
							int betweendays = 0;

							Date datB = d1;
							for (int i = 1; i < loopVar; i++) {

								cal1.setTime(datB);

								cal1.add(cal1.DAY_OF_MONTH, 1);

								datB = cal1.getTime();

								if ((!datB.toString().contains("Sat") && !datB
										.toString().contains("Sun"))) {

									betweendays += 1;

								}

							}
							betweenhours = (betweendays) * 9;
							if (hour < 22) {

								temp1 = 22 - hour;
							} else {
								temp1 = 0;
							}
							if (hour1 > 10) {

								temp2 = hour1 - 10;
							} else {
								temp2 = hour1;
							}

							elapseHours = temp2 + temp1 + betweenhours;

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					if (elapseHours <= sla) {
						temp += 1;
					}

				}

				slaBarDataset.setValue(temp,
						"Met " + itwSeverity.getShortname(), "30");

			}

		}

		JFreeChart salChart = ChartFactory.createBarChart3D(
				"SLA Adhereance Chart", // Title
				"Days", // X-Axis label
				"Number of Bugs",// Y-Axis label
				slaBarDataset, // Data set
				PlotOrientation.VERTICAL, true, // Show legend
				true, false);

		salChart.setBackgroundImageAlpha(1f);
		// salChart.setBackgroundPaint(Color.blue);
		salChart.setBorderVisible(true);

		try {

			ChartUtilities.saveChartAsJPEG(new File(request.getRealPath("")
					+ "/images/slaChart.jpg"), salChart, 900, 700);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Problem occurred creating chart.");
		}

		return new ModelAndView("PiaChartDemojsp");
	}

}

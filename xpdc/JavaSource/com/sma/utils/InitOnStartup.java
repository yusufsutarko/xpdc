package com.sma.utils;

import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

import org.apache.log4j.Logger;

/**
 * Class buatan sendiri untuk diload sebagai spring bean di awal, misalnya untuk compile seluruh jrxml (jasper reports)
 * 
 * @author Yusuf
 * @since Jun 20, 2013 (9:33:10 AM)
 *
 */
public class InitOnStartup{

	private static Logger logger = Logger.getLogger(InitOnStartup.class);

	/**
	 * Constructor
	 */
	public InitOnStartup(Properties props, ServletContext servletContext) {
		logger.debug("----- COMPILING REPORTS -----");
		
		boolean compile = props.getProperty("compile_reports_on_startup").equals("1");
		if(compile){
			try {
				List<String> reportList = Utils.listAllReports(props);
				String reportDir = props.getProperty("dir.report");
				
				for(String report : reportList) {
					String value = props.getProperty(report);
					value = "/WEB-INF/classes/" + reportDir + "/" + value + ".jrxml";
					try {
						JasperCompileManager.compileReportToFile(servletContext.getRealPath(value));
						logger.info("COMPILING REPORT [" +report+ "]: " + "SUCCESS");
					} catch (JRException e) {
						logger.info("COMPILING REPORT [" +report+ "]: " + "FAILED -> " + e.getMessage());
					}
				}
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}		
		}
		
	}
	
	public String test1, test2;

	public void setTest1(String test1) {
		System.out.println("TEST1 = " + test1);
		this.test1 = test1;
	}

	public void setTest2(String test2) {
		System.out.println("TEST2 = " + test2);
		this.test2 = test2;
	}

}
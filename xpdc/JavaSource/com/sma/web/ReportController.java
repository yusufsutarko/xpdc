package com.sma.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jolbox.bonecp.BoneCPDataSource;
import com.sma.model.DropDown;
import com.sma.model.User;
import com.sma.utils.Utils;

/**
 * Report Controller
 * 
 * @author Yusuf
 * @since Jun 26, 2013 (8:48:58 AM)
 *
 */
@Controller
@RequestMapping("/report")
public class ReportController extends ParentController{
	
	@Autowired
	private BoneCPDataSource dbDataSource;
	
	private Connection connection;
	
	private Connection getConnection() {
		//bila koneksi belum ada, retrieve dari db pool
		if(this.connection==null){
			try { this.connection = dbDataSource.getConnection(); } 
			catch (SQLException e) { e.printStackTrace(); }
			
		}else{
			//bila koneksi sudah ada, tapi tidak valid atau sudah tertutup, maka lakukan looping untuk mengambil koneksi yang valid
			try {
				while(!this.connection.isValid(5)){
					this.connection = dbDataSource.getConnection();
				}
			}catch (SQLException e) { e.printStackTrace(); }
		}
		return this.connection;
	}	
	
	//@ModelAttribute pada deklarasi method berarti: 
	//bisa lebih dari satu model attribute, bisa juga digunakan sebagai reference data
	@ModelAttribute("reff")
	public Map<String, Object> reff(HttpServletRequest request){
		List<DropDown> listFormat = new ArrayList<DropDown>();
		listFormat.add(new DropDown("pdf", "Adobe Reader (.pdf)", "Office"));
		listFormat.add(new DropDown("xls", "Excel (.xls)", "Office"));
		listFormat.add(new DropDown("html", "HTML (.html)", "Office"));
		listFormat.add(new DropDown("xlsx", "Excel 2007-2010 (.xlsx)", "Office"));
		listFormat.add(new DropDown("pptx", "Powerpoint 2007-2010 (.pptx)", "Office"));
		listFormat.add(new DropDown("docx", "Word 2007-2010 (.docx)", "Office"));
		//listFormat.add(new DropDown("jxl", "OpenOffice Call (.ods)", "Office"));
		//listFormat.add(new DropDown("odt", "OpenOffice Writer (.odt)", "Office"));
		listFormat.add(new DropDown("rtf", "Rich Text Format (.rtf)", "Office"));
		//listFormat.add(new DropDown("txt", "Text (.txt)", "Office"));

		List<DropDown> listTanggal = new ArrayList<DropDown>();
		listTanggal.add(new DropDown("tgl_stt", "Tanggal STT"));

		//User currentUser = (User) request.getSession().getAttribute("currentUser");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listFormat", listFormat);
		//map.put("AllBank", dbService.selectDropDown("id", "nama", "mst_bank", "active = 1 and jenis = 1", "nama"));
		return map;
	}
	
	/**
	 * Fungsi untuk generate report, dipanggil oleh seluruh report
	 * 
	 * @author Yusuf
	 * @since Feb 5, 2013 (3:23:00 PM)
	 *
	 * @param jenis
	 * @param params
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws JRException
	 * @throws IOException
	 */
	private String generateReport(String jenis, Map<String, Object> params, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws JRException, IOException{
		ServletContext context = session.getServletContext();
		String format = (String) params.get("format");
		
		//Generate report
		JasperPrint jasperPrint = JasperFillManager.fillReport(
			context.getRealPath("/WEB-INF/classes/" + props.getProperty("dir.report") + "/" +  
			props.getProperty("report." + jenis) + ".jasper"), //report path 
			params, //report parameters
			getConnection() //connection object
			);

		//Put generated report into session
		session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
		
		//Text File
		if(format.equalsIgnoreCase("txt")){
			JRCsvExporter exporter = new JRCsvExporter();
			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
			//tambahan header khusus file CSV
			response.setHeader("Content-Disposition","attachment; filename=\"report.txt\";");
			
			exporter.exportReport();
			return null;
	
		//CSV File
		}else if(format.equalsIgnoreCase("csv")){
				JRCsvExporter exporter = new JRCsvExporter();
				exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER,",");
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
				//tambahan header khusus file CSV
				response.setHeader("Content-Disposition","attachment; filename=\"report.csv\";");
				
				exporter.exportReport();
				return null;
		
		//HTML File
		}else if(format.equalsIgnoreCase("html")){
			JRHtmlExporter exporter = new JRHtmlExporter();
			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
			//HTML Specific parameters
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/jasper/image?image=");
			exporter.setParameter(JRHtmlExporterParameter.IGNORE_PAGE_MARGINS, true); //biar gak terlalu banyak white space
			exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true); //biar gak terlalu banyak white space
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, ""); //biar tidak ada paging (khusus html)
			
			exporter.exportReport();
			return null;

		//HTML File yg langsung print
		}else if(format.equalsIgnoreCase("htmlprint")){
			JRHtmlExporter exporter = new JRHtmlExporter();
			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
			//HTML Specific parameters
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/jasper/image?image=");
			//exporter.setParameter(JRHtmlExporterParameter.IGNORE_PAGE_MARGINS, true); //biar gak terlalu banyak white space
			//exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true); //biar gak terlalu banyak white space
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, ""); //biar tidak ada paging (khusus html)

			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "<html><head><script>window.print();</script><style>@page{size:auto !important; margin-top: 5mm !important; margin-left: 0mm !important; margin-right: 0mm !important; margin-bottom: 0mm !important;} span{font-size: 0.8em !important; font-family: Courier !important;} table{width: 100% !important;}</style></head><body>");
//			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "<html><head><script>window.print();</script><style>@page{size:auto !important; margin: 0mm !important;} span{font-size: 0.8em !important; font-family: \"Sans Serif\" !important;} table{width: 100% !important;}</style></head><body>");
//			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "<html><head><script>window.print();</script><style></style></head><body>");
			exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "</body></html>");
			
			exporter.exportReport();
			return null;				

		//format selain HTML dan TXT
		}else{
			return "redirect:/jasper/" + format; //redirect ke JasperReports Servlet sesuai format
		}		
	}

	//print STT
	@RequestMapping(value="/stt/{trans_id}/{flag}")
	public String stt(@PathVariable Integer trans_id, @PathVariable Integer flag, HttpSession session, HttpServletRequest request, HttpServletResponse response) 
			throws JRException, IOException, ServletRequestBindingException {
		
		//currently logged in user
		User currentUser = (User) session.getAttribute("currentUser");
		
		//jenis report
		String jenisReport = "";
		if(flag == 0) {
			jenisReport = "print_stt_blank_pdf";
		}else if(flag == 1) {
			jenisReport = "print_stt_pdf";
		}else if(flag == 2) {
			jenisReport = "print_stt_blank";
		}else if(flag == 3) {
			jenisReport = "print_stt";
		}
		
		logger.debug("Halaman: Report " + jenisReport);
		
		Map<String, Object> params = new HashMap<String, Object>(); 
		if(flag == 0 || flag == 1) {
			params.put("format", ServletRequestUtils.getStringParameter(request, "format", "pdf")); //format report, default = PDF
		}else{
			params.put("format", ServletRequestUtils.getStringParameter(request, "format", "htmlprint")); //format report, default = htmlprint (html yg lgsg print)				
		}
		params.put("trans_id", trans_id);

		return generateReport(jenisReport, params, session, request, response);
	}

	//print RBT
	@RequestMapping(value="/rbt/{delivery_id}/{flag}")
	public String rbt(@PathVariable Integer delivery_id, @PathVariable Integer flag, HttpSession session, HttpServletRequest request, HttpServletResponse response) 
			throws JRException, IOException, ServletRequestBindingException {
		
		//currently logged in user
		User currentUser = (User) session.getAttribute("currentUser");
		
		//jenis report
		String jenisReport = "";
		if(flag == 0) {
			jenisReport = "print_rincian_barang_pdf"; 
		}else{
			jenisReport = "print_rincian_barang";
		}
		
		logger.debug("Halaman: Report " + jenisReport);
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(flag == 0) {
			params.put("format", ServletRequestUtils.getStringParameter(request, "format", "pdf")); //format report, default = PDF
		}else{
			params.put("format", ServletRequestUtils.getStringParameter(request, "format", "htmlprint")); //format report, default = htmlprint (html yg lgsg print)				
		}params.put("delivery_id", delivery_id);

		return generateReport(jenisReport, params, session, request, response);
	}
	
	//report STT
	@RequestMapping("/stt")
	public String report_stt(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) 
			throws JRException, IOException, ServletRequestBindingException {
		
		//currently logged in user
		User currentUser = (User) session.getAttribute("currentUser");
		//jenis report
		String jenisReport = "report_stt";
		String tgl_awal = Utils.convertDateToString(dbService.selectSysdate(), "dd-MM-yyyy");
		String tgl_akhir = Utils.convertDateToString(dbService.selectSysdate(), "dd-MM-yyyy");
		String param = "";
		String type = "";
		
		logger.debug("Halaman: Report " + jenisReport);
		
		if(request.getParameter("show") != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("format", ServletRequestUtils.getRequiredStringParameter(request, "format")); //format report
			params.put("beg_date", ServletRequestUtils.getRequiredStringParameter(request, "beg_date"));
			params.put("end_date", ServletRequestUtils.getRequiredStringParameter(request, "end_date"));
			params.put("username", currentUser.username);	
			type = ServletRequestUtils.getRequiredStringParameter(request, "jenis_report");

			//summary
			if(type.equals("1")){ 
				jenisReport = "report_stt";
			//detail
			}else if(type.equals("2")){ 
				jenisReport = "report_stt_det";
			}
			
			logger.debug("Halaman: REPORT " + jenisReport);
			
			param = "AND DATE(a.tgl_stt) BETWEEN STR_TO_DATE($P{beg_date}, '%d-%m-%Y') and STR_TO_DATE($P{end_date}, '%d-%m-%Y')";
			params.put("param", param);

			return generateReport(jenisReport, params, session, request, response);
		}else{
			model.addAttribute("tgl_awal", tgl_awal);
			model.addAttribute("tgl_akhir", tgl_akhir);
			return jenisReport;
		}
	}
	
	//report SP/RBT
	@RequestMapping("/rbt")
	public String report_rbt(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) 
			throws JRException, IOException, ServletRequestBindingException {
		
		//currently logged in user
		User currentUser = (User) session.getAttribute("currentUser");
		//jenis report
		String jenisReport = "report_rbt";
		String tgl_awal = Utils.convertDateToString(dbService.selectSysdate(), "dd-MM-yyyy");
		String tgl_akhir = Utils.convertDateToString(dbService.selectSysdate(), "dd-MM-yyyy");
		String param = "";
		String type = "";
		
		logger.debug("Halaman: Report " + jenisReport);
		
		if(request.getParameter("show") != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("format", ServletRequestUtils.getRequiredStringParameter(request, "format")); //format report
			params.put("beg_date", ServletRequestUtils.getRequiredStringParameter(request, "beg_date"));
			params.put("end_date", ServletRequestUtils.getRequiredStringParameter(request, "end_date"));
			params.put("username", currentUser.username);	
			type = ServletRequestUtils.getRequiredStringParameter(request, "jenis_report");

			//summary
			if(type.equals("1")){ 
				jenisReport = "report_rbt";
			//detail
			}else if(type.equals("2")){ 
				jenisReport = "report_rbt_det";
			}
			
			logger.debug("Halaman: REPORT " + jenisReport);
			
			param = "AND DATE(a.tgl_kirim) BETWEEN STR_TO_DATE($P{beg_date}, '%d-%m-%Y') and STR_TO_DATE($P{end_date}, '%d-%m-%Y')";
			params.put("param", param);

			return generateReport(jenisReport, params, session, request, response);
		}else{
			model.addAttribute("tgl_awal", tgl_awal);
			model.addAttribute("tgl_akhir", tgl_akhir);
			return jenisReport;
		}
	}
	
	//report AR
	@RequestMapping("/ar")
	public String report_ar(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) 
			throws JRException, IOException, ServletRequestBindingException {
		
		//currently logged in user
		User currentUser = (User) session.getAttribute("currentUser");
		//jenis report
		String jenisReport = "report_ar";
		String tgl_awal = Utils.convertDateToString(dbService.selectSysdate(), "dd-MM-yyyy");
		String tgl_akhir = Utils.convertDateToString(dbService.selectSysdate(), "dd-MM-yyyy");
		String param = "";
		String type = "";
		
		logger.debug("Halaman: Report " + jenisReport);
		
		if(request.getParameter("show") != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("format", ServletRequestUtils.getRequiredStringParameter(request, "format")); //format report
			params.put("username", currentUser.username);	
			type = ServletRequestUtils.getRequiredStringParameter(request, "jenis_report");

			//summary
			if(type.equals("1")){ 
				jenisReport = "report_ar";
			//detail
			}else if(type.equals("2")){ 
				jenisReport = "report_ar_det";
			}
			
			logger.debug("Halaman: REPORT " + jenisReport);
			
			param = "";
			params.put("param", param);

			return generateReport(jenisReport, params, session, request, response);
		}else{
			return jenisReport;
		}
	}
	
	//report Keuangan
	@RequestMapping("/keuangan")
	public String report_keuangan(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) 
			throws JRException, IOException, ServletRequestBindingException {
		
		//currently logged in user
		User currentUser = (User) session.getAttribute("currentUser");
		//jenis report
		String jenisReport = "report_keuangan";
		String tgl_awal = Utils.convertDateToString(dbService.selectSysdate(), "dd-MM-yyyy");
		String tgl_akhir = Utils.convertDateToString(dbService.selectSysdate(), "dd-MM-yyyy");
		String param = "", param2 = "";
		String type = "";
		
		logger.debug("Halaman: Report " + jenisReport);
		
		if(request.getParameter("show") != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("format", ServletRequestUtils.getRequiredStringParameter(request, "format")); //format report
			params.put("beg_date", ServletRequestUtils.getRequiredStringParameter(request, "beg_date"));
			params.put("end_date", ServletRequestUtils.getRequiredStringParameter(request, "end_date"));
			params.put("username", currentUser.username);	
			type = ServletRequestUtils.getRequiredStringParameter(request, "jenis_report");

			//summary
			if(type.equals("1")){ 
				jenisReport = "report_keuangan";
			//detail
			}else if(type.equals("2")){ 
				jenisReport = "report_keuangan_det";
			}
			
			logger.debug("Halaman: REPORT " + jenisReport);
			
			param = "AND DATE(a.paid_date) BETWEEN STR_TO_DATE($P{beg_date}, '%d-%m-%Y') and STR_TO_DATE($P{end_date}, '%d-%m-%Y')";
			param2 = "AND DATE(a.paid_date) < STR_TO_DATE($P{beg_date}, '%d-%m-%Y')";
			params.put("param", param);
			params.put("param2", param2);

			return generateReport(jenisReport, params, session, request, response);
		}else{
			model.addAttribute("tgl_awal", tgl_awal);
			model.addAttribute("tgl_akhir", tgl_akhir);
			return jenisReport;
		}
	}

	//print tagihan (jasper)
	@RequestMapping("/tagihan/{customer_id}/{format}")
	public String report_tagihan_list(@PathVariable Integer customer_id, @PathVariable String format, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) 
			throws JRException, IOException, ServletRequestBindingException {
		
		//currently logged in user
		User currentUser = (User) session.getAttribute("currentUser");
		
		//jenis report
		String jenisReport = "report_tagihan";
		logger.debug("Halaman: Report " + jenisReport);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("format", format);
		params.put("customer_id", customer_id);
		params.put("username", currentUser.username);	

		return generateReport(jenisReport, params, session, request, response);
	}

	//print tagihan (form jsp)
	@RequestMapping("/tagihan")
	public String report_tagihan_list(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) 
			throws JRException, IOException, ServletRequestBindingException {
		
		//jenis report
		String jenisReport = "report_tagihan";
		logger.debug("Halaman: Report " + jenisReport);

		//paging
		String search 	= ServletRequestUtils.getStringParameter(request, "search", null); //search string
		double total 	= dbService.selectListOutstandingCustomerCount(search); //total data
		int rowcount 	= ServletRequestUtils.getIntParameter(request, "rowcount", 10); //berapa data yg ditampilkan
		double pages 	= Math.ceil(total/rowcount); //total halaman
		int page 		= ServletRequestUtils.getIntParameter(request, "page", 1); //halaman yg ditampilkan
		int offset 		= (page - 1) * rowcount; //tampilkan mulai row ke berapa
		model.addAttribute("search", search);
		model.addAttribute("rowcount", rowcount);
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		
		//model data
		model.addAttribute("listCustomer", dbService.selectListOutstandingCustomer(search, offset, rowcount));
		
		return jenisReport;
	}

}
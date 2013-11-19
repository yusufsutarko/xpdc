package com.sma.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sma.model.DropDown;
import com.sma.model.Payment;
import com.sma.model.User;
import com.sma.utils.Utils;
import com.sma.validator.TransaksiPaymentValidator;

/**
 * Controller untuk Transaksi Payment STT
 * 
 * test comment di github nih cup
 * 
 * @author Yusuf
 * @since Aug 5, 2013 (9:34:50 AM)
 *
 */
@Controller
@RequestMapping("/transaksi/payment")
public class TransaksiPaymentController extends ParentController {

	@Autowired
	protected TransaksiPaymentValidator validator;		

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(this.validator);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(Utils.defaultDF, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, Utils.defaultNF, true));
		binder.registerCustomEditor(Date.class, "createdate", new CustomDateEditor(Utils.completeDF, true));
		binder.registerCustomEditor(Date.class, "modifydate", new CustomDateEditor(Utils.completeDF, true));
		binder.registerCustomEditor(Date.class, "canceldate", new CustomDateEditor(Utils.completeDF, true));
	}
	
	//@ModelAttribute pada deklarasi method berarti: 
	//bisa lebih dari satu model attribute, bisa juga digunakan sebagai reference data
	@ModelAttribute("reff")
	public Map<String, Object> reff(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<DropDown> listDK = new ArrayList<DropDown>();
		listDK.add(new DropDown("D", "Debet"));
		//listDK.add(new DropDown("K", "Kredit"));
		map.put("listDK", listDK);
		
		List<DropDown> listCancel = new ArrayList<DropDown>();
		listCancel.add(new DropDown("0", "Tidak"));
		listCancel.add(new DropDown("1", "Ya"));
		map.put("listCancel", listCancel);
		
		map.put("listPayMode", dbService.selectDropDown("jenis", "keterangan", "lst_config", "id=2 AND active=1", "keterangan")); //id=1 paymode

		return map;
	}
	

	//list payment2 terakhir
	@RequestMapping(value={"", "/"})
	public String show(Model model, HttpServletRequest request) throws ParseException {
		logger.debug("Halaman: Input Payment, method: SHOW");
		
		//param tambahan periode (khusus halaman2 tertentu saja yg perlu, seperti STT, SP/RBT, Payment/Biaya)
		String periodFrom 	= ServletRequestUtils.getStringParameter(request, "periodFrom", "");
		String periodTo		= ServletRequestUtils.getStringParameter(request, "periodTo", "");
		Date dateFrom = null, dateTo = null;
		if(!periodFrom.equals(""))  dateFrom = Utils.defaultDF.parse(periodFrom);
		if(!periodTo.equals(""))  dateTo = Utils.defaultDF.parse(periodTo);
		model.addAttribute("periodFrom", periodFrom);
		model.addAttribute("periodTo", periodTo);

		//paging
		String search 	= ServletRequestUtils.getStringParameter(request, "search", null); //search string
		double total 	= dbService.selectListPaymentSTTCount(search, dateFrom, dateTo); //total data
		int rowcount 	= ServletRequestUtils.getIntParameter(request, "rowcount", 10); //berapa data yg ditampilkan
		double pages 	= Math.ceil(total/rowcount); //total halaman
		int page 		= ServletRequestUtils.getIntParameter(request, "page", 1); //halaman yg ditampilkan
		int offset 		= (page - 1) * rowcount; //tampilkan mulai row ke berapa
		model.addAttribute("search", search);
		model.addAttribute("rowcount", rowcount);
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		
		//model data
		model.addAttribute("listPayment", dbService.selectListPaymentSTT(search, dateFrom, dateTo, offset, rowcount));
		
		return "input_payment_list";
	}
	
	//insert payment
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("payment") Payment payment) {
		logger.debug("Halaman: Input Payment, method: INSERT");
		
		//Date sysdate = dbService.selectSysdate();
		payment.setDk("D");
		
		return "input_payment";
	}

	//update payment
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@ModelAttribute("payment") Payment payment, @PathVariable Integer id) {
		logger.debug("Halaman: Input Payment, method: UPDATE");
		Payment tmp = dbService.selectPayment(id);
		BeanUtils.copyProperties(tmp, payment);
		return "input_payment";
	}

	@RequestMapping(value="/save", method=RequestMethod.POST) //mapping request POST saja ke method ini
	public String save(@Valid @ModelAttribute("payment") Payment payment, BindingResult result, HttpServletRequest request, RedirectAttributes ra) throws MailException, MessagingException {
		logger.debug("Halaman:  Input Payment, method: SAVE");
		
		//currently logged in user
		User currentUser = (User) request.getSession(false).getAttribute("currentUser");
		
		//bila ada error, kembalikan ke halaman edit
		if (result.hasErrors()) {
			return "input_payment";
		}

		//simpan data disini, lalu kembalikan ke layar list input, letakkan pesan di flash attribute nya spring
		//flash attribute berguna untuk mengirimkan pesan (contohnya pesan sukses/error setelah save) 
		//ke layar berikutnya (hanya sampai di layar berikutnya, setelah itu hilang)
		String pesan ="";
		try{
			pesan = dbService.savePayment(payment, currentUser);
			//balikin ke layar list input
		}catch (Exception e) {
			e.printStackTrace();
			pesan = messageSource.getMessage("SaveFailed", new String[]{"Payment", payment.no_payment},null);
		}
		ra.addFlashAttribute("pesan", pesan);
		
		return "redirect:/transaksi/payment"; 
		
	}
	
}
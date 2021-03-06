package com.sma.web;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sma.model.Delivery;
import com.sma.model.User;
import com.sma.utils.Utils;

/**
 * Controller untuk Checking Transaksi SP (Rincian Barang Terkirim)
 * 
 * @author Yusuf
 * @since Jul 23, 2013 (3:27:56 PM)
 *
 */
@Controller
@RequestMapping("/transaksi/checking")
public class TransaksiCheckingController extends ParentController {

	//@ModelAttribute pada deklarasi method berarti: 
	//bisa lebih dari satu model attribute, bisa juga digunakan sebagai reference data
	@ModelAttribute("reff")
	public Map<String, Object> reff(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listMobil", dbService.selectDropDown("no_polisi", "no_polisi", "lst_mobil", "active=1", "no_polisi"));
		map.put("listSupir", dbService.selectDropDown("id", "nama", "lst_supir", "active=1", "nama"));
		map.put("listKapal", dbService.selectDropDown("kode", "concat('[', kode, '] ', nama)", "lst_kapal", "active=1", "kode, nama")); 
		return map;
	}

	//list sp
	@RequestMapping(value={"", "/"})
	public String show(Model model, HttpServletRequest request) throws ParseException{
		logger.debug("Halaman: Input Checking, method: SHOW");
		
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
		double total 	= dbService.selectListDeliveryCount(search, dateFrom, dateTo); //total data
		int rowcount 	= ServletRequestUtils.getIntParameter(request, "rowcount", 10); //berapa data yg ditampilkan
		double pages 	= Math.ceil(total/rowcount); //total halaman
		int page 		= ServletRequestUtils.getIntParameter(request, "page", 1); //halaman yg ditampilkan
		int offset 		= (page - 1) * rowcount; //tampilkan mulai row ke berapa
		model.addAttribute("search", search);
		model.addAttribute("rowcount", rowcount);
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		
		//model data
		model.addAttribute("listSP", dbService.selectListDelivery(search, dateFrom, dateTo, offset, rowcount));
		
		return "input_checking_list";
	}
	
	//update (checking) sp
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@ModelAttribute("sp") Delivery sp, @PathVariable Integer id) {
		logger.debug("Halaman: Input Checking, method: UPDATE");
		Delivery tmp = dbService.selectDelivery(id);
		tmp.setListDeliveryDet(dbService.selectListDeliveryDet(tmp.id));
		tmp.setListDeliveryCost(dbService.selectListDeliveryCost(tmp.id));
		BeanUtils.copyProperties(tmp, sp);
		return "input_checking";
	}

	@RequestMapping(value="/save", method=RequestMethod.POST) //mapping request POST saja ke method ini
	public String save(@Valid @ModelAttribute("sp") Delivery sp, BindingResult result, HttpServletRequest request, RedirectAttributes ra) throws MailException, MessagingException {
		logger.debug("Halaman:  Input Checking, method: SAVE");
		
		//currently logged in user
		User currentUser = (User) request.getSession(false).getAttribute("currentUser");
		
		//bila ada error, kembalikan ke halaman edit
		if (!result.hasErrors()) {
			BindException errors = new BindException(result);
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgl_sampai", "NotEmpty", new String[]{"Tanggal Sampai"});
		}
		if (result.hasErrors()) {
			return "input_checking";
		}

		//simpan data disini, lalu kembalikan ke layar list input, letakkan pesan di flash attribute nya spring
		//flash attribute berguna untuk mengirimkan pesan (contohnya pesan sukses/error setelah save) 
		//ke layar berikutnya (hanya sampai di layar berikutnya, setelah itu hilang)
		String pesan ="";
		try{
			pesan = dbService.saveCheckingSP(sp, currentUser);
			//balikin ke layar list input
		}catch (Exception e) {
			e.printStackTrace();
			pesan = messageSource.getMessage("SaveFailed", new String[]{"SP", sp.id.toString()},null);
		}
		ra.addFlashAttribute("pesan", pesan);
		
		return "redirect:/transaksi/checking"; 
		
	}
	
}
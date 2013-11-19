package com.sma.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.sma.model.DropDown;
import com.sma.model.Mobil;
import com.sma.model.User;

/**
 * Master Mobil
 * 
 * @author Rudy
 * @since Jun 29, 2013 (1:59:02 PM)
 *
 */
@Controller
@RequestMapping("/master/mobil")
public class MasterMobilController extends ParentController {

	//@ModelAttribute pada deklarasi method berarti: 
	//bisa lebih dari satu model attribute, bisa juga digunakan sebagai reference data
	@ModelAttribute("reff")
	public Map<String, Object> reff(){
		List<DropDown> listActive = new ArrayList<DropDown>();
		listActive.add(new DropDown("1", "Aktif"));
		listActive.add(new DropDown("0", "Tidak Aktif"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listJenisMobil", dbService.selectDropDown("jenis", "keterangan", "lst_config", "id = 4", "keterangan"));
		map.put("listActive", listActive);
		return map;
	}
	
	//list Mobil
	@RequestMapping(value={"", "/"})
	public String show(Model model, HttpServletRequest request) {
		logger.debug("Halaman: Master Mobil, method: SHOW");
		
		//paging
		String search 	= ServletRequestUtils.getStringParameter(request, "search", null); //search string
		double total 	= dbService.selectListMobilCount(search); //total data
		int rowcount 	= ServletRequestUtils.getIntParameter(request, "rowcount", 10); //berapa data yg ditampilkan
		double pages 	= Math.ceil(total/rowcount); //total halaman
		int page 		= ServletRequestUtils.getIntParameter(request, "page", 1); //halaman yg ditampilkan
		int offset 		= (page - 1) * rowcount; //tampilkan mulai row ke berapa
		model.addAttribute("search", search);
		model.addAttribute("rowcount", rowcount);
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		
		//model data
		model.addAttribute("listMobil", dbService.selectListMobil(search, offset, rowcount));
		
		return "master_mobil_list";
	}
	
	//insert Mobil
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("mobil") Mobil mobil) {
		logger.debug("Halaman:  Master Mobil, method: INSERT");
		
		mobil.setActive(1);
		mobil.setHarga(0.00);
		mobil.setMode("NEW");
		
		return "master_mobil";
	}

	//update mobil
	@RequestMapping(value="/update/{no_polisi}", method=RequestMethod.GET)
	public String update(@ModelAttribute("mobil") Mobil mobil, @PathVariable String no_polisi) {
		logger.debug("Halaman:  Master Mobil, method: UPDATE");
		Mobil tmp = dbService.selectMobil(no_polisi);
		BeanUtils.copyProperties(tmp, mobil);
		mobil.setMode("EDIT");
		return "master_mobil";
	}

	@RequestMapping(value="/save", method=RequestMethod.POST) //mapping request POST saja ke method ini
	public String save(@Valid @ModelAttribute("mobil") Mobil mobil, BindingResult result, HttpServletRequest request, RedirectAttributes ra) throws MailException, MessagingException {
		logger.debug("Halaman:  Master Mobil, method: SAVE");
		
		//currently logged in mobil
		User currentUser = (User) request.getSession(false).getAttribute("currentUser");
		
		//Validasi dilakukan langsung didalam controller (validasi tambahan, selain validasi standar yang sudah diset langsung di class mobil)
		if (!result.hasErrors()) {
			BindException errors = new BindException(result);
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "no_polisi", "NotEmpty", new String[]{"No Polisi"});
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jenis", "NotEmpty", new String[]{"Jenis Mobil"});
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "harga", "NotEmpty", new String[]{"Harga"});
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "active", "NotEmpty", new String[]{"Aktif"});
			
			if(mobil.mode.equals("NEW")){
				double total = dbService.selectNamaMobilCount(mobil.no_polisi); //total data berdasarkan no_polisi
				if (total>0){
					errors.rejectValue("no_polisi", null, mobil.no_polisi+" sudah pernah diinput sebelumnya ! ");
				}
			}
		}

		//bila ada error, kembalikan ke halaman edit
		if (result.hasErrors()) {
			return "master_mobil";
		}

		//simpan data disini, lalu kembalikan ke layar list input, letakkan pesan di flash attribute nya spring
		//flash attribute berguna untuk mengirimkan pesan (contohnya pesan sukses/error setelah save) 
		//ke layar berikutnya (hanya sampai di layar berikutnya, setelah itu hilang)
		String pesan ="";
		try{
			pesan = dbService.saveMobil(mobil, currentUser);
			//balikin ke layar list input
		}catch (Exception e) {
			e.printStackTrace();
			pesan = messageSource.getMessage("SaveFailed", new String[]{"Mobil", mobil.no_polisi},null);
		}
		ra.addFlashAttribute("pesan", pesan);
		
		return "redirect:/master/mobil"; 
		
	}
	
}
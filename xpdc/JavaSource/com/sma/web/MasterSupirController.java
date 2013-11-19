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
import com.sma.model.Supir;
import com.sma.model.User;

/**
 * Master Supir
 * 
 * @author Rudy
 * @since Jun 25, 2013 (1:59:02 PM)
 *
 */
@Controller
@RequestMapping("/master/supir")
public class MasterSupirController extends ParentController {

	//@ModelAttribute pada deklarasi method berarti: 
	//bisa lebih dari satu model attribute, bisa juga digunakan sebagai reference data
	@ModelAttribute("reff")
	public Map<String, Object> reff(){
		List<DropDown> listActive = new ArrayList<DropDown>();
		listActive.add(new DropDown("1", "Aktif"));
		listActive.add(new DropDown("0", "Tidak Aktif"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listActive", listActive);
		return map;
	}
	
	//list Supir
	@RequestMapping(value={"", "/"})
	public String show(Model model, HttpServletRequest request) {
		logger.debug("Halaman: Master Supir, method: SHOW");
		
		//paging
		String search 	= ServletRequestUtils.getStringParameter(request, "search", null); //search string
		double total 	= dbService.selectListSupirCount(search); //total data
		int rowcount 	= ServletRequestUtils.getIntParameter(request, "rowcount", 10); //berapa data yg ditampilkan
		double pages 	= Math.ceil(total/rowcount); //total halaman
		int page 		= ServletRequestUtils.getIntParameter(request, "page", 1); //halaman yg ditampilkan
		int offset 		= (page - 1) * rowcount; //tampilkan mulai row ke berapa
		model.addAttribute("search", search);
		model.addAttribute("rowcount", rowcount);
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		
		//model data
		model.addAttribute("listSupir", dbService.selectListSupir(search, offset, rowcount));
		
		return "master_supir_list";
	}
	
	//insert Supir
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("supir") Supir supir) {
		logger.debug("Halaman:  Master Supir, method: INSERT");
		
		supir.setActive(1);
		
		return "master_supir";
	}

	//update supir
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@ModelAttribute("supir") Supir supir, @PathVariable Integer id) {
		logger.debug("Halaman:  Master Supir, method: UPDATE");
		Supir tmp = dbService.selectSupir(id);
		BeanUtils.copyProperties(tmp, supir);
		return "master_supir";
	}

	@RequestMapping(value="/save", method=RequestMethod.POST) //mapping request POST saja ke method ini
	public String save(@Valid @ModelAttribute("supir") Supir supir, BindingResult result, HttpServletRequest request, RedirectAttributes ra) throws MailException, MessagingException {
		logger.debug("Halaman:  Master Supir, method: SAVE");
		
		//currently logged in supir
		User currentUser = (User) request.getSession(false).getAttribute("currentUser");
		
		//Validasi dilakukan langsung didalam controller (validasi tambahan, selain validasi standar yang sudah diset langsung di class supir)
		if (!result.hasErrors()) {
			BindException errors = new BindException(result);
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nama", "NotEmpty", new String[]{"Nama"});
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "active", "NotEmpty", new String[]{"Aktif"});
			
			if(supir.id == null){
				double total = dbService.selectNamaSupirCount(supir.nama); //total data berdasarkan nama
				if (total>0){
					errors.rejectValue("nama", null, supir.nama+" sudah pernah diinput sebelumnya ! ");
				}
			}
		}

		//bila ada error, kembalikan ke halaman edit
		if (result.hasErrors()) {
			return "master_supir";
		}

		//simpan data disini, lalu kembalikan ke layar list input, letakkan pesan di flash attribute nya spring
		//flash attribute berguna untuk mengirimkan pesan (contohnya pesan sukses/error setelah save) 
		//ke layar berikutnya (hanya sampai di layar berikutnya, setelah itu hilang)
		String pesan ="";
		try{
			pesan = dbService.saveSupir(supir, currentUser);
			//balikin ke layar list input
		}catch (Exception e) {
			e.printStackTrace();
			pesan = messageSource.getMessage("SaveFailed", new String[]{"Supir", supir.nama},null);
		}
		ra.addFlashAttribute("pesan", pesan);
		
		return "redirect:/master/supir"; 
		
	}
	
}
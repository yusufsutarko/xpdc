package com.sma.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sma.model.DropDown;
import com.sma.model.User;
import com.sma.utils.Utils;

/**
 * Master User
 * 
 * @author Yusuf
 * @since Jun 21, 2013 (1:59:02 PM)
 *
 */
@Controller
@RequestMapping("/master/user")
public class MasterUserController extends ParentController {

	//@ModelAttribute pada deklarasi method berarti: 
	//bisa lebih dari satu model attribute, bisa juga digunakan sebagai reference data
	@ModelAttribute("reff")
	public Map<String, Object> reff(){
		List<DropDown> listActive = new ArrayList<DropDown>();
		listActive.add(new DropDown("1", "Aktif"));
		listActive.add(new DropDown("0", "Tidak Aktif"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listCabang", dbService.selectDropDown("id", "nama", "lst_cabang", "active=1", "nama"));
		map.put("listGroupUser", dbService.selectDropDown("id", "nama", "lst_group_user", null, "nama"));
		map.put("listActive", listActive);
		return map;
	}
	
	//list user
	@RequestMapping(value={"", "/"})
	public String show(Model model, HttpServletRequest request) {
		logger.debug("Halaman: Master User, method: SHOW");
		
		//paging
		String search 	= ServletRequestUtils.getStringParameter(request, "search", null); //search string
		double total 	= dbService.selectListUserCount(search); //total data
		int rowcount 	= ServletRequestUtils.getIntParameter(request, "rowcount", 10); //berapa data yg ditampilkan
		double pages 	= Math.ceil(total/rowcount); //total halaman
		int page 		= ServletRequestUtils.getIntParameter(request, "page", 1); //halaman yg ditampilkan
		int offset 		= (page - 1) * rowcount; //tampilkan mulai row ke berapa
		model.addAttribute("search", search);
		model.addAttribute("rowcount", rowcount);
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		
		//model data
		model.addAttribute("listUser", dbService.selectListUser(search, offset, rowcount));
		
		return "master_user_list";
	}
	
	//insert user
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("user") User user) {
		logger.debug("Halaman:  Master User, method: INSERT");
		
		user.setActive(1);
		
		return "master_user";
	}

	//update user
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@ModelAttribute("user") User user, @PathVariable Integer id) {
		logger.debug("Halaman:  Master User, method: UPDATE");
		User tmp = dbService.selectUser(id, null, null);
		BeanUtils.copyProperties(tmp, user);
		return "master_user";
	}

	@RequestMapping(value="/save", method=RequestMethod.POST) //mapping request POST saja ke method ini
	public String save(@Valid @ModelAttribute("user") User user, BindingResult result, HttpServletRequest request, RedirectAttributes ra) throws MailException, MessagingException {
		logger.debug("Halaman:  Master User, method: SAVE");
		
		//currently logged in user
		User currentUser = (User) request.getSession(false).getAttribute("currentUser");
		
		//Validasi dilakukan langsung didalam controller (validasi tambahan, selain validasi standar yang sudah diset langsung di class User)
		if (!result.hasErrors()) {
			BindException errors = new BindException(result);
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cabang_id", "NotEmpty", new String[]{"Cabang"});
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "group_user_id", "NotEmpty", new String[]{"Grup User"});
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "active", "NotEmpty", new String[]{"Aktif"});
			
			if(!user.getConfirm_password().equals(user.getPassword())){
				errors.rejectValue("password", "ConfirmPassword");
			}
		}

		//bila ada error, kembalikan ke halaman edit
		if (result.hasErrors()) {
			return "master_user";
		}

		//simpan data disini, lalu kembalikan ke layar list input, letakkan pesan di flash attribute nya spring
		//flash attribute berguna untuk mengirimkan pesan (contohnya pesan sukses/error setelah save) 
		//ke layar berikutnya (hanya sampai di layar berikutnya, setelah itu hilang)
		String pesan ="";
		try{
			pesan = dbService.saveUser(user, currentUser);
			//balikin ke layar list input
		}catch (Exception e) {
			e.printStackTrace();
			pesan = messageSource.getMessage("SaveFailed", new String[]{"User", user.username},null);
		}
		ra.addFlashAttribute("pesan", pesan);
		
		return "redirect:/master/user"; 
		
	}
	
}
package com.sma.web;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sma.model.User;

/**
 * MultiActionController untuk fungsi2 dasar
 * 
 * @author Yusuf
 * @since Jun 20, 2013 (10:14:46 AM)
 *
 */
@Controller
public class MainController extends ParentController{

	@RequestMapping({"/", "/home"})
	public String home() throws MessagingException {
		logger.debug("Halaman: HOME");
		 
		return "home";
	}
	
	@RequestMapping("/console")
	public String console(Model model) {
		logger.debug("Halaman: CONSOLE");
		
		model.addAttribute("userMap", sessionRegistry.getUserMap());
		
		return "console";
	}

	@RequestMapping({"/err/{toggle}"})
	public String err(@PathVariable Integer toggle) throws MessagingException {
		dbService.updateTestDB(toggle);
		return "redirect:/";
	}
	
	@RequestMapping({ "/logout", "/keluar" })
	public String logout(HttpServletRequest request) {
		logger.debug("Halaman: LOGOUT");
		
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		sessionRegistry.kick(currentUser, request.getSession(false));
		
		return "redirect:/login";
	}
	
	@RequestMapping("/changepass")
	public String changepass(HttpServletRequest request, @ModelAttribute("user") User user, BindingResult result, RedirectAttributes ra) {
		logger.debug("Halaman: CHANGE PASSWORD");
		
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		user.setId(currentUser.getId());
		user.setUsername(currentUser.getUsername());
		
		if(request.getParameter("new_password") != null){
			ValidationUtils.rejectIfEmpty(result, "password", "NotEmpty", new String[]{"Password Lama"});
			ValidationUtils.rejectIfEmpty(result, "new_password", "NotEmpty", new String[]{"Password Baru"});
			ValidationUtils.rejectIfEmpty(result, "confirm_password", "NotEmpty", new String[]{"Konfirmasi Password Baru"});
			if (!result.hasErrors()) {
				User tmp = dbService.selectUser(user.getId(), user.getUsername(), user.getPassword());
				if(tmp == null) {
					result.rejectValue("password", "WrongPassword");
				}else if(!user.getNew_password().equals(user.getConfirm_password())){
					result.rejectValue("password", "ConfirmPassword");
				}else if(user.getNew_password().length() < 6 || user.getNew_password().length() > 20){
					result.rejectValue("password", "ShortPassword");
				}
			}
			
			//bila ada error, kembalikan ke halaman edit
			if (result.hasErrors()) {
				return "change_password";
			}

			//bila tidak ada error simpan data disini, lalu kembalikan ke layar list input, letakkan pesan di flash attribute nya spring
			//flash attribute berguna untuk mengirimkan pesan (contohnya pesan sukses/error setelah save) 
			//ke layar berikutnya (hanya sampai di layar berikutnya, setelah itu hilang)
			String pesan = dbService.changePassword(user);
			ra.addFlashAttribute("pesan", pesan);
			return "redirect:/";
		}

		return "change_password";
	}

	@RequestMapping("/loading")
	public String loading(HttpServletRequest request) {
		logger.debug("Halaman: LOADING");
		return "loading";
	}
	

}
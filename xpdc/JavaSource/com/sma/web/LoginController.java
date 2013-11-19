package com.sma.web;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sma.model.User;
import com.sma.utils.Utils;

/**
 * Contoh FormController
 * 
 * @author Yusuf
 * @since Jun 20, 2013 (11:00:11 AM)
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends ParentController{

	//show form pertama kali
	@RequestMapping(method=RequestMethod.GET)
	public String show(@ModelAttribute("user") User user, HttpServletRequest request, HttpSession session) {
		logger.debug("Halaman: LOGIN, method: SHOW");

		//bila masih logged in, langsung ke home saja
        if (session != null) {
        	User currentUser = (User) session.getAttribute("currentUser");
        	if(currentUser != null) {
            	return "redirect:/home";
        	}
        }

		user.setUsername(Utils.getCookie(request, "XPDC_USERNAME")); //tarik nama dari cookie
        
		return "login";
	}
	
	//saat user menekan tombol login, process form
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@Valid @ModelAttribute("user") User user, BindingResult result, HttpServletRequest request, HttpServletResponse response) 
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException { 
		logger.debug("Halaman: LOGIN, method: PROCESSFORM");

		//validasi dasar di model object, validasi lainnya langsung didalam controller
		if (!result.hasErrors()) {
			BindException errors = new BindException(result);
			User tmp = dbService.selectUser(null, user.getUsername(), user.getPassword());
			
			if(tmp == null) {
				errors.rejectValue("username", "", "Username tidak terdaftar atau password salah");
			}else{
				//set beberapa item dari object user yg ditarik dari database ke object user yg dipakai untuk login
				//user.setId(tmp.getId());
				//user.setGroup_user_id(tmp.getGroup_user_id());
				tmp.setPassword(user.getPassword());
				PropertyUtils.copyProperties(user, tmp);
			}
		}

		//bila ada error, kembalikan ke halaman login
		if (result.hasErrors()) {
			return "login";
		}

		//bila tidak error, lanjut ke login
		dbService.login(user, sessionRegistry, request, response);
		
		return "redirect:/home";
	}	
	
}

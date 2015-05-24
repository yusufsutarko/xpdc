package com.sma.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sma.service.DbService;
import com.sma.utils.Emailer;
import com.sma.utils.SessionRegistry;
import com.sma.utils.Utils;

/**
 * Abstract ParentController sebagai parent dari semua controller
 * Cuman untuk meletakkan reference data saja dan beberapa variable
 * 
 * contoh comment
 * 
 * @author Yusuf
 * @since Jun 20, 2013 (10:15:06 AM)
 *
 */
public abstract class ParentController {

	protected static Logger logger = Logger.getLogger(ParentController.class);
	
	@Autowired
	protected SessionRegistry sessionRegistry;
	
	@Autowired
	protected MessageSource messageSource;
		
	@Autowired
	protected DbService dbService;
	
	@Autowired
	protected Emailer email;

	@Autowired
	protected Properties props;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(Utils.defaultDF, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, Utils.defaultNF, true));
		binder.registerCustomEditor(Date.class, "createdate", new CustomDateEditor(Utils.completeDF, true));
		binder.registerCustomEditor(Date.class, "modifydate", new CustomDateEditor(Utils.completeDF, true));
		binder.registerCustomEditor(Date.class, "canceldate", new CustomDateEditor(Utils.completeDF, true));
	}
	
	//@ModelAttribute pada deklarasi method berarti: 
	//bisa lebih dari satu model attribute, bisa juga digunakan sebagai reference data
	@ModelAttribute("company")
	public Map<String, Object> company(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", messageSource.getMessage("company.name", null, null));
		map.put("address", messageSource.getMessage("company.address", null, null).replaceAll("\\n", "<br>"));
		map.put("copyright", messageSource.getMessage("company.copyright", new String[]{Utils.getCopyrightYears(dbService.selectSysdate())}, null));

		return map;
	}
	
	
	
}

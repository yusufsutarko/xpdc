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
import com.sma.model.Harga;
import com.sma.model.User;

/**
 * Master Harga - CRUD
 * 
 * Tambah comment (contoh)
 * 
 * @author Rudy
 * @since Jun 26, 2013 (1:59:02 PM)
 *
 */
@Controller
@RequestMapping("/master/harga")
public class MasterHargaController extends ParentController {

	//@ModelAttribute pada deklarasi method berarti: 
	//bisa lebih dari satu model attribute, bisa juga digunakan sebagai reference data
	@ModelAttribute("reff")
	public Map<String, Object> reff(){
		List<DropDown> listActive = new ArrayList<DropDown>();
		listActive.add(new DropDown("1", "Aktif"));
		listActive.add(new DropDown("0", "Tidak Aktif"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listCustomer", dbService.selectDropDown("id", "nama", "mst_customer", "active=1", "nama"));
		map.put("listBarang", dbService.selectDropDown("id", "nama", "lst_barang", "active=1", "nama"));
		map.put("listActive", listActive);
		return map;
	}
	
	//list Harga
	@RequestMapping(value={"", "/"})
	public String show(Model model, HttpServletRequest request) {
		logger.debug("Halaman: Master Harga, method: SHOW");
		
		//paging
		String search 	= ServletRequestUtils.getStringParameter(request, "search", null); //search string
		double total 	= dbService.selectListHargaCount(search); //total data
		int rowcount 	= ServletRequestUtils.getIntParameter(request, "rowcount", 10); //berapa data yg ditampilkan
		double pages 	= Math.ceil(total/rowcount); //total halaman
		int page 		= ServletRequestUtils.getIntParameter(request, "page", 1); //halaman yg ditampilkan
		int offset 		= (page - 1) * rowcount; //tampilkan mulai row ke berapa
		model.addAttribute("search", search);
		model.addAttribute("rowcount", rowcount);
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		
		//model data
		model.addAttribute("listHarga", dbService.selectListHarga(search, offset, rowcount));
		
		return "master_harga_list";
	}
	
	//insert Harga
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("harga") Harga harga) {
		logger.debug("Halaman:  Master Harga, method: INSERT");
		
		harga.setActive(1);
		harga.setHarga(0.00);
		harga.setMode("NEW");
		
		return "master_harga";
	}

	//update Harga
	@RequestMapping(value="/update/{customer_id}/{barang_id}", method=RequestMethod.GET)
	public String update(@ModelAttribute("harga") Harga harga, @PathVariable Integer customer_id, @PathVariable Integer barang_id) {
		logger.debug("Halaman:  Master Harga, method: UPDATE");
		Harga tmp = dbService.selectHarga(customer_id, barang_id);
		BeanUtils.copyProperties(tmp, harga);
		harga.setMode("EDIT");
		return "master_harga";
	}

	@RequestMapping(value="/save", method=RequestMethod.POST) //mapping request POST saja ke method ini
	public String save(@Valid @ModelAttribute("harga") Harga harga, BindingResult result, HttpServletRequest request, RedirectAttributes ra) throws MailException, MessagingException {
		logger.debug("Halaman:  Master Harga, method: SAVE");
		
		//currently logged in harga
		User currentUser = (User) request.getSession(false).getAttribute("currentUser");
		
		//Validasi dilakukan langsung didalam controller (validasi tambahan, selain validasi standar yang sudah diset langsung di class harga)
		if (!result.hasErrors()) {
			BindException errors = new BindException(result);
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customer_id", "NotEmpty", new String[]{"Nama Customer"});
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "barang_id", "NotEmpty", new String[]{"Nama Barang"});
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "active", "NotEmpty", new String[]{"Aktif"});
			
			if(harga.mode.equals("NEW")){
				double total = dbService.selectNamaHargaCount(harga.customer_id, harga.barang_id); //total data berdasarkan nama
				if (total>0){
					errors.rejectValue("customer_id", null, "kombinasi harga ini sudah pernah diinput sebelumnya ! ");
				}
			}
		}

		//bila ada error, kembalikan ke halaman edit
		if (result.hasErrors()) {
			return "master_harga";
		}

		//simpan data disini, lalu kembalikan ke layar list input, letakkan pesan di flash attribute nya spring
		//flash attribute berguna untuk mengirimkan pesan (contohnya pesan sukses/error setelah save) 
		//ke layar berikutnya (hanya sampai di layar berikutnya, setelah itu hilang)
		String pesan ="";
		try{
			pesan = dbService.saveHarga(harga, currentUser);
			//balikin ke layar list input
		}catch (Exception e) {
			e.printStackTrace();
			pesan = messageSource.getMessage("SaveFailed", new String[]{"Harga", harga.customerName},null);
		}
		ra.addFlashAttribute("pesan", pesan);
		
		return "redirect:/master/harga"; 
		
	}
	
}
package com.sma.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.sma.model.Biaya;
import com.sma.model.Customer;
import com.sma.model.DropDown;
import com.sma.model.Trans;
import com.sma.model.TransDet;

/**
 * MultiActionController untuk semua pemanggilan JSON (biasanya untuk ajax autocomplete / dropdown)
 * 
 * @author Yusuf
 * @since Jul 4, 2013 (2:17:51 PM)
 *
 */
@Controller
@RequestMapping("/json")
public class JSONController extends ParentController{
	
	/**
	 * List Customer
	 */
	@RequestMapping("/customer/{nama}")
	public String customer(HttpServletResponse response, @PathVariable String nama) throws IOException{
		response.setContentType("application/json");
		
		List<Customer> result = new ArrayList<Customer>();
		result = dbService.selectListPengirim(nama, null, null);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.close();
		
		return null;
	}
	
	/**
	 * List Tujuan
	 */
	@RequestMapping("/tujuan/{nama}")
	public String tujuan(HttpServletResponse response, @PathVariable String nama) throws IOException{
		response.setContentType("application/json");
		
		List<Customer> result = new ArrayList<Customer>();
		result = dbService.selectListPenerima(nama, null, null);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.close();
		
		return null;
	}

	/**
	 * List Harga Barang Per Customer
	 */
	@RequestMapping("/barang/{customer_id}/{nama}")
	public String barang(HttpServletResponse response, @PathVariable Integer customer_id, @PathVariable String nama) throws IOException{
		response.setContentType("application/json");
		
		List<DropDown> result = new ArrayList<DropDown>();
		result = dbService.selectListHargaPerCustomer(nama, customer_id);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.close();
		
		return null;
	}

	/**
	 * List STT dan barang yang belum naik semua (colly_remain > 0), dan statusnya tidak cancelled
	 */
	@RequestMapping("/stt/{no_stt}")
	public String stt(HttpServletResponse response, @PathVariable String no_stt) throws IOException{
		response.setContentType("application/json");
		
		List<TransDet> result = new ArrayList<TransDet>();
		result = dbService.selectListItemDalamSTT(no_stt);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.close();
		
		return null;
	}

	/**
	 * List biaya
	 */
	@RequestMapping("/biaya/{nama}")
	public String biaya(HttpServletResponse response, @PathVariable String nama) throws IOException{
		response.setContentType("application/json");
		
		List<Biaya> result = new ArrayList<Biaya>();
		result = dbService.selectListBiayaByNama(nama);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.close();
		
		return null;
	}

	/**
	 * List STT untuk di menu input payment
	 */
	@RequestMapping("/stt_payment/{no_stt}")
	public String stt_payment(HttpServletResponse response, @PathVariable String no_stt) throws IOException{
		response.setContentType("application/json");
		
		List<Trans> result = dbService.selectListSTTForPayment(no_stt);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.close();
		
		return null;
	}

}
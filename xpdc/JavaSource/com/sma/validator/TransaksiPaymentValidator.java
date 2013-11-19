package com.sma.validator;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sma.model.Payment;
import com.sma.service.DbService;

/**
 * Validator Untuk Input Transaksi Payment
 * 
 * @author Yusuf
 * @since Aug 5, 2013 (2:02:36 PM)
 *
 */
@Component
public class TransaksiPaymentValidator implements Validator {

	@Autowired
	protected DbService dbService;
	
	public void setDbService(DbService dbService) {
		this.dbService = dbService;
	}

	public boolean supports(Class clazz) {
		return Payment.class.equals(clazz);
	}
	
	public void validate(Object obj, Errors e) {
		Payment payment = (Payment) obj;
		
		//Validasi mst_payment
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "no_stt", "NotEmpty", new String[]{"Nomor STT"});
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "paid_date", "NotEmpty", new String[]{"Tanggal Bayar/Transaksi"});
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "dk", "NotEmpty", new String[]{"Debet/Kredit"});
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "pay_mode", "NotEmpty", new String[]{"Cara Pembayaran"});
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "nominal", "NotEmpty", new String[]{"Jumlah/Nominal"});
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "keterangan", "NotEmpty", new String[]{"Keterangan"});
		
		//Yusuf (11 Nov 13) tambah validasi hanya bisa diedit H+1 saja, khusus untuk update
		if(payment.payment_id != null){
			Date sysdate = dbService.selectSysdate();
			int days = Days.daysBetween(new DateTime(payment.createdate), new DateTime(sysdate)).getDays();
			if(days > 1){
				e.rejectValue("nominal", "", "Data tidak dapat dirubah karena sudah lewat 2 hari.");
			}
		}
		
		if(!e.hasErrors()){
			//Bila cara bayar giro, harus isi no giro dan tgl jt giro
			if(payment.pay_mode.intValue() == 3){
				ValidationUtils.rejectIfEmptyOrWhitespace(e, "no_giro", "NotEmpty", new String[]{"Nomor Giro"});
				ValidationUtils.rejectIfEmptyOrWhitespace(e, "due_date", "NotEmpty", new String[]{"Tanggal Jatuh Tempo Giro"});
			}
			//Bila ada nomor STT (pembayaran untuk STT), berarti harus DEBET (uang masuk)
			if(payment.getTrans_id() != null && !payment.getDk().equalsIgnoreCase("D")){
				e.rejectValue("dk", "", "Untuk pembayaran STT, harus DEBET (uang masuk)");
			}
		}
		
	}

}
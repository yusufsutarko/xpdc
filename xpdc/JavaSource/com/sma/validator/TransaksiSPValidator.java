package com.sma.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sma.model.Delivery;
import com.sma.model.DeliveryCost;
import com.sma.model.DeliveryDet;
import com.sma.service.DbService;

/**
 * Validator Untuk Input Transaksi SP
 * 
 * @author Yusuf
 * @since Jul 16, 2013 (10:56:05 AM)
 *
 */
@Component
public class TransaksiSPValidator implements Validator {

	@Autowired
	protected DbService dbService;
	
	public void setDbService(DbService dbService) {
		this.dbService = dbService;
	}

	public boolean supports(Class clazz) {
		return Delivery.class.equals(clazz);
	}
	
	public void validate(Object obj, Errors e) {
		Delivery sp = (Delivery) obj;
		
		//bila pembatalan SP/RBT, validasi tanggal sampai
		int cancel = sp.cancel == null ? 0 : sp.cancel;
		if(cancel == 1){
			if(sp.tgl_sampai != null) {
				e.reject("", "Surat SP/RBT tidak dapat dibatalkan, karena sudah sampai tujuan");
			}
		}
		
		//Validasi mst_delivery
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "tgl_kirim", "NotEmpty", new String[]{"Tanggal Muat"});
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "no_polisi", "NotEmpty", new String[]{"Mobil"});
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "supir_id", "NotEmpty", new String[]{"Supir"});
		
		//Validasi minimal harus ada 1 item yang naik
		if(sp.listDeliveryDet.size() == 0) {
			e.reject("", "Anda belum memasukkan Rincian Barang");
		}
		
		//Validasi minimal harus ada 1 detil rincian biaya
		if(sp.listDeliveryCost.size() == 0) {
			e.reject("", "Anda belum memasukkan Rincian Biaya");
		}
		
		//Validasi mst_delivery_det
		for(int i=0; i<sp.listDeliveryDet.size(); i++){
			DeliveryDet det = sp.listDeliveryDet.get(i);
			e.pushNestedPath("listDeliveryDet["+i+"]");
			
			//cegah NPE
			if(det.colly == null) det.colly = 0.;
			if(det.colly_naik == null) det.colly_naik = 0.;
			if(det.colly_sisa == null) det.colly_sisa = 0.;
			if(det.nominal == null) det.nominal = 0.;

			if(!det.flag_delete){ //hanya validasi, bila tidak ditandakan untuk dihapus
				//validasi
				String baris = new Integer(i+1).toString();
				ValidationUtils.rejectIfEmptyOrWhitespace(e, "no_stt", "ListItem2", new String[]{"Rincian Barang", baris, "No STT", "harus diisi"});
				if(det.colly_naik.intValue() == 0) 
					e.rejectValue("colly_naik", "ListItem2", new String[]{"Rincian Barang", baris, "Colly Naik", "harus lebih dari nol"}, null);
				//if(det.nominal.intValue() == 0) e.rejectValue("nominal", "ListItem2", new String[]{"Rincian Barang", baris, "Nominal", "harus lebih dari nol"}, null);
				
				//validasi setiap kali ada colly naik, hitung ulang dari seluruh colly naik dari barang tersebut (dari RBT2 lainnya juga)
				if(det.trans_id != null){
					double total_colly = dbService.selectTotalColly(det.trans_id, det.trans_urut);
					double total_colly_naik = dbService.selectTotalCollyNaik(det.delivery_id, det.trans_id, det.trans_urut);
					double total_colly_sisa = total_colly - total_colly_naik;
					if(det.colly_naik.doubleValue() > total_colly_sisa) {
						e.rejectValue("colly", "ListItem2", new String[]{"Rincian Barang", baris, "Colly Naik", "maksimum adalah " + total_colly_sisa}, "");
					}
				}
			}
			
			e.popNestedPath();
		}
		
		//Validasi mst_delivery_cost
		for(int i=0; i<sp.listDeliveryCost.size(); i++){
			DeliveryCost det = sp.listDeliveryCost.get(i);
			e.pushNestedPath("listDeliveryCost["+i+"]");
			
			//cegah NPE
			if(det.nominal == null) det.nominal = 0.;

			//validasi
			String baris = new Integer(i+1).toString();
			ValidationUtils.rejectIfEmptyOrWhitespace(e, "nama_biaya", "ListItem2", new String[]{"Rincian Biaya", baris, "Nama Biaya", "harus diisi"});
			if(det.nominal.intValue() == 0) e.rejectValue("nominal", "ListItem2", new String[]{"Rincian Biaya", baris, "Nominal", "harus lebih dari nol"}, null);
			
			e.popNestedPath();
		}
		
	}

}
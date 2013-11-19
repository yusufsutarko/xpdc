package com.sma.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sma.model.Trans;
import com.sma.model.TransDet;
import com.sma.service.DbService;

/**
 * Validator Untuk Input Transaksi STT
 * 
 * @author Yusuf
 * @since Jul 5, 2013 (10:45:17 AM)
 *
 */
@Component
public class TransaksiSTTValidator implements Validator {

	@Autowired
	protected DbService dbService;
	
	public void setDbService(DbService dbService) {
		this.dbService = dbService;
	}

	public boolean supports(Class clazz) {
		return Trans.class.equals(clazz);
	}
	
	public void validate(Object obj, Errors e) {
        Trans stt = (Trans) obj;

        int cancel = stt.cancel == null ? 0 : stt.cancel;

        //Validasi apakah customer ini sudah melewati limit hutangnya atau belum.
        if(cancel == 0){
            int cek = dbService.selectCekLimitHutangCustomer(stt.customer_id);
            if(cek > 0) e.reject("", "STT tidak dapat disimpan. Customer ini sudah melewati limit hutang yang diperbolehkan.");
        }

        //Validasi mst_trans harus dicek apakah sudah ada pembayaran belum. Validasi ini harus dijalankan baik untuk simpan maupun pembatalan STT
		if(stt.id != null){
			Trans tmp = dbService.selectTrans(stt.id);
			if((tmp.total_harga.doubleValue() - tmp.potongan.doubleValue()) != tmp.remain.doubleValue()){
				e.reject("", "Data STT tidak dapat dirubah/dibatalkan. Sudah ada data pembayaran terhadap STT ini.");
			}
		}

		//Validasi minimal harus ada 1 item dalam STT
		if(stt.listTransDet.size() == 0) {
			e.reject("", "Anda belum memasukkan isi Rincian Barang menurut pengakuan");
		}

		
		//bila pembatalan STT, ada validasi
		if(cancel == 1){
			int naik = dbService.selectValidasiSTTSudahNaikRBT(stt.id);
			if(naik > 0) e.reject("", "Data STT tidak dapat dibatalkan. Sudah ada barang yang naik SP/RBT dari STT ini.");
			return;
			
			/* dtutup rudy, seharusnya gk perlu sampai di looping kalau di STT, karena sudah pasti 1 trans_id = 1 STT
			//untuk setiap item dalam STT, harus dicek ke SP/RBT, apakah sudah ada barang yg naik pengiriman? bila sudah ada satu pun, tidak bisa dibatalkan
			for(TransDet det : stt.listTransDet){
				int naik = dbService.selectValidasiSTTSudahNaikRBT(det.trans_id);
				if(naik > 0) e.reject("", "Data STT tidak dapat dibatalkan. Sudah ada barang yang naik SP/RBT dari STT ini.");
				break;
			}
			return;
			*/
		}

		//Validasi mst_trans
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "cabang_id", "NotEmpty", new String[]{"Cabang"});
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "customer_id", "NotEmpty", new String[]{"Pengirim"});
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "pay_mode", "NotEmpty", new String[]{"Cara Pembayaran"});
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "tujuan_id", "NotEmpty", new String[]{"Penerima"});
		//ValidationUtils.rejectIfEmptyOrWhitespace(e, "contact_tujuan", "NotEmpty", new String[]{"Contact Person Penerima / Tujuan"});
		//ValidationUtils.rejectIfEmptyOrWhitespace(e, "alamat_tujuan", "NotEmpty", new String[]{"Alamat Penerima / Tujuan"});
		//ValidationUtils.rejectIfEmptyOrWhitespace(e, "telp_tujuan", "NotEmpty", new String[]{"Telp Penerima / Tujuan"});

		//hitung grand totalnya, sekaligus validasi mst_trans_det
		stt.total_harga = 0.;
		stt.colly = 0.;
		for(int i=0; i<stt.listTransDet.size(); i++){
			TransDet det = stt.listTransDet.get(i);
			e.pushNestedPath("listTransDet["+i+"]");
			
			//cegah NPE
			if(det.qty == null) det.qty = 0.;
			if(det.harga == null) det.harga = 0.;
			if(det.colly == null) det.colly = 0.;
			if(det.colly_remain == null) det.colly_remain = det.colly;	//ini yg rudy ubah
			if(det.colly_naik == null) det.colly_naik = 0.;

			String baris = new Integer(i+1).toString();

			//bila ditandakan untuk dihapus, cek apakah sudah naik ke SP (Surat Rincian Barang Terkirim), bila ya, tidak bisa dibatalkan
			if(det.flag_delete){
				if(dbService.selectValidasiItemSTTSudahNaikRBT(det) > 0){
					e.rejectValue("colly", "ListItem", new String[]{baris, "Jumlah Colly", "tidak dapat dirubah. Barang sudah naik ke Surat Rincian Barang Terkirim (SP)"}, null);
				}
				
			//hanya lakukan perhitungan dan validasi, bila tidak dinyatakan untuk dihapus
			}else{
				//PENTING!!! Validasi TransDet harus dicek apakah sudah naik ke SP (Surat Rincian Barang Terkirim)
				if(det.colly.doubleValue() < det.colly_naik.doubleValue()){
					e.rejectValue("colly", "ListItem", new String[]{baris, "Jumlah Colly", "tidak dapat dirubah. Barang sudah naik ke Surat Rincian Barang Terkirim (SP)"}, null);
					//e.popNestedPath();
					//return;
				}
				
				//hitung2
				det.jumlah = det.qty * det.harga;
				stt.total_harga += det.jumlah;
				stt.colly += det.colly;
				det.colly_remain = det.colly - det.colly_naik;
				
				//validasi
				ValidationUtils.rejectIfEmptyOrWhitespace(e, "nama_barang", "ListItem", new String[]{baris, "Nama Barang", "harus diisi"});
//				if(det.qty.intValue() == 0) e.rejectValue("qty", "ListItem", new String[]{baris, "Quantity", "harus lebih dari nol"}, null);
//				if(det.colly.intValue() == 0) e.rejectValue("colly", "ListItem", new String[]{baris, "Colly", "harus lebih dari nol"}, null);
			}

			e.popNestedPath();
		}
		
		//remain = total harga - potongan
		if(stt.potongan == null) stt.potongan = 0.;
		stt.remain = stt.total_harga - stt.potongan;

		//Validasi lanjutan
        //if(!e.hasErrors()){
        //}
	}

}
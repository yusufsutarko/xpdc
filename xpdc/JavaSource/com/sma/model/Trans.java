package com.sma.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Domain object untuk MST_TRANS (STT)
 * 
 * @author Yusuf
 * @since Jun 28, 2013 (2:17:57 PM)
 *
 */
public class Trans implements Serializable {

	private static final long serialVersionUID = -4503764298960403317L;
	
	public Integer id, cabang_id, customer_id, createby, cancelby, pay_mode, cancel, modifyby, urut, tujuan_id;
	public Double colly, total_harga, potongan, remain;
	public String no_stt, createuser, canceluser, contact_tujuan, alamat_tujuan, telp_tujuan, note, modifyuser, cabang, customer, value, tujuan;
	public Date tgl_stt, tgl_kirim_est, createdate, canceldate, modifydate;
	public List<TransDet> listTransDet;
	
	public Trans() {
		this.listTransDet = new ArrayList<TransDet>(100);
	}
	
	public String getTujuan() {
		return tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public Integer getTujuan_id() {
		return tujuan_id;
	}

	public void setTujuan_id(Integer tujuan_id) {
		this.tujuan_id = tujuan_id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getUrut() {
		return urut;
	}

	public void setUrut(Integer urut) {
		this.urut = urut;
	}

	public List<TransDet> getListTransDet() {
		return listTransDet;
	}
	public void setListTransDet(List<TransDet> listTransDet) {
		this.listTransDet = listTransDet;
	}
	public String getCabang() {
		return cabang;
	}
	public void setCabang(String cabang) {
		this.cabang = cabang;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Integer getModifyby() {
		return modifyby;
	}
	public void setModifyby(Integer modifyby) {
		this.modifyby = modifyby;
	}
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCabang_id() {
		return cabang_id;
	}
	public void setCabang_id(Integer cabang_id) {
		this.cabang_id = cabang_id;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getCreateby() {
		return createby;
	}
	public void setCreateby(Integer createby) {
		this.createby = createby;
	}
	public Integer getCancelby() {
		return cancelby;
	}
	public void setCancelby(Integer cancelby) {
		this.cancelby = cancelby;
	}
	public Integer getPay_mode() {
		return pay_mode;
	}
	public void setPay_mode(Integer pay_mode) {
		this.pay_mode = pay_mode;
	}
	public Integer getCancel() {
		return cancel;
	}
	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}
	public Double getColly() {
		return colly;
	}
	public void setColly(Double colly) {
		this.colly = colly;
	}
	public Double getTotal_harga() {
		return total_harga;
	}
	public void setTotal_harga(Double total_harga) {
		this.total_harga = total_harga;
	}
	public Double getPotongan() {
		return potongan;
	}
	public void setPotongan(Double potongan) {
		this.potongan = potongan;
	}
	public Double getRemain() {
		return remain;
	}
	public void setRemain(Double remain) {
		this.remain = remain;
	}
	public String getNo_stt() {
		return no_stt;
	}
	public void setNo_stt(String no_stt) {
		this.no_stt = no_stt;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getCanceluser() {
		return canceluser;
	}
	public void setCanceluser(String canceluser) {
		this.canceluser = canceluser;
	}
	public String getContact_tujuan() {
		return contact_tujuan;
	}
	public void setContact_tujuan(String contact_tujuan) {
		this.contact_tujuan = contact_tujuan;
	}
	public String getAlamat_tujuan() {
		return alamat_tujuan;
	}
	public void setAlamat_tujuan(String alamat_tujuan) {
		this.alamat_tujuan = alamat_tujuan;
	}
	public String getTelp_tujuan() {
		return telp_tujuan;
	}
	public void setTelp_tujuan(String telp_tujuan) {
		this.telp_tujuan = telp_tujuan;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getTgl_stt() {
		return tgl_stt;
	}
	public void setTgl_stt(Date tgl_stt) {
		this.tgl_stt = tgl_stt;
	}
	public Date getTgl_kirim_est() {
		return tgl_kirim_est;
	}
	public void setTgl_kirim_est(Date tgl_kirim_est) {
		this.tgl_kirim_est = tgl_kirim_est;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getCanceldate() {
		return canceldate;
	}
	public void setCanceldate(Date canceldate) {
		this.canceldate = canceldate;
	}
	
}
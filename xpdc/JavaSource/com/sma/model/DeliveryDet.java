package com.sma.model;

import java.io.Serializable;

public class DeliveryDet implements Serializable{

	private static final long serialVersionUID = 7476185666353329229L;

	public Integer delivery_id, trans_id, trans_urut;
	public String note, no_stt, customer_nama, nama_barang, note_delivery;
	public Double colly_naik, colly_sisa, nominal, colly, colly_total, colly_sampai;
	//colly_stt = colly awal yg tercatat di STT.
	//colly_naik = colly yg naik ke SP.
	//colly_sisa = colly sisa yg belum naik ke SP, saat SP dibuat.
	//colly_total = total colly saat SP dibuat (bisa saja berbeda dengan colly awal yg dicatat di STT). colly_naik + colly_sisa
	//colly_sampai = colly yg sampai
	
	public boolean flag, flag_delete;
	
	//set default values on init/constructor
	public DeliveryDet() {
		this.flag = true;
	}

	public boolean isFlag_delete() {
		return flag_delete;
	}

	public void setFlag_delete(boolean flag_delete) {
		this.flag_delete = flag_delete;
	}

	public String getNote_delivery() {
		return note_delivery;
	}

	public void setNote_delivery(String note_delivery) {
		this.note_delivery = note_delivery;
	}

	public Double getColly_sampai() {
		return colly_sampai;
	}

	public void setColly_sampai(Double colly_sampai) {
		this.colly_sampai = colly_sampai;
	}

	public Double getColly_total() {
		return colly_total;
	}

	public void setColly_total(Double colly_total) {
		this.colly_total = colly_total;
	}

	public String getNo_stt() {
		return no_stt;
	}

	public void setNo_stt(String no_stt) {
		this.no_stt = no_stt;
	}

	public String getCustomer_nama() {
		return customer_nama;
	}

	public void setCustomer_nama(String customer_nama) {
		this.customer_nama = customer_nama;
	}

	public String getNama_barang() {
		return nama_barang;
	}

	public void setNama_barang(String nama_barang) {
		this.nama_barang = nama_barang;
	}

	public Double getColly() {
		return colly;
	}

	public void setColly(Double colly) {
		this.colly = colly;
	}

	public Double getNominal() {
		return nominal;
	}

	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}

	public Integer getDelivery_id() {
		return delivery_id;
	}

	public void setDelivery_id(Integer delivery_id) {
		this.delivery_id = delivery_id;
	}

	public Integer getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(Integer trans_id) {
		this.trans_id = trans_id;
	}

	public Integer getTrans_urut() {
		return trans_urut;
	}

	public void setTrans_urut(Integer trans_urut) {
		this.trans_urut = trans_urut;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getColly_naik() {
		return colly_naik;
	}

	public void setColly_naik(Double colly_naik) {
		this.colly_naik = colly_naik;
	}

	public Double getColly_sisa() {
		return colly_sisa;
	}

	public void setColly_sisa(Double colly_sisa) {
		this.colly_sisa = colly_sisa;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
package com.sma.model;

import java.io.Serializable;

public class TransDet implements Serializable{

	private static final long serialVersionUID = -624192637222692926L;

	public Integer trans_id, urut, satuan_id, barang_id, customer_id;
	public String nama_barang, no_stt, customer, value;
	public Double qty, harga, jumlah, colly_stt, colly, colly_remain, colly_naik; 
	public boolean flag, flag_delete;
	
	//set default values on init/constructor
	public TransDet() {
		this.flag = true;
	}
	
	public boolean isFlag_delete() {
		return flag_delete;
	}

	public void setFlag_delete(boolean flag_delete) {
		this.flag_delete = flag_delete;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getNo_stt() {
		return no_stt;
	}

	public void setNo_stt(String no_stt) {
		this.no_stt = no_stt;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Double getColly_stt() {
		return colly_stt;
	}

	public void setColly_stt(Double colly_stt) {
		this.colly_stt = colly_stt;
	}

	public Double getColly() {
		return colly;
	}

	public void setColly(Double colly) {
		this.colly = colly;
	}

	public Integer getBarang_id() {
		return barang_id;
	}

	public void setBarang_id(Integer barang_id) {
		this.barang_id = barang_id;
	}

	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Double getJumlah() {
		return jumlah;
	}
	public void setJumlah(Double jumlah) {
		this.jumlah = jumlah;
	}
	public Integer getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(Integer trans_id) {
		this.trans_id = trans_id;
	}
	public Integer getUrut() {
		return urut;
	}
	public void setUrut(Integer urut) {
		this.urut = urut;
	}
	public Integer getSatuan_id() {
		return satuan_id;
	}
	public void setSatuan_id(Integer satuan_id) {
		this.satuan_id = satuan_id;
	}
	public String getNama_barang() {
		return nama_barang;
	}
	public void setNama_barang(String nama_barang) {
		this.nama_barang = nama_barang;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Double getHarga() {
		return harga;
	}
	public void setHarga(Double harga) {
		this.harga = harga;
	}

	public Double getColly_remain() {
		return colly_remain;
	}

	public void setColly_remain(Double colly_remain) {
		this.colly_remain = colly_remain;
	}

	public Double getColly_naik() {
		return colly_naik;
	}

	public void setColly_naik(Double colly_naik) {
		this.colly_naik = colly_naik;
	}
	
}
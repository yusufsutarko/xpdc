package com.sma.model;

import java.io.Serializable;

public class DeliveryCost implements Serializable{

	private static final long serialVersionUID = -9073856008698623399L;

	public Integer delivery_id, biaya_id;
	public String note, nama_biaya;
	public Double nominal;

	public boolean flag, flag_delete;
	
	//set default values on init/constructor
	public DeliveryCost() {
		this.flag = true;
	}

	public boolean isFlag_delete() {
		return flag_delete;
	}

	public void setFlag_delete(boolean flag_delete) {
		this.flag_delete = flag_delete;
	}

	public String getNama_biaya() {
		return nama_biaya;
	}

	public void setNama_biaya(String nama_biaya) {
		this.nama_biaya = nama_biaya;
	}

	public Integer getDelivery_id() {
		return delivery_id;
	}

	public void setDelivery_id(Integer delivery_id) {
		this.delivery_id = delivery_id;
	}

	public Integer getBiaya_id() {
		return biaya_id;
	}

	public void setBiaya_id(Integer biaya_id) {
		this.biaya_id = biaya_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getNominal() {
		return nominal;
	}

	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
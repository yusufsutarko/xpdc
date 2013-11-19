package com.sma.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Domain object untuk MST_DELIVERY (SP / Rincian Barang Terkirim)
 * 
 * @author Yusuf
 * @since Jul 16, 2013 (11:09:10 AM)
 *
 */
public class Delivery implements Serializable {

	private static final long serialVersionUID = 1676846544349287089L;

	public Integer id, supir_id, createby, cancelby, modifyby, cancel;
	public Date tgl_kirim, tgl_sampai, createdate, canceldate, modifydate;
	public String no_polisi, kode_kapal, supir_nama, kapal_nama, createuser, canceluser, modifyuser;
	
	public List<DeliveryDet> listDeliveryDet;
	public List<DeliveryCost> listDeliveryCost;
	
	public Delivery() {
		this.listDeliveryDet = new ArrayList<DeliveryDet>(100);
		this.listDeliveryCost = new ArrayList<DeliveryCost>(100);
	}

	public List<DeliveryCost> getListDeliveryCost() {
		return listDeliveryCost;
	}

	public void setListDeliveryCost(List<DeliveryCost> listDeliveryCost) {
		this.listDeliveryCost = listDeliveryCost;
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

	public String getModifyuser() {
		return modifyuser;
	}

	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}

	public String getSupir_nama() {
		return supir_nama;
	}

	public void setSupir_nama(String supir_nama) {
		this.supir_nama = supir_nama;
	}

	public String getKapal_nama() {
		return kapal_nama;
	}

	public void setKapal_nama(String kapal_nama) {
		this.kapal_nama = kapal_nama;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSupir_id() {
		return supir_id;
	}

	public void setSupir_id(Integer supir_id) {
		this.supir_id = supir_id;
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

	public Integer getModifyby() {
		return modifyby;
	}

	public void setModifyby(Integer modifyby) {
		this.modifyby = modifyby;
	}

	public Integer getCancel() {
		return cancel;
	}

	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}

	public Date getTgl_kirim() {
		return tgl_kirim;
	}

	public void setTgl_kirim(Date tgl_kirim) {
		this.tgl_kirim = tgl_kirim;
	}

	public Date getTgl_sampai() {
		return tgl_sampai;
	}

	public void setTgl_sampai(Date tgl_sampai) {
		this.tgl_sampai = tgl_sampai;
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

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public String getNo_polisi() {
		return no_polisi;
	}

	public void setNo_polisi(String no_polisi) {
		this.no_polisi = no_polisi;
	}

	public String getKode_kapal() {
		return kode_kapal;
	}

	public void setKode_kapal(String kode_kapal) {
		this.kode_kapal = kode_kapal;
	}

	public List<DeliveryDet> getListDeliveryDet() {
		return listDeliveryDet;
	}

	public void setListDeliveryDet(List<DeliveryDet> listDeliveryDet) {
		this.listDeliveryDet = listDeliveryDet;
	}
	
}
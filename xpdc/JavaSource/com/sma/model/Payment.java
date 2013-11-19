package com.sma.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain object untuk mst_payment
 * 
 * @author Yusuf
 * @since Aug 5, 2013 (9:46:15 AM)
 *
 */
public class Payment implements Serializable{

	private static final long serialVersionUID = 3514171401474438306L;
	
	public Integer payment_id, trans_id, pay_mode, createby, modifyby, cancelby, cancel;
	public String no_payment, dk, no_giro, keterangan, createuser, modifyuser, canceluser, pay_ket, no_stt, customer, tujuan;
	public Date paid_date, due_date, createdate, modifydate, canceldate;
	public Double nominal;
	
	public String getNo_stt() {
		return no_stt;
	}
	public void setNo_stt(String no_stt) {
		this.no_stt = no_stt;
	}
	public String getPay_ket() {
		return pay_ket;
	}
	public void setPay_ket(String pay_ket) {
		this.pay_ket = pay_ket;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	public String getCanceluser() {
		return canceluser;
	}
	public void setCanceluser(String canceluser) {
		this.canceluser = canceluser;
	}
	public Integer getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}
	public Integer getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(Integer trans_id) {
		this.trans_id = trans_id;
	}
	public Integer getPay_mode() {
		return pay_mode;
	}
	public void setPay_mode(Integer pay_mode) {
		this.pay_mode = pay_mode;
	}
	public Integer getCreateby() {
		return createby;
	}
	public void setCreateby(Integer createby) {
		this.createby = createby;
	}
	public Integer getModifyby() {
		return modifyby;
	}
	public void setModifyby(Integer modifyby) {
		this.modifyby = modifyby;
	}
	public Integer getCancelby() {
		return cancelby;
	}
	public void setCancelby(Integer cancelby) {
		this.cancelby = cancelby;
	}
	public Integer getCancel() {
		return cancel;
	}
	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}
	public String getNo_payment() {
		return no_payment;
	}
	public void setNo_payment(String no_payment) {
		this.no_payment = no_payment;
	}
	public String getDk() {
		return dk;
	}
	public void setDk(String dk) {
		this.dk = dk;
	}
	public String getNo_giro() {
		return no_giro;
	}
	public void setNo_giro(String no_giro) {
		this.no_giro = no_giro;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public Date getPaid_date() {
		return paid_date;
	}
	public void setPaid_date(Date paid_date) {
		this.paid_date = paid_date;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	public Date getCanceldate() {
		return canceldate;
	}
	public void setCanceldate(Date canceldate) {
		this.canceldate = canceldate;
	}
	public Double getNominal() {
		return nominal;
	}
	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getTujuan() {
		return tujuan;
	}
	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}
	
}
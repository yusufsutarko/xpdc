package com.sma.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain object untuk HARGA
 * 
 * @author Rudy
 * @since Jun 26, 2013 (9:29:53 AM)
 *
 */
public class Harga implements Serializable {

	private static final long serialVersionUID = 5245601545391273148L;
	
	public String menuAkses, createuser, modifyuser, customerName, barangName, mode;
    public Integer customer_id, barang_id, active, createby, modifyby;
    public Date createdate, modifydate;
    public Double harga;
    
    public Harga() {
	}

    public Harga(Integer customer_id, Integer barang_id, Double harga, Integer active, Integer createby, Date createdate) {
    	this.customer_id = customer_id;
    	this.barang_id = barang_id;
    	this.harga = harga;
    	this.active = active;
    	this.createby = createby;
    	this.createdate = createdate;
	}
    
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMenuAkses() {
		return menuAkses;
	}
	public void setMenuAkses(String menuAkses) {
		this.menuAkses = menuAkses;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBarangName() {
		return barangName;
	}
	public void setBarangName(String barangName) {
		this.barangName = barangName;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getBarang_id() {
		return barang_id;
	}
	public void setBarang_id(Integer barang_id) {
		this.barang_id = barang_id;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
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
	public Double getHarga() {
		return harga;
	}
	public void setHarga(Double harga) {
		this.harga = harga;
	}

}
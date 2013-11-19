package com.sma.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain object untuk BIAYA
 * 
 * @author Rudy
 * @since Jul 17, 2013 (9:29:53 AM)
 *
 */
public class Biaya implements Serializable {

	private static final long serialVersionUID = 2977651360641293021L;
	
	public String nama, menuAkses, createuser, modifyuser, value;
    public Integer id, active, createby, modifyby;
    public Date createdate, modifydate;
    public Double nominal;
    
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getMenuAkses() {
		return menuAkses;
	}
	public void setMenuAkses(String menuAkses) {
		this.menuAkses = menuAkses;
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
	public Double getNominal() {
		return nominal;
	}
	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}

}
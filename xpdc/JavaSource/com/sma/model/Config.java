package com.sma.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain object untuk CONFIG
 * 
 * @author Rudy
 * @since Jun 26, 2013 (9:29:53 AM)
 *
 */
public class Config implements Serializable {

	private static final long serialVersionUID = 999175139647887011L;
	
	public String keterangan, menuAkses, createuser, modifyuser;
    public Integer id, jenis, active, createby, modifyby;
    public Date createdate, modifydate;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getJenis() {
		return jenis;
	}
	public void setJenis(Integer jenis) {
		this.jenis = jenis;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
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

}
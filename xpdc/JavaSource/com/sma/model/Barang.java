package com.sma.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain object untuk BARANG
 * 
 * @author Rudy
 * @since Jun 25, 2013 (9:29:53 AM)
 *
 */
public class Barang implements Serializable {

	private static final long serialVersionUID = -6177367811996640465L;
	
	public String nama, menuAkses, createuser, modifyuser;
    public Integer id, active, createby, modifyby;
    public Date createdate, modifydate;
    
    public Barang() {
    	
	}
    
	public Barang(String nama_barang, int active, Integer createby, Date createdate) {
		this.nama = nama_barang;
		this.active = active;
		this.createby = createby;
		this.createdate = createdate;
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

}
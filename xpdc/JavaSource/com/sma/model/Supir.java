package com.sma.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain object untuk SUPIR
 * 
 * @author Rudy
 * @since Jun 25, 2013 (9:29:53 AM)
 *
 */
public class Supir implements Serializable {

	private static final long serialVersionUID = -9165380186569512463L;

    public String nama, menuAkses, createuser, modifyuser;
    public Integer id, active, createby, modifyby;
    public Date tgl_mulai, tgl_berakhir, createdate, modifydate;
    
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
	public Date getTgl_mulai() {
		return tgl_mulai;
	}
	public void setTgl_mulai(Date tgl_mulai) {
		this.tgl_mulai = tgl_mulai;
	}
	public Date getTgl_berakhir() {
		return tgl_berakhir;
	}
	public void setTgl_berakhir(Date tgl_berakhir) {
		this.tgl_berakhir = tgl_berakhir;
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
package com.sma.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

/**
 * Domain object untuk KAPAL
 * 
 * @author Rudy
 * @since Jun 26, 2013 (9:29:53 AM)
 *
 */
public class Kapal implements Serializable {

	private static final long serialVersionUID = 2346480498916573715L;
	
	@Pattern(regexp="^[a-zA-Z0-9_-]+$")
	public String kode;
	
	public String nama, menuAkses, createuser, modifyuser, mode, jenisName;
    public Integer jenis, active, createby, modifyby;
    public Date createdate, modifydate;
    public Double harga;
    
	public String getJenisName() {
		return jenisName;
	}
	public void setJenisName(String jenisName) {
		this.jenisName = jenisName;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}    
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
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
	public Integer getJenis() {
		return jenis;
	}
	public void setJenis(Integer jenis) {
		this.jenis = jenis;
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
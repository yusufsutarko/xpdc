package com.sma.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Domain object untuk CUSTOMER
 * 
 * @author Yusuf
 * @since Jun 25, 2013 (4:02:14 PM)
 *
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 6016572585898873748L;

	@NotEmpty
	public String nama;	
	
	@Email
	public String email;

	public Integer id, active, createby, modifyby, type;
	public String kode, alamat, kota, contact, no_hp, no_fax, createuser, modifyuser, value, no_telp, nama_type;
	public Double limit_hutang, total_harga, potongan, paid, remain;
	public Date due_date, createdate, modifydate, since;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getSince() {
		return since;
	}
	public void setSince(Date since) {
		this.since = since;
	}
	public Double getTotal_harga() {
		return total_harga;
	}
	public void setTotal_harga(Double total_harga) {
		this.total_harga = total_harga;
	}
	public Double getPotongan() {
		return potongan;
	}
	public void setPotongan(Double potongan) {
		this.potongan = potongan;
	}
	public Double getPaid() {
		return paid;
	}
	public void setPaid(Double paid) {
		this.paid = paid;
	}
	public Double getRemain() {
		return remain;
	}
	public void setRemain(Double remain) {
		this.remain = remain;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getKota() {
		return kota;
	}
	public void setKota(String kota) {
		this.kota = kota;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getNo_telp() {
		return no_telp;
	}
	public void setNo_telp(String no_telp) {
		this.no_telp = no_telp;
	}
	public String getNo_hp() {
		return no_hp;
	}
	public void setNo_hp(String no_hp) {
		this.no_hp = no_hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNo_fax() {
		return no_fax;
	}
	public void setNo_fax(String no_fax) {
		this.no_fax = no_fax;
	}
	public Double getLimit_hutang() {
		return limit_hutang;
	}
	public void setLimit_hutang(Double limit_hutang) {
		this.limit_hutang = limit_hutang;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getNama_type() {
		return nama_type;
	}
	public void setNama_type(String nama_type) {
		this.nama_type = nama_type;
	}
	
}
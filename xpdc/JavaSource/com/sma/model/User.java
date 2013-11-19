package com.sma.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Domain object untuk USER
 * 
 * @author Yusuf
 * @since Jun 20, 2013 (9:29:53 AM)
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 5367856769525827869L;

	//variable utama, validasi2 standar langsung di-define disini dan 
	//di-enable di Spring menggunakan annotation @Valid, dan menggunakan library Hibernate Validator (spesifikasi JSR-303)
	//error message bisa di-define satu persatu disini, atau diletakkan di messages.properties di WEB-INF
	//untuk definisi lengkapnya, bisa lihat dokumentasi hibernate validator 4.2.0 bagian 2.4.1
	
	@NotEmpty
	@Size(max=20, min=3)
	@Pattern(regexp="^[a-zA-Z0-9_-]+$")
	public String username;

	@NotEmpty
	@Size(max=20, min=6)
	public String password;
		
	public Integer id, cabang_id, group_user_id, flag_approval, flag_akses_all, active, createby, modifyby;
	public String nama, confirm_password, email, nik, createuser, modifyuser, menuAkses, new_password, cabang;
	
	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public Date dob, lastlogin, createdate, modifydate;
	
	//constructors
	public User() {
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	//getter setter
	public String getCabang() {
		return cabang;
	}

	public void setCabang(String cabang) {
		this.cabang = cabang;
	}

	public String getUsername() {
		return username;
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

	public String getNik() {
		return nik;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCabang_id() {
		return cabang_id;
	}

	public void setCabang_id(Integer cabang_id) {
		this.cabang_id = cabang_id;
	}

	public Integer getGroup_user_id() {
		return group_user_id;
	}

	public void setGroup_user_id(Integer group_user_id) {
		this.group_user_id = group_user_id;
	}

	public Integer getFlag_approval() {
		return flag_approval;
	}

	public void setFlag_approval(Integer flag_approval) {
		this.flag_approval = flag_approval;
	}

	public Integer getFlag_akses_all() {
		return flag_akses_all;
	}

	public void setFlag_akses_all(Integer flag_akses_all) {
		this.flag_akses_all = flag_akses_all;
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

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
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

}
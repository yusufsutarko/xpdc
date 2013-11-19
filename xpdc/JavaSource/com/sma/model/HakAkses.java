package com.sma.model;

import java.io.Serializable;

/**
 * Domain object untuk HAK AKSES
 * 
 * @author Yusuf
 * @since Jun 25, 2013 (10:00:37 AM)
 *
 */
public class HakAkses implements Serializable {

	private static final long serialVersionUID = -60381517947364100L;
	
	public Integer group_user_id, menu_id, active, parent_id;
	public String nama, parent_nama;
	
	//constructors
	public HakAkses() {
	}

	//getter setter
	public Integer getGroup_user_id() {
		return group_user_id;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public String getParent_nama() {
		return parent_nama;
	}

	public void setParent_nama(String parent_nama) {
		this.parent_nama = parent_nama;
	}

	public void setGroup_user_id(Integer group_user_id) {
		this.group_user_id = group_user_id;
	}

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}
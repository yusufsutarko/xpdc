package com.sma.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Domain object untuk GROUP USER dan HAK AKSES
 * 
 * @author Yusuf
 * @since Jun 25, 2013 (10:00:37 AM)
 *
 */
public class GroupUser implements Serializable {

	private static final long serialVersionUID = 8549146871903075019L;
	
	public Integer id;
	
	@NotEmpty
	public String nama;
	
	public List<HakAkses> listHakAkses;
	
	//constructors
	public GroupUser() {
	}

	//getter setter
	public Integer getId() {
		return id;
	}

	public List<HakAkses> getListHakAkses() {
		return listHakAkses;
	}

	public void setListHakAkses(List<HakAkses> listHakAkses) {
		this.listHakAkses = listHakAkses;
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
	
}
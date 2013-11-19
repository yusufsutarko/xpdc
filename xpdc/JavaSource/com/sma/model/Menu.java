package com.sma.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain object untuk MENU
 * 
 * @author Yusuf
 * @since Jun 25, 2013 (10:00:37 AM)
 *
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = -7708477234362169913L;

	public Integer id, parent, urut, level, path, active, createby, modifyby;
	public String nama, link, mode;
	public Date createdate, modifydate;
    
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	//constructors
	public Menu() {
	}

	//getter setter	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Integer getUrut() {
		return urut;
	}

	public void setUrut(Integer urut) {
		this.urut = urut;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getPath() {
		return path;
	}

	public void setPath(Integer path) {
		this.path = path;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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
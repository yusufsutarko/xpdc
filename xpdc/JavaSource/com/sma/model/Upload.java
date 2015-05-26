package com.sma.model;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Domain object (Model) untuk file upload
 * 
 * @author yusuf_sutarko
 * @since May 26, 2015 (8:17:07 AM)
 */
public class Upload implements Serializable {

	private static final long serialVersionUID = -6177367811996640465L;
	
	public String nama;
    public CommonsMultipartFile file;
    
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
    
}
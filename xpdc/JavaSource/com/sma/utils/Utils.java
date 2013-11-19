package com.sma.utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sma.model.Menu;

/**
 * Utility classes, rata2 function/vars disini static saja
 * 
 * @author Yusuf
 * @since Jun 20, 2013 (9:34:23 AM)
 *
 */
public class Utils{
	
	// Formatter2 default ada disini, tidak perlu di-register satu2 di spring xml
	public static final DateFormat defaultDF = new SimpleDateFormat("dd-MM-yyyy");
	public static final DateFormat completeDF = new SimpleDateFormat("dd-MM-yy (HH:mm:ss)");
	public static final DateFormat yearDF = new SimpleDateFormat("yyyy");
	public static final NumberFormat defaultNF = NumberFormat.getInstance(); //DecimalFormat("#,##0.00;(#,##0.00)")
	
	/**
	 * Tarik data tahun aplikasi. Contoh hasilnya "2006-2013" 
	 * 
	 * @param now
	 * @return
	 */
	public static String getCopyrightYears(Date now){
		int tahunAwal = 2013;
		int tahunSekarang = Integer.parseInt(yearDF.format(now));
		
		String tahun;
		if(tahunSekarang > tahunAwal) tahun = tahunAwal + "-" + tahunSekarang;
		else tahun = String.valueOf(tahunAwal);
		return tahun;
	}
	
	/**
	 * Fungsi untuk me-listing semua report yang ada di file properties, dimana key nya harus dimulai dengan report atau subreport 
	 * 
	 * @author Yusuf
	 * @since Jul 8, 2008 (10:56:24 AM)
	 * @param props
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> listAllReports(Properties props) {
		List<String> reportList = new ArrayList<String>();
		for(Iterator it = props.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			if(key.startsWith("report") || key.startsWith("subreport")) {
				reportList.add(key);
			}
		}
		Collections.sort(reportList);
		return reportList;
	}

	/**
	 * Generate Hirarki Menu
	 * 
	 * @author Yusuf
	 * @since 25 Jun 13
	 * @param listMenuAkses
	 * @param path
	 * @return
	 */
	public static String generateMenuAkses(List<Menu> listMenuAkses, String path){
		StringBuffer result = new StringBuffer();
		String disabled = "";
		int hit = 0;
		
		result.append("<ul id=\"menubar\" class=\"menubar\">");
		result.append("<li><a href=\"" +path+ "\">Home</a></li>"); //Tambah 1 menu di awal untuk link ke halaman Home
		
		for(Menu m : listMenuAkses){
			if(m.parent.intValue() == 1) { //bila menu utama.

				//prosedur tambahan untuk ngecek apakah MENU ini ada SUBMENU nya. bila tidak ada sama sekali, tidak usah ditampilkan.
				hit = 0;
				for(Menu c : listMenuAkses){
					if(c.parent.intValue() == m.id.intValue()) { //bila merupakan anak dari menu utama
						if(c.active.intValue() == 1){
							hit++;
						}
					}
				}
				
				//hanya tampilkan bila ada submenunya
				if(hit > 0){
					result.append("<li><a href=\"#\">" +m.nama+ "</a>");
					result.append("<ul>");
					//System.out.println(m.nama);
					
					for(Menu c : listMenuAkses){
						if(c.parent.intValue() == m.id.intValue()) { //bila merupakan anak dari menu utama
							//System.out.println(" " + c.nama + " = " + c.active);
							if(c.active.intValue() == 1){ //hanya tampilkan menu yg status aktifnya = 1 (allowed to access, dan masih active)
								disabled = "";
								if(c.link == null) disabled = "class=\"ui-state-disabled\""; //bila link null maka disable menunya
								result.append("<li " +disabled+ "><a href=\"" +path+ "/" +c.link+ "\">" +c.nama+ "</a></li>");
							}
						}
					}
					result.append("</ul>");
				}
			}else{
				break;
			}
		}
		
		result.append("<li><a href=\"" +path+ "/logout\">Logout</a></li>"); //Tambah 1 menu di akhir untuk tombol Logout
		result.append("</ul><br>");
		
		return result.toString();
	}
	
	/**
	 * Cek kosong
	 * 
	 * @author Yusuf
	 * @since 5 Jul 13
	 * @param listMenuAkses
	 * @param path
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		if(object==null) return true;
		else if(object instanceof String) {
			String tmp = (String) object;
			if(tmp.trim().equals("")) return true;
			else return false;
		}else if(object instanceof List) {
			List<?> tmp = (List<?>) object;
			return tmp.isEmpty();
		}else if(object instanceof Map){
			return ((Map<?, ?>) object).isEmpty();
		}
		return true;
	}
	
	public static String convertDateToString(Date tanggal,String format){
		if(tanggal==null)return null;
		else{
			try{
				return new SimpleDateFormat(format).format(tanggal);
			}catch (Exception e) {
				
				return null;
			}
		}
	}
	
	/**
	 * Fungsi untuk menarik nilai cookie dari browser user, dengan default value
	 * 
	 * @author Yusuf
	 * @since Jun 30, 2008 (2:35:10 PM)
	 * @param request
	 * @param key nama cookie
	 * @param defaultValue nilai default cookie bila tidak ditemukan
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String key, String defaultValue) {
		String result = getCookie(request, key);
		if(result == null) return defaultValue;
		else if(result.trim().equals("")) return defaultValue;
		else return result;
	}
	
	/**
	 * Fungsi untuk menarik nilai cookie dari browser user, tanpa default value (return "")
	 * 
	 * @author Yusuf
	 * @since Jun 30, 2008 (2:35:36 PM)
	 * @param request
	 * @param key nama cookie
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			int length = cookies.length;
			for (int i = 0; i < length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals(key))
					return cookie.getValue();
				
			}
		}
		return "";
	}
	
	/**
	 * Fungsi untuk menyimpan cookie ke browser user
	 * @author Yusuf
	 * @since Jun 30, 2008 (2:36:04 PM)
	 * @param response
	 * @param key nama cookie
	 * @param value nilai cookie
	 * @param maxDays berapa hari cookie ingin disimpan dalam browser user, bila diisi null, default = 1 bulan
	 */
	public static void setCookie(HttpServletResponse response, String key, String value, Integer maxDays) {
		Cookie kue = new Cookie(key, value);
		if(maxDays == null) maxDays = 30;
		kue.setMaxAge(24 * 60 * 60 * maxDays); // Simpan umur cookie selama 1 bulan
		response.addCookie(kue);
	}
	
	/**
	 * Fungsi yang mengikuti fungsi RPAD di Oracle, contoh: rpad("0", "YUSUF", 10) menghasilkan "00000YUSUF"
	 * 
	 * @author Yusuf
	 * @since Feb 21, 2011 (7:41:43 PM)
	 * @param karakter untuk melengkapi sisa string
	 * @param kata (String) yang mau dipanjangkan
	 * @param panjang dari string hasilnya
	 * @return String hasil penggabungan dari karakter dan kata
	 * @see Fungsi RPAD di Oracle
	 */
	public static String rpad(String karakter, String kata, int panjang) {
		if(kata==null) return null;
		StringBuffer result = new StringBuffer();
		if (kata.length() < panjang) {
			for (int i = 0; i < panjang - kata.length(); i++) {
				result.append(karakter);
			}
			result.append(kata);
			return result.toString();
		} else {
			return kata;
		}
	}
	
	public static Date convertStringToDate(String tanggal,String format){
		if(tanggal==null)return null;
		else{
			try{
				return new SimpleDateFormat(format).parse(tanggal);
			}catch (Exception e) {
				
				return null;
			}
		}
	}
	
}
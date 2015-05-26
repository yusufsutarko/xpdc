package com.sma.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sma.model.Barang;
import com.sma.model.Biaya;
import com.sma.model.Cabang;
import com.sma.model.Customer;
import com.sma.model.Delivery;
import com.sma.model.DeliveryCost;
import com.sma.model.DeliveryDet;
import com.sma.model.DropDown;
import com.sma.model.GroupUser;
import com.sma.model.HakAkses;
import com.sma.model.Harga;
import com.sma.model.Kapal;
import com.sma.model.Menu;
import com.sma.model.Mobil;
import com.sma.model.Payment;
import com.sma.model.Supir;
import com.sma.model.Trans;
import com.sma.model.TransDet;
import com.sma.model.Upload;
import com.sma.model.User;
import com.sma.persistence.DbMapper;
import com.sma.utils.SessionRegistry;
import com.sma.utils.Utils;

/**
 * Main service object. semua business logic diletakkan disini
 * Fitur baru MyBatis, tidak perlu buat class DAO implementation lagi, cukup buat mapper interface + sql nya saja langsung bisa pakai
 * 
 * @author Yusuf
 * @since Jun 20, 2013 (9:41:13 AM)
 *
 */
@Transactional
public class DbService {

	private static Logger logger = Logger.getLogger(DbService.class);
	
	@Autowired
	private DbMapper dbMapper;

	@Autowired
	private MessageSource messageSource;

	public Date selectSysdate() {
		logger.debug("selectSysdate");
		return dbMapper.selectSysdate();
	}

	public Integer selectTestDB() {
		return dbMapper.selectTestDB();
	}
	public Integer updateTestDB(Integer toggle) {
		return dbMapper.updateTestDB(toggle);
	}

	public List<DropDown> selectDropDown(String key, String value, String table, String where, String order) {
		return dbMapper.selectDropDown(key, value, table, where, order);
	}
	
	public void login(User currentUser, SessionRegistry sessionRegistry, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("login(User currentUser, SessionRegistry sessionRegistry, HttpServletRequest request)");
		//bila sudah pernah login, kick session lama
		if(sessionRegistry.contains(currentUser)) sessionRegistry.kick(currentUser, request.getSession(false));
		//create session baru
		HttpSession session = request.getSession(true);
		//set login time
		currentUser.setLastlogin(dbMapper.selectSysdate());
		//update login time terakhir
		dbMapper.updateUser(currentUser);
		
		//generate menu sesuai hak akses
		List<Menu> listMenuAkses = dbMapper.selectMenuAkses(currentUser.getGroup_user_id());
		currentUser.setMenuAkses(Utils.generateMenuAkses(listMenuAkses, request.getContextPath()));
		
		//letakkan currentUser di session
		session.setAttribute("currentUser", currentUser);
		//letakkan currentUser di daftar user
		sessionRegistry.put(currentUser);
		
		//simpan username yg login terakhir ke cookie
		Utils.setCookie(response, "XPDC_USERNAME", currentUser.getUsername(), 30);
	}

	public String changePassword(User user) {
		User update = new User();
		update.setId(user.id);
		update.setPassword(user.confirm_password);
		update.setModifyby(user.id);
		update.setModifydate(selectSysdate());
		dbMapper.updateUser(update);
		return messageSource.getMessage("SaveSuccess", new String[]{"Password baru", user.username}, null);
	}

	public User selectUser(Integer id, String username, String password) {
		return dbMapper.selectUser(id, username, password);
	}
	
	public List<User> selectListUser( String search, Integer offset, Integer rowcount) {
		return dbMapper.selectListUser(search, offset, rowcount);
	}
	
	public Integer selectListUserCount(String search) {
		return dbMapper.selectListUserCount(search);
	}
	
	public String saveUser(User user, User currentUser){
		logger.debug("saveUser(User user, User currentUser)");
		
		if(user.id == null){ //INSERT
			user.createby = currentUser.id;
			user.createdate = dbMapper.selectSysdate();
			user.id = dbMapper.insertUser(user);
			
		}else { //UPDATE
			user.modifyby = currentUser.id;
			user.modifydate = dbMapper.selectSysdate();
			dbMapper.updateUser(user);
		}

		return messageSource.getMessage("SaveSuccess", new String[]{"Master User", user.username},null);
	}

	public GroupUser selectGroupUser(Integer id) {
		GroupUser gu = dbMapper.selectGroupUser(id);
		gu.setListHakAkses(dbMapper.selectListHakAkses(id));
		return gu;
	}
	
	public List<GroupUser> selectListGroupUser( String search, Integer offset, Integer rowcount) {
		return dbMapper.selectListGroupUser(search, offset, rowcount);
	}
	
	public Integer selectListGroupUserCount(String search) {
		return dbMapper.selectListGroupUserCount(search);
	}
	
	public String saveGroupUser(GroupUser groupUser, User currentUser){
		logger.debug("saveUser(User user, User currentUser)");

		//Group User
		if(groupUser.id == null){ //INSERT
			dbMapper.insertGroupUser(groupUser);
		}else { //UPDATE
			//do nothing
		}

		//Hak Akses
		dbMapper.deleteHakAkses(groupUser.id);
		for(HakAkses ha : groupUser.getListHakAkses()){
			ha.setGroup_user_id(groupUser.id);
			dbMapper.insertHakAkses(ha);
		}
		
		return messageSource.getMessage("SaveSuccess", new String[]{"Hak Akses", groupUser.nama},null);
	}
	
	public List<HakAkses> selectListHakAkses(Integer id) {
		return dbMapper.selectListHakAkses(id);
	}

	public Supir selectSupir(Integer id) {
		return dbMapper.selectSupir(id);
	}
	
	public List<Supir> selectListSupir( String search, Integer offset, Integer rowcount) {
		return dbMapper.selectListSupir(search, offset, rowcount);
	}
	
	public Integer selectListSupirCount(String search) {
		return dbMapper.selectListSupirCount(search);
	}
	
	public Integer selectNamaSupirCount(String search) {
		return dbMapper.selectNamaSupirCount(search);
	}
	
	public String saveSupir(Supir supir, User currentUser){
		logger.debug("saveSupir(Supir supir, User currentUser)");
		
		if(supir.id == null){ //INSERT
			supir.createby = currentUser.id;
			supir.createdate = dbMapper.selectSysdate();
			supir.id = dbMapper.insertSupir(supir);
			
		}else { //UPDATE
			supir.modifyby = currentUser.id;
			supir.modifydate = dbMapper.selectSysdate();
			dbMapper.updateSupir(supir);
		}

		return messageSource.getMessage("SaveSuccess", new String[]{"Master Supir", supir.nama},null);
	}

	public Customer selectCustomer (Integer id) {
		return dbMapper.selectCustomer(id);
	}
	
	public List<Customer> selectListCustomer( String search, Integer offset, Integer rowcount) {
		return dbMapper.selectListCustomer(search, offset, rowcount);
	}
	
	public List<Customer> selectListPengirim( String search, Integer offset, Integer rowcount) {
		return dbMapper.selectListPengirim(search, offset, rowcount);
	}
	
	public List<Customer> selectListPenerima( String search, Integer offset, Integer rowcount) {
		return dbMapper.selectListPenerima(search, offset, rowcount);
	}
	
	public Integer selectListCustomerCount(String search) {
		return dbMapper.selectListCustomerCount(search);
	}
	
	public String saveCustomer(Customer customer, User currentUser){
		logger.debug("saveCustomer(Customer customer, User currentUser)");
		
		if(customer.id == null){ //INSERT
			customer.createby = currentUser.id;
			customer.createdate = dbMapper.selectSysdate();
			dbMapper.insertCustomer(customer);
			
		}else { //UPDATE
			customer.modifyby = currentUser.id;
			customer.modifydate = dbMapper.selectSysdate();
			dbMapper.updateCustomer(customer);
		}

		return messageSource.getMessage("SaveSuccess", new String[]{"Master Customer", customer.nama},null);
	}

	public Barang selectBarang(Integer id) {
		return dbMapper.selectBarang(id);
	}
	
	public List<Barang> selectListBarang( String search, Integer offset, Integer rowcount) {
		return dbMapper.selectListBarang(search, offset, rowcount);
	}
	
	public Integer selectListBarangCount(String search) {
		return dbMapper.selectListBarangCount(search);
	}
	
	public Integer selectNamaBarangCount(String search) {
		return dbMapper.selectNamaBarangCount(search);
	}
	
	public String saveBarang(Barang barang, User currentUser){
		logger.debug("saveBarang(Barang barang, User currentUser)");
		
		if(barang.id == null){ //INSERT
			barang.createby = currentUser.id;
			barang.createdate = dbMapper.selectSysdate();
			barang.id = dbMapper.insertBarang(barang);
			
		}else { //UPDATE
			barang.modifyby = currentUser.id;
			barang.modifydate = dbMapper.selectSysdate();
			dbMapper.updateBarang(barang);
		}

		return messageSource.getMessage("SaveSuccess", new String[]{"Master Barang", barang.nama},null);
	}

	public Cabang selectCabang(Integer id) {
		return dbMapper.selectCabang(id);
	}
	
	public List<Cabang> selectListCabang( String search, Integer offset, Integer rowcount) {
		return dbMapper.selectListCabang(search, offset, rowcount);
	}
	
	public Integer selectListCabangCount(String search) {
		return dbMapper.selectListCabangCount(search);
	}
	
	public Integer selectNamaCabangCount(String search) {
		return dbMapper.selectNamaCabangCount(search);
	}
	
	public String saveCabang(Cabang cabang, User currentUser){
		logger.debug("saveCabang(Cabang cabang, User currentUser)");
		
		if(cabang.id == null){ //INSERT
			cabang.createby = currentUser.id;
			cabang.createdate = dbMapper.selectSysdate();
			cabang.id = dbMapper.insertCabang(cabang);
			
		}else { //UPDATE
			cabang.modifyby = currentUser.id;
			cabang.modifydate = dbMapper.selectSysdate();
			dbMapper.updateCabang(cabang);
		}

		return messageSource.getMessage("SaveSuccess", new String[]{"Master Cabang", cabang.nama},null);
	}

	public Mobil selectMobil(String no_polisi) {
		return dbMapper.selectMobil(no_polisi);
	}
	
	public List<Mobil> selectListMobil( String search, Integer offset, Integer rowcount) {
		return dbMapper.selectListMobil(search, offset, rowcount);
	}
	
	public Integer selectListMobilCount(String search) {
		return dbMapper.selectListMobilCount(search);
	}
	
	public Integer selectNamaMobilCount(String search) {
		return dbMapper.selectNamaMobilCount(search);
	}
	
	public String saveMobil(Mobil mobil, User currentUser){
		logger.debug("saveMobil(Mobil mobil, User currentUser)");
		
		if(mobil.mode.equals("NEW")){ //INSERT
			mobil.createby = currentUser.id;
			mobil.createdate = dbMapper.selectSysdate();
			dbMapper.insertMobil(mobil);
			
		}else { //UPDATE
			mobil.modifyby = currentUser.id;
			mobil.modifydate = dbMapper.selectSysdate();
			dbMapper.updateMobil(mobil);
		}

		return messageSource.getMessage("SaveSuccess", new String[]{"Master Mobil", mobil.no_polisi},null);
	}

	public Kapal selectKapal(String kode) {
		return dbMapper.selectKapal(kode);
	}
	
	public List<Kapal> selectListKapal( String search, Integer offset, Integer rowcount) {
		return dbMapper.selectListKapal(search, offset, rowcount);
	}
	
	public Integer selectListKapalCount(String search) {
		return dbMapper.selectListKapalCount(search);
	}
	
	public Integer selectNamaKapalCount(String search) {
		return dbMapper.selectNamaKapalCount(search);
	}
	
	public String saveKapal(Kapal kapal, User currentUser){
		logger.debug("saveKapal(Kapal kapal, User currentUser)");
		
		if(kapal.mode.equals("NEW")){ //INSERT
			kapal.createby = currentUser.id;
			kapal.createdate = dbMapper.selectSysdate();
			dbMapper.insertKapal(kapal);
			
		}else { //UPDATE
			kapal.modifyby = currentUser.id;
			kapal.modifydate = dbMapper.selectSysdate();
			dbMapper.updateKapal(kapal);
		}

		return messageSource.getMessage("SaveSuccess", new String[]{"Master Kapal", kapal.nama},null);
	}

	public Harga selectHarga(Integer customer_id, Integer barang_id) {
		return dbMapper.selectHarga(customer_id, barang_id);
	}
	
	public List<Harga> selectListHarga( String search, Integer offset, Integer rowcount) {
		return dbMapper.selectListHarga(search, offset, rowcount);
	}
	
	public Integer selectListHargaCount(String search) {
		return dbMapper.selectListHargaCount(search);
	}
	
	public Integer selectNamaHargaCount(Integer customer_id, Integer barang_id) {
		return dbMapper.selectNamaHargaCount(customer_id, barang_id);
	}
	
	public String saveHarga(Harga harga, User currentUser){
		logger.debug("saveHarga(Harga harga, User currentUser)");
		
		if(harga.mode.equals("NEW")){ //INSERT
			harga.createby = currentUser.id;
			harga.createdate = dbMapper.selectSysdate();
			dbMapper.insertHarga(harga);
			
		}else { //UPDATE
			harga.modifyby = currentUser.id;
			harga.modifydate = dbMapper.selectSysdate();
			dbMapper.updateHarga(harga);
		}

		return "Harga berhasil disimpan";
	}

	public Trans selectTrans(Integer id) {
		return dbMapper.selectTrans(id);
	}
	
	public List<Trans> selectListTrans( String search, Date periodFrom, Date periodTo, Integer offset, Integer rowcount) {
		return dbMapper.selectListTrans(search, periodFrom, periodTo, offset, rowcount);
	}
	
	public Integer selectListTransCount(String search, Date periodFrom, Date periodTo) {
		return dbMapper.selectListTransCount(search, periodFrom, periodTo);
	}
	
	public List<TransDet> selectListTransDet(Integer trans_id){
		return dbMapper.selectListTransDet(trans_id);
	}
	
	public String saveSTT(Trans stt, User currentUser){
		logger.debug("saveSTT(Trans stt, User currentUser)");
		Date sysdate = dbMapper.selectSysdate();
		
		int cancel = stt.cancel == null ? 0 : stt.cancel;
		String message = "SaveSuccess";

		//CANCEL
		if(cancel == 1){
			message = "CancelSuccess";
			stt.cancelby = currentUser.id;
			stt.canceldate = sysdate;
			dbMapper.updateTrans(stt);

		}else if(stt.id == null){ //INSERT
			stt.createby = currentUser.id;
			stt.createdate = sysdate;
			dbMapper.insertTrans(stt);
			
		}else { //UPDATE
			stt.modifyby = currentUser.id;
			stt.modifydate = sysdate;
			dbMapper.updateTrans(stt);
		}

		//insert/update mst_trans_det
		//int urut = stt.urut;
		if(cancel == 0){ //prosedur ini hanya dijalankan bila bukan pembatalan
			for(TransDet det : stt.listTransDet){

				//bila baris det ini ditandakan untuk dihapus
				if(det.flag_delete){
					det.trans_id = stt.id;
					dbMapper.deleteTransDet(det);
					
				//simpan baris det
				}else{
					//set id barang (bila ada) untuk disimpan di trans_det
					det.barang_id = dbMapper.selectIdFromNamaBarang(det.nama_barang);

					det.trans_id = stt.id;
					//det.urut = ++urut;
					if(dbMapper.updateTransDet(det) == 0) dbMapper.insertTransDet(det);

					//bila flag "Nama Barang" dicentang, maka data disimpan
					if(det.isFlag()){
						if(det.barang_id == null) { //bila belum ada, baru disimpan
							Barang barang = new Barang(det.nama_barang, 1, currentUser.id, sysdate);
							dbMapper.insertBarang(barang);
							
							det.barang_id = barang.id;
							
							Harga harga = new Harga(stt.customer_id, barang.id, det.harga, 1, currentUser.id, sysdate);
							dbMapper.insertHarga(harga);
						}
					}					
				}
				
			}
		}
		
		//tarik no STT yang terakhir di insert/update
		stt.no_stt = dbMapper.selectNoSTTFromIdSTT(stt.id);

		return messageSource.getMessage(message, new String[]{"STT", stt.no_stt},null);
	}
	
	public Menu selectMenu(Integer id) {
		return dbMapper.selectMenu(id);
	}
	
	public List<Menu> selectListMenu(String search,Integer offset,Integer rowcount) {
		return dbMapper.selectListMenu(search, offset, rowcount);
	}
	
	public Integer selectListMenuCount(String search) {
		return dbMapper.selectListMenuCount(search);
	}
	
	public String saveMenu(Menu menu, User currentUser){
		logger.debug("saveMenu(Menu menu, User currentUser)");

		String pesan;

		if("NEW".equals(menu.mode)){
			menu.active=1;
			menu.createby = currentUser.id;
			menu.createdate = dbMapper.selectSysdate();
			menu.level = selectMenu(menu.parent).level+1;
			menu.id=dbMapper.insertMenu(menu);
			pesan = messageSource.getMessage("submitsuccess", new String[]{"Master Menu",""+menu.nama,"ditambah"},null);
		}else if("DELETE".equals(menu.mode)){
			menu.active=0;
			menu.modifyby = currentUser.id;
			menu.modifydate = dbMapper.selectSysdate();
			dbMapper.updateMenu(menu);
			pesan = messageSource.getMessage("submitsuccess", new String[]{"Master Menu",""+menu.nama,"dihapus"},null);
		}else  if("EDIT".equals(menu.mode)){
			menu.modifyby = currentUser.id;
			menu.modifydate = dbMapper.selectSysdate();
			menu.level=selectMenu(menu.parent).level+1;
			dbMapper.updateMenu(menu);
			pesan = messageSource.getMessage("submitsuccess", new String[]{"Master Menu",""+menu.nama,"diubah"},null);
		}else{
			throw new RuntimeException ("WARNING !! Metode Save tidak ditemukan untuk Mode "+menu.mode);
		}


		return pesan;
	}

	public List<DropDown> selectListHargaPerCustomer(String nama, Integer customer_id) {
		return dbMapper.selectListHargaPerCustomer(nama, customer_id);
	}
	
	public Delivery selectDelivery(Integer id) {
		return dbMapper.selectDelivery(id);
	}
	
	public List<Delivery> selectListDelivery( String search, Date periodFrom, Date periodTo, Integer offset, Integer rowcount) {
		return dbMapper.selectListDelivery(search, periodFrom, periodTo, offset, rowcount);
	}
	
	public Integer selectListDeliveryCount(String search, Date periodFrom, Date periodTo) {
		return dbMapper.selectListDeliveryCount(search, periodFrom, periodTo);
	}
	
	public List<DeliveryDet> selectListDeliveryDet(Integer delivery_id){
		return dbMapper.selectListDeliveryDet(delivery_id);
	}
	
	public String saveSP(Delivery sp, User currentUser){
		logger.debug("saveSP(Delivery sp, User currentUser)");
		Date sysdate = dbMapper.selectSysdate();
		
		int cancel = sp.cancel == null ? 0 : sp.cancel;
		String message = "SaveSuccess";

		//CANCEL
		if(cancel == 1){
			message = "CancelSuccess";
			sp.cancelby = currentUser.id;
			sp.canceldate = sysdate;
			dbMapper.updateDelivery(sp);
		
		//insert/update mst_delivery
		}else if(sp.id == null){ //INSERT
			sp.createby = currentUser.id;
			sp.createdate = sysdate;
			dbMapper.insertDelivery(sp);
			
		}else { //UPDATE
			sp.modifyby = currentUser.id;
			sp.modifydate = sysdate;
			dbMapper.updateDelivery(sp);
		}

		//insert/update mst_delivery_det dan mst_trans_det untuk update saldo colly nya
		for(DeliveryDet det : sp.listDeliveryDet){
			det.delivery_id = sp.id;
			
			//bila SP/RBT dibatalkan, maka colly naik di reset menjadi nol, sehingga perhitungan dibawah otomatis mengembalikan nilai colly
			if(cancel == 1) det.colly_naik = 0.;
			
			//untuk amannya, setiap kali ada colly naik, hitung ulang dari seluruh colly naik dari barang tersebut
			double total_colly = 0;
			double total_colly_naik = 0;
			if(det.trans_id != null){
				total_colly = dbMapper.selectTotalColly(det.trans_id, det.trans_urut);
				total_colly_naik = dbMapper.selectTotalCollyNaik(det.delivery_id, det.trans_id, det.trans_urut);
			}
			
			//bila baris det ini ditandakan untuk dihapus
			if(det.flag_delete){
				det.colly_naik = 0.; //colly naik di nolkan, untuk perhitungan dibawah
				det.colly_sisa = total_colly - (total_colly_naik + det.colly_naik);
				dbMapper.deleteDeliveryDet(det);
				
			//simpan baris det, sekaligus perhitungan2
			}else{
				det.colly_sisa = total_colly - (total_colly_naik + det.colly_naik);
				if(dbMapper.updateDeliveryDet(det) == 0) dbMapper.insertDeliveryDet(det);
			}
			
			//update saldo colly remain
			TransDet td = new TransDet();
			td.trans_id = det.trans_id;
			td.urut = det.trans_urut;
			td.colly_remain = det.colly_sisa;
			dbMapper.updateTransDet(td);				
		}
		
		//insert/update mst_delivery_cost
		
		dbMapper.deleteDeliveryCost(sp.id); //hapus dulu semuanya
		
		for(DeliveryCost det : sp.listDeliveryCost){
			
			//bila baris det ini ditandakan untuk dihapus, jgn disimpan datanya
			if(!det.flag_delete){
				det.delivery_id = sp.id;
				dbMapper.insertDeliveryCost(det);
				
				//bila biaya tidak ada, insert ke masternya
				if(det.biaya_id == null) {
					Biaya biaya = new Biaya();
					biaya.nama = det.nama_biaya;
					biaya.nominal = det.nominal;
					biaya.active = 1;
					biaya.createby = currentUser.id;
					biaya.createdate = sysdate;
					dbMapper.insertBiaya(biaya);
				}
			}
		}
		
		return messageSource.getMessage(message, new String[]{"SP", sp.id.toString()},null);
	}
	
	public String saveCheckingSP(Delivery sp, User currentUser){
		logger.debug("saveSP(Delivery sp, User currentUser)");
		Date sysdate = dbMapper.selectSysdate();
		
		//update mst_delivery (tgl sampai)
		sp.modifyby = currentUser.id;
		sp.modifydate = sysdate;
		dbMapper.updateDelivery(sp);

		//update mst_delivery_det (cek, colly sampai, dan catatan penerimaan)
		for(DeliveryDet det : sp.listDeliveryDet){
			det.delivery_id = sp.id;
			dbMapper.updateDeliveryDet(det);
		}
		
		return messageSource.getMessage("SaveSuccess", new String[]{"Checking Penerimaan Barang", sp.id.toString()},null);
	}
	
	public List<TransDet> selectListItemDalamSTT(String no_stt){
		return dbMapper.selectListItemDalamSTT(no_stt);
	}

	public Biaya selectBiaya(Integer id) {
		return dbMapper.selectBiaya(id);
	}
	
	public List<Biaya> selectListBiaya( String search, Integer offset, Integer rowcount) {
		return dbMapper.selectListBiaya(search, offset, rowcount);
	}
	
	public Integer selectListBiayaCount(String search) {
		return dbMapper.selectListBiayaCount(search);
	}
	
	public Integer selectNamaBiayaCount(String search) {
		return dbMapper.selectNamaBiayaCount(search);
	}
	
	public String saveBiaya(Biaya biaya, User currentUser){
		logger.debug("saveBiaya(Biaya biaya, User currentUser)");
		
		if(biaya.id == null){ //INSERT
			biaya.createby = currentUser.id;
			biaya.createdate = dbMapper.selectSysdate();
			biaya.id = dbMapper.insertBiaya(biaya);
			
		}else { //UPDATE
			biaya.modifyby = currentUser.id;
			biaya.modifydate = dbMapper.selectSysdate();
			dbMapper.updateBiaya(biaya);
		}

		return messageSource.getMessage("SaveSuccess", new String[]{"Master Biaya", biaya.nama},null);
	}

	public List<Biaya> selectListBiayaByNama(String nama) {
		return dbMapper.selectListBiayaByNama(nama);
	}
	
	public List<DeliveryCost> selectListDeliveryCost(@Param("delivery_id") Integer delivery_id) {
		return dbMapper.selectListDeliveryCost(delivery_id);	
	}
	
	public Double selectTotalColly(Integer trans_id, Integer trans_urut){
		return dbMapper.selectTotalColly(trans_id, trans_urut);
	}
	
	public Double selectTotalCollyNaik(Integer delivery_id, Integer trans_id, Integer trans_urut){
		return dbMapper.selectTotalCollyNaik(delivery_id, trans_id, trans_urut);
	}

	public Payment selectPayment(Integer payment_id) {
		return dbMapper.selectPayment(payment_id);
	}
	
	public List<Payment> selectListPayment(String search, Date periodFrom, Date periodTo, Integer offset, Integer rowcount){
		return dbMapper.selectListPayment(search, periodFrom, periodTo, offset, rowcount);
	}
	
	public Integer selectListPaymentCount(String search, Date periodFrom, Date periodTo) {
		return dbMapper.selectListPaymentCount(search, periodFrom, periodTo);
	}
	
	public List<Payment> selectListPaymentSTT(String search, Date periodFrom, Date periodTo, Integer offset, Integer rowcount){
		return dbMapper.selectListPaymentSTT(search, periodFrom, periodTo, offset, rowcount);
	}
	
	public Integer selectListPaymentSTTCount(String search, Date periodFrom, Date periodTo) {
		return dbMapper.selectListPaymentSTTCount(search, periodFrom, periodTo);
	}
	
	public String savePayment(Payment payment, User currentUser){
		logger.debug("savePayment(Payment payment, User currentUser)");
		
		Date sysdate = dbMapper.selectSysdate();

		int cancel = payment.cancel == null ? 0 : payment.cancel;
		String message = "SaveSuccess";
		
		//CANCEL
		if(cancel == 1){
			message = "CancelSuccess";
			payment.cancelby = currentUser.id;
			payment.canceldate = sysdate;
			dbMapper.updatePayment(payment);
			
		//INSERT
		}else if(payment.payment_id == null){
			payment.createby = currentUser.id;
			payment.createdate = sysdate;
			dbMapper.insertPayment(payment);
			
		//UPDATE
		}else { 
			payment.modifyby = currentUser.id;
			payment.modifydate = sysdate;
			dbMapper.updatePayment(payment);
		}

		//bila merupakan pembayaran terhadap STT tertentu (termasuk juga pembatalan payment), 
		//maka update saldo remain di mst_trans (dengan menghitung total langsung dari DB dari yg status cancel=0)
		if(payment.trans_id != null){
			double remain = dbMapper.selectRemainingPayment(payment.trans_id);
			Trans trans = new Trans();
			trans.id = payment.trans_id;
			trans.remain = remain;
			trans.modifyby = currentUser.id;
			trans.modifydate = sysdate;
			dbMapper.updateTrans(trans);
		}
		
		return messageSource.getMessage(message, new String[]{"Payment", payment.payment_id.toString()},null);
	}
	
	public List<Trans> selectListSTTForPayment(String no_stt) {
		return dbMapper.selectListSTTForPayment(no_stt);
	}
	
	public List<Customer> selectListOutstandingCustomer(String search, Integer offset, Integer rowcount){
		return dbMapper.selectListOutstandingCustomer(search, offset, rowcount);
	}

	public Integer selectListOutstandingCustomerCount(String search){
		return dbMapper.selectListOutstandingCustomerCount(search);
	}

	public Integer selectCekLimitHutangCustomer(Integer customer_id){
		return dbMapper.selectCekLimitHutangCustomer(customer_id);
	}
	
	public Integer selectValidasiSTTSudahNaikRBT(Integer trans_id){
		return dbMapper.selectValidasiSTTSudahNaikRBT(trans_id);
	}
	
	public Integer selectValidasiItemSTTSudahNaikRBT(TransDet det){
		return dbMapper.selectValidasiItemSTTSudahNaikRBT(det);
	}
	
	public String uploadFile(Upload upload) throws IOException{
		CommonsMultipartFile file = upload.getFile();
        ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes());
        Workbook workbook;
        
        if(file.isEmpty()){
            return "Upload failed (empty)";
        }else if (file.getOriginalFilename().endsWith("xls")) {
            workbook = new HSSFWorkbook(bis);
        }else if (file.getOriginalFilename().endsWith("xlsx")) {
            workbook = new XSSFWorkbook(bis);
        }else {
            throw new IllegalArgumentException("Received file does not have a standard excel extension.");
        }

        for (Row row : workbook.getSheetAt(0)) {
        	if(row.getRowNum() < 10) {
        		System.out.println("Row " + row.getRowNum() + " = " + row.getCell(0).getStringCellValue());
        	}
        	/*
			if (row.getRowNum() == 0) {
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					System.out.println(cell.getStringCellValue());
				}
			}
			*/
        }
        
        workbook.close();
        
        return "Upload Success";

        /*
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
        */

	}
	
}
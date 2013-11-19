package com.sma.persistence;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

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
import com.sma.model.User;

/**
 * MyBatis Mapper, sebagai pengganti DAO. Sudah tidak perlu dibuat class implement lagi (cukup interfacenya saja)
 * 
 * @author Yusuf
 * @since Jun 20, 2013 (9:40:15 AM)
 *
 */
public interface DbMapper {

	public Date selectSysdate() throws DataAccessException;
	public Integer selectTestDB() throws DataAccessException;
	public Integer updateTestDB(Integer toggle) throws DataAccessException;

	public List<DropDown> selectDropDown(
			@Param("key") String key,
			@Param("value") String value,
			@Param("table") String table,
			@Param("where") String where,
			@Param("order") String order) throws DataAccessException;
	
	public List<Menu> selectMenuAkses(@Param("group_user_id") Integer group_user_id) throws DataAccessException;
	
	public User selectUser(
			@Param("id") Integer id,
			@Param("username") String username,
			@Param("password") String password) throws DataAccessException;
	public Integer insertUser(User user) throws DataAccessException;
	public Integer updateUser(User user) throws DataAccessException;
	public List<User> selectListUser(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListUserCount(
			@Param("search") String search) throws DataAccessException;

	public GroupUser selectGroupUser(@Param("id") Integer id) throws DataAccessException;
	public Integer insertGroupUser(GroupUser groupUser) throws DataAccessException;
	public List<GroupUser> selectListGroupUser(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListGroupUserCount(
			@Param("search") String search) throws DataAccessException;

	public List<HakAkses> selectListHakAkses(@Param("id") Integer id) throws DataAccessException;	
	public Integer insertHakAkses(HakAkses hakAkses) throws DataAccessException;
	public Integer deleteHakAkses(Integer group_user_id) throws DataAccessException;
	
	public Supir selectSupir(@Param("id") Integer id) throws DataAccessException;
	public Integer insertSupir(Supir supir) throws DataAccessException;
	public Integer updateSupir(Supir supir) throws DataAccessException;
	public List<Supir> selectListSupir(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListSupirCount(
			@Param("search") String search) throws DataAccessException;
	public Integer selectNamaSupirCount(
			@Param("search") String search) throws DataAccessException;
	
	public Customer selectCustomer(@Param("id") Integer id) throws DataAccessException;
	public Integer insertCustomer(Customer customer) throws DataAccessException;
	public Integer updateCustomer(Customer customer) throws DataAccessException;
	public List<Customer> selectListCustomer(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public List<Customer> selectListPengirim(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public List<Customer> selectListPenerima(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListCustomerCount(
			@Param("search") String search) throws DataAccessException;	
	
	public Barang selectBarang(@Param("id") Integer id) throws DataAccessException;
	public Integer insertBarang(Barang barang) throws DataAccessException;
	public Integer updateBarang(Barang barang) throws DataAccessException;
	public List<Barang> selectListBarang(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListBarangCount(
			@Param("search") String search) throws DataAccessException;
	public Integer selectNamaBarangCount(
			@Param("search") String search) throws DataAccessException;
	
	public Cabang selectCabang(@Param("id") Integer id) throws DataAccessException;
	public Integer insertCabang(Cabang cabang) throws DataAccessException;
	public Integer updateCabang(Cabang cabang) throws DataAccessException;
	public List<Cabang> selectListCabang(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListCabangCount(
			@Param("search") String search) throws DataAccessException;
	public Integer selectNamaCabangCount(
			@Param("search") String search) throws DataAccessException;
	
	public Mobil selectMobil(@Param("no_polisi") String no_polisi) throws DataAccessException;
	public Integer insertMobil(Mobil mobil) throws DataAccessException;
	public Integer updateMobil(Mobil mobil) throws DataAccessException;
	public List<Mobil> selectListMobil(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListMobilCount(
			@Param("search") String search) throws DataAccessException;
	public Integer selectNamaMobilCount(
			@Param("search") String search) throws DataAccessException;
	
	public Kapal selectKapal(@Param("kode") String kode) throws DataAccessException;
	public Integer insertKapal(Kapal kapal) throws DataAccessException;
	public Integer updateKapal(Kapal kapal) throws DataAccessException;
	public List<Kapal> selectListKapal(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListKapalCount(
			@Param("search") String search) throws DataAccessException;
	public Integer selectNamaKapalCount(
			@Param("search") String search) throws DataAccessException;
	
	public Harga selectHarga(@Param("customer_id") Integer customer_id, @Param("barang_id") Integer barang_id) throws DataAccessException;
	public Integer insertHarga(Harga harga) throws DataAccessException;
	public Integer updateHarga(Harga harga) throws DataAccessException;
	public List<Harga> selectListHarga(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListHargaCount(
			@Param("search") String search) throws DataAccessException;
	public Integer selectNamaHargaCount(
			@Param("customer_id") Integer customer_id,
			@Param("barang_id") Integer barang_id) throws DataAccessException;
	
	public Trans selectTrans(@Param("id") Integer id) throws DataAccessException;
	public Integer insertTrans(Trans stt) throws DataAccessException;
	public Integer updateTrans(Trans stt) throws DataAccessException;
	public List<Trans> selectListTrans(
			@Param("search") String search,
			@Param("periodFrom") Date periodFrom,
			@Param("periodTo") Date periodTo,
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListTransCount(
			@Param("search") String search,
			@Param("periodFrom") Date periodFrom,
			@Param("periodTo") Date periodTo) throws DataAccessException;

	public Integer insertMenu(Menu menu) throws DataAccessException;
	public void updateMenu(Menu menu) throws DataAccessException;
	public Menu selectMenu(Integer id) throws DataAccessException;
	public List<Menu> selectListMenu(
			@Param("search") String search,
			@Param("offset") Integer offset, @Param("rowcount") Integer rowcount) throws DataAccessException;
	public Integer selectListMenuCount(String search) throws DataAccessException;
	
	public List<DropDown> selectListHargaPerCustomer(
			@Param("nama") String nama,
			@Param("customer_id") Integer customer_id) throws DataAccessException;
	
	public List<TransDet> selectListTransDet(@Param("trans_id") Integer trans_id) throws DataAccessException;	
	public Integer insertTransDet(TransDet det) throws DataAccessException;
	public Integer updateTransDet(TransDet det) throws DataAccessException;
	public Integer deleteTransDet(TransDet det) throws DataAccessException;
	
	public Integer selectIdFromNamaBarang(String nama) throws DataAccessException;
	public String selectNoSTTFromIdSTT(Integer id) throws DataAccessException;
	
	public Delivery selectDelivery(@Param("id") Integer id) throws DataAccessException;
	public Integer insertDelivery(Delivery sp) throws DataAccessException;
	public Integer updateDelivery(Delivery sp) throws DataAccessException;
	public List<Delivery> selectListDelivery(
			@Param("search") String search,  
			@Param("periodFrom") Date periodFrom,
			@Param("periodTo") Date periodTo,
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListDeliveryCount(
			@Param("search") String search,
			@Param("periodFrom") Date periodFrom,
			@Param("periodTo") Date periodTo) throws DataAccessException;
	
	public List<DeliveryDet> selectListDeliveryDet(@Param("delivery_id") Integer delivery_id) throws DataAccessException;	
	public Integer insertDeliveryDet(DeliveryDet det) throws DataAccessException;
	public Integer updateDeliveryDet(DeliveryDet det) throws DataAccessException;
	public Integer deleteDeliveryDet(DeliveryDet det) throws DataAccessException;
	
	public List<TransDet> selectListItemDalamSTT(String no_stt) throws DataAccessException;
	
	public Biaya selectBiaya(@Param("id") Integer id) throws DataAccessException;
	public Integer insertBiaya(Biaya biaya) throws DataAccessException;
	public Integer updateBiaya(Biaya biaya) throws DataAccessException;
	public List<Biaya> selectListBiaya(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListBiayaCount(
			@Param("search") String search) throws DataAccessException;
	public Integer selectNamaBiayaCount(
			@Param("search") String search) throws DataAccessException;
	
	public List<Biaya> selectListBiayaByNama(String nama) throws DataAccessException;

	public List<DeliveryCost> selectListDeliveryCost(@Param("delivery_id") Integer delivery_id) throws DataAccessException;	
	
	public Integer insertDeliveryCost(DeliveryCost deliveryCost) throws DataAccessException;
	public Integer deleteDeliveryCost(Integer delivery_id) throws DataAccessException;
	
	public Double selectTotalColly(
			@Param("trans_id") Integer trans_id, 
			@Param("trans_urut") Integer trans_urut) throws DataAccessException;
	public Double selectTotalCollyNaik(
			@Param("delivery_id") Integer delivery_id,
			@Param("trans_id") Integer trans_id, 
			@Param("trans_urut") Integer trans_urut) throws DataAccessException;
	
	public Payment selectPayment(@Param("payment_id") Integer payment_id) throws DataAccessException;
	public Integer insertPayment(Payment payment) throws DataAccessException;
	public Integer updatePayment(Payment payment) throws DataAccessException;
	public List<Payment> selectListPayment(
			@Param("search") String search,  
			@Param("periodFrom") Date periodFrom,
			@Param("periodTo") Date periodTo,
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListPaymentCount(
			@Param("search") String search,
			@Param("periodFrom") Date periodFrom,
			@Param("periodTo") Date periodTo) throws DataAccessException;

	public List<Payment> selectListPaymentSTT(
			@Param("search") String search,  
			@Param("periodFrom") Date periodFrom,
			@Param("periodTo") Date periodTo,
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListPaymentSTTCount(
			@Param("search") String search,
			@Param("periodFrom") Date periodFrom,
			@Param("periodTo") Date periodTo) throws DataAccessException;
	
	public List<Trans> selectListSTTForPayment(String no_stt) throws DataAccessException;
	
	public Double selectRemainingPayment(Integer trans_id) throws DataAccessException;
	
	public List<Customer> selectListOutstandingCustomer(
			@Param("search") String search,  
			@Param("offset") Integer offset, 
			@Param("rowcount") Integer rowcount) throws DataAccessException;	
	public Integer selectListOutstandingCustomerCount(
			@Param("search") String search) throws DataAccessException;
	
	public Integer selectCekLimitHutangCustomer(Integer customer_id) throws DataAccessException;
	
	public Integer selectValidasiSTTSudahNaikRBT(Integer trans_id) throws DataAccessException;
	public Integer selectValidasiItemSTTSudahNaikRBT(TransDet det) throws DataAccessException;
	
}
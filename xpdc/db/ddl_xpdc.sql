-- Table structure for table lst_barang
CREATE TABLE IF NOT EXISTS lst_barang (
  id int(11) NOT NULL AUTO_INCREMENT,
  nama varchar(100) DEFAULT NULL,
  active tinyint(1) DEFAULT '1',
  createby int(11) DEFAULT NULL,
  createdate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Dumping data for table lst_barang
INSERT INTO lst_barang (id, nama, active, createby, createdate, modifyby, modifydate) VALUES
(1, 'Mamy Poko NB', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-19 08:42:05'),
(2, 'Mamy Poko S', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-19 08:42:05'),
(3, 'Mamy Poko M', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-19 08:42:05'),
(4, 'Mamy Poko L', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-19 08:42:05'),
(5, 'Mamy Poko XL', 0, 1, '2013-07-11 08:48:40', 2, '2013-07-19 08:42:05'),
(6, 'Mamy Poko XXL', 0, 1, '2013-07-11 08:48:40', 2, '2013-07-19 08:42:05'),
(7, 'Pampers S', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-19 08:42:05'),
(8, 'Pampers M', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-19 08:42:05'),
(9, 'Pampers L', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-19 08:42:05'),
(10, 'Huggies S', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-19 08:42:05'),
(11, 'Huggies M', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-19 08:42:05'),
(12, 'Huggies L', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-19 08:42:05');
commit;

-- Table structure for table lst_biaya
CREATE TABLE IF NOT EXISTS lst_biaya (
  id int(11) NOT NULL AUTO_INCREMENT,
  nama varchar(100) DEFAULT NULL,
  nominal decimal(14,2) DEFAULT '0.00',
  active tinyint(1) DEFAULT '1',
  createby int(11) DEFAULT NULL,
  createdate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Dumping data for table lst_biaya
INSERT INTO lst_biaya (id, nama, nominal, active, createby, createdate, modifyby, modifydate) VALUES
(1, 'Transport (Mobil Luar)', '3000000.00', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(2, 'Transport (Mobil Pribadi - Besar)', '700000.00', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(3, 'Transport (Mobil Pribadi - Kecil)', '650000.00', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(4, 'Gayor (Mobil Luar)', '750000.00', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(5, 'Gayor (Mobil Pribadi)', '100000.00', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(6, 'Jemput Barang', '200000.00', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(7, 'Trip (Supir)', '500000.00', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40');
commit;

-- Table structure for table lst_cabang
CREATE TABLE IF NOT EXISTS lst_cabang (
  id int(11) NOT NULL AUTO_INCREMENT,
  kode varchar(2) DEFAULT NULL COMMENT 'untuk kode apabila ada, contoh : TB',
  nama varchar(30) DEFAULT NULL,
  active tinyint(1) DEFAULT '1',
  createby int(11) DEFAULT NULL,
  createdate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Dumping data for table lst_cabang
INSERT INTO lst_cabang (id, kode, nama, active, createby, createdate, modifyby, modifydate) VALUES
(1, 'BG', 'Bangka', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(2, 'JK', 'Jakarta', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40');
commit;

-- Table structure for table lst_config
CREATE TABLE IF NOT EXISTS lst_config (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '1 : pay mode di mst_trans\n2 : cara bayar di mst_payment\n3 : satuan barang\n4 : jenis mobil\n5 : jenis kapal',
  jenis int(11) NOT NULL COMMENT 'nilai yg terkandung dari masing2 id',
  keterangan varchar(100) DEFAULT NULL,
  active tinyint(1) DEFAULT NULL,
  createby int(11) DEFAULT NULL,
  createdate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (id,jenis)
);

-- Dumping data for table lst_config
INSERT INTO lst_config (id, jenis, keterangan, active, createby, createdate, modifyby, modifydate) VALUES
(1, 1, 'Cash', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(1, 2, 'Bayar JKT', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(1, 3, 'Bayar PGK', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(2, 1, 'Cash', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(2, 2, 'Transfer', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(2, 3, 'Giro', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(3, 1, 'Kg', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(3, 2, 'Meter Kubik', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(4, 1, 'Fuso', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(4, 2, 'CD6', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(4, 3, 'Engkel 4 Ban', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(5, 1, 'Conventional Liner Vesell', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(5, 2, 'Semi Container Vesell', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40'),
(5, 3, 'Full Container Vesell', 1, 1, '2013-07-11 08:48:40', 2, '2013-07-11 08:48:40');
commit;

-- Table structure for table lst_group_user
CREATE TABLE IF NOT EXISTS lst_group_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  nama varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Dumping data for table lst_group_user
INSERT INTO lst_group_user (id, nama) VALUES
(1, 'Admin'),
(2, 'User'),
(3, 'Report');
commit;

-- Table structure for table lst_hak_akses
CREATE TABLE IF NOT EXISTS lst_hak_akses (
  group_user_id int(11) NOT NULL,
  menu_id int(11) NOT NULL,
  active tinyint(1) DEFAULT '1',
  PRIMARY KEY (group_user_id,menu_id));

-- Dumping data for table lst_hak_akses
INSERT INTO lst_hak_akses (group_user_id, menu_id, active) VALUES
(1, 1, 1),
(1, 2, 1),
(1, 3, 1),
(1, 4, 1),
(1, 5, 1),
(1, 6, 1),
(1, 7, 1),
(1, 8, 1),
(1, 9, 1),
(1, 10, 1),
(1, 11, 1),
(1, 12, 1),
(1, 13, 1),
(1, 14, 1),
(1, 15, 1),
(1, 16, 1),
(1, 17, 1),
(1, 18, 1),
(1, 19, 1),
(1, 20, 1),
(1, 21, 1),
(1, 22, 1),
(1, 23, 1),
(1, 24, 1),
(1, 25, 1),
(1, 26, 1),
(1, 27, 1),
(2, 7, 0),
(2, 8, 1),
(2, 9, 0),
(2, 10, 0),
(2, 11, 1),
(2, 12, 1),
(2, 13, 1),
(2, 14, 1),
(2, 15, 1),
(2, 16, 1),
(2, 17, 1),
(2, 18, 1),
(2, 19, 1),
(2, 20, 1),
(2, 21, 1),
(2, 22, 1),
(2, 23, 1),
(2, 24, 1),
(2, 25, 1),
(2, 26, 1),
(2, 27, 1),
(3, 7, 0),
(3, 8, 1),
(3, 9, 0),
(3, 10, 0),
(3, 11, 0),
(3, 12, 0),
(3, 13, 0),
(3, 14, 0),
(3, 15, 0),
(3, 16, 0),
(3, 17, 0),
(3, 18, 0),
(3, 19, 0),
(3, 20, 0),
(3, 21, 1),
(3, 22, 0),
(3, 23, 0),
(3, 24, 1),
(3, 25, 1),
(3, 26, 1),
(3, 27, 1);
commit;

-- Table structure for table lst_harga
CREATE TABLE IF NOT EXISTS lst_harga (
  customer_id int(11) NOT NULL,
  barang_id int(11) NOT NULL,
  harga decimal(14,2) DEFAULT '0.00',
  active tinyint(1) DEFAULT NULL,
  createby int(11) DEFAULT NULL,
  createdate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (customer_id,barang_id)
);

-- Dumping data for table lst_harga
INSERT INTO lst_harga (customer_id, barang_id, harga, active, createby, createdate, modifyby, modifydate) VALUES
(1, 1, '10000.00', 1, 1, '2013-07-19 09:56:41', 2, '2013-07-19 09:56:41'),
(1, 2, '20000.00', 1, 1, '2013-07-19 09:56:41', 2, '2013-07-19 09:56:41'),
(1, 3, '30000.00', 1, 1, '2013-07-19 09:56:41', 2, '2013-07-19 09:56:41'),
(1, 4, '40000.00', 1, 1, '2013-07-19 09:56:41', 2, '2013-07-19 09:56:41'),
(1, 5, '50000.00', 1, 1, '2013-07-19 09:56:41', 2, '2013-07-19 09:56:41'),
(1, 6, '60000.00', 1, 1, '2013-07-19 09:56:41', 2, '2013-07-19 09:56:41'),
(1, 15, '70000.00', 1, 1, '2013-07-19 09:56:41', 2, '2013-07-19 09:56:41'),
(1, 16, '80000.00', 1, 1, '2013-07-11 13:42:56', 2, '2013-07-19 09:57:04'),
(1, 17, '2500000.00', 1, 1, '2013-08-22 16:21:51', NULL, NULL),
(2, 17, '850.00', 1, 31, '2013-11-08 09:46:43', NULL, NULL),
(3, 18, '700.00', 1, 31, '2013-11-08 09:43:03', NULL, NULL),
(4, 20, '1000.00', 1, 31, '2013-11-10 05:27:56', NULL, NULL),
(12, 21, '700.00', 1, 31, '2013-11-10 05:33:12', NULL, NULL);
commit;

-- Table structure for table lst_kapal
CREATE TABLE IF NOT EXISTS lst_kapal (
  kode varchar(40) NOT NULL,
  nama varchar(80) DEFAULT NULL,
  jenis int(11) DEFAULT NULL COMMENT 'jenis kapal\nlst_config id 5',
  harga decimal(14,2) DEFAULT '0.00',
  active tinyint(1) DEFAULT '1',
  createby int(11) DEFAULT NULL,
  createdate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (kode)
) ;

-- Dumping data for table lst_kapal
INSERT INTO lst_kapal (kode, nama, jenis, harga, active, createby, createdate, modifyby, modifydate) VALUES
('GETEK', 'KRI Naga Bonar', 1, '1000.00', 1, 1, '2013-07-19 09:56:41', 2, '2013-07-19 09:56:41');
commit;

-- Table structure for table lst_menu
CREATE TABLE IF NOT EXISTS lst_menu (
  id int(11) NOT NULL AUTO_INCREMENT,
  parent int(11) DEFAULT NULL,
  nama varchar(50) DEFAULT NULL,
  link varchar(100) DEFAULT NULL,
  urut int(11) DEFAULT NULL,
  level int(11) DEFAULT NULL COMMENT 'kedalaman tampilan menu',
  path varchar(45) DEFAULT NULL,
  active tinyint(1) DEFAULT '1',
  createby int(11) DEFAULT NULL,
  createdate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (id)
)  AUTO_INCREMENT=28 ;

-- Dumping data for table lst_menu
INSERT INTO lst_menu (id, parent, nama, link, urut, level, path, active, createby, createdate, modifyby, modifydate) VALUES
(1, NULL, 'Utama', NULL, 1, 0, '0.1', 1, 1, NULL, 1, NULL),
(2, 1, 'Administrasi', NULL, 1, 1, '0.1.1', 1, 1, NULL, 1, NULL),
(3, 1, 'Master Data', NULL, 2, 1, '0.1.2', 1, 1, NULL, 1, NULL),
(4, 1, 'Transaksi', NULL, 3, 1, '0.1.3', 1, 1, NULL, 1, NULL),
(5, 1, 'Keuangan', NULL, 4, 1, '0.1.4', 1, 1, NULL, 1, NULL),
(6, 1, 'Laporan', NULL, 5, 1, '0.1.5', 1, 1, NULL, 1, NULL),
(7, 2, 'Console', 'console', 1, 2, '0.1.1.1', 1, 1, NULL, 1, NULL),
(8, 2, 'Rubah Password', 'changepass', 2, 2, '0.1.1.2', 1, 1, NULL, 1, NULL),
(9, 2, 'Master User dan Akses', 'master/user', 3, 2, '0.1.1.3', 1, 1, NULL, 1, NULL),
(10, 2, 'Master Cabang', 'master/cabang', 4, 2, '0.1.1.4', 1, 1, NULL, 1, NULL),
(11, 3, 'Master Customer', 'master/customer', 1, 2, '0.1.2.1', 1, 1, NULL, 1, NULL),
(12, 3, 'Master Supir', 'master/supir', 2, 2, '0.1.2.2', 1, 1, NULL, 1, NULL),
(13, 3, 'Master Barang', 'master/barang', 3, 2, '0.1.2.3', 1, 1, NULL, 1, NULL),
(14, 3, 'Master Harga', 'master/harga', 4, 2, '0.1.2.4', 1, 1, NULL, 1, NULL),
(15, 3, 'Master Mobil', 'master/mobil', 5, 2, '0.1.2.5', 1, 1, NULL, 1, NULL),
(16, 3, 'Master Kapal', 'master/kapal', 6, 2, '0.1.2.6', 1, 1, NULL, 1, NULL),
(17, 3, 'Master Biaya', 'master/biaya', 7, 2, '0.1.2.7', 1, 1, NULL, 1, NULL),
(18, 4, 'Transaksi Input STT (Surat Tanda Terima)', 'transaksi/stt', 1, 2, '0.1.3.1', 1, 1, NULL, 1, NULL),
(19, 4, 'Transaksi Input SP (Rincian Barang Terkirim)', 'transaksi/sp', 2, 2, '0.1.3.2', 1, 1, NULL, 1, NULL),
(20, 4, 'Transaksi Checking Penerimaan Barang', 'transaksi/checking', 3, 2, '0.1.3.3', 1, 1, NULL, 1, NULL),
(21, 4, 'Print Surat Tagihan', 'report/tagihan', 6, 2, '0.1.3.6', 1, 1, NULL, 1, NULL),
(22, 5, 'Penerimaan Pembayaran Customer', 'transaksi/payment', 1, 2, '0.1.4.1', 1, 1, NULL, 1, NULL),
(23, 5, 'Penginputan Uang Masuk / Biaya Lain-Lain', 'transaksi/biaya', 2, 2, '0.1.4.2', 1, 1, NULL, 1, NULL),
(24, 6, 'Laporan STT', 'report/stt', 1, 2, '0.1.5.1', 1, 1, NULL, 1, NULL),
(25, 6, 'Laporan Rincian Barang Terkirim', 'report/rbt', 2, 2, '0.1.5.2', 1, 1, NULL, 1, NULL),
(26, 6, 'Laporan AR (Piutang)', 'report/ar', 3, 2, '0.1.5.3', 1, 1, NULL, 1, NULL),
(27, 6, 'Laporan Penerimaan & Pengeluaran Uang', 'report/keuangan', 4, 2, '0.1.5.4', 1, 1, NULL, 1, NULL);
commit;

-- Triggers lst_menu
/*
DROP TRIGGER IF EXISTS lst_menu_BINS;

CREATE TRIGGER lst_menu_BINS BEFORE INSERT ON lst_menu
 FOR EACH ROW
SET NEW.path = CONCAT(IFNULL((select path from lst_menu where id = NEW.parent), '0'), '.', New.urut)

DROP TRIGGER IF EXISTS lst_menu_BUPD;

CREATE TRIGGER lst_menu_BUPD BEFORE UPDATE ON lst_menu
 FOR EACH ROW
SET NEW.path = CONCAT(IFNULL((select path from lst_menu where id = NEW.parent), '0'), '.', New.urut)
*/

-- Table structure for table lst_mobil
CREATE TABLE IF NOT EXISTS lst_mobil (
  no_polisi varchar(80) NOT NULL,
  jenis int(11) DEFAULT NULL COMMENT 'jenis mobil\nlst_config id 4',
  km_awal decimal(14,2) DEFAULT '0.00',
  harga decimal(14,2) DEFAULT '0.00' COMMENT 'lst_config id 6',
  active tinyint(1) DEFAULT '1',
  createby int(11) DEFAULT NULL,
  createdate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (no_polisi)
) ;

-- Dumping data for table lst_mobil
INSERT INTO lst_mobil (no_polisi, jenis, km_awal, harga, active, createby, createdate, modifyby, modifydate) VALUES
('B 911 YX', 1, '0.00', '0.00', 1, NULL, NULL, 1, '2013-08-06 09:06:35'),
('B. 9098 BYV', 2, NULL, '0.00', 1, 31, '2013-11-10 04:05:58', NULL, NULL),
('B. 9099 BYV', 2, NULL, '0.00', 1, 31, '2013-11-10 04:06:35', NULL, NULL),
('B. 9124 BYW', 2, NULL, '0.00', 1, 31, '2013-11-10 04:02:15', NULL, NULL),
('B. 9125 BYW', 2, NULL, '0.00', 1, 31, '2013-11-10 04:02:43', NULL, NULL),
('B. 9126 BYW', 2, NULL, '0.00', 1, 31, '2013-11-10 04:03:15', NULL, NULL),
('B. 9127 BYW', 2, NULL, '0.00', 1, 31, '2013-11-10 04:03:38', NULL, NULL),
('B. 9128 BYW', 2, NULL, '0.00', 1, 31, '2013-11-10 04:04:10', NULL, NULL),
('B. 9187 BYV', 2, NULL, '0.00', 1, 31, '2013-11-10 04:09:17', NULL, NULL),
('B. 9188 BYV', 2, NULL, '0.00', 1, 31, '2013-11-10 04:09:37', NULL, NULL),
('B. 9293 BDC', 2, NULL, '0.00', 1, 31, '2013-11-10 04:06:58', NULL, NULL),
('B. 9294 BDC', 2, NULL, '0.00', 1, 31, '2013-11-10 04:07:17', NULL, NULL),
('B. 9295 BDC', 2, NULL, '0.00', 1, 31, '2013-11-10 04:07:38', NULL, NULL),
('B. 9506 BDC', 2, NULL, '0.00', 1, 31, '2013-11-10 04:04:34', NULL, NULL),
('B. 9647 BDC', 2, NULL, '0.00', 1, 31, '2013-11-10 04:04:57', NULL, NULL),
('B. 9739 BDC', 2, NULL, '0.00', 1, 31, '2013-11-10 04:05:20', NULL, NULL);
commit;

-- Table structure for table lst_supir
CREATE TABLE IF NOT EXISTS lst_supir (
  id int(11) NOT NULL AUTO_INCREMENT,
  nama varchar(40) DEFAULT NULL,
  tgl_mulai date DEFAULT NULL,
  tgl_berakhir date DEFAULT NULL,
  active tinyint(1) DEFAULT '1',
  createby int(11) DEFAULT NULL,
  createdate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (id)
)  AUTO_INCREMENT=16 ;

-- Dumping data for table lst_supir
INSERT INTO lst_supir (id, nama, tgl_mulai, tgl_berakhir, active, createby, createdate, modifyby, modifydate) VALUES
(1, 'OMAT', '2011-10-03', NULL, 1, NULL, NULL, 31, '2013-11-10 03:51:02'),
(2, 'SADI', '2011-10-03', '2013-11-01', 1, 31, '2013-11-08 09:40:20', 31, '2013-11-10 03:52:10'),
(3, 'PAK IWAN', '2011-10-03', NULL, 1, 31, '2013-11-10 03:44:28', 31, '2013-11-10 03:51:23'),
(4, 'ACENG', '2011-10-03', NULL, 1, 31, '2013-11-10 03:47:18', 31, '2013-11-10 03:50:41'),
(5, 'IDING', '2011-10-03', NULL, 1, 31, '2013-11-10 03:50:24', NULL, NULL),
(6, 'SUDAR', '2011-10-03', NULL, 1, 31, '2013-11-10 03:52:40', NULL, NULL),
(7, 'SUKARNO', '2011-10-03', NULL, 1, 31, '2013-11-10 03:53:10', NULL, NULL),
(8, 'NURJI', '2011-10-03', NULL, 1, 31, '2013-11-10 03:53:37', NULL, NULL),
(9, 'SOBIRIN', '2011-10-03', NULL, 1, 31, '2013-11-10 03:54:11', NULL, NULL),
(10, 'SYARIEF', '2011-10-03', NULL, 1, 31, '2013-11-10 03:54:45', NULL, NULL),
(11, 'PAK GIOK', '2011-10-03', NULL, 1, 31, '2013-11-10 03:55:12', NULL, NULL),
(12, 'UDIN', '2011-10-03', NULL, 1, 31, '2013-11-10 03:55:58', NULL, NULL),
(13, 'AJAT', '2011-10-03', NULL, 1, 31, '2013-11-10 03:57:12', NULL, NULL),
(14, 'USMAN', '2011-10-03', NULL, 1, 31, '2013-11-10 03:58:08', NULL, NULL),
(15, 'KUSWAN', '2011-10-03', NULL, 1, 31, '2013-11-10 03:59:30', NULL, NULL);
commit;

-- Table structure for table lst_user
CREATE TABLE IF NOT EXISTS lst_user (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'User ID',
  cabang_id int(11) DEFAULT NULL COMMENT 'Cabang ID',
  username varchar(20) DEFAULT NULL,
  password varchar(20) DEFAULT NULL,
  group_user_id int(11) NOT NULL,
  flag_approval int(1) DEFAULT NULL COMMENT 'klo flag 1 user approval bisa acc approval ',
  flag_akses_all int(1) DEFAULT '0' COMMENT 'kalau 1 artinya boleh liat semua cabang',
  nik varchar(10) DEFAULT NULL COMMENT 'nik di mst_karyawan',
  nama varchar(100) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  dob date DEFAULT NULL,
  lastlogin datetime DEFAULT NULL,
  active tinyint(1) DEFAULT '1',
  createby int(11) DEFAULT NULL,
  createdate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (id),
)  AUTO_INCREMENT=4 ;

-- Dumping data for table lst_user
INSERT INTO lst_user (id, cabang_id, username, password, group_user_id, flag_approval, flag_akses_all, nik, nama, email, dob, lastlogin, active, createby, createdate, modifyby, modifydate) VALUES
(1, 2, 'YUSUF', '123456', 1, NULL, 0, NULL, '', '', NULL, '2013-11-08 07:23:24', 1, 1, '2013-06-25 14:48:03', 1, '2013-06-25 15:55:51'),
(2, 2, 'YODHIAN', '123456', 1, NULL, 0, NULL, 'Rudy Hermawan', 'rudy.hermawan@yahoo.com', NULL, '2013-06-28 13:56:50', 1, 1, '2013-06-25 14:48:03', 1, '2013-11-08 07:27:33'),
(3, 1, 'ADJIE', '123456', 2, NULL, 0, NULL, '', '', NULL, '2013-08-22 09:00:45', 1, 1, '2013-06-25 14:48:03', 1, '2013-06-25 14:57:24');
commit;

-- Table structure for table mst_customer
CREATE TABLE IF NOT EXISTS mst_customer (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '99999',
  kode varchar(10) DEFAULT NULL COMMENT 'CYYMMXXXXX',
  nama varchar(60) DEFAULT NULL,
  alamat varchar(200) DEFAULT NULL,
  kota varchar(60) DEFAULT NULL,
  contact varchar(60) DEFAULT NULL,
  no_telp varchar(50) DEFAULT NULL,
  no_hp varchar(30) DEFAULT NULL,
  email varchar(60) DEFAULT NULL,
  no_fax varchar(30) DEFAULT NULL,
  limit_hutang decimal(14,2) DEFAULT NULL COMMENT 'untuk limit hutang per client',
  due_date datetime DEFAULT NULL,
  active tinyint(1) DEFAULT NULL,
  createby int(11) DEFAULT NULL,
  createdate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (id)
)  AUTO_INCREMENT=11 ;

-- Dumping data for table mst_customer
INSERT INTO mst_customer (id, kode, nama, alamat, kota, contact, no_telp, no_hp, email, no_fax, limit_hutang, due_date, active, createby, createdate, modifyby, modifydate) VALUES
(1, 'C130600001', 'PT Maju Mundur Lagi', 'Di tengah hutan', 'Bangka', 'Simanungmanung', '1357', '2468', 'b@c.d', '975135', '124000000.00', NULL, 1, 1, '2013-06-26 08:40:29', 1, '2013-06-26 08:42:02'),
(2, 'C130800002', 'PT Kiri Kanan Oke', 'gang subur', 'Jakarta', 'Subur', '123', '456', 'abc@def.com', '789', '1000000000.00', NULL, 1, 1, '2013-08-12 10:13:53', 1, '2013-08-12 10:14:08'),
(3, 'C131100003', 'PT. ULTRA PRIMA ABADI', 'jakarta', 'jakarta', 'PAK BEJO', '021 5525419', '085283837719', '', '021 5525227', NULL, NULL, 1, 31, '2013-11-08 09:38:05', 31, '2013-11-10 04:25:58'),
(4, 'C131100004', 'CI ANGO', 'JAKARTA', 'JAKARTA', 'CI ANGO', '021 99593232', '08158305617', '', '', NULL, NULL, 1, 31, '2013-11-10 04:27:24', NULL, NULL),
(5, 'C131100005', 'PANCA AGUNG SUKSES', 'GONDRONG ', 'JAKARTA', 'KO JONI', '021 44380777', '081365420369', '', '', NULL, NULL, 1, 31, '2013-11-10 04:29:13', NULL, NULL),
(6, 'C131100006', 'MY SHOP 88', 'JAKARTA', 'JAKARTA', 'CI BERLIN', '021 54202992', '0819820088', '', '', NULL, NULL, 1, 31, '2013-11-10 04:30:37', NULL, NULL),
(7, 'C131100007', 'PT. MALINDO CEMERLANG PRATAMA', 'JL. TANJUNG PURA NO. 168', 'JAKARTA', 'IBU YAYANG', '021 54398968', '', '', '', NULL, NULL, 1, 31, '2013-11-10 04:33:32', NULL, NULL),
(8, 'C131100008', 'KARYA BAJA', 'JAKARTA', 'JAKARTA', 'CI LINCE', '021 55741999', '', '', '021 55751168', NULL, NULL, 1, 31, '2013-11-10 04:35:06', NULL, NULL),
(9, 'C131100009', 'PT. CAHAYA BENTENG MAS', 'JAKARTA', 'JAKARTA', 'IBU BUNGA', '021 6281964 EXT.801', '', '', '', NULL, NULL, 1, 31, '2013-11-10 04:37:26', NULL, NULL),
(10, 'C131100010', 'WIJAYA METALINDO', 'JL. ASEM RAYA\r\nDURI KEPA\r\nKEBON JERUK', 'JAKARTA', 'KO AWI', '021 70806867', '08128012474', '', '', NULL, NULL, 1, 31, '2013-11-10 04:39:28', NULL, NULL);
commit;

/*
-- Triggers mst_customer
DROP TRIGGER IF EXISTS mst_customer_BINS;

CREATE TRIGGER mst_customer_BINS BEFORE INSERT ON mst_customer
 FOR EACH ROW
SET NEW.kode = CONCAT('C'
,date_format(sysdate(),'%y')
,date_format(sysdate(),'%m')
,lpad( (SELECT mod(auto_increment, 99999) FROM INFORMATION_SCHEMA.TABLES 
WHERE table_name = 'mst_customer' limit 0,1),5,'0')
)
*/

-- Table structure for table mst_delivery
CREATE TABLE IF NOT EXISTS mst_delivery (
  id int(11) NOT NULL AUTO_INCREMENT,
  tgl_kirim date DEFAULT NULL,
  tgl_sampai date DEFAULT NULL,
  no_polisi varchar(80) DEFAULT NULL,
  supir_id int(11) DEFAULT NULL,
  kode_kapal varchar(40) DEFAULT NULL,
  createby int(11) DEFAULT NULL COMMENT 'User ID',
  createdate datetime DEFAULT NULL,
  cancel tinyint(1) DEFAULT '0',
  cancelby int(11) DEFAULT NULL,
  canceldate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (id),
)  AUTO_INCREMENT=14 ;

-- Dumping data for table mst_delivery
INSERT INTO mst_delivery (id, tgl_kirim, tgl_sampai, no_polisi, supir_id, kode_kapal, createby, createdate, cancel, cancelby, canceldate, modifyby, modifydate) VALUES
(9, '2013-07-23', NULL, 'B 911 YX', 1, 'GETEK', 1, '2013-07-23 14:52:31', 0, NULL, NULL, 1, '2013-07-23 14:53:18'),
(10, '2013-07-23', '2013-08-04', 'B 911 YX', 1, 'GETEK', 1, '2013-07-23 15:07:22', 0, NULL, NULL, 31, '2013-11-08 09:03:12'),
(11, '2013-08-22', NULL, 'B 911 YX', 1, 'GETEK', 29, '2013-08-22 09:20:49', 0, NULL, NULL, NULL, NULL);
commit;

-- Table structure for table mst_delivery_cost
CREATE TABLE IF NOT EXISTS mst_delivery_cost (
  delivery_id int(11) NOT NULL,
  biaya_id int(11) NOT NULL,
  note varchar(200) DEFAULT NULL,
  nominal decimal(14,2) DEFAULT '0.00',
  PRIMARY KEY (delivery_id,biaya_id)
) ;

-- Dumping data for table mst_delivery_cost
INSERT INTO mst_delivery_cost (delivery_id, biaya_id, note, nominal) VALUES
(9, 7, '', '500000.00'),
(10, 1, 'mobil', '3000000.00'),
(10, 4, 'gayor', '750000.00'),
(10, 7, 'supir', '500000.00');
commit;

-- Table structure for table mst_delivery_det
CREATE TABLE IF NOT EXISTS mst_delivery_det (
  delivery_id int(11) NOT NULL,
  trans_id int(11) NOT NULL,
  trans_urut int(3) NOT NULL,
  colly_naik decimal(9,0) DEFAULT '0',
  colly_sisa decimal(9,0) DEFAULT '0',
  colly_sampai decimal(9,0) DEFAULT '0',
  note varchar(200) DEFAULT NULL,
  nominal decimal(14,2) DEFAULT NULL,
  note_delivery varchar(200) DEFAULT NULL COMMENT 'catatan delivery (bila misalnya colly sampai tidak sesuai colly naik)',
  PRIMARY KEY (delivery_id,trans_id,trans_urut)) ;

-- Dumping data for table mst_delivery_det
INSERT INTO mst_delivery_det (delivery_id, trans_id, trans_urut, colly_naik, colly_sisa, colly_sampai, note, nominal, note_delivery) VALUES
(9, 2, 1, '30', '70', '0', '', '20000.00', NULL),
(10, 2, 1, '20', '50', '19', 'hahaha', '2000000.00', 'zxcv'),
(10, 5, 1, '100', '0', '99', 'hehehe', '3500000.00', 'asdf'),
(10, 7, 3, '40', '60', '49', 'hihihi', '4500000.00', 'qwer');
commit;

-- Table structure for table mst_payment
CREATE TABLE IF NOT EXISTS mst_payment (
  payment_id int(11) NOT NULL AUTO_INCREMENT,
  no_payment varchar(18) DEFAULT NULL COMMENT 'PMMYYCC99999\n\nMM = bulan\nYY = tahun\n99999 = auto inc',
  trans_id int(11) DEFAULT NULL,
  paid_date date DEFAULT NULL,
  dk char(1) DEFAULT NULL COMMENT 'd = uang masuk\nk = uang keluar',
  pay_mode int(11) DEFAULT '1' COMMENT 'cara pembayaran uang\nlst_config id 2',
  nominal decimal(14,2) DEFAULT NULL,
  no_giro varchar(20) DEFAULT NULL,
  due_date date DEFAULT NULL,
  keterangan varchar(200) DEFAULT NULL,
  createby int(11) DEFAULT NULL COMMENT 'User ID',
  createdate datetime DEFAULT NULL,
  cancel tinyint(1) DEFAULT '0',
  cancelby int(11) DEFAULT NULL,
  canceldate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (payment_id)
)  AUTO_INCREMENT=13 ;

-- Dumping data for table mst_payment
INSERT INTO mst_payment (payment_id, no_payment, trans_id, paid_date, dk, pay_mode, nominal, no_giro, due_date, keterangan, createby, createdate, cancel, cancelby, canceldate, modifyby, modifydate) VALUES
(1, 'P0813JK00001', NULL, '2013-08-05', 'D', 1, '100000.00', NULL, NULL, 'Nemu di jalan', 1, '2013-08-05 10:07:37', 0, NULL, NULL, NULL, NULL),
(2, 'P0813JK00002', NULL, '2013-08-05', 'K', 1, '25000.00', NULL, NULL, 'Bayar preman', 1, '2013-08-05 10:13:35', 0, NULL, NULL, NULL, NULL),
(3, 'P0813JK00003', NULL, '2013-08-05', 'K', 1, '17500.00', '', NULL, 'Beli gorengan', 1, '2013-08-05 10:14:32', 0, NULL, NULL, 1, '2013-08-06 10:41:35'),
(4, 'P0813JK00004', 2, '2013-08-05', 'D', 1, '3800000.00', '', NULL, 'Lunas', 1, '2013-08-05 10:29:21', 1, 1, '2013-08-22 16:19:29', NULL, NULL),
(7, 'P0813JK00007', NULL, '2013-08-04', 'K', 3, '256000.00', 'no giro', '2013-08-12', 'beli rokok', 1, '2013-08-05 14:42:30', 0, NULL, NULL, NULL, NULL),
(8, 'P0813JK00008', NULL, '2013-08-05', 'D', 3, '256000.00', 'no giro', '2013-08-20', 'sadfasdf', 1, '2013-08-05 14:55:00', 1, 1, '2013-08-06 11:15:23', NULL, NULL),
(9, 'P0813JK00009', NULL, '2013-08-06', 'D', 1, '5000.00', '123', '2013-08-12', 'qwer', 1, '2013-08-05 15:07:46', 0, NULL, NULL, 1, '2013-08-05 15:08:21'),
(10, 'P0813JK00010', 5, '2013-08-05', 'D', 2, '11000.00', '', NULL, 'Baru bayar sebelas ribu', 1, '2013-08-05 15:10:44', 0, NULL, NULL, 1, '2013-08-06 10:41:16'),
(11, 'P1113JK00011', 8, '2013-11-05', 'D', 2, '300000.00', '', NULL, 'ok', 31, '2013-11-08 09:08:05', 0, NULL, NULL, 31, '2013-11-08 09:11:38'),
(12, 'P1113JK00012', NULL, '2013-11-06', 'K', 1, '350000.00', '', NULL, 'beli makan malam', 31, '2013-11-08 09:20:57', 0, NULL, NULL, NULL, NULL);
commit;

/*
-- Triggers mst_payment
DROP TRIGGER IF EXISTS mst_payment_BINS;

CREATE TRIGGER mst_payment_BINS BEFORE INSERT ON mst_payment
 FOR EACH ROW
SET NEW.no_payment = CONCAT('P'
,date_format(sysdate(),'%m')
,date_format(sysdate(),'%y')
,(SELECT kode FROM lst_cabang c, lst_user u where c.id = u.cabang_id and u.id=New.createby)
,lpad( (SELECT mod(auto_increment, 99999) FROM INFORMATION_SCHEMA.TABLES 
WHERE table_name = 'mst_payment' limit 0,1),5,'0')
)
*/

-- Table structure for table mst_trans
CREATE TABLE IF NOT EXISTS mst_trans (
  id int(11) NOT NULL AUTO_INCREMENT,
  cabang_id int(11) NOT NULL,
  customer_id int(11) DEFAULT NULL,
  no_stt varchar(18) DEFAULT NULL COMMENT 'MMYY99999\n\nMM = bulan\nYY = tahun\n99999 = auto inc',
  tgl_stt date NOT NULL,
  tgl_kirim_est date DEFAULT NULL COMMENT 'tgl kirim estimasi',
  pay_mode int(11) DEFAULT NULL COMMENT 'cara bayar\nlst_config id1',
  colly decimal(9,0) DEFAULT '0' COMMENT 'jumlah dalam colly',
  contact_tujuan varchar(100) DEFAULT NULL,
  alamat_tujuan varchar(200) DEFAULT NULL,
  telp_tujuan varchar(100) DEFAULT NULL,
  note varchar(200) DEFAULT NULL,
  total_harga decimal(14,2) DEFAULT '0.00' COMMENT 'total harga dari harga di mst_trans_det',
  potongan decimal(14,2) DEFAULT '0.00' COMMENT 'potongan total',
  remain decimal(14,2) DEFAULT '0.00' COMMENT 'default remain = total_harga - potongan\nakan berkurang seiring pada waktu pembayaran.\n\nnilai remain ini yg nantinya digunakan untuk laporan piutang',
  createby int(11) DEFAULT NULL COMMENT 'User ID',
  createdate datetime DEFAULT NULL,
  cancel tinyint(1) DEFAULT '0',
  cancelby int(11) DEFAULT NULL,
  canceldate datetime DEFAULT NULL,
  modifyby int(11) DEFAULT NULL,
  modifydate datetime DEFAULT NULL,
  PRIMARY KEY (id)
)  AUTO_INCREMENT=11 ;

-- Dumping data for table mst_trans
INSERT INTO mst_trans (id, cabang_id, customer_id, no_stt, tgl_stt, tgl_kirim_est, pay_mode, colly, contact_tujuan, alamat_tujuan, telp_tujuan, note, total_harga, potongan, remain, createby, createdate, cancel, cancelby, canceldate, modifyby, modifydate) VALUES
(2, 2, 1, '061300002', '2013-06-28', '2013-07-12', 2, '300', 'Johnny', 'Amerika', 'Cash', 'Cupski', '2540000.00', '5000.00', '2535000.00', NULL, NULL, 0, NULL, NULL, 1, '2013-08-22 16:21:51'),
(3, 1, 1, '061300003', '2013-06-28', NULL, 2, '0', 'Joni', 'Joni', '123', '', '0.00', '0.00', '0.00', NULL, NULL, 0, NULL, NULL, 1, '2013-08-06 09:07:30'),
(4, 1, 2, '071300004', '2013-07-11', '2013-07-11', 2, '100', 'Yusuf', 'Metland Puri', '08812341981', 'Kirim ganja sama popok', '101000000.00', '0.00', '101000000.00', 1, '2013-07-11 08:48:40', 0, NULL, NULL, NULL, NULL),
(5, 2, 1, '071300005', '2013-07-11', '2013-07-11', 3, '100', 'Yusuf', 'Puri Metland', '08812341981', '', '4111000.00', '0.00', '4100000.00', 1, '2013-07-11 09:55:28', 0, NULL, NULL, 1, '2013-08-06 10:41:16'),
(7, 1, 2, '071300007', '2013-07-11', '2013-07-15', 1, '440', 'Yusuf', 'Sunrise', '08812341981', 'asdfadf', '1247197890.00', '0.00', '1247197890.00', 1, '2013-07-11 10:06:26', 0, NULL, NULL, 1, '2013-07-16 10:37:03'),
(8, 2, 1, '111300008', '2013-11-08', '2013-11-08', 3, '39', 'edwin', 'pangkal pinang', '0707', '', '358500.00', '8500.00', '50000.00', 31, '2013-11-08 08:42:56', 0, NULL, NULL, 31, '2013-11-08 09:11:38'),
(9, 2, 4, '111300009', '2013-11-10', '2013-11-10', 3, '40', 'CI ANGO', 'TK. AGUNG\r\nPANGKAL PINANG', '021 99593232', '', '388750.00', '0.00', '388750.00', 31, '2013-11-10 05:27:56', 0, NULL, NULL, NULL, NULL),
(10, 2, 12, '111300010', '2013-11-10', '2013-11-10', 3, '102', 'FERYY', 'BENGKEL BUBUT ASING\r\nPANGKAL PINANG', '021 55701329', '', '857500.00', '0.00', '857500.00', 31, '2013-11-10 05:33:12', 0, NULL, NULL, NULL, NULL);
commit;

/*
-- Triggers mst_trans
DROP TRIGGER IF EXISTS mst_trans_BINS;

CREATE TRIGGER mst_trans_BINS BEFORE INSERT ON mst_trans
 FOR EACH ROW
SET NEW.no_stt = CONCAT(date_format(sysdate(),'%m')
,date_format(sysdate(),'%y')
,lpad( (SELECT mod(auto_increment, 99999) FROM INFORMATION_SCHEMA.TABLES 
WHERE table_name = 'mst_trans' limit 0,1),5,'0')
)
*/

-- Table structure for table mst_trans_det
CREATE TABLE IF NOT EXISTS mst_trans_det (
  trans_id int(11) NOT NULL,
  urut int(3) NOT NULL,
  barang_id int(11) DEFAULT NULL COMMENT 'bisa diisi, bisa juga kosong, ini hanya sebagai alat bantu pencairan saja',
  nama_barang varchar(100) DEFAULT NULL COMMENT 'harus diisi',
  satuan_id int(11) DEFAULT NULL COMMENT 'satuan barang \nlst_config id 3',
  qty decimal(9,0) DEFAULT '0' COMMENT 'qty pengiriman, bisa dalam KG atau M2',
  harga decimal(14,2) DEFAULT '0.00',
  colly decimal(9,0) DEFAULT '0' COMMENT 'jumlah colly pengiriman',
  colly_remain decimal(9,0) DEFAULT '0' COMMENT 'sisa pengiriman, ini buat trigger pada saat pengiriman.\ndefault awal disamakan dengan colly',
  PRIMARY KEY (trans_id,urut)) ;

-- Dumping data for table mst_trans_det
INSERT INTO mst_trans_det (trans_id, urut, barang_id, nama_barang, satuan_id, qty, harga, colly, colly_remain) VALUES
(2, 1, 1, 'Mamy Poko NB', 1, '1', '10000.00', '100', '50'),
(2, 3, 3, 'Mamy Poko M', 1, '1', '30000.00', '100', '100'),
(2, 5, NULL, 'Heroin', 1, '1', '2500000.00', '100', '100'),
(4, 3, NULL, 'Mamy Poko NB', 1, '100', '10000.00', '100', '100'),
(4, 4, NULL, 'Ganja', 1, '1', '100000000.00', '100', '100'),
(5, 1, NULL, 'Mamy Poko M', 1, '100', '30000.00', '100', '0'),
(5, 2, NULL, 'Huggies M', 1, '1000', '1111.00', '100', '100'),
(7, 1, NULL, 'Mamy Poko NB', 1, '123', '10000.00', '20', '20'),
(7, 2, NULL, 'Huggies M', 2, '456', '25000.00', '100', '100'),
(7, 3, NULL, 'Cimeng', 2, '10', '123456789.00', '100', '60'),
(8, 1, 2, 'Mamy Poko S', 1, '125', '1500.00', '25', '25'),
(8, 2, 1, 'Mamy Poko NB', 1, '114', '1500.00', '14', '14'),
(9, 1, NULL, 'SUSU', 1, '325', '1000.00', '25', '25'),
(9, 2, NULL, 'PAMPERS', 1, '43', '1500.00', '15', '15'),
(10, 1, NULL, 'PLAT POTONG', 1, '1225', '700.00', '102', '102');
commit;
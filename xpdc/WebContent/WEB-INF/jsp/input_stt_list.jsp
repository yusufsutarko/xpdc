<%@ include file="/WEB-INF/jsp/include_taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>XPDC</title>
<%@ include file="/WEB-INF/jsp/include_head.jsp"%>
<script type="text/javascript">
function cetak(id, flag){
	$("#pdfDialog").attr("src", "${path}/loading"); //tampilkan loading message dulu, siapa tau agak lambat
	$("#printDialog").dialog({
		modal: true,
		title: "Cetak",
		minWidth: 800,
		minHeight: 600
	});	
	$("#pdfDialog").attr("src", "${path}/report/stt/" + id + "/" + flag); //baru tampilkan isinya
}

</script>
<style type="text/css">
table { 
	font-size: 0.8em;
}
</style>
</head>
<body>

${sessionScope.currentUser.menuAkses}

<div class="tabs">
	<ul>
		<li><a href="#tabs-1">Home &raquo; Input STT (Surat Tanda Terima)</a></li>
	</ul>
	<div id="tabs-1">
		<form method="post">
		<table class="gridTables">
			<caption>
				<label for="from" title="Tanggal STT">Periode:</label>
				<input type="text" id="periodFrom" name="periodFrom" class="datepicker-disabled" value="${periodFrom}" />
				<label for="to">s/d</label>
				<input type="text" id="periodTo" name="periodTo" class="datepicker-disabled" value="${periodTo}" />
				<label for="search">Cari:</label>
				<input type="text" name="search" value="${search}" title="Cari berdasarkan NOMOR STT / NAMA CUSTOMER">
				<label for="rowcount">Tampilkan:</label>
				<select name="rowcount" onchange="form.submit();">
					<c:forEach var="r" begin="10" step="10" end="100">
					<option <c:if test="${r eq rowcount}">selected</c:if> value="${r}">${r}</option>
					</c:forEach>
				</select>
				<label for="page">Halaman:</label>
				<select name="page" onchange="form.submit();">
					<c:forEach var="p" begin="1" end="${pages}">
					<option <c:if test="${p eq page}">selected</c:if> value="${p}">${p}</option>
					</c:forEach>
				</select>
			</caption>
			<tr>
				<%--<th>Id</th> --%>
				<th>No STT</th>
				<th>Cabang</th>
				<th>Pengirim</th>
				<th>Penerima</th>
				<th>Colly</th>
				<th>Total Harga</th>
				<th>Potongan</th>
				<th>Tagihan</th>
				<th>Tgl STT</th>
				<th>Tgl Kirim Estimasi</th>
				<th>Batal</th>
				<th>Dibuat</th>
				<th>Tindakan</th>
			</tr>
			<c:forEach items="${listSTT}" var="t" varStatus="s">
			<tr>
				<%-- <td>${t.id}</td> --%>
				<td>${t.no_stt}</td>
				<td>${t.cabang}</td>
				<td>${t.customer}</td>
				<td>${t.tujuan}</td>
				<td class="center"><fmt:formatNumber value="${t.colly}" /></td>
				<td class="right"><fmt:formatNumber value="${t.total_harga}" type="currency" currencySymbol="Rp " /></td>
				<td class="right"><fmt:formatNumber value="${t.potongan}" type="currency" currencySymbol="Rp " /></td>
				<td class="right"><fmt:formatNumber value="${t.remain}" type="currency" currencySymbol="Rp " /></td>
				<td><fmt:formatDate value="${t.tgl_stt}" pattern="dd-MM-yyyy"/></td>
				<td><fmt:formatDate value="${t.tgl_kirim_est}" pattern="dd-MM-yyyy"/></td>
				<td class="center">
					<c:choose>
						<c:when test="${t.cancel eq 0}"><span style="color: blue;">Tidak</span></c:when>
						<c:when test="${t.cancel eq 1}"><span style="color: red;">Ya</span></c:when>
					</c:choose>
				</td>
				<td><fmt:formatDate value="${t.createdate}" pattern="dd-MM-yy (HH:mm:ss)"/></td>
				<td>
					<c:choose>
						<c:when test="${t.cancel eq 0}">
							<button onclick="window.location='${path}/transaksi/stt/update/${t.id}'; return false;">Rubah</button>
							<button onclick="cetak(${t.id}, 0); return false;">Cetak Kosong</button>
							<button onclick="cetak(${t.id}, 1); return false;">Cetak</button>
						</c:when>
						<c:when test="${t.cancel eq 1}">
							<button onclick="window.location='${path}/transaksi/stt/update/${t.id}'; return false;">Lihat</button>
						</c:when>
					</c:choose>
				</td>
			</tr>
			</c:forEach>
		</table>
		<p>
			<button onclick="window.location='${path}/transaksi/stt/insert'; return false;">Tambah Baru</button>
		</p>
		</form>
	</div>
</div>

<div id="printDialog" style="display:none;">
    <div>
    <iframe id="pdfDialog" width="750" height="550" frameborder="0">Error!</iframe>
    </div>
</div>

</body>
</html>
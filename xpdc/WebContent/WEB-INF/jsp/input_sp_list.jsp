<%@ include file="/WEB-INF/jsp/include_taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>XPDC</title>
<%@ include file="/WEB-INF/jsp/include_head.jsp"%>
<script type="text/javascript">
function cetak(id){
	$("#pdfDialog").attr("src", "${path}/loading"); //tampilkan loading message dulu, siapa tau agak lambat
	$("#printDialog").dialog({
		modal: true,
		title: "Cetak",
		minWidth: 800,
		minHeight: 600
	});	
	$("#pdfDialog").attr("src", "${path}/report/rbt/" + id); //baru tampilkan isinya
}
</script>
</head>
<body>

${sessionScope.currentUser.menuAkses}

<div class="tabs">
	<ul>
		<li><a href="#tabs-1">Home &raquo; Input SP (Rincian Barang Terkirim)</a></li>
	</ul>
	<div id="tabs-1">
		<form method="post">
		<table class="gridTables">
			<caption>
				<label for="from" title="Tanggal Muat">Periode:</label>
				<input type="text" id="periodFrom" name="periodFrom" class="datepicker-disabled" value="${periodFrom}" />
				<label for="to">s/d</label>
				<input type="text" id="periodTo" name="periodTo" class="datepicker-disabled" value="${periodTo}" />
				<label for="search">Cari:</label>
				<input type="text" name="search" value="${search}" title="Cari berdasarkan NOMOR MOBIL/NAMA SUPIR/KODE KAPAL/NAMA KAPAL">
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
				<th>No SP</th>
				<th>Tgl Muat</th>
				<th>Tgl Sampai</th>
				<th>Mobil</th>
				<th>Supir</th>
				<th>Kapal</th>
				<th>Batal</th>
				<th>Dibuat</th>
				<th>Tindakan</th>
			</tr>
			<c:forEach items="${listSP}" var="d" varStatus="s">
			<tr>
				<td>${d.id}</td>
				<td><fmt:formatDate value="${d.tgl_kirim}" pattern="dd-MM-yyyy"/></td>
				<td><fmt:formatDate value="${d.tgl_sampai}" pattern="dd-MM-yyyy"/></td>
				<td>${d.no_polisi}</td>
				<td>${d.supir_nama}</td>
				<td>[${d.kode_kapal}] ${d.kapal_nama}</td>
				<td class="center">
					<c:choose>
						<c:when test="${d.cancel eq 0}"><span style="color: blue;">Tidak</span></c:when>
						<c:when test="${d.cancel eq 1}"><span style="color: red;">Ya</span></c:when>
					</c:choose>
				</td>
				<td><fmt:formatDate value="${d.createdate}" pattern="dd-MM-yy (HH:mm:ss)"/></td>
				<td>
					<c:choose>
						<c:when test="${d.cancel eq 0}">
							<button onclick="window.location='${path}/transaksi/sp/update/${d.id}'; return false;">Rubah</button>
							<button onclick="cetak(${d.id}); return false;">Cetak</button>
						</c:when>
						<c:when test="${d.cancel eq 1}">
							<button onclick="window.location='${path}/transaksi/sp/update/${d.id}'; return false;">Lihat</button>
						</c:when>
					</c:choose>
				</td>
			</tr>
			</c:forEach>
		</table>
		<p>
			<button onclick="window.location='${path}/transaksi/sp/insert'; return false;">Tambah Baru</button>
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
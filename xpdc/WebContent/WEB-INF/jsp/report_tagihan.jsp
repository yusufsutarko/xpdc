<%@ include file="/WEB-INF/jsp/include_taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>XPDC</title>
<%@ include file="/WEB-INF/jsp/include_head.jsp"%>
<script type="text/javascript">
function cetak(id){
	
	var format = $("#format").val();

	//cetak pdf
	$("#pdfDialog").attr("src", "${path}/loading"); //tampilkan loading message dulu, siapa tau agak lambat
	$("#printDialog").dialog({
		modal: true,
		title: "Cetak",
		minWidth: 800,
		minHeight: 600
	});	
	$("#pdfDialog").attr("src", "${path}/report/tagihan/" + id + "/" + format); //baru tampilkan isinya
}

</script>
</head>
<body>

${sessionScope.currentUser.menuAkses}

<div class="tabs">
	<ul>
		<li><a href="#tabs-1">Home &raquo; Cetak Tagihan</a></li>
	</ul>
	<div id="tabs-1">
		<form method="post">
		<table class="gridTables">
			<caption>
				<label for="format" title="Format">Format:</label>
				<select id="format" name="format">
					<c:forEach items="${reff.listFormat}" var="f">
						<option value="${f.key}">${f.value}</option>
					</c:forEach>
				</select>
				<label for="search">Cari:</label>
				<input type="text" name="search" value="${search}" title="Cari berdasarkan NAMA CUSTOMER">
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
				<th>Sejak</th>
				<th>Nama</th>
				<th>Total Harga</th>
				<th>Potongan</th>
				<th>Sudah Dibayar</th>
				<th>Sisa Hutang</th>
				<th>Limit Hutang</th>
				<th>Tindakan</th>
			</tr>
			<c:forEach items="${listCustomer}" var="c" varStatus="s">
			<tr>
				<td><fmt:formatDate value="${c.since}" pattern="dd-MM-yyyy"/></td>
				<td>${c.nama}</td>
				<td class="right"><fmt:formatNumber value="${c.total_harga}"  currencySymbol="Rp " /></td>
				<td class="right"><fmt:formatNumber value="${c.potongan}"  currencySymbol="Rp " /></td>
				<td class="right"><span style="color: blue;"><fmt:formatNumber value="${c.paid}"  currencySymbol="Rp " /></span></td>
				<td class="right"><span style="color: red;"><fmt:formatNumber value="${c.remain}"  currencySymbol="Rp " /></span></td>
				<td class="right"><fmt:formatNumber value="${c.limit_hutang}"  currencySymbol="Rp " /></td>
				<td>
					<button onclick="cetak(${c.id}); return false;">Cetak</button>
				</td>
			</tr>
			</c:forEach>
		</table>
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
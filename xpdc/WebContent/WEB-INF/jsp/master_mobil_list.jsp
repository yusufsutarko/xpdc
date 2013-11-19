<%@ include file="/WEB-INF/jsp/include_taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>XPDC</title>
<%@ include file="/WEB-INF/jsp/include_head.jsp"%>
</head>
<body>

${sessionScope.currentUser.menuAkses}

<div class="tabs">
	<ul>
		<li><a href="#tabs-1">Home &raquo; Master Mobil</a></li>
	</ul>
	<div id="tabs-1">
		<form method="post">
		<table class="gridTables">
			<caption>
				Cari:
				<input type="text" name="search" value="${search}" title="Cari berdasarkan NOMOR POLISI">
				Tampilkan:
				<select name="rowcount" onchange="form.submit();">
					<c:forEach var="r" begin="10" step="10" end="100">
					<option <c:if test="${r eq rowcount}">selected</c:if> value="${r}">${r}</option>
					</c:forEach>
				</select>
				Halaman:
				<select name="page" onchange="form.submit();">
					<c:forEach var="p" begin="1" end="${pages}">
					<option <c:if test="${p eq page}">selected</c:if> value="${p}">${p}</option>
					</c:forEach>
				</select>
			</caption>
			<tr>
				<th>No Polisi</th>
				<th>Jenis Mobil</th>
				<th>KM Awal</th>
				<th>Harga</th>
				<th>Aktif</th>
				<th>Dibuat</th>
				<th>Tindakan</th>
			</tr>
			<c:forEach items="${listMobil}" var="u" varStatus="s">
			<tr>
				<td>${u.no_polisi}</td>
				<td>${u.jenisName}</td>
				<td class="right"><fmt:formatNumber value="${u.km_awal}" /></td>
				<td class="right"><fmt:formatNumber value="${u.harga}" type="currency" currencySymbol="Rp " /></td>
				<td><c:forEach items="${reff.listActive}" var="a"><c:if test="${a.key eq u.active}">${a.value}</c:if></c:forEach></td>
				<td><fmt:formatDate value="${u.createdate}" pattern="dd-MM-yy (HH:mm:ss)"/></td>
				<td>
					<button onclick="window.location='${path}/master/mobil/update/${u.no_polisi}'; return false;">Rubah</button>
				</td>
			</tr>
			</c:forEach>
		</table>
		<p>
			<button onclick="window.location='${path}/master/mobil/insert'; return false;">Tambah Baru</button>
		</p>
		</form>
	</div>
</div>

</body>
</html>
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
		<li><a href="#tabs-1">Home &raquo; Master Cabang</a></li>
	</ul>
	<div id="tabs-1">
		<form method="post">
		<table class="gridTables">
			<caption>
				Cari:
				<input type="text" name="search" value="${search}" title="Cari berdasarkan NAMA CABANG">
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
				<th>Id</th>
				<th>Kode</th>
				<th>Nama</th>
				<th>Aktif</th>
				<th>Dibuat</th>
				<th>Tindakan</th>
			</tr>
			<c:forEach items="${listCabang}" var="u" varStatus="s">
			<tr>
				<td>${u.id}</td>
				<td>${u.kode}</td>
				<td>${u.nama}</td>
				<td><c:forEach items="${reff.listActive}" var="a"><c:if test="${a.key eq u.active}">${a.value}</c:if></c:forEach></td>
				<td><fmt:formatDate value="${u.createdate}" pattern="dd-MM-yy (HH:mm:ss)"/></td>
				<td>
					<button onclick="window.location='${path}/master/cabang/update/${u.id}'; return false;">Rubah</button>
				</td>
			</tr>
			</c:forEach>
		</table>
		<p>
			<button onclick="window.location='${path}/master/cabang/insert'; return false;">Tambah Baru</button>
		</p>
		</form>
	</div>
</div>

</body>
</html>
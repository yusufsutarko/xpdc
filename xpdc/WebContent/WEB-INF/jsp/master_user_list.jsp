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
		<li><a href="#tabs-1">Home &raquo; Master User</a></li>
	</ul>
	<div id="tabs-1">
		<form method="post">
		<table class="gridTables">
			<caption>
				Cari:
				<input type="text" name="search" value="${search}" title="Cari berdasarkan USERNAME">
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
				<th>Cabang</th>
				<th>Username</th>
				<th>Grup</th>
				<th>Aktif</th>
				<th>Login Terakhir</th>
				<th>Dibuat</th>
				<th>Tindakan</th>
			</tr>
			<c:forEach items="${listUser}" var="u" varStatus="s">
			<tr>
				<td>${u.id}</td>
				<td><c:forEach items="${reff.listCabang}" var="c"><c:if test="${c.key eq u.cabang_id}">${c.value}</c:if></c:forEach></td>
				<td>${u.username}</td>
				<td><c:forEach items="${reff.listGroupUser}" var="g"><c:if test="${g.key eq u.group_user_id}">${g.value}</c:if></c:forEach></td>
				<td><c:forEach items="${reff.listActive}" var="a"><c:if test="${a.key eq u.active}">${a.value}</c:if></c:forEach></td>
				<td><fmt:formatDate value="${u.lastlogin}" pattern="dd-MM-yy (HH:mm:ss)"/></td>
				<td><fmt:formatDate value="${u.createdate}" pattern="dd-MM-yy (HH:mm:ss)"/></td>
				<td>
					<button onclick="window.location='${path}/master/user/update/${u.id}'; return false;">Rubah</button>
				</td>
			</tr>
			</c:forEach>
		</table>
		<p>
			<button onclick="window.location='${path}/master/user/insert'; return false;">Tambah Baru</button>
			<button onclick="window.location='${path}/master/akses'; return false;">Hak Akses</button>
		</p>
		</form>
	</div>
</div>

</body>
</html>
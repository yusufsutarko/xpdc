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
		<li><a href="#tabs-1">Home &raquo; Master Akses &raquo; Edit</a></li>
	</ul>
	<div id="tabs-1">
		<form:form commandName="groupUser" action="${path}/master/akses/save" method="POST" accept-charset="utf-8">
		<h3>Grup User</h3>
		<table class="gridTables">
			<tr>
				<th>ID:</th>
				<td><form:input path="id" readonly="true"/></td>
			</tr>
			<tr>
				<th>Nama:</th>
				<td><form:input path="nama" title="Nama Group User" cssErrorClass="fieldError"/></td>
			</tr>
		</table>
		<h3>Hak Akses</h3>
		<table class="gridTables">
			<tr>
				<th>Menu</th>
				<th>Sub Menu</th>
				<th>Aktif</th>
			</tr>
			<c:forEach var="h" items="${groupUser.listHakAkses}" varStatus="s">
			<tr>
				<td>${h.parent_nama}</td>
				<td>${h.nama}
					<form:hidden path="listHakAkses[${s.index}].parent_nama"/>
					<form:hidden path="listHakAkses[${s.index}].nama"/>
					<form:hidden path="listHakAkses[${s.index}].group_user_id"/>
					<form:hidden path="listHakAkses[${s.index}].menu_id"/>
				</td>
				<td class="buttonset"><form:radiobuttons path="listHakAkses[${s.index}].active" items="${reff.listActive}" itemValue="key" itemLabel="value"/></td>
			</tr>
			</c:forEach>
		</table>
		<spring:hasBindErrors name="groupUser">
			<div id="messageBox">
			<p class="ui-state-error ui-corner-all">
			<strong>Error:</strong><br/>
			<form:errors path="*" />
			</p>
			</div>
		</spring:hasBindErrors>
		<p>
			<button onclick="if(confirm('Simpan data?')) form.submit(); return false;">Simpan</button>
			<button onclick="window.location='${path}/master/akses/insert'; return false;">Baru</button>
		</p>
		</form:form>
		<button onclick="window.location='${path}/master/akses'; return false;">&laquo; Kembali</button>
	</div>
</div>

</body>
</html>
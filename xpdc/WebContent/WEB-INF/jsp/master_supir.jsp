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
		<li><a href="#tabs-1">Home &raquo; Master Supir &raquo; Edit</a></li>
	</ul>
	<div id="tabs-1">
		<form:form commandName="supir" action="${path}/master/supir/save" method="POST" accept-charset="utf-8">
		<table class="gridTables">
			<tr>
				<th>ID:</th>
				<td><form:input path="id" readonly="true"/></td>
			</tr>
			<tr>
				<th>Nama:</th>
				<td><form:input path="nama"/></td>
			</tr>
			<tr>
				<th>Tgl Masuk:</th>
				<td><form:input path="tgl_mulai" cssClass="text_field datepicker"/></td>
			</tr>
			<tr>
				<th>Tgl Keluar:</th>
				<td><form:input path="tgl_berakhir" cssClass="text_field datepicker"/></td>
			</tr>
			<tr>
				<th>Aktif:</th>
				<td class="buttonset"><form:radiobuttons path="active" items="${reff.listActive}" itemValue="key" itemLabel="value"/></td>
			</tr>
		</table>
		<br>
		<table class="gridTables">
			<tr>
				<th>Dibuat Oleh:</th>
				<td><form:input path="createuser" readonly="true" tabindex="-1"/></td>
				<th>Dirubah Oleh:</th>
				<td><form:input path="modifyuser" readonly="true" tabindex="-1"/></td>
			</tr>
			<tr>
				<th>Dibuat Tgl:</th>
				<td><form:input path="createdate" readonly="true" tabindex="-1"/></td>
				<th>Dirubah Tgl:</th>
				<td><form:input path="modifydate" readonly="true" tabindex="-1"/></td>
			</tr>
		</table>
		<spring:hasBindErrors name="supir">
			<div id="messageBox">
			<p class="ui-state-error ui-corner-all">
			<strong>Error:</strong><br/>
			<form:errors path="*" />
			</p>
			</div>
		</spring:hasBindErrors>
		<p>
			<button onclick="if(confirm('Simpan data?')) form.submit(); return false;">Simpan</button>
			<button onclick="window.location='${path}/master/supir/insert'; return false;">Baru</button>
		</p>
		</form:form>
		<button onclick="window.location='${path}/master/supir'; return false;">&laquo; Kembali</button>
	</div>
</div>

</body>
</html>
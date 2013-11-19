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
		<li><a href="#tabs-1">Home &raquo; Master Customer &raquo; Edit</a></li>
	</ul>
	<div id="tabs-1">
		<form:form commandName="customer" action="${path}/master/customer/save" method="POST" accept-charset="utf-8">
		<table class="gridTables">
			<tr>
				<th>ID:</th>
				<td><form:input path="id" readonly="true"/></td>
			</tr>
			<tr>
				<th>Kode:</th>
				<td><form:input path="kode" readonly="true"/></td>
			</tr>
			<tr>
				<th>Type:</th>
				<td class="buttonset"><form:radiobuttons path="type" items="${reff.listType}" itemValue="key" itemLabel="value"/></td>
			</tr>
			<tr>
				<th>Nama:</th>
				<td><form:input path="nama"/></td>
			</tr>
			<tr>
				<th>Alamat:</th>
				<td><form:textarea path="alamat" rows="3" cols="50"/></td>
			</tr>
			<tr>
				<th>Kota:</th>
				<td><form:input path="kota"/></td>
			</tr>
			<tr>
				<th>Contact Person:</th>
				<td><form:input path="contact"/></td>
			</tr>
			<tr>
				<th>No Telp:</th>
				<td><form:input path="no_telp"/></td>
			</tr>
			<tr>
				<th>No HP:</th>
				<td><form:input path="no_hp"/></td>
			</tr>
			<tr>
				<th>Email:</th>
				<td><form:input path="email"/></td>
			</tr>
			<tr>
				<th>No Fax:</th>
				<td><form:input path="no_fax"/></td>
			</tr>
			<tr>
				<th>Limit Hutang:</th>
				<td><form:input path="limit_hutang" cssClass="nominal" cssErrorClass="nominal fieldError"/></td>
			</tr>
			<tr>
				<th>Due Date:</th>
				<td><input type="text" readonly="true" value="<fmt:formatDate value="${user.due_date}" pattern="dd-MM-yyyy"/>"></td>
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
		<spring:hasBindErrors name="customer">
			<div id="messageBox">
			<p class="ui-state-error ui-corner-all">
			<strong>Error:</strong><br/>
			<form:errors path="*" />
			</p>
			</div>
		</spring:hasBindErrors>
		<p>
			<button onclick="if(confirm('Simpan data?')) form.submit(); return false;">Simpan</button>
			<button onclick="window.location='${path}/master/customer/insert'; return false;">Baru</button>
		</p>
		</form:form>
		<button onclick="window.location='${path}/master/customer'; return false;">&laquo; Kembali</button>
	</div>
</div>

</body>
</html>
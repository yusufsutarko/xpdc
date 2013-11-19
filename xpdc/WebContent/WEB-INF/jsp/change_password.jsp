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
		<li><a href="#tabs-1">Home &raquo; Change Password</a></li>
	</ul>
	<div id="tabs-1">
		<form:form commandName="user" action="${path}/changepass" method="POST" accept-charset="utf-8">
		<table class="gridTables">
			<tr>
				<th>ID:</th>
				<td><form:input path="id" readonly="true"/></td>
			</tr>
			<tr>
				<th>Username:</th>
				<td><form:input path="username" readonly="true"/></td>
			</tr>
			<tr>
				<th>Password Lama:</th>
				<td><form:password path="password" title="Silahkan masukkan password lama"/></td>
			</tr>
			<tr>
				<th>Password Baru:</th>
				<td><form:password path="new_password" title="Silahkan masukkan password baru"/></td>
			</tr>
			<tr>
				<th>Konfirmasi Password Baru:</th>
				<td><form:password path="confirm_password" title="Silahkan masukkan ulang password baru"/></td>
			</tr>
		</table>
		<spring:hasBindErrors name="user">
			<div id="messageBox">
			<p class="ui-state-error ui-corner-all">
			<strong>Error:</strong><br/>
			<form:errors path="*" />
			</p>
			</div>
		</spring:hasBindErrors>
		<p>
			<button onclick="if(confirm('Simpan data?')) form.submit(); return false;">Simpan</button>
		</p>
		</form:form>
	</div>
</div>

</body>
</html>
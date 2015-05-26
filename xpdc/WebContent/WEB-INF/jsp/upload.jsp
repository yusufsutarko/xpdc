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
		<li><a href="#tabs-1">Home &raquo; Upload</a></li>
	</ul>
	<div id="tabs-1">
		<form:form commandName="upload" action="${path}/upload" enctype="multipart/form-data" method="POST" accept-charset="utf-8">
		<table class="gridTables">
			<tr>
				<th>File to upload:</th>
				<td><form:input path="file" type="file"/></td>
			</tr>
			<tr>
				<th>Name:</th>
				<td><form:input path="nama"/></td>
			</tr>
		</table>
		<spring:hasBindErrors name="upload">
			<div id="messageBox">
			<p class="ui-state-error ui-corner-all">
			<strong>Error:</strong><br/>
			<form:errors path="*" />
			</p>
			</div>
		</spring:hasBindErrors>
		<p>
			<button onclick="if(confirm('Upload data?')) form.submit(); return false;">Upload</button>
		</p>
		</form:form>
	</div>
</div>

</body>
</html>
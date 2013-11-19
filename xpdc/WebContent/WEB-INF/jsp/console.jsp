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
		<li><a href="#tabs-1">Home &raquo; Console</a></li>
	</ul>
	<div id="tabs-1">
		<h3>User yang sedang Login</h3>
		
		<table class="gridTables">
			<tr>
				<th>No</th>
				<th>Username</th>
				<th>Logintime</th>
			</tr>
			<c:forEach items="${userMap}" var="u" varStatus="s">
			<tr>
				<td>${s.count}.</td>
				<td>${u.value.username}</td>
				<td><fmt:formatDate value="${u.value.lastlogin}" pattern="dd-MM-yy (HH:mm:ss)"/></td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>

</body>
</html>
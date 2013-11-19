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
		<li><a href="#tabs-1">Home</a></li>
	</ul>
	<div id="tabs-1">
		<h3>${company.name}</h3>
		<p>${company.address}</p>
		<h3>Informasi Login</h3>
		<p>Anda login sebagai <strong>${sessionScope.currentUser.username}</strong> sejak <strong><fmt:formatDate value="${sessionScope.currentUser.lastlogin}" pattern="dd-MM-yyyy (HH:mm:ss)"/></strong></p>
	</div>
</div>

</body>
</html>
<%@ include file="/WEB-INF/jsp/include_taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>XPDC</title>
<%@ include file="/WEB-INF/jsp/include_head.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$("#showDetail").click(function () {
		 $("#stackTrace").animate({
	      height:'toggle'
	    });
	});
});
</script>
</head>
<body>

${sessionScope.currentUser.menuAkses}

<div class="tabs">
	<ul>
		<li><a href="#tabs-1">ERROR</a></li>
	</ul>
	<div id="tabs-1">
		<p>Maaf telah terjadi kesalahan pada aplikasi.<br/>
		   Detail aplikasi sudah dikirim ke administrator dan dapat dilihat di bawah.
		</p>
		
		<br/><a href="${path}">&laquo; Kembali ke Halaman Utama</a>
		<br/><a href="#" onclick="history.go(-1);return false;">&laquo; Kembali ke Halaman Sebelumnya</a>
		<br/><br/>
		<button id="showDetail">Show / Hide Error Detail</button>
	
		<div id="stackTrace" style="display: none;">
			<pre>${exception}</pre>
		</div>
	</div>
</div>

</body>
</html>

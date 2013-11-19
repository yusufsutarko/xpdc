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
		<li><a href="#tabs-1">Home &raquo; Laporan Penerimaan & Pengeluaran Uang</a></li>
	</ul>
	<div id="tabs-1">
		<form method="POST" target="_blank">
		<table class="gridTables">
			<tr>
				<th>Jenis Report:</th>
				<td>
					<input type="radio" name="jenis_report" value="1" checked="checked"><label>Summary</label>
					<input type="radio" name="jenis_report" value="2"><label>Detail</label>
				</td>
			</tr>
			<tr>
				<th>Tanggal Pembayaran:</th>
				<td><input name="beg_date" class="datepicker" value=${tgl_awal}> s/d <input name="end_date" class="datepicker" value=${tgl_akhir}></td>
			</tr>
			<tr>
				<th>Format Laporan:</th>
				<td>
					<select name="format">
						<c:forEach items="${reff.listFormat}" var="f">
							<option value="${f.key}">${f.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		<p>
			<input type="hidden" name="show" value="1">
			<button onclick="form.submit(); return false;">Tampilkan</button>
		</p>
		</form>
	</div>
</div>

</body>
</html>
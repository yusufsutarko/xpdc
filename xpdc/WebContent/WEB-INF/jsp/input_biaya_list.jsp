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
		<li><a href="#tabs-1">Home &raquo; Input Uang Masuk / Biaya Lain-lain</a></li>
	</ul>
	<div id="tabs-1">
		<form method="post">
		<table class="gridTables">
			<caption>
				<label for="from" title="Tanggal Transaksi">Periode:</label>
				<input type="text" id="periodFrom" name="periodFrom" class="datepicker-disabled" value="${periodFrom}" />
				<label for="to">s/d</label>
				<input type="text" id="periodTo" name="periodTo" class="datepicker-disabled" value="${periodTo}" />
				<label for="search">Cari:</label>
				<input type="text" name="search" value="${search}" title="Cari berdasarkan NOMOR PAYMENT / KETERANGAN / CARA BAYAR">
				<label for="rowcount">Tampilkan:</label>
				<select name="rowcount" onchange="form.submit();">
					<c:forEach var="r" begin="10" step="10" end="100">
					<option <c:if test="${r eq rowcount}">selected</c:if> value="${r}">${r}</option>
					</c:forEach>
				</select>
				<label for="page">Halaman:</label>
				<select name="page" onchange="form.submit();">
					<c:forEach var="p" begin="1" end="${pages}">
					<option <c:if test="${p eq page}">selected</c:if> value="${p}">${p}</option>
					</c:forEach>
				</select>
			</caption>
			<tr>
				<th>No Payment</th>
				<%-- <th>No STT</th> --%>
				<th>Tgl Transaksi</th>
				<th>D/K</th>
				<th>Nominal</th>
				<th>Keterangan</th>
				<th>Pembayaran</th>
				<th>Tgl JT Giro</th>
				<th>Batal</th>
				<th>Dibuat</th>
				<th>Tindakan</th>
			</tr>
			<c:forEach items="${listPayment}" var="d" varStatus="s">
			<tr>
				<td>${d.no_payment}</td>
				<%-- <td>${d.no_stt}</td> --%>
				<td><fmt:formatDate value="${d.paid_date}" pattern="dd-MM-yyyy"/></td>
				<td class="center">
					<c:choose>
						<c:when test="${d.dk eq \"K\"}"><span style="color: red;">${d.dk}</span></c:when>
						<c:when test="${d.dk eq \"D\"}"><span style="color: green;">${d.dk}</span></c:when>
						<c:otherwise>${d.dk}</c:otherwise>
					</c:choose>
				</td>
				<td class="right"><fmt:formatNumber value="${d.nominal}" type="currency" currencySymbol="Rp " /></td>
				<td>${d.keterangan}</td>
				<td class="center">${d.pay_ket}</td>
				<td><fmt:formatDate value="${d.due_date}" pattern="dd-MM-yyyy"/></td>
				<td class="center">
					<c:choose>
						<c:when test="${d.cancel eq 0}"><span style="color: blue;">Tidak</span></c:when>
						<c:when test="${d.cancel eq 1}"><span style="color: red;">Ya</span></c:when>
					</c:choose>
				</td>
				<td><fmt:formatDate value="${d.createdate}" pattern="dd-MM-yyyy (HH:mm:ss)"/></td>
				<td>
					<c:choose>
						<c:when test="${d.cancel eq 0}">
							<button onclick="window.location='${path}/transaksi/biaya/update/${d.payment_id}'; return false;">Rubah</button>
						</c:when>
						<c:when test="${d.cancel eq 1}">
							<button onclick="window.location='${path}/transaksi/biaya/update/${d.payment_id}'; return false;">Lihat</button>
						</c:when>
					</c:choose>
				</td>
			</tr>
			</c:forEach>
		</table>
		<p>
			<button onclick="window.location='${path}/transaksi/biaya/insert'; return false;">Tambah Baru</button>
		</p>
		</form>
	</div>
</div>

</body>
</html>
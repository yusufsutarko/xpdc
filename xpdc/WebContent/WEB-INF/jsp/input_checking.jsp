<%@ include file="/WEB-INF/jsp/include_taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>XPDC</title>
<%@ include file="/WEB-INF/jsp/include_head.jsp"%>
<script type="text/javascript">

//hitung2 total
function hitungDet(){
	var totalNaik = 0;
	var totalNominal = 0;
	
	//hitung jumlah baris dalam table DeliveryDet
	var rows =  $("#tableDeliveryDet >tbody >tr").length;
	
	//lakukan looping semua row
	for(i=0; i<rows; i++){
		//var nama_barang = $("input[name=\"listDeliveryDet[" +i+ "].nama_barang\"]").val();
		//var colly_total = getNumber($("input[name=\"listDeliveryDet[" +i+ "].colly_total\"]").val());
		var colly_naik = getNumber($("input[name=\"listDeliveryDet[" +i+ "].colly_naik\"]").val());
		//var colly_sisa = colly_total - colly_naik;
		//if(colly_sisa < 0) {
		//	alert("Maksimum colly naik untuk barang " + nama_barang + " adalah " + colly_total);
		//	return false;
		//}
	    //$("input[name=\"listDeliveryDet[" +i+ "].colly_sisa\"]").val(formatCurrency(colly_sisa));	    
		
	    var nominal = getNumber($("input[name=\"listDeliveryDet[" +i+ "].nominal\"]").val());
	    totalNaik += colly_naik;
	    totalNominal += nominal;
	}

	$("input[name=\"total_naik\"]").val(formatCurrency(totalNaik));
	$("input[name=\"total_nominal\"]").val(formatCurrency(totalNominal));	    
	//if (window.console) console.log( "totalNaik = " + formatCurrency(totalNaik) + ", totalNominal = " + formatCurrency(totalNominal));
	
}

//hitung2 total
function hitungBiaya(){
	var totalBiaya = 0;
	
	//hitung jumlah baris dalam table DeliveryCost
	var rows =  $("#tableDeliveryCost >tbody >tr").length;
	
	//lakukan looping semua row
	for(i=0; i<rows; i++){
		var biaya = getNumber($("input[name=\"listDeliveryCost[" +i+ "].nominal\"]").val());
		totalBiaya += biaya;
	}

	$("input[name=\"total_biaya\"]").val(formatCurrency(totalBiaya));	    
}

//fungsi kopi colly naik ke colly sampai
function kopi(i){
// 	alert (
// 			$("input[name='listDeliveryDet[" +i+ "].colly_sampai']").val()
// 	);
	
	$("input[name='listDeliveryDet[" +i+ "].colly_sampai']").val($("input[name='listDeliveryDet[" +i+ "].colly_naik']").val());
	
}

//onload
$(function() {
	$(".tableBarang").styleTable();
	$(".tableBiaya").styleTable();
	hitungDet();
	hitungBiaya();
});
</script>
</head>
<body>

${sessionScope.currentUser.menuAkses}

<div class="tabs">
	<ul>
		<li><a href="#tabs-1">Home &raquo; Checking Penerimaan Barang (Rincian Barang Terkirim)</a></li>
	</ul>
	<div id="tabs-1" class="ui-widget">
		<form:form commandName="sp" action="${path}/transaksi/checking/save" method="POST" accept-charset="utf-8">
		<table class="gridTables">
			<tr>
				<th>No SP:</th>
				<td><form:hidden path="id" />${sp.id}</td>
				<th>Tgl Muat:</th>
				<td><fmt:formatDate value="${sp.tgl_kirim}" pattern="dd-MM-yyyy"/></td>
				<th><form:label path="tgl_sampai">Tgl Sampai:<span class="mandatory">*</span></form:label></th>
				<td><form:input path="tgl_sampai" cssClass="datepicker" cssErrorClass="fieldError datepicker"/></td>
			</tr>
			<tr>
				<th>Mobil/No Polisi:</th>
				<td>${sp.no_polisi}</td>
				<th>Supir:</th>
				<td>${sp.supir_nama}</td>
				<th>Kode Kapal:</th>
				<td>${sp.kapal_nama}</td>
			</tr>
		</table>
		<br>
		<table class="gridTables">
			<tr>
				<th>Rincian Barang<span class="mandatory">*</span></th>
				<td>
					<table class="tableBarang" id="tableDeliveryDet">
						<thead>
						<tr>
							<th>No</th>
							<th>No STT</th>
							<th>Toko/Nama</th>
							<%-- <th>Colly</th> --%>
							<th>Barang</th>
							<th>Nominal</th>
							<th>Keterangan</th>
							<th>Colly Naik / Sisa</th>
							<th>Cek<span class="mandatory">*</span></th>
							<th>Colly Sampai</th>
							<th>Catatan Penerimaan<span class="mandatory">*</span></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${sp.listDeliveryDet}" var="d" varStatus="s">
						<tr>
							<td>${s.count}.</td>
							<td class="center">
								<form:hidden path="listDeliveryDet[${s.index}].no_stt" />
								<form:hidden path="listDeliveryDet[${s.index}].colly_naik" />
								<form:hidden path="listDeliveryDet[${s.index}].nominal" />
								<form:hidden path="listDeliveryDet[${s.index}].trans_id" />
								<form:hidden path="listDeliveryDet[${s.index}].trans_urut" />
								${d.no_stt}
							</td>
							<td>${d.customer_nama}</td>
							<%-- <td class="center"><fmt:formatNumber value="${d.colly}" /></td> --%>
							<td>${d.nama_barang}</td>
							<td class="right"><fmt:formatNumber value="${d.nominal}" /></td>
							<td>${d.note}</td>
							<td class="center"><fmt:formatNumber value="${d.colly_naik}" /> / <fmt:formatNumber value="${d.colly_sisa}" /></td>
							<td><form:checkbox path="listDeliveryDet[${s.index}].flag" /></td>
							<td>
								<form:input path="listDeliveryDet[${s.index}].colly_sampai" cssClass="right" size="2"/>
								<button onclick="kopi(${s.index}); return false;" title="Copy Colly Naik">C</button>
							</td>
							<td><form:textarea path="listDeliveryDet[${s.index}].note_delivery" cssClass="right" rows="1" cols="25"/></td>
						</tr>
						</c:forEach>
						</tbody>
						<tfoot>
						<tr>
							<td colspan="5" class="right"><strong>Total:</strong></td>
							<td><input type="text" name="total_naik" size="6" readonly="true" tabindex="-1" class="right"/></td>
							<td><input type="text" name="total_nominal" readonly="true" tabindex="-1" class="right"/></td>
							<td colspan="3"></td>
						</tr>
						</tfoot>
					</table>
				</td>
			</tr>
			<tr>
				<th>Rincian Biaya (BB & Trip)</th>
				<td>
					<table class="tableBiaya" id="tableDeliveryCost">
						<thead>
						<tr>
							<th>No</th>
							<th>Item</th>
							<th>Nominal</th>
							<th>Keterangan</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${sp.listDeliveryCost}" var="c" varStatus="s">
						<tr>
							<td>${s.count}.</td>
							<td>
								<form:hidden path="listDeliveryCost[${s.index}].biaya_id" />
								<form:hidden path="listDeliveryCost[${s.index}].nominal" />
								${c.nama_biaya}
							</td>
							<td class="right"><fmt:formatNumber value="${c.nominal}" /></td>
							<td>${c.note}</td>
						</tr>
						</c:forEach>
						</tbody>
						<tfoot>
						<tr>
							<td colspan="2" class="right"><strong>Total:</strong></td>
							<td class="right"><input type="text" name="total_biaya" readonly="true" tabindex="-1" class="right"/></td>
							<td></td>
						</tr>
						</tfoot>
					</table>
				</td>
			</tr>
			<tr>
				<th><form:label path="cancel">Status Batal:</form:label></th>
				<td>
					<c:choose>
						<c:when test="${sp.cancel eq 0}"><span style="color: blue;">Tidak</span></c:when>
						<c:when test="${sp.cancel eq 1}"><span style="color: red;">Ya</span></c:when>
					</c:choose>
				</td>
			</tr>
		</table>
		<br>
		<table class="gridTables">
			<tr>
				<th>Dibuat Oleh:</th>
				<td>${sp.createuser}</td>
				<th>Dirubah Oleh:</th>
				<td>${sp.modifyuser}</td>
				<th>Dibatalkan Oleh:</th>
				<td>${sp.canceluser}</td>
			</tr>
			<tr>
				<th>Dibuat Tgl:</th>
				<td><fmt:formatDate value="${sp.createdate}" pattern="dd-MM-yyyy (HH:mm)"/></td>
				<th>Dirubah Tgl:</th>
				<td><fmt:formatDate value="${sp.modifydate}" pattern="dd-MM-yyyy (HH:mm)"/></td>
				<th>Dibatalkan Tgl:</th>
				<td><fmt:formatDate value="${sp.canceldate}" pattern="dd-MM-yyyy (HH:mm)"/></td>
			</tr>
		</table>
		<spring:hasBindErrors name="sp">
			<div id="messageBox">
			<p class="ui-state-error ui-corner-all">
			<strong>Error:</strong><br/>
			<form:errors path="*" />
			</p>
			</div>
		</spring:hasBindErrors>
		<%-- Bila status sudah cancelled, tidak bisa pencet tambah/save --%>
		<c:if test="${sp.cancel ne 1}">
			<p><button onclick="if(confirm('Simpan data?')) form.submit(); return false;">Simpan</button></p>
		</c:if>
		</form:form>
		<button onclick="window.location='${path}/transaksi/checking'; return false;">&laquo; Kembali</button>
	</div>
</div>

</body>
</html>
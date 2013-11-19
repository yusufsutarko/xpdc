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

//init fungsi autocomplete di semua input box dengan class tertentu
function initAutoComplete(nama){
	var cache1 = {};
	var cache2 = {};

	if(nama == "stt"){
		$(".stt").each(function(i) {
			//if (window.console) console.log( i + ": " + $(this).attr("name") + " = " + $(this).hasClass("ui-autocomplete-input"));
			if(!$(this).hasClass("ui-autocomplete-input")){ //jgn di-init untuk seluruh element, melainkan hanya yg belum di-init saja.
				$(this).autocomplete({
					minLength: 2,
					source: function( request, response ) {
						var term = request.term;
						if ( term in cache1 ) {
							response( cache1[ term ] );
							return;
						}
						
						$.getJSON( "${path}/json/stt/" + term, request, function( data, status, xhr ) {
							cache1[ term ] = data;
							response( data );
						});
					},
					select: function( event, ui ) {
						$(this).val( ui.item.no_stt ); //default action: isi inputbox dengan no_stt nya
						$(this).closest("tr").find("input[name$='trans_id']").val(ui.item.trans_id);
						$(this).closest("tr").find("input[name$='trans_urut']").val(ui.item.urut);
						$(this).closest("tr").find("input[name$='customer_nama']").val(ui.item.customer);
						$(this).closest("tr").find("input[name$='colly']").val(ui.item.colly_stt);
						$(this).closest("tr").find("input[name$='nama_barang']").val(ui.item.nama_barang);
						//$(this).closest("tr").find("input[name$='colly_total']").val(ui.item.colly_remain);
						$(this).closest("tr").find("input[name$='colly_naik']").val(ui.item.colly_remain);
						$(this).closest("tr").find("input[name$='colly_sisa']").val(ui.item.colly_remain);
						return false;
					}
				});
			}
		});
		
	}else if(nama == "biaya"){
		$(".biaya").each(function(i) {
			//if (window.console) console.log( i + ": " + $(this).attr("name") + " = " + $(this).hasClass("ui-autocomplete-input"));
			if(!$(this).hasClass("ui-autocomplete-input")){ //jgn di-init untuk seluruh element, melainkan hanya yg belum di-init saja.
				$(this).autocomplete({
					minLength: 2,
					source: function( request, response ) {
						var term = request.term;
						if ( term in cache2 ) {
							response( cache2[ term ] );
							return;
						}
						
						$.getJSON( "${path}/json/biaya/" + term, request, function( data, status, xhr ) {
							cache2[ term ] = data;
							response( data );
						});
					},
					select: function( event, ui ) {
						$(this).val( ui.item.nama ); //default action: isi inputbox dengan nama biaya nya
						$(this).closest("tr").find("input[name$='biaya_id']").val(ui.item.id);
						$(this).closest("tr").find("input[name$='nominal']").val(ui.item.nominal);
						return false;
					}
				});
			}
		});

	}
		
}
	
//tambah row ke tabelDeliveryDet
function tambahRowBarang(){
	//hitung jumlah baris dalam table tersebut
	var rows =  $("#tableDeliveryDet >tbody >tr").length;
	
	$("#tableDeliveryDet").append("<tr id=\"baris" +rows+ "\">" +
		"<td>" +(rows+1)+ ".</td>" + //Urutan saja
		"<td><input type=\"text\" name=\"listDeliveryDet[" +rows+ "].no_stt\" size=\"10\" class=\"stt\" />" + //no stt
		"<input type=\"hidden\" name=\"listDeliveryDet[" +rows+ "].trans_id\" />" + //PK trans det
		"<input type=\"hidden\" name=\"listDeliveryDet[" +rows+ "].trans_urut\" /></td>" + //PK trans det
		//"<input type=\"hidden\" name=\"listDeliveryDet[" +rows+ "].colly_total\" /></td>" + //total colly 
		"<td><input type=\"text\" name=\"listDeliveryDet[" +rows+ "].customer_nama\" readonly=\"true\" tabindex=\"-1\" /></td>" + //customer_nama
		"<td><input type=\"text\" name=\"listDeliveryDet[" +rows+ "].colly\" size=\"6\" readonly=\"true\" tabindex=\"-1\" /></td>" + //colly
		"<td><input type=\"text\" name=\"listDeliveryDet[" +rows+ "].nama_barang\" readonly=\"true\" tabindex=\"-1\" /></td>" + //nama_barang
		"<td><input type=\"text\" name=\"listDeliveryDet[" +rows+ "].colly_naik\" size=\"6\" class=\"nominal\" onchange=\"return hitungDete);\" /> / " + //colly_naik
		"<input type=\"text\" name=\"listDeliveryDet[" +rows+ "].colly_sisa\" size=\"6\" readonly=\"true\" tabindex=\"-1\" class=\"right\" /></td>" + //colly_sisa
		"<td><input type=\"text\" name=\"listDeliveryDet[" +rows+ "].nominal\" class=\"nominal\" onchange=\"return hitungDet();\" /></td>" + //nominal
		"<td><textarea name=\"listDeliveryDet[" +rows+ "].note\" class=\"right\" rows=\"1\" cols=\"25\" /></td>" + //note
		"<td><input type=\"checkbox\" name=\"listDeliveryDet[" +rows+ "].flag_delete\" value=\"true\"></td>" + //tombol hapus
 	"</tr>");

	//semua function yg perlu di re-initialize
	initAutoComplete("stt");
	$(".tableBarang").styleTable(); 
	$("#tableDeliveryDet").find("input[readonly]").addClass("ui-widget-header"); 

	$("#tableDeliveryDet").find(".nominal")
		.change(function(){$(this).val(formatCurrency($(this).val()));$("#nominal").text("");})
		.keyup(function(){$("#nominal").text(formatCurrency($(this).val()));
	}).addClass("right");
	
	$("#tableDeliveryDet").find("input[type=text], select, textarea")
		.addClass("ui-widget-content ui-corner-all") 
		.click(function() {
			$(this).select();
		});
	
	return false;
}

//tambah row ke tabelDeliveryCost
function tambahRowBiaya(){
	//hitung jumlah baris dalam table tersebut
	var rows =  $("#tableDeliveryCost >tbody >tr").length;

	$("#tableDeliveryCost").append("<tr>" +
		"<td>" +(rows+1)+ ".</td>" + //Urutan saja
		"<td><input type=\"hidden\" name=\"listDeliveryCost[" +rows+ "].biaya_id\" />" + //PK delivery det
		"<input type=\"text\" name=\"listDeliveryCost[" +rows+ "].nama_biaya\" class=\"biaya\" /></td>" + //nama_biaya
		"<td><input type=\"text\" name=\"listDeliveryCost[" +rows+ "].nominal\" class=\"nominal\" onchange=\"return hitungBiaya();\" /></td>" + //nominal
		"<td><textarea name=\"listDeliveryCost[" +rows+ "].note\" class=\"right\" rows=\"1\" cols=\"25\" /></td>" + //note
		"<td><input type=\"checkbox\" name=\"listDeliveryCost[" +rows+ "].flag_delete\" value=\"true\"></td>" + //tombol hapus
	"</tr>");

	//semua function yg perlu di re-initialize
	initAutoComplete("biaya");
	$(".tableBiaya").styleTable(); 
	$("#tableDeliveryCost").find("input[readonly]").addClass("ui-widget-header"); 

	$("#tableDeliveryCost").find(".nominal")
		.change(function(){$(this).val(formatCurrency($(this).val()));$("#nominal").text("");})
		.keyup(function(){$("#nominal").text(formatCurrency($(this).val()));
	}).addClass("right");
	
	$("#tableDeliveryCost").find("input[type=text], select, textarea")
		.addClass("ui-widget-content ui-corner-all") 
		.click(function() {
			$(this).select();
		});
	
	return false;
}

//onclick tombol submit
function simpan(){
	if($("select[name='cancel']").val() == 1){
		if(confirm('Anda yakin membatalkan/menghapus data? Data yang sudah dibatalkan tidak bisa digunakan kembali.')) form.submit();
	}else{
		if(confirm('Simpan data?')) form.submit(); 
	}
	return false;
}

//onload
$(function() {
	initAutoComplete("stt");
	initAutoComplete("biaya");
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
		<li><a href="#tabs-1">Home &raquo; Input SP (Rincian Barang Terkirim) &raquo; Edit</a></li>
	</ul>
	<div id="tabs-1" class="ui-widget">
		<form:form commandName="sp" action="${path}/transaksi/sp/save" method="POST" accept-charset="utf-8">
		<table class="gridTables">
			<tr>
				<th><form:label path="id">No SP:</form:label></th>
				<td><form:input path="id" readonly="true" tabindex="-1" /></td>
				<th><form:label path="tgl_kirim">Tgl Muat:<span class="mandatory">*</span></form:label></th>
				<td><form:input path="tgl_kirim" cssClass="datepicker"/></td>
				<th><form:label path="tgl_sampai">Tgl Sampai:</form:label></th>
				<td><form:input path="tgl_sampai" cssClass="datepicker"/></td>
			</tr>
			<tr>
				<th><form:label path="no_polisi">Mobil/No Polisi:<span class="mandatory">*</span></form:label></th>
				<td><form:select path="no_polisi" items="${reff.listMobil}" itemValue="key" itemLabel="value" /></td>
				<th><form:label path="supir_id">Supir:<span class="mandatory">*</span></form:label></th>
				<td><form:select path="supir_id" items="${reff.listSupir}" itemValue="key" itemLabel="value" /></td>
				<th><form:label path="kode_kapal">Kode Kapal:</form:label></th>
				<td><form:select path="kode_kapal" items="${reff.listKapal}" itemValue="key" itemLabel="value" /></td>
			</tr>
		</table>
		<br>
		<table class="gridTables">
			<tr>
				<th>Rincian Barang</th>
				<td>
					<table class="tableBarang" id="tableDeliveryDet">
						<thead>
						<tr>
							<th>No</th>
							<th title="Nomor STT">No STT<span class="mandatory">*</span></th>
							<th title="Nama Penerima">Penerima</th>
							<th title="Total Colly Barang di STT tersebut">Colly</th>
							<th title="Nama Barang">Barang</th>
							<th title="Total Colly yang Naik di SP/RBT ini, dan sisa yang belum naik">Naik<span class="mandatory">*</span>/Sisa</th>
							<th title="Jumlah Nominal dalam Rupiah">Nominal<span class="mandatory">*</span></th>
							<th title="Keterangan Tambahan">Keterangan</th>
							<th title="Tandakan untuk menghapus baris tersebut">Hapus</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${sp.listDeliveryDet}" var="d" varStatus="s">
						<tr id="baris${s.index}">
							<td>${s.count}.</td>
							<td>
								<form:input path="listDeliveryDet[${s.index}].no_stt" size="10" cssClass="stt" cssErrorClass="stt fieldError" />
								<form:hidden path="listDeliveryDet[${s.index}].trans_id" />
								<form:hidden path="listDeliveryDet[${s.index}].trans_urut" />
								<%-- <form:input path="listDeliveryDet[${s.index}].colly_total" /> --%>
							</td>
							<td><form:input path="listDeliveryDet[${s.index}].customer_nama" readonly="true" tabindex="-1" /></td>
							<td><form:input path="listDeliveryDet[${s.index}].colly" size="6" readonly="true" tabindex="-1" /></td>
							<td><form:input path="listDeliveryDet[${s.index}].nama_barang" readonly="true" tabindex="-1" /></td>
							<td><form:input path="listDeliveryDet[${s.index}].colly_naik" size="6" cssClass="nominal" onchange="return hitungDet();" cssErrorClass="nominal fieldError"/> / <form:input path="listDeliveryDet[${s.index}].colly_sisa" size="6" readonly="true" tabindex="-1" cssClass="right" /></td>
							<td><form:input path="listDeliveryDet[${s.index}].nominal" cssClass="nominal" onchange="return hitungDet();" cssErrorClass="nominal fieldError" /></td>
							<td><form:textarea path="listDeliveryDet[${s.index}].note" cssClass="right" rows="1" cols="25"/></td>
							<td class="center"><form:checkbox path="listDeliveryDet[${s.index}].flag_delete" /></td>
						</tr>
						</c:forEach>
						</tbody>
						<tfoot>
						<tr>
							<td colspan="5" class="right"><strong>Total:</strong></td>
							<td><input type="text" name="total_naik" size="6" readonly="true" tabindex="-1" class="right"/></td>
							<td><input type="text" name="total_nominal" readonly="true" tabindex="-1" class="right"/></td>
						</tr>
						</tfoot>
					</table>
					<%-- Bila status sudah cancelled, tidak bisa pencet tambah/save --%>
					<c:if test="${sp.cancel ne 1}">
						<p><button onclick="return tambahRowBarang();">Tambah</button></p>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>Rincian Biaya (BB & Trip)</th>
				<td>
					<table class="tableBiaya" id="tableDeliveryCost">
						<thead>
						<tr>
							<th>No</th>
							<th title="Jenis Biaya">Item<span class="mandatory">*</span></th>
							<th title="Jumlah Biaya">Nominal<span class="mandatory">*</span></th>
							<th title="Keterangan Tambahan">Keterangan</th>
							<th title="Tandakan untuk menghapus baris tersebut">Hapus</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${sp.listDeliveryCost}" var="c" varStatus="s">
						<tr>
							<td>${s.count}.</td>
							<td>
								<form:hidden path="listDeliveryCost[${s.index}].biaya_id" />
								<form:input path="listDeliveryCost[${s.index}].nama_biaya" cssClass="biaya" cssErrorClass="biaya fieldError" />
							</td>
							<td><form:input path="listDeliveryCost[${s.index}].nominal" cssClass="nominal" onchange="return hitungBiaya();" cssErrorClass="nominal fieldError" /></td>
							<td><form:textarea path="listDeliveryCost[${s.index}].note" cssClass="right" rows="1" cols="25"/></td>
							<td class="center"><form:checkbox path="listDeliveryCost[${s.index}].flag_delete" /></td>
						</tr>
						</c:forEach>
						</tbody>
						<tfoot>
						<tr>
							<td colspan="2" class="right"><strong>Total:</strong></td>
							<td><input type="text" name="total_biaya" readonly="true" tabindex="-1" class="right"/></td>
						</tr>
						</tfoot>
					</table>
					<%-- Bila status sudah cancelled, tidak bisa pencet tambah/save --%>
					<c:if test="${sp.cancel ne 1}">
						<p><button onclick="return tambahRowBiaya();">Tambah</button></p>
					</c:if>
				</td>
			</tr>
			<tr>
				<th><form:label path="cancel">Status Batal:</form:label></th>
				<td><form:select path="cancel" items="${reff.listCancel}" itemValue="key" itemLabel="value"/></td>
			</tr>
		</table>
		<br>
		<table class="gridTables">
			<tr>
				<th>Dibuat Oleh:</th>
				<td><form:input path="createuser" readonly="true" tabindex="-1"/></td>
				<th>Dirubah Oleh:</th>
				<td><form:input path="modifyuser" readonly="true" tabindex="-1"/></td>
				<th>Dibatalkan Oleh:</th>
				<td><form:input path="canceluser" readonly="true" tabindex="-1"/></td>
			</tr>
			<tr>
				<th>Dibuat Tgl:</th>
				<td><input type="text" readonly="true" tabindex="-1" value="<fmt:formatDate value="${sp.createdate}" pattern="dd-MM-yyyy (HH:mm)"/>"></td>
				<th>Dirubah Tgl:</th>
				<td><input type="text" readonly="true" tabindex="-1" value="<fmt:formatDate value="${sp.modifydate}" pattern="dd-MM-yyyy (HH:mm)"/>"></td>
				<th>Dibatalkan Tgl:</th>
				<td><input type="text" readonly="true" tabindex="-1" value="<fmt:formatDate value="${sp.canceldate}" pattern="dd-MM-yyyy (HH:mm)"/>"></td>
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
			<p>
				<button onclick="return simpan();">Simpan</button>
				<button onclick="window.location='${path}/transaksi/sp/insert'; return false;">Baru</button>
			</p>
		</c:if>
		</form:form>
		<button onclick="window.location='${path}/transaksi/sp'; return false;">&laquo; Kembali</button>
	</div>
</div>

</body>
</html>
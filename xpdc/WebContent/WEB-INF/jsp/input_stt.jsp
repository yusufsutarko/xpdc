<%@ include file="/WEB-INF/jsp/include_taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>XPDC</title>
<%@ include file="/WEB-INF/jsp/include_head.jsp"%>
<script type="text/javascript">

//fungsi untuk menghitung qty x harga = jumlah, serta grand total dari seluruh jumlah
function hitung(){
	var total = 0;
	var totalColly = 0;
	
	//hitung jumlah baris dalam table TransDet
	var rows =  $("#tableTransDet >tbody >tr").length;
    
	//lakukan looping untuk menjumlah semuanya
	for(i=0; i<rows; i++){
		//ambil nilai qty & harga, lalu kalikan ke dalam var jml. ambil nilai colly juga
		var qty = getNumber($("input[name=\"listTransDet[" +i+ "].qty\"]").val());
		var harga = getNumber($("input[name=\"listTransDet[" +i+ "].harga\"]").val());
	    var jml = qty * harga;
		var colly = getNumber($("input[name=\"listTransDet[" +i+ "].colly\"]").val());
	    
		//if (window.console) console.log("baris = " + i + ", qty = " + qty + ", harga = " + harga + ", jml = " + jml + ", colly = " + colly);
		
	    //total harga seluruh, dan total colly
	    total += jml;
	    totalColly += colly;
	    
	    //set field jml = qty * harga
	    $("input[name=\"listTransDet[" +i+ "].jumlah\"]").val(formatCurrency(jml));	    
	}
	
	//if (window.console) console.log("total = " + total + ", totalColly = " + totalColly);
	
    //set field total harga = sum dari seluruh jml
    $("input[name=\"total_harga\"]").val(formatCurrency(total));
	//set field remain = total harga - potongan
	var potongan = getNumber($("input[name=\"potongan\"]").val());
    $("input[name=\"remain\"]").val(formatCurrency(total-potongan));
    //set field total colly = sum dari seluruh colly
    $("input[name=\"colly\"]").val(formatCurrency(totalColly));
	
}

//init fungsi autocomplete di semua input box dengan class "barang", "kastamer", "tujuannih"
function initAutoComplete(){
	var cache1 = {};
	var cache2 = {};
	var cache3 = {};
	
	$(".barang").each(function(i) {
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
					
					$.getJSON( "${path}/json/barang/" + $("#customer_id").val() + "/" + term, request, function( data, status, xhr ) {
						cache1[ term ] = data;
						response( data );
					});
				},
				select: function( event, ui ) {
					$(this).val( ui.item.value ); //default action: isi inputbox dengan value nya
					$(this).closest("tr").find("input[name$='harga']").val(formatCurrency(ui.item.desc)); //isi element dalam row yg sama yg namanya berakhir dengan "harga"
					//alert( ui.item.key );
					//alert( ui.item.value );
					//alert( ui.item.desc );
					return false;
				}
			});
		}
	});	

	$(".kastamer").each(function(i) {
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
					
					$.getJSON( "${path}/json/customer/" + term, request, function( data, status, xhr ) {
						cache2[ term ] = data;
						response( data );
					});
				},
				select: function( event, ui ) {
					$(this).val( ui.item.nama ); //default action: isi inputbox dengan nama cust
					$(this).closest("tr").find("input[name='customer_id']").val(ui.item.id); //isi element dalam row yg sama yg namanya= "customer_id"
					//alert( ui.item.key );
					//alert( ui.item.value );
					//alert( ui.item.desc );
					return false;
				}
			});
		}
	});	

	$(".tujuannih").each(function(i) {
		//if (window.console) console.log( i + ": " + $(this).attr("name") + " = " + $(this).hasClass("ui-autocomplete-input"));
		if(!$(this).hasClass("ui-autocomplete-input")){ //jgn di-init untuk seluruh element, melainkan hanya yg belum di-init saja.
			$(this).autocomplete({
				minLength: 2,
				source: function( request, response ) {
					var term = request.term;
					if ( term in cache3 ) {
						response( cache3[ term ] );
						return;
					}
					
					$.getJSON( "${path}/json/tujuan/" + term, request, function( data, status, xhr ) {
						cache3[ term ] = data;
						response( data );
					});
				},
				select: function( event, ui ) {
					$(this).val( ui.item.nama ); //default action: isi inputbox dengan nama cust
					$(this).closest("tr").find("input[name='tujuan_id']").val(ui.item.id);
					$(this).closest("tr").find("input[name='contact_tujuan']").val(ui.item.contact);
					$(this).closest("tr").find("input[name='telp_tujuan']").val(ui.item.no_telp);
					$(this).closest("tr").find("textarea[name='alamat_tujuan']").val(ui.item.alamat);
					//alert( ui.item.key );
					//alert( ui.item.value );
					//alert( ui.item.desc );
					return false;
				}
			});
		}
	});	

}

//tambah row ke tabel TransDet
function tambahRow(){
	//hitung jumlah baris dalam table tersebut
	var rows =  $("#tableTransDet >tbody >tr").length;
	
	var numRows = $("input[name=urut]").val(); //nomor urut terakhir dari TransDet
	numRows++; //tambahkan no urut terakhir
	$("input[name=urut]").val(numRows);

	$("#tableTransDet").append("<tr>" +
		"<td><input name=\"listTransDet[" +rows+ "].urut\" size=\"2\" readonly=\"true\" tabindex=\"-1\" value=\"" + numRows + "\" /></td>" + //Urut
		"<td><input name=\"listTransDet[" +rows+ "].colly\" size=\"6\" class=\"nominal\" type=\"text\" onchange=\"hitung();\" />" + //Colly
		"<input type=\"hidden\" name=\"listTransDet[" +rows+ "].colly_remain\" /></td>" + //Colly Remain
		"<td><input name=\"listTransDet[" +rows+ "].nama_barang\" class=\"barang\" type=\"text\" /></td>" + //Nama Barang
		"<td><select name=\"listTransDet[" +rows+ "].satuan_id\" class=\"listSatuan\"></td>" + //Satuan
		"<td><input name=\"listTransDet[" +rows+ "].qty\" class=\"nominal\" type=\"text\" onchange=\"hitung();\" /></td>" + //Qty
		"<td><input name=\"listTransDet[" +rows+ "].harga\" class=\"nominal\" type=\"text\" onchange=\"hitung();\" /></td>" + //Harga
		"<td><input name=\"listTransDet[" +rows+ "].jumlah\" class=\"right\" readonly=\"true\" tabindex=\"-1\" type=\"text\" /></td>" + //Jumlah
		"<td><input name=\"listTransDet[" +rows+ "].flag\" value=\"true\" checked=\"true\" type=\"checkbox\"></td>" + //flag simpan
		"<td><input name=\"listTransDet[" +rows+ "].flag_delete\" type=\"checkbox\" value=\"true\"></td>" + //tombol hapus
	"</tr>");

	//semua function yg perlu di re-initialize
	initAutoComplete();
	$(".tableBarang").styleTable(); 
	$("input[readonly]").addClass("ui-widget-header"); 
	$(".nominal")
		.change(function(){$(this).val(formatCurrency($(this).val()));$("#nominal").text("");})
		.keyup(function(){$("#nominal").text(formatCurrency($(this).val()));
	}).addClass("right");
	$("input[type=text], select")
		.addClass("ui-widget-content ui-corner-all") 
		.click(function() {
		   $(this).select();
		});

	//populate empty select saja, yg sudah ada isi jgn di populate lagi
	$(".listSatuan").each(function(){
		if( $(this).has("option").length == 0 ) {
			<c:forEach items="${reff.listSatuan}" var="s">
			$(this).append(new Option("${s.value}", "${s.key}"));
			</c:forEach>
		}
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
	initAutoComplete();
	$(".tableBarang").styleTable();
	hitung();
});
</script>
</head>
<body>

${sessionScope.currentUser.menuAkses}

<div class="tabs">
	<ul>
		<li><a href="#tabs-1">Home &raquo; Input STT (Surat Tanda Terima) &raquo; Edit</a></li>
	</ul>
	<div id="tabs-1" class="ui-widget">
		<form:form commandName="stt" action="${path}/transaksi/stt/save" method="POST" accept-charset="utf-8">
		<table class="gridTables">
			<tr>
				<th><form:label path="no_stt">No STT:</form:label></th>
				<td>
					<form:hidden path="id"/>
					<form:input path="no_stt" readonly="true" tabindex="-1" />
				</td>
				<th><form:label path="cabang_id">Cabang:<span class="mandatory">*</span></form:label></th>
				<td class="buttonset"><form:radiobuttons path="cabang_id" items="${reff.listCabang}" itemValue="key" itemLabel="value"/></td>
			</tr>
		</table>
		<br>
		<table class="gridTables">
			<tr>
				<th rowspan="2"><form:label path="customer_id">Pengirim:<span class="mandatory">*</span></form:label></th>
				<td rowspan="2">
					<%-- <form:select cssErrorClass="fieldError" path="customer_id" items="${reff.listCustomer}" itemValue="key" itemLabel="value"/> --%>
					<form:hidden path="customer_id"/>					
					<form:input path="customer" cssClass="kastamer" cssErrorClass="kastamer fieldError" size="44" />
				</td>
				<th colspan="2"><form:label path="pay_mode">Pembayaran:<span class="mandatory">*</span></form:label></th>
			</tr>
			<tr>
				<td colspan="2" class="buttonset center"><form:radiobuttons path="pay_mode" items="${reff.listPayMode}" itemValue="key" itemLabel="value"/></td>
			</tr>
			<tr>
				<th><form:label path="tujuan_id">Penerima dan Contact Person:<span class="mandatory">*</span></form:label></th>
				<td>
					<form:hidden path="tujuan_id"/>					
					<form:input path="tujuan" cssClass="tujuannih" cssErrorClass="tujuannih fieldError" size="44" />
					<br><br>Contact Person:<br>
					<form:input cssErrorClass="fieldError" path="contact_tujuan" title="Contact Person"/>
					<form:input cssErrorClass="fieldError" path="telp_tujuan" title="Telepon"/>
					<br>
					<form:textarea cssErrorClass="fieldError" path="alamat_tujuan" rows="2" cols="42" title="Alamat"/>
				</td>
				<th><form:label path="colly">Jumlah Colly:</form:label></th>
				<td><form:input path="colly" readonly="true" tabindex="-1"/></td>
			</tr>
			
			<tr>
				<th>Isi Menurut Pengakuan:</th>
				<td colspan="3">
					<table class="tableBarang" id="tableTransDet">
						<thead>
						<tr>
							<th>No</th>
							<th title="Jumlah Colly/Dus">Colly/Dus<span class="mandatory">*</span></th>
							<th title="Nama Barang">Nama Barang<span class="mandatory">*</span></th>
							<th title="Satuan">Satuan<span class="mandatory">*</span></th>
							<th title="Jumlah barang">Qty<span class="mandatory">*</span></th>
							<th title="Harga per satuan">Harga/Satuan (Kg/m³/dus)<span class="mandatory">*</span></th>
							<th title="Jumlah dalam Rupiah">Jumlah (Qty x Harga)</th>
							<th title="Pilih bila ingin data nama barang disimpan">Simpan</th>
							<th title="Pilih bila ingin menghapus baris ini">Hapus</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${stt.listTransDet}" var="d" varStatus="s">
						<tr>
							<td><form:input path="listTransDet[${s.index}].urut" size="2" readonly="true" tabindex="-1" /></td>
							<td>
								<form:input path="listTransDet[${s.index}].colly" size="6" cssClass="nominal" onchange="hitung();" cssErrorClass="fieldError" />
								<form:hidden path="listTransDet[${s.index}].colly_remain" />
								<form:hidden path="listTransDet[${s.index}].colly_naik" />
							</td>
							<td><form:input path="listTransDet[${s.index}].nama_barang" cssClass="barang" cssErrorClass="barang fieldError"/></td>
							<td><form:select path="listTransDet[${s.index}].satuan_id" items="${reff.listSatuan}" itemValue="key" itemLabel="value"/></td>
							<td><form:input path="listTransDet[${s.index}].qty" cssClass="nominal" onchange="hitung();" cssErrorClass="fieldError"/></td>
							<td><form:input path="listTransDet[${s.index}].harga" cssClass="nominal" onchange="hitung();" cssErrorClass="fieldError"/></td>
							<td><form:input path="listTransDet[${s.index}].jumlah" readonly="true" tabindex="-1" cssClass="right jumlah"/></td>
							<td><form:checkbox path="listTransDet[${s.index}].flag" value="true" /></td>
							<td class="center"><form:checkbox path="listTransDet[${s.index}].flag_delete" /></td>
						</tr>
						</c:forEach>
						</tbody>
						<tfoot>
						<tr>
							<td colspan="6" class="right"><form:label path="total_harga"><strong>Total:</strong></form:label></td>
							<td><form:input path="total_harga" readonly="true" tabindex="-1" cssClass="right"/></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="6" class="right"><form:label path="potongan"><strong>Potongan:</strong></form:label></td>
							<td><form:input path="potongan" cssClass="nominal right" onchange="hitung();"/></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="6" class="right"><form:label path="remain"><strong>Grand Total:</strong></form:label></td>
							<td><form:input path="remain" readonly="true" tabindex="-1" cssClass="right"/></td>
							<td></td>
						</tr>
						</tfoot>
					</table>
					<p>
						<%-- Bila status sudah cancelled, tidak bisa pencet tambah/save --%>
						<c:if test="${stt.cancel ne 1}">
							<button onclick="return tambahRow();">Tambah</button>
						</c:if>
						<form:label path="urut">Jumlah Item:</form:label><form:input path="urut" readonly="true" tabindex="-1" cssClass="right" size="2"/>
					</p>
				</td>
			</tr>
			<tr>
				<th rowspan="2">Note:</th>
				<td rowspan="2"><form:textarea path="note" rows="3" cols="42"/></td>
				<th>Tanggal STT:</th>
				<td><form:input path="tgl_stt" cssClass="datepicker"/></td>
			</tr>
			<tr>
				<th>Tanggal Kirim Estimasi:</th>
				<td><form:input path="tgl_kirim_est" cssClass="datepicker"/></td>
			</tr>
			<tr>
				<th><form:label path="cancel">Status Batal:</form:label></th>
				<td><form:select path="cancel" items="${reff.listCancel}" itemValue="key" itemLabel="value" /></td>
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
				<td><form:input path="createdate" readonly="true" tabindex="-1"/></td>
				<th>Dirubah Tgl:</th>
				<td><form:input path="modifydate" readonly="true" tabindex="-1"/></td>
				<th>Dibatalkan Tgl:</th>
				<td><form:input path="canceldate" readonly="true" tabindex="-1"/></td>
			</tr>
		</table>
		<spring:hasBindErrors name="stt">
			<div id="messageBox">
			<p class="ui-state-error ui-corner-all">
			<strong>Error:</strong><br/>
			<form:errors path="*" />
			</p>
			</div>
		</spring:hasBindErrors>
		<%-- Bila status sudah cancelled, tidak bisa pencet tambah/save --%>
		<c:if test="${stt.cancel ne 1}">
			<p>
				<button onclick="return simpan();">Simpan</button>
				<button onclick="window.location='${path}/transaksi/stt/insert'; return false;">Baru</button>
			</p>
		</c:if>
		</form:form>
		<button onclick="window.location='${path}/transaksi/stt'; return false;">&laquo; Kembali</button>
	</div>
</div>

</body>
</html>
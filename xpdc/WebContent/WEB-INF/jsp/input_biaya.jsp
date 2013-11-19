<%@ include file="/WEB-INF/jsp/include_taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>XPDC</title>
<%@ include file="/WEB-INF/jsp/include_head.jsp"%>
<script type="text/javascript">

//onclick tombol submit
function simpan(){
	if($("select[name='cancel']").val() == 1){
		if(confirm('Anda yakin membatalkan/menghapus data? Data yang sudah dibatalkan tidak bisa digunakan kembali.')) form.submit();
	}else{
		if(confirm('Simpan data?')) form.submit(); 
	}
	return false;
}

</script>
</head>
<body>

${sessionScope.currentUser.menuAkses}

<div class="tabs">
	<ul>
		<li><a href="#tabs-1">Home &raquo; Input Uang Masuk / Biaya Lain-lain</a></li>
	</ul>
	<div id="tabs-1" class="ui-widget">
		<form:form commandName="payment" action="${path}/transaksi/biaya/save" method="POST" accept-charset="utf-8">
		<table class="gridTables">
			<tr>
				<th>No Payment:</th>
				<td>
					<form:hidden path="payment_id" />
					<form:input path="no_payment" readonly="true" tabindex="-1"/>
				</td>
			</tr>
			<tr>
				<th><form:label path="paid_date">Tgl Bayar/Transaksi:<span class="mandatory">*</span></form:label></th>
				<td><form:input path="paid_date" cssClass="datepicker" cssErrorClass="fieldError datepicker"/></td>
			</tr>
			<tr>
				<th><form:label path="dk">Debet/Kredit:<span class="mandatory">*</span></form:label></th>
				<td class="buttonset"><form:radiobuttons path="dk" items="${reff.listDK}" itemValue="key" itemLabel="value"/>&nbsp;&nbsp;<span class="note">* Debet: Uang Masuk, Kredit: Uang Keluar</span></td>
			</tr>
			<tr>
				<th><form:label path="pay_mode">Pembayaran:<span class="mandatory">*</span></form:label></th>
				<td class="buttonset"><form:radiobuttons path="pay_mode" items="${reff.listPayMode}" itemValue="key" itemLabel="value"/></td>
			</tr>
			<tr>
				<th><form:label path="nominal">Jumlah:<span class="mandatory">*</span></form:label></th>
				<td><form:input path="nominal" cssClass="nominal" cssErrorClass="nominal fieldError" /></td>
			</tr>
			<tr>
				<th><form:label path="no_giro">No Giro:</form:label></th>
				<td><form:input path="no_giro" cssErrorClass="fieldError" /></td>
			</tr>
			<tr>
				<th><form:label path="due_date">Tgl JT Giro:</form:label></th>
				<td><form:input path="due_date" cssClass="datepicker" cssErrorClass="fieldError datepicker"/></td>
			</tr>
			<tr>
				<th><form:label path="keterangan">Keterangan:<span class="mandatory">*</span></form:label></th>
				<td><form:textarea path="keterangan" rows="3" cols="30" cssErrorClass="fieldError"/></td>
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
				<td><form:input path="createdate" readonly="true" tabindex="-1"/></td>
				<th>Dirubah Tgl:</th>
				<td><form:input path="modifydate" readonly="true" tabindex="-1"/></td>
				<th>Dibatalkan Tgl:</th>
				<td><form:input path="canceldate" readonly="true" tabindex="-1"/></td>
			</tr>
		</table>
		<spring:hasBindErrors name="payment">
			<div id="messageBox">
			<p class="ui-state-error ui-corner-all">
			<strong>Error:</strong><br/>
			<form:errors path="*" />
			</p>
			</div>
		</spring:hasBindErrors>

		<%-- Bila status sudah cancelled, tidak bisa pencet tambah/save --%>
		<c:if test="${payment.cancel ne 1}">
			<p><button onclick="return simpan();">Simpan</button></p>
		</c:if>

		</form:form>
		<button onclick="window.location='${path}/transaksi/biaya'; return false;">&laquo; Kembali</button>
	</div>
</div>

</body>
</html>
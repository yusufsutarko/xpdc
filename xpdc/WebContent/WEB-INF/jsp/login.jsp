<%@ include file="/WEB-INF/jsp/include_taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>XPDC</title>
<%@ include file="/WEB-INF/jsp/include_head.jsp"%>
<style type="text/css">
body {
	background-image: url("static/logo.png");
	background-repeat: repeat;
	background-position: center center;
}
</style>
<script>
$().ready(function(){
	
	$( "#dialog-form" ).dialog({ //modal login dialog box
		dialogClass: "no-close",
		autoOpen: true,
		modal: true,		
		closeOnEscape: false
	});
	
});	
</script>
</head>
<body>

<div id="dialog-form">
	<form:form commandName="user" method="POST" accept-charset="utf-8">
		<table>
			<tr>
				<th><form:label path="username">Username</form:label></th>
				<td><form:input path="username" cssClass="text ui-widget-content ui-corner-all" /></td>
			</tr>
			<tr>
				<th><form:label path="password">Password</form:label></th>
				<td><form:password path="password" cssClass="text ui-widget-content ui-corner-all" /></td>
			</tr>
			<spring:hasBindErrors name="user">
				<tr>
					<td colspan="2">
						<div class="ui-state-error ui-corner-all">
						<strong>Error:</strong><br/>
						<form:errors path="*" />
						</div>
					</td>
				</tr>
			</spring:hasBindErrors>
			<tr>
				<th></th>
				<td><input type="submit" name="login" value="Login" /></td>
			</tr>
		</table>
	</form:form>
</div>

</body>
</html>
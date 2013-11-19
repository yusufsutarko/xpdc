<c:set var="path" value="${pageContext.request.contextPath}" />

<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link href="${path}/static/style.css" rel="stylesheet">

<link href="${path}/static/jquery/start/jquery-ui-1.10.3.custom.css" rel="stylesheet">
<link href="${path}/static/jquery/jquery.ui.menubar.css" rel="stylesheet">

<script src="${path}/static/jquery/jquery-1.9.1.js"></script>
<script src="${path}/static/jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="${path}/static/jquery/jquery.ui.menubar.js"></script>

<script src="${path}/static/script.js"></script>
<script type="text/javascript">
// pesan error/sukses
var pesan = '${pesan}'.replace(/<br\s*[\/]?>/gi, "\n");
if(pesan!='') alert(pesan);
</script>
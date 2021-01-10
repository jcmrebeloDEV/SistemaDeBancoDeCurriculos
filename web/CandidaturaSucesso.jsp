<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<div class="conteudo" style="float:none;">
<div class="breadcumb">
<div>
</div></div>
<s:actionerror />
<s:actionmessage cssClass="msgOk" />
<script language="javascript">
$(document).ready(function(){
$(".bemVindo").css("visibility", "hidden");
});
</script>
<input type="button" value="Fechar" onclick="window.close();" style="margin:30px;" />
</div>
</div>
<jsp:include page="INCLUDES/footer.jsp" />


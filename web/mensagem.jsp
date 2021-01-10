<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<jsp:include page="INCLUDES/menu.jsp" />
<div class="conteudo">
<div class="breadcumb">
<div>
</div></div>
<s:actionerror />
<s:actionmessage cssClass="msgOk" />
</div>
<!-- input type="button" value="Fechar esta janela" onclick="window.close();" style="margin:30px;" / -->
</div>
<jsp:include page="INCLUDES/footer.jsp" />

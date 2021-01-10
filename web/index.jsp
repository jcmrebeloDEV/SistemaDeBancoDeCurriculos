<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<jsp:include page="INCLUDES/menu.jsp" />
<div class="conteudo">
    <div class="breadcumb"><div>Home</div></div>
    <h4>Bem vindo(a), <s:property value="#session.nome"/>!</h4>
    <p>Este é um sistema que se destina a cadastro e busca de currículos e vagas de emprego.</p>
    <p>Para utilizar o sistema, escolha as opções que desejar no menu ao lado.<p>
</div>
</div>
<jsp:include page="INCLUDES/footer.jsp" />
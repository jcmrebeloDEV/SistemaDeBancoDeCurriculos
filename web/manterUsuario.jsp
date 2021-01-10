<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<jsp:include page="INCLUDES/menu.jsp" />
<div class="conteudo">
<div class="breadcumb"><div>Manter Usuários</div></div>

<div class="boxCinza">
<s:form  action="ManterUsuario" namespace="/" validate="true" method="POST">
<label style="margin-right:5px;">Nome do usuário:</label>
<input type="text" name="list" value="<s:property value="list"/>" class="caixaBusca">
<input type="submit" value="Localizar">
</s:form>
</div>
<s:actionmessage />

<s:if test="usrs != null">
<s:form  action="ManterUsuario" id="formularioDeExclusao" namespace="/" validate="true" method="POST" >
<s:hidden name="delete" value=""/>
<table class="listagem">
<tr><th></th><th></th><th></th><th>Tipo</th><th>Nome</th></tr>
<s:iterator value="usrs" var="u">
<s:if test="#u.tipo!=1"> <!-- O adm nao e mostrado na lista-->
<tr>
    <td class="colunaSrv"><input type="checkbox" value=${u.pessoa.id} name="ids" /></td>
    <td class="colunaSrv"><a href="ManterUsuario.action?edit=${u.pessoa.id}"><img src="img/b_edit.png" title="Editar" class="imgBtnEditar" /></a></td>
    <td class="colunaSrv"><a href="ManterUsuario.action?delete&ids=${u.pessoa.id}"><img src="img/b_drop.png"  class="imgBtnExcluir" title="Excluir"/></a></td>
    <td class="colunaSrv2"><s:if test="#u.tipo==2">Pessoa Física</s:if><s:elseif test="#u.tipo==3">Pessoa Jurídica</s:elseif></td>
    <td>${u.pessoa.nome}</td>
</tr>
</s:if>
</s:iterator>
</table>
<s:submit id="imgBtnExcluir" cssStyle="margin-top:10px;" value="Excluir Selecionado(s)" align="center" />
</s:form>

</s:if>

</div>

</div>

<jsp:include page="INCLUDES/footer.jsp" />
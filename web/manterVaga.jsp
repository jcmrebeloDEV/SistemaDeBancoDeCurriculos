
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<jsp:include page="INCLUDES/menu.jsp" />
<div class="conteudo">
<div class="breadcumb"><div>Manter Vagas</div></div>
<s:actionmessage />
<p>Nesta página você pode gerenciar as vagas de emprego publicadas por sua empresa.</p><br/>
<s:submit  align="left" cssStyle="margin-bottom:20px;margin-left:10px;"  onclick="window.location='manterVagaAdd.jsp'" value="Adicionar nova vaga" />
<s:if test="vgs != null">
<s:form  action="ManterVaga" id="formularioDeExclusao" namespace="/" validate="true" method="POST" >
<s:hidden name="delete" value=""/>
<table class="listagem">
<tr><th></th><th></th><th></th><th>Candidaturas</th><th>Publicada em</th><th>Cargo</th></tr>
<s:iterator value="vgs" var="vaga">
    <tr>
    <td class="colunaSrv"><input type="checkbox" value=${vaga.id} name="ids" /></td>
    <td class="colunaSrv"><a href="ManterVaga.action?edit=${vaga.id}"><img src="img/b_edit.png" title="Editar" class="imgBtnEditar" /></a></td>
    <td class="colunaSrv"><a href="ManterVaga.action?delete&ids=${vaga.id}"><img src="img/b_drop.png"  class="imgBtnExcluir" title="Excluir"/></a></td>
    <td style="width:100px"><a href="Candidatura.action?listaPorVaga=${vaga.id}" title="Exibir candidaturas para esta vaga">Exibir</a></td>
    <td style="width:100px"><s:date format="dd/MM/yyyy hh:mm" name="#vaga.dataDePublicacao"/></td>
    <td><a target="blank" href="ManterVaga.action?view=${vaga.id}" >${vaga.cargo}</a></td>
</tr>
</s:iterator>
</table>
<s:submit id="imgBtnExcluir" cssStyle="margin-top:10px;" value="Excluir Selecionado(s)" align="center" />
</s:form>

</s:if>
<s:else><p>Você ainda não possui nenhuma vaga cadastrada.</p></s:else>

</div>

</div>

<jsp:include page="INCLUDES/footer.jsp" />
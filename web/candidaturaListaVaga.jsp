<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<jsp:include page="INCLUDES/menu.jsp" />
<div class="conteudo">
<div class="breadcumb"><div>Candidaturas para a vaga</div></div>
<s:actionmessage />
<p>Nesta página você pode ver as candidaturas para a vaga publicada.</p><br/>
<s:if test="cands != null">
<p align="center" style="color:white;font-weight:bold;background-color:#9D9D9D;padding:3px;margin-bottom:5px;">
<s:subset source="cands" count="1">
<s:iterator var="cand">
Vaga: ${cand.vaga.cargo}
</s:iterator>
</s:subset>
</p>
<form>
<table class="listagem">
<tr><th style="width:100px">Candidatura em</th><th>Nome do candidato</th><th>Mensagem</th></tr>
<s:iterator value="cands" var="cand">
    <tr>
    <td style="width:100px"><s:date format="dd/MM/yyyy" name="#cand.dataCandidatura"/></td>
    <td><a href="ManterCurriculo.action?view=${cand.pessoaFisica.id}" target="blank">${cand.pessoaFisica.nome}</a></td><td>${cand.mensagem}</td>
</tr>
</s:iterator>
</table>
<input style="margin-top:10px;" type="button" onclick="window.location='ManterVaga.action?list'" value="Voltar"/>
</form>
</s:if>
<s:else><p>Nehuma candidatura.</p></s:else>
</div>

</div>

<jsp:include page="INCLUDES/footer.jsp" />
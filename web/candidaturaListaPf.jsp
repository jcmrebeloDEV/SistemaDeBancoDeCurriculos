<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<jsp:include page="INCLUDES/menu.jsp" />
<div class="conteudo">
<div class="breadcumb"><div>Candidaturas Recentes</div></div>
<s:actionmessage />
<p>Nesta página você pode ver as suas candidaturas mais recentes.</p><br/>
<s:if test="cands != null">
<form>
<table class="listagem">
<tr><th style="width:100px">Candidatura em</th><th>Cargo</th></tr>
<s:iterator value="cands" var="cand">
    <tr>
    <td style="width:100px"><s:date format="dd/MM/yyyy" name="#cand.dataCandidatura"/></td>
    <td><a target="blank" href="ManterVaga.action?view=${cand.vaga.id}&pid=${cand.vaga.pessoaJuridica.id}&howBox=false" >${cand.vaga.cargo}</a></td>
</tr>
</s:iterator>
</table>
</form>
</s:if>
<s:else><p><i>Nehuma candidatura.</i></p></s:else>
</div>

</div>

<jsp:include page="INCLUDES/footer.jsp" />
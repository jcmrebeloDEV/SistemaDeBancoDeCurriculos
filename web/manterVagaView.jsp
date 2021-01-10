<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<div class="conteudo" style="float:none;">
<script language="javascript">
$(document).ready(function(){
$("#ManterVaga :input").attr("readonly","readonly");
$("#ManterVaga :input").css("background-color", "#F2F2F2");
$(".bemVindo").css("visibility", "hidden");
});
</script>
<div class="breadcumb"><div>Visualizar propriedades da vaga</div></div>

<s:actionmessage cssClass="msgOk" />

<s:form  action="ManterVaga"  namespace="/" validate="true" method="POST"  cssClass="formCinza">
<s:if test="#session.tipo != 3">
<s:textfield name="vg.pessoaJuridica.nome"  label="Empresa"/>
</s:if>
<s:textfield name="vg.pessoaJuridica.siteCorporativo" label="Site" />
<s:textfield name="vg.uf" label="Local de trabalho"/>
<s:textfield name="vg.areaProfissional" label="Área profissional" />
<s:textfield name="vg.cargo" label="Cargo" />
<s:textarea name="vg.descricao" label="Descrição" />
<s:textarea name="vg.observacoes" label="Observações" />
</s:form>
<s:if test="showBox == true">
    <p style="margin-top:20px;"></p>
<s:form action="Candidatura"  cssClass="boxCinza" namespace="/" method="POST" cssStyle="margin-left:0px;">
<s:hidden name="candidatarIdVaga" value="%{view}" />
<s:label value=">> Candidatar-se à vaga" cssStyle="font-weight:bold;color:#808080;font-size:12px;" />
<s:label value="Para se candidatar a esta vaga, preencha o campo abaixo com uma mensagem de apresentação para a empresa (opcional) e clique em 'Efetuar candidatura'; Para sair sem se candidatar clique no botão 'Fechar'." cssStyle="display:block;width:350px;" />
<s:textarea name="mensagemCandidato" />
<s:submit value="Efetuar candidatura"  align="left"/>
</s:form>
</s:if>
<div align="center" style="margin-top:40px;">
<input type="button" value="Fechar" onclick="window.close();"/>
</div>
</div>

</div>

<jsp:include page="INCLUDES/footer.jsp" />
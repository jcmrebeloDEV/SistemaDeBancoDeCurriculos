<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<div class="conteudo" style="float:none;">
<div class="breadcumb">
<div>
<script language="javascript">
$(document).ready(function(){
$("#ManterCurriculo :input").attr("readonly","readonly");
$("#ManterCurriculo :input").css("background-color", "#F2F2F2");
$(".bemVindo").css("visibility","hidden");
});
</script>
Currículo de <s:property value="c.pessoaFisica.nome"/>. Atualizado em <s:date name="c.dataUltimaAlteracao" format="dd/MM/yyyy" /> |
<a href="javascript:window.print()"><img src="img/icon_print.gif" title="imprimir" class="imgBtnEditar"/></a></div></div>
<s:form cssClass="formCinza" action="ManterCurriculo" namespace="/" validate="true" method="POST" acceptcharset="UTF-8" >
<s:textfield name="c.pessoaFisica.nome" label="Nome"/>
<s:date name="c.pessoaFisica.dataDeNascimento" format="dd/MM/yyyy" var="nascimento"/>
<s:textfield label="Nascimento" value="%{nascimento}" />
<s:textfield name="c.pessoaFisica.email" label="Email" />
<s:textfield name="c.pessoaFisica.telefone" label="Telefone" />
<s:textfield name="c.pessoaFisica.cidade" label="Cidade" />
<s:textfield name="c.pessoaFisica.uf" label="UF" />
<s:textfield name="c.areaProfissional" label="Área Profissional" />
<s:textarea name="c.descExperiencia" label="Descrição da Experiência Profissional"/>
<s:textarea name="c.descConhecimenros" label="Descrição dos Conheciemntos Profissionais"/>
<s:textarea name="c.descIdiomas" label="Idiomas"/>
<s:textfield name="c.escolaridade" label="Escolaridade" />
<s:textarea name="c.descFormacaoAcademica" label="Formação Acadêmica"/>
<s:textarea name="c.observacoes" label="Observações / Dados Complementares"/>
</s:form>
<s:reset value="Fechar" onclick="window.close();" align="center"  cssStyle="margin-top:30px;"/>
</div>
</div>
<jsp:include page="INCLUDES/footer.jsp" />
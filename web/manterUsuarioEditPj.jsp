<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<jsp:include page="INCLUDES/menu.jsp" />
<div class="conteudo">
<div class="breadcumb">
<script language="javascript">

function mostra(tipo){
  if(tipo==0) {
      $("[name=novaSenha]").attr("disabled","disabled");
      $("[name=novaSenha]").css("background-color", "#D4D0C8");
      $("[name=senhaConfirma]").attr("disabled","disabled");
      $("[name=senhaConfirma]").css("background-color", "#D4D0C8");
      $("[name=novaSenha]").val("");
      $("[name=senhaConfirma]").val("");

        }
        if(tipo==1) {
      $("[name=novaSenha]").attr("disabled","");
      $("[name=novaSenha]").css("background-color", "#FFFFFF");
      $("[name=senhaConfirma]").attr("disabled","");
      $("[name=senhaConfirma]").css("background-color", "#FFFFFF");
        }
}

$(document).ready(function(){

$("[name=trocarSenha]").click(function(event){
if($("[name=trocarSenha]").attr("checked")) mostra(1); else mostra(0);
});

$("[name=trocarSenha]").triggerHandler("click","");
       
});

</script>
<div>
Editar Perfil
</div></div>
<s:actionerror  cssClass="msgerro"/>
<s:form cssClass="formCinza" action="ManterUsuario" namespace="/" validate="true" method="POST" acceptcharset="UTF-8" >
<s:hidden name="update"/>
<s:textfield label="Nome" name="pj.nome" cssStyle="width:300px;background-color:#F2F2F2;"  readonly="true"/>
<s:textfield label="Endereço" name="pj.endereco"  cssStyle="width:300px" />
<s:textfield label="Cep" name="pj.cep" />
<s:textfield label="Telefone" name="pj.telefone" />
<s:select label="UF" list="#{'AC':'AC','AL':'AL','AM':'AM','AP':'AP','BA':'BA','CE':'CE','ES':'ES','GO':'GO','MA':'MA','MG':'MG','MT':'MT','MTS':'MTS','PA':'PA','PE':'PE','PI':'PI','PR':'PR','RJ':'RJ','RO':'RO','RN':'RN','RR':'RR','RS':'RS','SC':'SC','SE':'SE','SP':'SP','TO':'TO'}" name="pj.uf"/>
<s:textfield label="Cidade" name="pj.cidade" />
<s:textfield label="E-mail" name="pj.email" />
<s:textfield label="Cnpj" name="pj.cnpj" />
<s:textfield label="Site corporativo" name="pj.siteCorporativo" />
<s:label style="display:block;width:300px;height:30px;margin-top:20px" value="Para alterar a senha, marque a caixa abaixo e digite a nova senha e a confirmação da nova senha." />
<s:checkbox  name="trocarSenha"  label="Alterar senha" />
<s:password label="Nova Senha" name="novaSenha" />
<s:password label="Confirmar Senha" name="senhaConfirma" />
<s:submit value="Salvar" align="center" cssStyle="margin-top:20px;width:100px;"/>
<s:reset align="center" value="Cancelar" onclick="window.back();" cssStyle="width:100px;"/>
</s:form>

</div>

</div>
<jsp:include page="INCLUDES/footer.jsp" />
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
<s:date format="dd-MM-yyyy" name="pf.dataDeNascimento" var="data1" />

<script language="javascript">
$(document).ready(function(){

//seletor de datas - form de cadastro de usuários
/* chooseDate é um elemnto DOM qualquer que vai ser substituido no form*/
$("#chooseDate").after("<div id=\"cjDatas\"><label style=\"margin-right:30px;\">Nascimento:</label><select title=\"dia\" id=\"dia\"></select><label>/</label><select title=\"mes\"id=\"mes\"></select><label>/</label><select title=\"ano\" id=\"ano\"></select></div>");
$("#chooseDate").remove();

//valor default
if($("#ManterUsuario_dataDeNascimento").val().length==0)
$("#ManterUsuario_dataDeNascimento").val('<s:property value="%{data1}" />');

for (a=1;a<32;a++){
if (a<10)$("#dia").append("<option value=\"0"+a+"\">0"+a);
else
$("#dia").append("<option value=\""+a+"\">"+a);
}

for (a=1;a<13;a++){
if (a<10)$("#mes").append("<option value=\"0"+a+"\">0"+a);
else
$("#mes").append("<option value=\""+a+"\">"+a);
}

for (a=1950;a<2000;a++){
$("#ano").append("<option value=\""+a+"\">"+a);
}

//'copia' os valores da caixa de texto para os selects
$("#dia").val($("#ManterUsuario_dataDeNascimento").val().substr(0,2));
$("#mes").val($("#ManterUsuario_dataDeNascimento").val().substr(3,2));
$("#ano").val($("#ManterUsuario_dataDeNascimento").val().substr(6,4));

$("#cjDatas :select").change(function(event){
  $("#ManterUsuario_dataDeNascimento").val($("#dia").val()+"-"+$("#mes").val()+"-"+$("#ano").val());
});

});

</script>
<s:actionerror  cssClass="msgerro"/>
<s:form cssClass="formCinza" action="ManterUsuario" namespace="/" validate="true" method="POST" acceptcharset="UTF-8" >
<s:hidden name="update"/>
<s:textfield label="Nome" name="pf.nome" cssStyle="width:300px;background-color:#F2F2F2;"  readonly="true"/>
<s:select label="Sexo" name="pf.sexo" list="#{'M':'M','F':'F'}" />
<s:textfield label="Endereço" name="pf.endereco"  cssStyle="width:300px" />
<s:textfield label="Cep" name="pf.cep" />
<s:textfield label="Telefone" name="pf.telefone" />
<s:select label="UF" list="#{'AC':'AC','AL':'AL','AM':'AM','AP':'AP','BA':'BA','CE':'CE','ES':'ES','GO':'GO','MA':'MA','MG':'MG','MT':'MT','MTS':'MTS','PA':'PA','PE':'PE','PI':'PI','PR':'PR','RJ':'RJ','RO':'RO','RN':'RN','RR':'RR','RS':'RS','SC':'SC','SE':'SE','SP':'SP','TO':'TO'}" name="pf.uf"/>
<s:textfield label="Cidade" name="pf.cidade" />
<s:textfield label="E-mail" name="pf.email" />
<s:textfield label="Cpf" name="pf.cpf" />
<!-- s:textfield name="data"  value="%{data1}"/ -->
<s:hidden name="dataDeNascimento" label="Nascimento"/>
<s:submit value=""  id="chooseDate" align="left"/>
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
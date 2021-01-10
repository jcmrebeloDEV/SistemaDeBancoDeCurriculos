<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<!-- jsp:include page="INCLUDES/menu.jsp" / -->
<div class="areaEsquerda">
<p style="margin:8px">
Para voltar para a página de login clique <a href="login.jsp">aqui</a>.
</p>
</div>
<div class="conteudo">
<div class="breadcumb">
<div>
Adicionar Usuário Pessoa Física
</div></div>
<script language="javascript">
$(document).ready(function(){

//seletor de datas
/* chooseDate é um elemnto DOM qualquer que vai ser substituido no form*/
$("#chooseDate").after("<div id=\"cjDatas\"><label style=\"margin-right:30px;\">Nascimento:</label><select title=\"dia\" id=\"dia\"></select><label>/</label><select title=\"mes\"id=\"mes\"></select><label>/</label><select title=\"ano\" id=\"ano\"></select></div>");
$("#chooseDate").remove();

//valor default
if($("#ManterUsuario_dataDeNascimento").val().length==0)
$("#ManterUsuario_dataDeNascimento").val("01-01-1980");

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
<s:actionmessage cssClass="msgOk" id="usuarioAdicionadoMsg" />
<s:form cssClass="formCinza" action="ManterUsuario" namespace="/" validate="true" method="POST">
<s:label value="Digite abaixo as informações necessárias para se cadastrar no sistema:"  cssStyle="display:block;margin-bottom:10px;"/>
<s:actionerror cssClass="msgerro" />
<s:hidden name="create"/>

<s:hidden name="usr.tipo" value="2"/>
<s:textfield label="Login" name="usr.login" />

<s:password label="Senha" name="novaSenha" />
<s:password label="Confirmar Senha" name="senhaConfirma" />

<s:textfield label="Nome" name="pf.nome"  cssStyle="width:300px" />
<s:select label="Sexo" name="pf.sexo" list="#{'M':'M','F':'F'}" />
<s:textfield label="Endereço" name="pf.endereco"  cssStyle="width:300px" />
<s:textfield label="Cep" name="pf.cep" />
<s:textfield label="Telefone" name="pf.telefone" />
<s:select label="UF" list="#{'AC':'AC','AL':'AL','AM':'AM','AP':'AP','BA':'BA','CE':'CE','ES':'ES','GO':'GO','MA':'MA','MG':'MG','MT':'MT','MTS':'MTS','PA':'PA','PE':'PE','PI':'PI','PR':'PR','RJ':'RJ','RO':'RO','RN':'RN','RR':'RR','RS':'RS','SC':'SC','SE':'SE','SP':'SP','TO':'TO'}" name="pf.uf"/>
<s:textfield label="Cidade" name="pf.cidade" />
<s:textfield label="E-mail" name="pf.email" />
<s:textfield  label="Cpf" name="pf.cpf"  title="Digite somente números no campo cpf"/>
<s:hidden name="dataDeNascimento" label="Nascimento"/>
<s:submit value=""  id="chooseDate" align="left"/>
<s:submit  cssStyle="margin-top:20px;" value="Criar" align="center" />

</s:form>

</div>

</div>
<jsp:include page="INCLUDES/footer.jsp" />
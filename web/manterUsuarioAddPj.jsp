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
Adicionar Usuário Pessoa Jurídica
</div></div>
<s:actionmessage cssClass="msgOk" id="usuarioAdicionadoMsg" />
<s:form cssClass="formCinza" action="ManterUsuario" namespace="/" validate="true" method="POST">
<s:label value="Digite abaixo as informações necessárias para se cadastrar no sistema:"  cssStyle="display:block;margin-bottom:10px;"/>
<s:actionerror cssClass="msgerro" />
<s:hidden name="create"/>

<s:hidden name="usr.tipo" value="3"/>
<s:textfield label="Login" name="usr.login" />

<s:password label="Senha" name="novaSenha" />
<s:password label="Confirmar Senha" name="senhaConfirma" />

<s:textfield label="Nome" name="pj.nome"  cssStyle="width:300px" />
<s:textfield label="Endereço" name="pj.endereco"  cssStyle="width:300px" />
<s:textfield label="Cep" name="pj.cep" />
<s:textfield label="Telefone" name="pj.telefone" />
<s:select label="UF" list="#{'AC':'AC','AL':'AL','AM':'AM','AP':'AP','BA':'BA','CE':'CE','ES':'ES','GO':'GO','MA':'MA','MG':'MG','MT':'MT','MTS':'MTS','PA':'PA','PE':'PE','PI':'PI','PR':'PR','RJ':'RJ','RO':'RO','RN':'RN','RR':'RR','RS':'RS','SC':'SC','SE':'SE','SP':'SP','TO':'TO'}" name="usr.pessoa.uf"/>
<s:textfield label="Cidade" name="pj.cidade" />
<s:textfield label="E-mail" name="pj.email" />
<s:textfield  label="Cnpj:" name="pj.cnpj" />
<s:textfield label="Site corporativo" name="pj.siteCorporativo" />
<s:submit  cssStyle="margin-top:20px;" value="Criar" align="center" />

</s:form>

</div>

</div>
<jsp:include page="INCLUDES/footer.jsp" />
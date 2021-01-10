<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<div class="areaEsquerda">
<p style="margin:8px">
Digite ao lado o seu username e a sua senha para entrar no sistema.
</p>
<p style="margin-top:20px;">Fazer novo cadastro:</P>
<ul>
<li style="margin:3px;"><a href="manterUsuarioAddPj.jsp">Pessoa jurídica</a>.</li>
<li style="margin:3px;"><a href="manterUsuarioAddPf.jsp">Pessoa física</a>.</li>
</ul>
<br />
</div>

<div class="conteudo">
<div class="breadcumb"></div>
<div class="divlogin">
<s:actionerror cssClass="msgerro" />
<s:form namespace="/" action="login" validate="true" method="POST" >
<s:textfield name="usrname" key="usrname" label="username"/>
<s:password name="pswd" key="psdw" label="senha" />
<s:submit value="Entrar"  align="center"  cssStyle="margin-top:10px;"/>
</s:form>

</div>
</div>

</div>
<jsp:include page="INCLUDES/footer.jsp" />
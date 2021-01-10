<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Sistema de Banco de Currículos</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style/geral.css" rel="stylesheet" />
<script src="script/jquery-1.3.2.js" type="text/javascript"></script>
<script src="script/more.js" type="text/javascript"></script>
</head>
<body>
<div class="head" >
    <div></div>
    <h1>Sistema de Banco de Currículos</h1>
</div>


<div class="bemVindo">
    <s:if test="#session.tipo != null "><img src="img/user-icon.png">
       Usuário: <s:property value="#session.nome"/>
    | <a class="linkLogout">Sair</a>
 
     </s:if>
</div>
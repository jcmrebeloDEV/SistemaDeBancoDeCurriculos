<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="areaEsquerda">
<div class="menu">
    <div class="bordaDeCima">
        <ul>
            <s:if test="#session.tipo == 1 ">
                <li class="tituloMenu">Administrar ::</li>
                <li><a href="manterUsuario.jsp">Manter usuários</a></li>
            </s:if>
            <s:elseif test="#session.tipo == 2">
                <li class="tituloMenu">Pessoa Física ::</li>
                <li><a href="ManterCurriculo.action?edit">Editar Curriculo</a></li>
                <li><a href="Candidatura.action?listaPorPf">Candidaturas Recentes</a></li>
                <li><a href="ManterUsuario.action?edit=<s:property value="#session.id" />">Editar Perfil</a></li>
                <li><a href="busca.jsp">Buscar Vagas</a></li>
            </s:elseif>
            <s:elseif test="#session.tipo == 3">
                <li class="tituloMenu">Empresa ::</li>
                <li><a href="ManterVaga.action?list">Manter Vagas</a></li>
                <li><a href="Estatistica.action?tipo=-1">Estatísticas</a></li>
                <li><a href="ManterUsuario.action?edit=<s:property value="#session.id" />">Editar Perfil</a></li>
                <li><a href="busca.jsp">Buscar Currículos</a></li>
            </s:elseif>
            <s:else>
                <%-- usuario nao logado --%>
                <jsp:forward page="/login.jsp" />
            </s:else>
               
        </ul>
    </div>
</div>
</div>
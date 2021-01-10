<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<jsp:include page="INCLUDES/menu.jsp" />
<div class="conteudo">

<div class="breadcumb">
<s:if test="#session.tipo == 2">
<div>Buscar Vagas</div>
</s:if>
<s:elseif test="#session.tipo == 3">
<div>Buscar Curriculos</div>
</s:elseif>
</div>
<script language="javascript">
$(document).ready(function(){

$("#ajudaButton").click(
function(event){
    $("#ajudaBusca").toggle();
});

});

</script>
<p><a href="#" id="ajudaButton">Exibir/Ocultar a <b>Ajuda</b></a></p>
<div id="ajudaBusca" style="border:1px solid #F2F2F2;margin:6px; background-color:#F2F2F2;display:none">
<p>
Na busca você pode usar os operadores AND, NOT, OR, caracteres curinga (*).
Ex:
</p>
<ul>
<li>
Exp* OR curso, RJ AND SP
</li>  
 </ul>
<p>
 O recurso de highlight mostra trechos de campos onde foram encontradas referências
 das palavras-chave da busca. Embora outros campos sejam também pesquisados na busca (UF, Área profissional, escolaridade), os campos
 onde o recurso de highlight é aplicado, são:</p>
<ul>
<li>
  Currículo:Descrição da experiência, Descrição dos conhecimentos, Observações.
</li>
<li>
Vaga:Descrição, Observações.
</li>

</ul>
<!-- p style="margin-bottom:20px">Caso não haja ocorrência de qualquer palavra-chave da busca nos campos citados, é exibida a mensagem "Nehum destaque".
    Quando é possível, são exibidos os 5 termos mais frequentes no documento, com base nos campos citados acima para currículo e vaga.
</p -->
</div>
<s:form  action="Busca" namespace="/" method="POST"  cssClass="boxCinza"> 
<s:textfield  name="userQuery" label="Palavras-chave" cssClass="caixaBusca" cssStyle="width:280px;" />
<s:label value=""  label="Filtros"/>
<s:select name="maxResultsPg" label="Resultados por página" list="#{'32':'32', '16':'16', '8':'8', '4':'4', '2':'2'}" />
<s:select name="uf" label="UF" list="#{'*':'Qualquer', 'AC':'AC','AL':'AL','AM':'AM','AP':'AP','BA':'BA','CE':'CE','ES':'ES','GO':'GO','MA':'MA','MG':'MG','MT':'MT','MTS':'MTS','PA':'PA','PE':'PE','PI':'PI','PR':'PR','RJ':'RJ','RO':'RO','RN':'RN','RR':'RR','RS':'RS','SC':'SC','SE':'SE','SP':'SP','TO':'TO'}" />
<s:select name="areaProfissional"  label="Área Profissional" list="#{'*':'Qualquer', 'Outra':'Outra','Administração de Empresas':'Administração de Empresas','Administração de Vendas':'Administração de Vendas','Administração Hospitalar':'Administração Hospitalar','Administração Pública':'Administração Pública','Administrativa':'Administrativa','Agrimensura':'Agrimensura','Agronomia':'Agronomia','Agropecuária':'Agropecuária','Análise de Sistemas':'Análise de Sistemas','Antropologia':'Antropologia','Arquitetura/Urbanismo':'Arquitetura/Urbanismo','Artes Cênicas':'Artes Cênicas','Artes Gráficas':'Artes Gráficas','Artes Plásticas':'Artes Plásticas','Atendimento a Clientes/Call Center':'Atendimento a Clientes/Call Center','Atendimento ao Público':'Atendimento ao Público','Atuária':'Atuária','Automação Industrial':'Automação Industrial','Aviação/Aeronáutica':'Aviação/Aeronáutica','Bancária':'Bancária','Biblioteconomia':'Biblioteconomia','Biologia':'Biologia','Biomédicas':'Biomédicas','Bioquímica':'Bioquímica','Biotecnologia':'Biotecnologia','Ciências (Licenciatura)':'Ciências (Licenciatura)','Ciências Sociais':'Ciências Sociais','Comercial/Vendas':'Comercial/Vendas','Comércio Exterior/Trade':'Comércio Exterior/Trade','Comunicação Social':'Comunicação Social','Comunicação Visual':'Comunicação Visual','Construção Civil':'Construção Civil','Contabilidade':'Contabilidade','Controladoria':'Controladoria','Departamento Pessoal':'Departamento Pessoal','Desenho Industrial':'Desenho Industrial','Direito':'Direito','Econômica':'Econômica','Edificações':'Edificações','Educação Artística':'Educação Artística','Educação Física':'Educação Física','Educação/Ensino':'Educação/Ensino','Energia':'Energia','Enfermagem':'Enfermagem','Engenharia Administrativa':'Engenharia Administrativa','Engenharia Aeroespacial':'Engenharia Aeroespacial','Engenharia Aeronáutica':'Engenharia Aeronáutica','Engenharia Civil':'Engenharia Civil','Engenharia da Computação':'Engenharia da Computação','Engenharia de Alimentos':'Engenharia de Alimentos','Engenharia de Meio Ambiente':'Engenharia de Meio Ambiente','Engenharia de Minas e Energia':'Engenharia de Minas e Energia','Engenharia de Produção':'Engenharia de Produção','Engenharia de Segurança':'Engenharia de Segurança','Engenharia de Sistemas/Computação':'Engenharia de Sistemas/Computação','Engenharia de Telecomunicações':'Engenharia de Telecomunicações','Engenharia Elétrica/Eletrônica':'Engenharia Elétrica/Eletrônica','Engenharia Eletro-mecânica':'Engenharia Eletro-mecânica','Engenharia Eletrotécnica':'Engenharia Eletrotécnica','Engenharia Florestal':'Engenharia Florestal','Engenharia Industrial/Operacional':'Engenharia Industrial/Operacional','Engenharia Mecânica':'Engenharia Mecânica','Engenharia Mecatrônica':'Engenharia Mecatrônica','Engenharia Metalúrgica':'Engenharia Metalúrgica','Engenharia Naval':'Engenharia Naval','Engenharia Nuclear':'Engenharia Nuclear','Engenharia Química':'Engenharia Química','Estatística':'Estatística','Exportação':'Exportação','Farmacêutica':'Farmacêutica','Filosofia':'Filosofia','Financeira':'Financeira','Física':'Física','Fisioterapia':'Fisioterapia','Fonoaudiologia':'Fonoaudiologia','Geofísica':'Geofísica','Geografia':'Geografia','Geologia':'Geologia','Gerência Geral':'Gerência Geral','História':'História','Hotelaria/Turismo':'Hotelaria/Turismo','Importação':'Importação','Industrial':'Industrial','Informática':'Informática','Internet/E-commerce/E-Business/Web':'Internet/E-commerce/E-Business/Web','Jornalismo':'Jornalismo','Jurídica':'Jurídica','Letras':'Letras','Marketing':'Marketing','Matemática':'Matemática','Médico/Hospitalar':'Médico/Hospitalar','Meio Ambiente/Ecologia':'Meio Ambiente/Ecologia','Música':'Música','Nutrição':'Nutrição','Odontologia':'Odontologia','Operacional':'Operacional','Organização e Métodos':'Organização e Métodos','Pedagogia':'Pedagogia','Private Corporate Bank':'Private Corporate Bank','Processamento de Dados':'Processamento de Dados','Psicologia':'Psicologia','Publicidade e Propaganda':'Publicidade e Propaganda','Qualidade':'Qualidade','Química':'Química','Rádio e Televisão':'Rádio e Televisão','Recepção':'Recepção','Recursos Humanos':'Recursos Humanos','Relações Internacionais':'Relações Internacionais','Relações Públicas':'Relações Públicas','Secretaria':'Secretaria','Segurança do Trabalho':'Segurança do Trabalho','Segurança Patrimonial':'Segurança Patrimonial','Seguros':'Seguros','Serviço Social':'Serviço Social','Siderurgia':'Siderurgia','Suprimentos/Logística':'Suprimentos/Logística','Técnicas':'Técnicas','Tecnologia da Informação':'Tecnologia da Informação','Telecomunicações':'Telecomunicações','Telemarketing Ativo':'Telemarketing Ativo','Telemarketing Receptivo':'Telemarketing Receptivo','Teleprocessamento':'Teleprocessamento','Tesouraria':'Tesouraria','Tradutor/Intérprete':'Tradutor/Intérprete','Transportes':'Transportes','Turismo':'Turismo','Veterinária':'Veterinária','Zootecnia':'Zootecnia'}"  />

<!-- o valor da variavel tipoDeBusca eh setado sempre porque quando a
pagina eh chamada pela primeira vez nao existe nenhuma variavel
com esse nome no escopo
-->
<s:if test="#session.tipo == 2">
<s:hidden name="tipoDeBusca" value="1"/>
</s:if>
<s:elseif test="#session.tipo == 3">
<s:hidden name="tipoDeBusca" value="0"/>
<s:select name="escolaridade" label="Escolaridade" list="#{'*':'Qualquer', 'Segundo grau completo':'Segundo grau completo','Terceiro grau completo':'Terceiro grau completo','Pós Graduado ':'Pós Graduado','Mestrado':'Mestrado','Doutorado':'Doutorado'}" />
</s:elseif>

<s:submit cssStyle="margin-top:10px;" value="Buscar" align="center" />
</s:form>

<s:actionmessage />
<s:actionerror cssClass="msgerro" />

<s:if test="resultados.size > 0">

<p>Total de <s:property value="totalResults" /> resultado(s) encontrado(s).</p>

<table  id="tabelaDeBusca" class="listagem">
<s:iterator value="resultados" var="res">
<tr>
<td>
<s:if test="#session.tipo == 2">
<s:url action="ManterVaga" var="urlOpen">
<s:param name="view" value="#res.id" />
<s:param name="pid" value="#res.pid" />
<s:param name="showBox" value="true" />
</s:url>
</s:if>
<s:elseif test="#session.tipo == 3">
<s:url action="ManterCurriculo" var="urlOpen">
<s:param name="view" value="#res.id" />
<s:param name="pid" value="#res.pid" />
</s:url>
</s:elseif>
<p><a target="blank" href="${urlOpen}">${res.nome}</a></p>
<s:if test="#res.highlight.size > 0">
<s:iterator value="#res.highlight">
    <p>...<s:property escape="false"/>...</p>
</s:iterator>
</s:if>
<s:else>
 <!-- p>(Nenhum destaque)</p-->
</s:else>
    <s:if test="#res.termos.size > 0">      
<p style="margin-top: 5px;">
<ul><li>Fragmentos mais frequentes:
<s:iterator value="#res.termos">
"<i><s:property escape="false"/></i>"&nbsp;&nbsp;
</s:iterator>
</li></ul>
</p>
</s:if>
</td>
</tr>
</s:iterator>
</table>
<!-- paginacao -->
<div align="center" class="boxCinza" style="margin-top:20px;">
Página(s):
<s:if test="(totalResults % maxResultsPg) > 0">
<s:set name="paginas" value="(totalResults / maxResultsPg) + 1"/>
</s:if>
<s:else>
<s:set name="paginas" value="(totalResults / maxResultsPg)"/>
</s:else>

<s:iterator status="stat" value="(#paginas).{#this}" >
<s:if test="pg == #stat.count">
<span><b>${stat.count}</b></span>
</s:if>
<s:else>
<s:url action="Busca" var="urlBusca" includeParams="all">
<s:param name="pg" value="#stat.count" />
</s:url>
<a href="${urlBusca}" ><b>${stat.count}</b></a>
</s:else>
</s:iterator>

</div>

</s:if>

</div>

</div>

<jsp:include page="INCLUDES/footer.jsp" />
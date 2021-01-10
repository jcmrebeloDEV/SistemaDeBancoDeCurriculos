<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="INCLUDES/head.jsp" />
<div class="main">
<jsp:include page="INCLUDES/menu.jsp" />
<div class="conteudo">
<div class="breadcumb"><div>Estatísticas</div></div>
<p>Esta página fornece estatísticas relativas aos usuários do tipo pessoa física (candidatos).
<br>Escolha uma das opções disponíveis abaixo:
</p>
<form style="margin-top:10px;">
<select id="opcoes">
    <option value="0">Porcentagens de candidatos por estado
    <option value="1">Porcentagens de candidatos por escolaridade
    <option value="2">Porcentagens de candidatos por faixa de idade
    <option value="3">Porcentagens de candidatos por área profissional
</select>
<input type="button" value="Exibir" id="exibirGraficos">
</form>
<div>
<script language="javascript">

    function getRandomInt(min, max)
    {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    function grafs(){

        var d = new Array("Porcentagens de candidatos por estado<br>(Valores não representados tem porcentagem igual a zero)",
        "Porcentagens de candidatos por escolaridade<br>(Valores não representados tem porcentagem igual a zero)",
        "Porcentagens de candidatos por faixa de idade",
        "Porcentagens de candidatos por área Profissional<br>(Valores não representados tem porcentagem igual a zero)");

        var titulo = $("#opcoes :selected").html();
        $("#titulo").html(d[$("#opcoes").val()]);
        $("#grafico").hide();
        $("#grafico").show(800);
        //$("#titulo").slideUp("fast");
        //$("#titulo").slideDown("normal");
        var src="Estatistica.action?tipo="+$("#opcoes").val()+"&rnd="+getRandomInt(0,9999);
        $("#grafico").attr("src",src );
    }

    $(document).ready(function(){

        $("#exibirGraficos").click(function(event){
            grafs();
        });

    });

</script>

<p id="titulo" style="margin-top:20px;"> </p>
<img src="" id="grafico" style="display:none;border: solid 0px #557CBF;"/>
</div>

</div>

<jsp:include page="INCLUDES/footer.jsp" />
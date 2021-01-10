$(document).ready(function(){

//***para o link de logout
$(".linkLogout").attr("href","login.action?logout");
$(".linkLogout").attr("title","Sair do sistema");
$(".linkLogout").click(function(event){

    if(confirm ("Deseja fazer Logout?")==false) {
        event.preventDefault();
    }
});

//***para confirmacao de exclusao em listas
$(".imgBtnExcluir").click(function(event){

    if(confirm ("Deseja realmente realizar a exclusão?")==false) {
        event.preventDefault();
    }
});

//***evita o envio de forms sem nehum ceheckbox selecionado
$("#formularioDeExclusao input:submit").click(function(event){

    var chks = $(".listagem input:checkbox:checked");
    if(chks.length==0) {
        alert("Selecione pelo menos um elemento!");
        event.preventDefault();
    }else{
       if(confirm ("Deseja realmente realizar a exclusão?")==false) {
        event.preventDefault();
    }
    }
});


//***coloca listras na tabela listagem
$(".listagem tr:even").addClass("par");
$(".listagem tr:odd").addClass("impar");

 $(".listagem tr:even").mouseover(function(){
      $(this).removeClass("par");
      $(this).addClass("parOver");
     }).mouseout(function(){
     $(this).removeClass("parOver");
     $(this).addClass("par");
     });


 $(".listagem tr:odd").mouseover(function(){
      $(this).removeClass("impar");
      $(this).addClass("imparOver");
     }).mouseout(function(){
     $(this).removeClass("imparOver");
     $(this).addClass("impar");
     });

});


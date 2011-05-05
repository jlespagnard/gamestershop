/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function getGamesByPlatform(idPlatform) {
    $.post("GetGamesByPlatform", {idPlatform : idPlatform}, function(data){
        alert(data);
        var content = "<%@include file=\"games.jsp\" %>";
        ("#content").html(content);
    });
}

function addToBasket(idGame) {
    $.post("AddToBasket", {idGame : idGame}, function(game){
        alert(game);
        var thisdialog = new dijit.Dialog({title: "Information", content: "Article ajout&eacute; au panier"});

        var spanAv = ("#available_"+idGame);
        if(game.isAvailable) {
            spanAv.html("Disponible");
            spanAv.attr("style", "color:lime;");
        }
        else {
            spanAv.html("Non disponible");
            spanAv.attr("style", "color:red;");
        }

        thisdialog.show();
    },"json");
}

function modifierQuantite(idGame, price) {
    var quantite = $("#quantity_"+idGame).val();
    $("#subTotal_"+idGame).html(quantite*price);
    
    var total = 0;
    var quantites = $(".quantite");
    var subTotals = $(".subTotal");
    for(var i in quantites) {
        total = total + quantites[i].val()*parseInt(subTotals[i].html());
    }
    $("#totalPrice").html(total);
}
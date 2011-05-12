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
    var oldSubTotal = $("#subTotal_"+idGame).html();
    var quantite = $("#quantity_"+idGame).val();
    $("#subTotal_"+idGame).html(quantite*price);
    
    var totalPrice = $("#totalPrice").html();
    totalPrice = totalPrice-oldSubTotal;
    totalPrice = totalPrice+(quantite*price);
    $("#totalPrice").html(totalPrice);
}

function addRemoveShippingAddress() {    
    if(dijit.byId("hasShippingAddress").checked) {
        $("#divShippingAddress").attr("style", "visibility: visible;");
    }
    else {
        $("#divShippingAddress").attr("style", "visibility: hidden;");
    }
}

function showConnectionDialog() {
    dijit.byId('connectionDialog').show();
}

function connectGuest() {
    $.post("ConnectGuest", {email:$("#email").val(), password:$("#password").val()}, function(success) {
        if(success) {
            location.reload();
            dijit.byId('connectionDialog').hide();
        }
        else {
            new dijit.Dialog({title: "Erreur", content: "Bad email/password"}).show();
        }
    },"json");
}
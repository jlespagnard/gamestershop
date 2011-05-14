/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function getGamesByPlatform(idPlatform) {
    $.post("GetGamesByPlatform", {idPlatform : idPlatform}, function(data){
        alert(data);
        var content = "<%@include file=\"games.jsp\" %>";
        $("#content").html(content);
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

function addRemoveShippingAddress_sign() {    
    if(dijit.byId("hasShippingAddress_sign").checked) {
        $("#divShippingAddress_sign").attr("style", "visibility: visible;");
    }
    else {
        $("#divShippingAddress_sign").attr("style", "visibility: hidden;");
    }
}

function showConnectionDialog() {
    dijit.byId('connectionDialog').show();
}

function showSignupDialog() {
    dijit.byId('signupDialog').show();
}


function connectGuest() {
    $.post("ConnectGuest", {email:$("#email_conn").val(), password:$("#password_conn").val()}, function(success) {
        if(success) {
            document.location.href="home.jsp";
            dijit.byId('connectionDialog').hide();
        }
        else {
            new dijit.Dialog({title: "Erreur", content: "Bad email/password"}).show();
        }
    },"json");
}

function signupGuest() {
    $.post("SignupGuest", { firstname:$("#firstname_sign").val(), 
                            surname:$("#surname_sign").val(), 
                            email:$("#email_sign").val(), 
                            password:$("#password_sign").val(),
                            phone:$("#phone_sign").val(), 
                            cellular:$("#cellular_sign").val(), 
                            fax:$("#fax_sign").val(), 
                            billingAddressNumber:$("#billingAddressNumber_sign").val(), 
                            billingAddressRoad:$("#billingAddressRoad_sign").val(), 
                            billingAddressExtraInfos:$("#billingAddressExtraInfos_sign").val(), 
                            billingAddressZipCode:$("#billingAddressZipCode_sign").val(), 
                            billingAddressCity:$("#billingAddressCity_sign").val(), 
                            billingAddressCountrie:$("#billingAddressCountrie_sign").val(), 
                            hasShippingAddress:$("#hasShippingAddress_sign").val(), 
                            shippingAddressNumber:$("#shippingAddressNumber_sign").val(), 
                            shippingAddressRoad:$("#shippingAddressRoad_sign").val(), 
                            shippingAddressExtraInfos:$("#shippingAddressExtraInfos_sign").val(), 
                            shippingAddressZipCode:$("#shippingAddressZipCode_sign").val(), 
                            shippingAddressCity:$("#shippingAddressCity_sign").val(), 
                            shippingAddressCountrie:$("#shippingAddressCountrie_sign").val()}, 
            function(success) {
                if(success) {
                    document.location.href="home.jsp";
                    dijit.byId('signupDialog').hide();
                }
                else {
                    new dijit.Dialog({title: "Erreur", content: "A problem occurs during the sign up process.<br />Please, contact Papa Minouche or Mamie Cannette."}).show();
                }
            },"json");
}

function getListGuests(idCurrentGuest,firstResult) {
    $.post("GetListGuests",{firstResult: firstResult}, function(data) {
        var guests = data.guests;
        var nbTotalGuests = data.nbTotalGuests;
        
        var contentDiv = "";
        contentDiv += "<table style=\"width: 100%;\">";
        contentDiv += "  <tr class=\"titleTable\">";
        contentDiv += "      <td>Firstname</td>";
        contentDiv += "      <td>Surname</td>";
        contentDiv += "      <td>Email</td>";
        contentDiv += "      <td>Contact</td>";
        contentDiv += "      <td>Address</td>";
        contentDiv += "      <td class=\"transcol\">&nbsp;</td>";
        contentDiv += "  </tr>";
        for(var i in guests) {
            var guest = guests[i];
            if(parseInt(i) % 2 == 0) {
                contentDiv += "  <tr class=\"dataTableLight\">";
            }
            else {
                contentDiv += "  <tr class=\"dataTable\">";
            }
            contentDiv += "      <td>" + guest.firstname + "</td>";
            contentDiv += "      <td>" + guest.surname + "</td>";
            contentDiv += "      <td>" + guest.email + "</td>";
            if(guest.contact != null) {
                contentDiv += "      <td>";
                contentDiv += "          <table>";
                contentDiv += "              <tr>";
                contentDiv += "                  <td>Phone number : " + guest.contact.phone + "</td>";
                contentDiv += "              </tr>";
                contentDiv += "              <tr>";
                contentDiv += "                  <td>Cellular : " + guest.contact.cellular + "</td>";
                contentDiv += "              </tr>";
                contentDiv += "              <tr>";
                contentDiv += "                  <td>Fax number : " + guest.contact.fax + "</td>";
                contentDiv += "              </tr>";
                contentDiv += "          </table>";
                contentDiv += "      </td>";
            }
            else {
                contentDiv += "      <td>&nbsp;</td>";
            }
            
            contentDiv += "      <td>";
            contentDiv += "          <table>";
            contentDiv += "              <tr>";
            contentDiv += "                  <td style=\"vertical-align: top;\">Billing Address</td>";
            contentDiv += "                  <td>";
            contentDiv += "                      Number : " + guest.billingAddress.number + "<br />";
            contentDiv += "                      Road : " + guest.billingAddress.road + "<br />";
            contentDiv += "                      Extra informations : " + guest.billingAddress.suppInfos + "<br />";
            contentDiv += "                      Zip code : " + guest.billingAddress.zipCode + "<br />";
            contentDiv += "                      City : " + guest.billingAddress.city + "<br />";
            contentDiv += "                      Countrie : " + guest.billingAddress.countrie + "<br />";
            contentDiv += "                  </td>";
            contentDiv += "              </tr>";
            if(guest.shippingAddress != null) {
                contentDiv += "              <tr>";
                contentDiv += "                  <td style=\"vertical-align: top;\">Shipping Address</td>";
                contentDiv += "                  <td>";
                contentDiv += "                      Number : " + guest.shippingAddress.number + "<br />";
                contentDiv += "                      Road : " + guest.shippingAddress.road + "<br />";
                contentDiv += "                      Extra informations : " + guest.shippingAddress.suppInfos + "<br />";
                contentDiv += "                      Zip code : " + guest.shippingAddress.zipCode + "<br />";
                contentDiv += "                      City : " + guest.shippingAddress.city + "<br />";
                contentDiv += "                      Countrie : " + guest.shippingAddress.countrie + "<br />";
                contentDiv += "                  </td>";
                contentDiv += "              </tr>";
            }
            contentDiv += "          </table>";
            contentDiv += "      </td>";
            if(guest.id == idCurrentGuest) {
                contentDiv += "      <td class=\"transcol\">&nbsp;</td>";
            }
            else {
                contentDiv += "      <td class=\"transcol\"><img src=\"images/remove.png\" onclick=\"removeGuest(" + idCurrentGuest + "," + guest.id + "," + firstResult + ");\" /></td>";
            }
            contentDiv += "  </tr>";
        }
        contentDiv += " <tr>";
        contentDiv += "     <td colspan=\"5\" style=\"text-align: center;\">";
        if(parseInt(firstResult > 0)) {
            contentDiv += "         <a href=\"#\" onclick=\"getListGuests(" + idCurrentGuest + "," + firstResult + ")\"><img src=\"images/array_previous.png\" /></a>&nbsp;";
        }
        var firstResultPlusDix = parseInt(firstResult) + 10;
        if(firstResultPlusDix > nbTotalGuests) {
            firstResultPlusDix = nbTotalGuests;
        }
        contentDiv += "         <span>" + firstResult + "&nbsp;&agrave;&nbsp;" + firstResultPlusDix + "&nbsp;sur&nbsp;" + nbTotalGuests + "</span>&nbsp;";
        if(firstResultPlusDix < nbTotalGuests) {
            contentDiv += "         <a href=\"#\" onclick=\"getListGuests(" + idCurrentGuest + "," + firstResultPlusDix + ")\"><img src=\"images/array_next.png\" /></a>";
        }
        contentDiv += "     </td>";
        contentDiv += " </tr>";
        contentDiv += "</table>";
        $("#content").html(contentDiv);
    },"json");
}

function removeGuest(idCurrentGuest, idGuest, firstResult) {
    $.post("RemoveGuest",{idGuest: idGuest}, function(data) {
        if(data) {
            new dijit.Dialog({title: "Information", content: "Guest has been removed."}).show();
        }
        else {
            new dijit.Dialog({title: "Erreur", content: "A problem occurs during the deletion process.<br />Please, contact Papa Minouche or Mamie Cannette."}).show();
        }
        getListGuests(idCurrentGuest, firstResult);
    },"json");
}
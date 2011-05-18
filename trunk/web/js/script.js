/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function addToBasket(idGame) {
    jQuery.post("AddToBasket", {idGame : idGame}, function(game){
        var spanAv = jQuery("#available_"+idGame);
        if(game.available) {
            spanAv.html("Disponible");
            spanAv.attr("style", "color:lime;");
        }
        else {
            spanAv.html("Non disponible");
            spanAv.attr("style", "color:red;");
        }
        new dijit.Dialog({title: "Information", content: "Article ajout&eacute; au panier"}).show();
    },"json");
}

function showImageDialog(idImage) {
    var urlImage = dojo.attr(idImage,'src');
    var imgbal = "<img alt=\"" + urlImage + "\" src=\"" + urlImage + "\" /><br />";
    new dijit.Dialog({title: "Screenshot", content: imgbal}).show();
}

function modifierQuantite(idGame, price) {
    var oldSubTotal = jQuery("#subTotal_"+idGame).html();
    var quantite = jQuery("#quantity_"+idGame).val();
    jQuery("#subTotal_"+idGame).html(quantite*price);
    
    var totalPrice = jQuery("#totalPrice").html();
    totalPrice = totalPrice-oldSubTotal;
    totalPrice = totalPrice+(quantite*price);
    jQuery("#totalPrice").html(totalPrice);
}

function addRemoveShippingAddress() {    
    if(dijit.byId("hasShippingAddress").checked) {
        jQuery("#divShippingAddress").attr("style", "visibility: visible;");
    }
    else {
        jQuery("#divShippingAddress").attr("style", "visibility: hidden;");
    }
}

function addRemoveShippingAddress_sign() {    
    if(dijit.byId("hasShippingAddress_sign").checked) {
        jQuery("#divShippingAddress_sign").attr("style", "visibility: visible;");
    }
    else {
        jQuery("#divShippingAddress_sign").attr("style", "visibility: hidden;");
    }
}

function showConnectionDialog() {
    dijit.byId('connectionDialog').show();
}

function showSignupDialog() {
    dijit.byId('signupDialog').show();
}

function suggestSearch(value) {
    jQuery.post("GetSuggestSearch", {suggestValue: value}, function(data) {
        if(data != null && data.source != null && data.source.length > 0) {
            jQuery("input#searchGameValue").autocomplete({
                delay: 0,
                source: data.source
            });
        }
    },"json");
}

function connectGuest() {
    jQuery.post("ConnectGuest", {email:jQuery("#email_conn").val(), password:jQuery("#password_conn").val()}, function(success) {
        if(success) {
            document.location.href="home.jsp";
            dijit.byId('connectionDialog').hide();
        }
        else {
            new dijit.Dialog({title: "Erreur", content: "Bad email/password"}).show();
        }
    },"json");
}

function signupGuest(idGuest) {    
    jQuery.post("SignupGuest", {firstname:jQuery("#firstname_sign").val(), 
                            surname:jQuery("#surname_sign").val(), 
                            email:jQuery("#email_sign").val(), 
                            password:jQuery("#password_sign").val(),
                            phone:jQuery("#phone_sign").val(), 
                            cellular:jQuery("#cellular_sign").val(), 
                            fax:jQuery("#fax_sign").val(), 
                            billingAddressNumber:jQuery("#billingAddressNumber_sign").val(), 
                            billingAddressRoad:jQuery("#billingAddressRoad_sign").val(), 
                            billingAddressExtraInfos:jQuery("#billingAddressExtraInfos_sign").val(), 
                            billingAddressZipCode:jQuery("#billingAddressZipCode_sign").val(), 
                            billingAddressCity:jQuery("#billingAddressCity_sign").val(), 
                            billingAddressCountrie:jQuery("#billingAddressCountrie_sign").val(), 
                            hasShippingAddress:jQuery("#hasShippingAddress_sign").val(), 
                            shippingAddressNumber:jQuery("#shippingAddressNumber_sign").val(), 
                            shippingAddressRoad:jQuery("#shippingAddressRoad_sign").val(), 
                            shippingAddressExtraInfos:jQuery("#shippingAddressExtraInfos_sign").val(), 
                            shippingAddressZipCode:jQuery("#shippingAddressZipCode_sign").val(), 
                            shippingAddressCity:jQuery("#shippingAddressCity_sign").val(), 
                            shippingAddressCountrie:jQuery("#shippingAddressCountrie_sign").val()}, 
            function(success) {
                if(success) {
                    dijit.byId('signupForm').reset();
                    addRemoveShippingAddress_sign();
                    dijit.byId('signupDialog').hide();
                    if(idGuest > 0) {
                        getListGuests(idGuest,0);
                    }
                    else {
                        document.location.href="home.jsp";
                    }
                }
                else {
                    new dijit.Dialog({title: "Erreur", content: "A problem occurs during the sign up process.<br />Please, contact Papa Minouche or Mamie Cannette."}).show();
                }
            },"json");
}

function getListProducts(firstResult) {
    jQuery.post("GetListProducts",{firstResult: firstResult}, function(data) {
        var products = data.products;
        var nbTotalProducts = data.nbTotalProducts;
        
        var contentDiv = "";
        contentDiv += "<table style=\"width: 100%;\">";
        contentDiv += "  <tr class=\"titleTable\">";
        contentDiv += "      <td>Cover</td>";
        contentDiv += "      <td style=\"width: 40%;\">Name</td>";
        contentDiv += "      <td style=\"width: 15%;\">Paltform</td>";
        contentDiv += "      <td style=\"width: 10%;\">Price</td>";
        contentDiv += "      <td style=\"width: 10%;\">Quantity</td>";
        contentDiv += "      <td class=\"transcol\" style=\"width: 5%;\">&nbsp;</td>";
        contentDiv += "  </tr>";
        for(var i in products) {
            var product = products[i];
            if(parseInt(i) % 2 == 0) {
                contentDiv += "  <tr class=\"dataTableLight\">";
            }
            else {
                contentDiv += "  <tr class=\"dataTable\">";
            }
            contentDiv += "      <td style=\"text-align: center;\"><img alt=\"cover\" src=\"" + product.urlCover + "\" width=\"100px\" height=\"130px\" /></td>";
            contentDiv += "      <td>" + product.name + "</td>";
            contentDiv += "      <td>" + product.platform.name + "</td>";
            contentDiv += "      <td>" + product.price + "&nbsp;$</td>";
            contentDiv += "      <td>" + product.remainingQuantity + "</td>";
            contentDiv += "      <td class=\"transcol\"><img src=\"images/remove.png\" onclick=\"removeProduct(" + product.id + "," + firstResult + ");\" /></td>";
            contentDiv += "  </tr>";
        }
        contentDiv += " <tr>";
        contentDiv += "     <td colspan=\"5\" style=\"text-align: center;\">";
        if(parseInt(firstResult) > 0) {
            contentDiv += "         <a href=\"#\" onclick=\"getListProducts(" + (firstResult-10) + ")\"><img src=\"images/array_previous.png\" /></a>&nbsp;";
        }
        var firstResultPlusDix = parseInt(firstResult) + 10;
        if(firstResultPlusDix > nbTotalProducts) {
            firstResultPlusDix = nbTotalProducts;
        }
        contentDiv += "         <span>" + (firstResult+1) + "&nbsp;&agrave;&nbsp;" + firstResultPlusDix + "&nbsp;sur&nbsp;" + nbTotalProducts + "</span>&nbsp;";
        if(firstResultPlusDix < nbTotalProducts) {
            contentDiv += "         <a href=\"#\" onclick=\"getListProducts(" + firstResultPlusDix + ")\"><img src=\"images/array_next.png\" /></a>";
        }
        contentDiv += "     </td>";
        contentDiv += " </tr>";
        contentDiv += "</table>";
        jQuery("#content").html(contentDiv);
    },"json");
}

function removeProduct(idProduct, firstResult) {
    jQuery.post("RemoveProduct",{idProduct: idProduct}, function(data) {
        if(data) {
            new dijit.Dialog({title: "Information", content: "Product has been removed."}).show();
        }
        else {
            new dijit.Dialog({title: "Erreur", content: "A problem occurs during the deletion process.<br />Please, contact Papa Minouche or Mamie Cannette."}).show();
        }
        getListProducts(firstResult);
    },"json");
}

function getListGuests(idCurrentGuest,firstResult) {
    jQuery.post("GetListGuests",{firstResult: firstResult}, function(data) {
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
            if(guest.billingAddress.suppInfos != null) {
                contentDiv += "                      Extra informations : " + guest.billingAddress.suppInfos + "<br />";
            }
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
                if(guest.shippingAddress.suppInfos != null) {
                    contentDiv += "                      Extra informations : " + guest.shippingAddress.suppInfos + "<br />";
                }
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
        if(parseInt(firstResult) > 0) {
            contentDiv += "         <a href=\"#\" onclick=\"getListGuests(" + idCurrentGuest + "," + (firstResult-10) + ")\"><img src=\"images/array_previous.png\" /></a>&nbsp;";
        }
        var firstResultPlusDix = parseInt(firstResult) + 10;
        if(firstResultPlusDix > nbTotalGuests) {
            firstResultPlusDix = nbTotalGuests;
        }
        contentDiv += "         <span>" + (firstResult+1) + "&nbsp;&agrave;&nbsp;" + firstResultPlusDix + "&nbsp;sur&nbsp;" + nbTotalGuests + "</span>&nbsp;";
        if(firstResultPlusDix < nbTotalGuests) {
            contentDiv += "         <a href=\"#\" onclick=\"getListGuests(" + idCurrentGuest + "," + firstResultPlusDix + ")\"><img src=\"images/array_next.png\" /></a>";
        }
        contentDiv += "     </td>";
        contentDiv += " </tr>";
        contentDiv += "</table>";
        jQuery("#content").html(contentDiv);
    },"json");
}

function removeGuest(idCurrentGuest, idGuest, firstResult) {
    jQuery.post("RemoveGuest",{idGuest: idGuest}, function(data) {
        if(data) {
            new dijit.Dialog({title: "Information", content: "Guest has been removed."}).show();
        }
        else {
            new dijit.Dialog({title: "Erreur", content: "A problem occurs during the deletion process.<br />Please, contact Papa Minouche or Mamie Cannette."}).show();
        }
        getListGuests(idCurrentGuest, firstResult);
    },"json");
}

function purchaseOrder(idCurrentGuest) {
    if(idCurrentGuest <= 0) {
        showConnectionDialog();
    }
    else {
        var itemsQuantities = "{";
        jQuery('input.[id^="quantity_"]').each(function(){
            var id = this.id.replace("quantity_", "");
            itemsQuantities += id+":"+this.value+",";
        });
        itemsQuantities = itemsQuantities.substr(0, itemsQuantities.length-1);
        itemsQuantities += "}";
        alert(itemsQuantities);
        jQuery.post("PurchaseOrder", {itemsQuantities:itemsQuantities}, function(data) {
            if(data == 0) {
                var dialog = new dijit.Dialog({title: "Information", content: "Your order has been placed."});
                dialog.show();
                document.location.href="home.jsp";
            }
            else if(data == 1) {
                new dijit.Dialog({title: "Erreur", content: "Insuffisance r&eacute;nale.... euhhh no, insuffisance stock (sorry) : please review your order."}).show();
            }
            else {
                new dijit.Dialog({title: "Erreur", content: "A problem occurs during the order process.<br />Please, contact Papa Minouche or Mamie Cannette."}).show();
            }
        },"json");
    }
}

var guestsGlobal = new Array();
function showProfilDialog(index) {
    var guest = guestsGlobal[index];
    var profil = "";
    profil += "<table>";
    profil += "     <tr>";
    profil += "         <td>Firstname&nbsp;:&nbsp;" + guest.firstname + "</td>";
    profil += "     </tr>";
    profil += "     <tr>";
    profil += "         <td>Surname&nbsp;:&nbsp;" + guest.surname + "</td>";
    profil += "     </tr>";
    profil += "     <tr>";
    profil += "         <td>Email&nbsp;:&nbsp;" + guest.email + "</td>";
    profil += "     </tr>";
    if(guest.contact != null) {
        profil += "     <tr>";
        profil += "         <td>";
        profil += "             <p><b>Contact</b><br />";
        var phone = "";
        if(guest.contact.phone != null) {
            phone = guest.contact.phone;
        }
        var cellular = "";
        if(guest.contact.cellular != null) {
            phone = guest.contact.cellular;
        }
        var fax = "";
        if(guest.contact.fax != null) {
            phone = guest.contact.fax;
        }
        profil += "             Phone number&nbsp;:&nbsp;" + phone + "<br />";
        profil += "             Cellular&nbsp;:&nbsp;" + cellular + "<br />";
        profil += "             Fax&nbsp;:&nbsp;" + fax + "</p>";
        profil += "         </td>";
        profil += "     </tr>";
    }
    profil += "     <tr>";
    profil += "         <td>";
    profil += "             <p><b>Billing address</b><br />";
    profil += "             Number&nbsp;:&nbsp;" + guest.billingAddress.number + "<br />";
    profil += "             Road&nbsp;:&nbsp;" + guest.billingAddress.road + "<br />";
    if(guest.billingAddress.suppInfos != null) {
        profil += "             Extra infos&nbsp;:&nbsp;" + guest.billingAddress.suppInfos + "<br />";
    }
    profil += "             Zip code&nbsp;:&nbsp;" + guest.billingAddress.zipCode + "<br />";
    profil += "             City&nbsp;:&nbsp;" + guest.billingAddress.city + "<br />";
    profil += "             Countrie&nbsp;:&nbsp;" + guest.billingAddress.road + "</p>";
    profil += "         </td>";
    profil += "     </tr>";
    if(guest.shippingAddress != null) {
        profil += "     <tr>";
        profil += "         <td>";
        profil += "             <p><b>Shipping address</b><br />";
        profil += "             Number&nbsp;:&nbsp;" + guest.shippingAddress.number + "<br />";
        profil += "             Road&nbsp;:&nbsp;" + guest.shippingAddress.road + "<br />";
        if(guest.shippingAddress.suppInfos != null) {
            profil += "             Extra infos&nbsp;:&nbsp;" + guest.shippingAddress.suppInfos + "<br />";
        }
        profil += "             Zip code&nbsp;:&nbsp;" + guest.shippingAddress.zipCode + "<br />";
        profil += "             City&nbsp;:&nbsp;" + guest.shippingAddress.city + "<br />";
        profil += "             Countrie&nbsp;:&nbsp;" + guest.shippingAddress.road + "</p>";
        profil += "         </td>";
        profil += "     </tr>";
    }
    profil += "</table>";
    new dijit.Dialog({title: "Profil", content: profil}).show();
}

var itemsGlobal = new Array();
function showItemsDialog(index) {
    var items = itemsGlobal[index];
    var profil = "";
    profil += "<div style=\"height: 90%; overflow: atuo;\">";
    profil += " <table>";
    for(var i in items) {
        var item = items[i];
        profil += "     <tr>";
        profil += "         <td><img alt=\"Cover\" src=\"" + item.game.urlCover + "\" width=\"100px\" height=\"130px\" /></td>";
        profil += "         <td style=\"vertical-align: top;\">";
        profil += "             Name&nbsp;:&nbsp;" + item.game.name + "<br />";
        profil += "             Unit price&nbsp;:&nbsp;" + item.game.price + "&nbsp;$<br />";
        profil += "             Quantity&nbsp;:&nbsp;" + item.quantity + "<br />";
        profil += "             Subtotal&nbsp;:&nbsp;" + item.subTotal + "&nbsp;$";
        profil += "         </td>";
        profil += "     </tr>";
    }
    profil += " </table>";
    profil += "</div>";
    new dijit.Dialog({title: "Items", content: profil}).show();
}

function removeOrder(idOrder, firstResult) {
    jQuery.post("RemoveOrder",{idOrder: idOrder}, function(data) {
        if(data) {
            new dijit.Dialog({title: "Information", content: "Order has been removed."}).show();
        }
        else {
            new dijit.Dialog({title: "Erreur", content: "A problem occurs during the deletion process.<br />Please, contact Papa Minouche or Mamie Cannette."}).show();
        }
        getListOrders(firstResult);
    },"json");
}

function getListOrders(firstResult) {
    jQuery.post("GetListOrders",{firstResult: firstResult}, function(data) {
        var orders = data.orders;
        var nbTotalOrders = data.nbTotalOrders;
        
        var contentDiv = "";
        contentDiv += "<table style=\"width: 100%;\">";
        contentDiv += "  <tr class=\"titleTable\">";
        contentDiv += "      <td>Date</td>";
        contentDiv += "      <td>Amount</td>";
        contentDiv += "      <td>ID Guest</td>";
        contentDiv += "      <td>ID Items</td>";
        contentDiv += "      <td class=\"transcol\" style=\"width: 5%;\">&nbsp;</td>";
        contentDiv += "  </tr>";
        for(var i in orders) {
            var order = orders[i];
            guestsGlobal[i] = order.guest;
            itemsGlobal[i] = order.items;
            
            if(parseInt(i) % 2 == 0) {
                contentDiv += "  <tr class=\"dataTableLight\">";
            }
            else {
                contentDiv += "  <tr class=\"dataTable\">";
            }
            contentDiv += "      <td>" + order.orderDate + "</td>";
            contentDiv += "      <td>" + order.totalBasePrice + "&nbsp;$</td>";
            contentDiv += "      <td>" + order.guest.id + "&nbsp;<img style=\"float:right;vertical-align:bottom;\" src=\"images/eye.png\" onclick=\"showProfilDialog(" + i + ");\" /></td>";
            contentDiv += "      <td>" + order.nbItems + "&nbsp;<img style=\"float:right;vertical-align:bottom;\" alt=\"See items\" src=\"images/eye.png\" onclick=\"showItemsDialog(" + i + ");\" /></td>";
            contentDiv += "      <td class=\"transcol\"><img src=\"images/remove.png\" onclick=\"removeOrder(" + order.id + "," + firstResult + ");\" /></td>";
            contentDiv += "  </tr>";
        }
        contentDiv += " <tr>";
        contentDiv += "     <td colspan=\"5\" style=\"text-align: center;\">";
        if(parseInt(firstResult) > 0) {
            contentDiv += "         <a href=\"#\" onclick=\"getListProducts(" + (firstResult-10) + ")\"><img src=\"images/array_previous.png\" /></a>&nbsp;";
        }
        var firstResultPlusDix = parseInt(firstResult) + 10;
        if(firstResultPlusDix > nbTotalOrders) {
            firstResultPlusDix = nbTotalOrders;
        }
        contentDiv += "         <span>" + (firstResult+1) + "&nbsp;&agrave;&nbsp;" + firstResultPlusDix + "&nbsp;sur&nbsp;" + nbTotalOrders + "</span>&nbsp;";
        if(firstResultPlusDix < nbTotalOrders) {
            contentDiv += "         <a href=\"#\" onclick=\"getListProducts(" + firstResultPlusDix + ")\"><img src=\"images/array_next.png\" /></a>";
        }
        contentDiv += "     </td>";
        contentDiv += " </tr>";
        contentDiv += "</table>";
        jQuery("#content").html(contentDiv);
    },"json");
}
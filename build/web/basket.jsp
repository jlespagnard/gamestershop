<%--
    Document   : basket
    Created on : 24 avr. 2011, 19:01:17
    Author     : Julien LESPAGNARD
    Author     : Anthony BONIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<div dojoType="dijit.layout.ContentPane" region="center">
    <c:set var="items" value="${sessionScope['basket']}" />

        <c:choose>
            <c:when test="${items == null || fn:length(items) == 0}">
                <span>Votre panier est vide.</span>
            </c:when>
            <c:otherwise>
                <div dojoType="dijit.form.Form" id="purchaseForm" jsId="purchaseForm" encType="multipart/form-data" action="PurchaseOrder" method="POST">
                    <table>
                        <tr>
                            <td colspan="3">Contenu de votre panier</td>
                        </tr>
                        <c:set var="totalPrice" value="0" />
                        <c:forEach var="i" items="${items}">
                            <tr>
                                <td>i.game.name</td>
                                <td><input class="quantite" type="text" id="quantity_${i.game.id}" name="quantity_${i.game.id}" value="${i.quantity}" required="true" dojoType="dijit.form.NumberTextBox" onchange="modifierQuantite(${i.game.id},${i.game.price});" /></td>
                                <td><span class="subTotal" id="subTotal_${i.game.id}">i.subTotal</span></td>
                            </tr>
                            <c:set var="totalPrice" value="${totalPrice+i.subTotal}" />
                        </c:forEach>
                        <tr>
                            <td colspan="2" style="text-align: right"></td>
                            <td><span class="totalPrice" id="totalPrice">${totalPrice}</span>&nbsp;&euro;</td>
                        </tr>
                    </table>
                        <button dojoType="dijit.form.Button" type="submit" name="purchaseButton">Commander</button>
                </div>
            </c:otherwise>
        </c:choose>

</div>
<%@include file="footer.jsp" %>
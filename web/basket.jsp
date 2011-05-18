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
            <span>Your basket is empty.</span>
        </c:when>
        <c:otherwise>
            <div dojoType="dijit.form.Form" id="orderForm" style="overflow: auto;" jsId="orderForm" encType="multipart/form-data" action="" method="">
                <table style="width: 80%;">
                    <tr class="titleTable">
                        <td colspan="4"><b>Contenu de votre panier</b></td>
                    </tr>
                    <c:set var="totalPrice" value="0" />
                    <c:set var="index" value="0" />
                    <c:forEach var="i" items="${items}">
                        <c:choose>
                            <c:when test="${index % 2 == 0}">
                                <tr class="dataTableLight">
                            </c:when>
                            <c:otherwise>
                                <tr class="dataTable">
                            </c:otherwise>
                        </c:choose>
                            <td style="text-align: center;"><img alt="Cover" src="${i.game.urlCover}" width="100px" height="130px" /></td>
                            <td style="width: 50%;">${i.game.name}</td>
                            <td style="text-align: right;width: 10%;"><input type="text" class="quantite" id="quantity_${i.game.id}" name="quantity_${i.game.id}" value="${i.quantity}" required="true" trim="true" dojoType="dijit.form.NumberTextBox" maxlength="2" style="width: 1.5em;text-align: right;" onchange="modifierQuantite(${i.game.id},${i.game.price});" /></td>
                            <td style="width: 20%;text-align: right;"><span class="subTotal" id="subTotal_${i.game.id}">${i.subTotal}</span>&nbsp;$</td>
                        </tr>
                        <c:set var="totalPrice" value="${totalPrice+i.subTotal}" />
                        <c:set var="index" value="${index+1}" />
                    </c:forEach>
                    <tr>
                        <td colspan="3" style="text-align: right"><b>TOTAL</b></td>
                        <td style="text-align: right;"><span class="totalPrice" id="totalPrice">${totalPrice}</span>&nbsp;$</td>
                    </tr>
                </table>
                    <c:if test="${sessionScope['guest'] == null}">
                        <br /><button dojoType="dijit.form.Button" name="purchaseButton" style="float: right;position: relative;" onclick="purchaseOrder(-1)">Order</button>
                    </c:if>
                    <c:if test="${sessionScope['guest'] != null}">
                        <br /><button dojoType="dijit.form.Button" name="purchaseButton" style="float: right;position: relative;" onclick="purchaseOrder(${sessionScope['guest'].id})">Order</button>
                    </c:if>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="footer.jsp" %>
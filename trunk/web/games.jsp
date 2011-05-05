<%-- 
    Document   : games
    Created on : 24 avr. 2011, 19:01:17
    Author     : Julien LESPAGNARD
    Author     : Anthony BONIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<div dojoType="dijit.layout.ContentPane" region="center">
    <table>
        <c:forEach var="g" items="${requestScope['games']}">
            <c:set var="available" value="${g.isAvailable}" />
            <tr>
                <td style="text-align:center;">
                    <img alt="cover" src="${g.urlCover}" width="100px" height="130px" /><br />
                    <b>${g.price}&nbsp;&euro;</b>
                </td>
                <td style="vertical-align:top;">
                    <a href="GetGameInformations?idGame=${g.id}"><b>${g.name}</b></a><br />
                    D&eacute;veloppeur : ${g.developer}<br />
                    &Eacute;diteur : ${g.publisher}<br />
                    Date de sortie : ${g.releaseToString}<br />
                    <c:set var="i" value="1" />
                    <c:forEach var="p" items="${g.pegis}">
                        <img id="img_${g.id}${i}" src="${p.urlPictogram}" alt="${p.altText}" />
                        <span dojoType="dijit.Tooltip" connectId="img_${g.id}${i}">${p.altText}</span>
                        <c:set var="i" value="${i+1}" />
                    </c:forEach>
                    <br />
                    <c:choose>
                        <c:when test="${available}">
                            <span id="available_${g.id}" style="color: lime">Disponible</span>
                        </c:when>
                        <c:otherwise>
                            <span id="available_${g.id}" style="color: red">Non disponible</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td style="vertical-align: top;">
                    <button style="float: right; position: relative;" dojoType="dijit.form.Button" name="addToBasketButton" onClick="addToBasket(${g.id});" <c:if test="${!available}">disabled</c:if>>Ajouter au panier</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="footer.jsp" %>
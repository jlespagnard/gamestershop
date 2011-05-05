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
    <c:set var="g" value="${requestScope['game']}" />
    <c:set var="available" value="${g.isAvailable}" />
    <table>
        <tr>
            <td colspan="3"><b>${g.name}</b></td>
        </tr>
        <tr>
            <td style="text-align:center;">
                <img alt="cover" src="${g.urlCover}" width="100px" height="130px" /><br />
                <b>${g.price}&nbsp;&euro;</b>
            </td>
            <td style="vertical-align:top;">
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
                        <span id="available_${g.id}" style="color: lime;">Disponible</span>
                    </c:when>
                    <c:otherwise>
                        <span id="available_${g.id}" style="color: red;">Non disponible</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td style="vertical-align: top;">
                <button style="float: right; position: absolute;" dojoType="dijit.form.Button" name="addToBasketButton" onClick="addToBasket(${g.id});" <c:if test="${!available}">disabled</c:if>>Ajouter au panier</button>
            </td>
        </tr>
    </table>
    <div dojoType="dijit.layout.TabContainer" style="width: 100%; height: 70%;">
        <div dojoType="dijit.layout.ContentPane" title="Description" selected="true">
            ${g.description}
        </div>
        <div dojoType="dijit.layout.ContentPane" title="Images">
            <div id="page">
                <div id="gallery">
                    <table>
                        <tr>
                            <td>
                                <c:if test="${g.urlScreenshots[0] != null}">
                                    <div id="panel">
                                        <img id="largeImage" src="${g.urlScreenshots[0]}" width="550px" height="auto" />
                                        <div id="description">${g.urlScreenshots[0]}</div>
                                    </div>
                                </c:if>
                            </td>
                            <td style="width:auto;">
                                <div id="thumbs">
                                    <table>
                                        <c:forEach var="url" items="${g.urlScreenshots}">
                                            <tr>
                                                <td>
                                                    <img src="${url}" alt="${url}" width="100px" height="auto" />
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <script>
                dojo.query('#thumbs img').onclick( function(e){
                    dojo.attr('largeImage','src',dojo.attr(this,'src').replace('thumb','large'));
                    dojo.attr('description','innerHTML',dojo.attr(this,'alt'));
                });
            </script>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
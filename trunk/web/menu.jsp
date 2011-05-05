<%-- 
    Document   : menu
    Created on : 25 avr. 2011, 22:14:36
    Author     : Julien LESPAGNARD
    Author     : Anthony BONIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<input type="hidden" name="idPlatform" id="idPlatform" value="-1" />
<div dojoType="dijit.layout.ContentPane" region="leading">
    <ul>
        <li><a href="GetSelection">S&eacute;lection</a></li>
        <c:forEach var="p" items="${sessionScope['platforms']}" >
            <li><a href="GetGamesByPlatform?idPlatform=${p.id}">${p.name}</a></li>
        </c:forEach>
    </ul>
</div>
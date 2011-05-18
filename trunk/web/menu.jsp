<%-- 
    Document   : menu
    Created on : 25 avr. 2011, 22:14:36
    Author     : Julien LESPAGNARD
    Author     : Anthony BONIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<input type="hidden" name="idPlatform" id="idPlatform" value="-1" />
<div dojoType="dijit.layout.ContentPane" region="leading">
    <div>
        <form action="GetGames" method="POST">
            <label for="searchGameValue">Find a game</label><br />
            <input type="text" id="searchGameValue" name="searchGameValue" required="false" dojoType="dijit.form.TextBox" autocomplete="off" style="width: 175px;" onkeyup="suggestSearch(this.value);" />&nbsp;
            <input type="image" alt="Magnifying glass" src="images/loupe.png" style="vertical-align: bottom;" height="20px" width="auto" />
        </form>
    </div>
    <ul>
        <li><a href="GetSelection">S&eacute;lection</a></li>
        <c:forEach var="p" items="${sessionScope['platforms']}" >
            <li><a href="GetGames?idPlatform=${p.id}">${p.name}</a></li>
        </c:forEach>
    </ul>
</div>
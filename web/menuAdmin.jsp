<%-- 
    Document   : menuadmin
    Created on : 25 avr. 2011, 22:14:36
    Author     : Julien LESPAGNARD
    Author     : Anthony BONIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div dojoType="dijit.layout.ContentPane" region="leading">
    <div dojoType="dijit.layout.AccordionContainer" style="height: 400px;width: 200px;">
        <div dojoType="dijit.layout.ContentPane" title="Utilisateurs">
            <ul>
                <li>Liste</li>
                <li>Rechercher</li>
                <li>Nouvel utilisateur</li>
            </ul>
        </div>
        <div dojoType="dijit.layout.ContentPane" title="Produits">
            <ul>
                <li>Liste</li>
                <li>Rechercher</li>
                <li>Ajouter un produit</li>
            </ul>
        </div>
        <div dojoType="dijit.layout.ContentPane" title="Commandes" selected="true">
            <ul>
                <li>Liste</li>
                <li>Rechercher</li>
            </ul>
        </div>
    </div>
</div>
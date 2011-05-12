<%-- 
    Document   : header
    Created on : 23 avr. 2011, 23:23:51
    Author     : Roulio
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gamestershop</title>
        <script src="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dojo/dojo.xd.js" type="text/javascript" djConfig="parseOnLoad: true"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            dojo.require("dijit.dijit");
            dojo.require("dijit.Menu");
            dojo.require("dijit.layout.BorderContainer");
            dojo.require("dijit.Tooltip");
            dojo.require("dijit.layout.TabContainer");
            dojo.require("dijit.layout.ContentPane");
            dojo.require("dojo.data.api.Read");
            dojo.require("dojo.data.ItemFileReadStore");
            dojo.require("dojox.image.Gallery");
            dojo.require("dojo.parser");
            dojo.require("dojo.fx");
            dojo.require("dijit.Dialog");
            dojo.require("dijit.form.Form");
            dojo.require("dijit.form.NumberTextBox");
            dojo.require("dijit.form.CheckBox");
            dojo.require("dijit.form.ValidationTextBox");
            dojo.require("dijit.form.TextBox");
            dojo.require("dijit.form.Button");
            dojo.require("dijit.layout.AccordionContainer");
        </script>
        <script src="js/script.js" type="text/javascript" djConfig="parseOnLoad: true"></script>
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dijit/themes/nihilo/nihilo.css" />
        <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dojox/image/resources/image.css">
        <link rel="stylesheet" href="css/default.css" />        
    </head>
    <body class="nihilo">
        <div dojoType="dijit.layout.BorderContainer" style="width: 100%; height: 100%">
            <div id="headerPane" name="headerPane" dojoType="dijit.layout.ContentPane" region="top" refreshOnShow="true">
                ${msgHome}<br />
                <div style="position: relative; float: right;">
                    <form action="DispatchActionHeader" method="POST">
                        <input type="image" id="imgHome" src="images/home.png" onclick="$('#actionHeader').val('home');" />
                        <span dojoType="dijit.Tooltip" connectId="imgHome">Home</span>&nbsp;
                        <c:choose>
                            <c:when test="${sessionScope['guest'] == null}">
                                <img src="images/login.png" id="imgLogin" onclick="showConnectionDialog();" />
                                <span dojoType="dijit.Tooltip" connectId="imgLogin">Login</span>&nbsp;
                            </c:when>
                            <c:otherwise>
                                <input type="image" src="images/profil.png" id="imgProfil" onclick="$('#actionHeader').val('profilPanel');" />
                                <span dojoType="dijit.Tooltip" connectId="imgProfil">Profil</span>&nbsp;
                            </c:otherwise>
                        </c:choose>
                        
                        <input type="image" src="images/admin.png" id="imgAdmin" onclick="$('#actionHeader').val('adminPanel');" />
                        <span dojoType="dijit.Tooltip" connectId="imgAdmin">Admin Console</span>&nbsp;
                        <input type="image" src="images/caddie.png" id="imgBasket" onclick="$('#actionHeader').val('basket');" />
                        <span dojoType="dijit.Tooltip" connectId="imgBasket">Basket</span>
                        <input type="hidden" id="actionHeader" name="actionHeader" value="" />
                    </form>
                </div>
            </div>
            <div dojoType="dijit.Dialog" id="connectionDialog" jsId="connectionDialog" title="Connection">
                <div dojoType="dijit.form.Form" id="connectionForm" jsId="connectionForm" encType="multipart/form-data" action="" method="">
                    <table>
                        <tr>
                            <td>Email</td>
                            <td><input type="text" id="email" name="email" required="true" trim="true" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td>Mot de passe</td>
                            <td><input type="password" id="password" name="password" required="true" trim="true" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" style="float:right;position:relative;"><button dojoType="dijit.form.Button" name="connectGuestButton" onclick="connectGuest()">Connection</button></td>
                        </tr>
                    </table>
                </div>
            </div>
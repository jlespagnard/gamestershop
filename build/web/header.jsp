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
        </script>
        <script src="js/script.js" type="text/javascript"></script>
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dijit/themes/nihilo/nihilo.css" />
        <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dojox/image/resources/image.css">
        <link rel="stylesheet" href="css/default.css" />        
    </head>
    <body class="nihilo">
        <div dojoType="dijit.layout.BorderContainer" style="width: 100%; height: 100%">
            <div dojoType="dijit.layout.ContentPane" region="top">
                Bonsoir le monde !<br />
                <a style="float: right; position: relative;" href="basket.jsp">Panier</a>
            </div>
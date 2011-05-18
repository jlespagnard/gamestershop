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
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dijit/themes/claro/claro.css" />
        <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dojox/image/resources/image.css" />
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/lightbox.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="css/default.css" />
        <script src="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dojo/dojo.xd.js" type="text/javascript" djConfig="parseOnLoad: true"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.13/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/prototype.js"></script>
        <script type="text/javascript" src="js/scriptaculous.js?load=effects,builder"></script>
        <script type="text/javascript" src="js/lightbox.js"></script>
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
    </head>
    <body class="claro">
        <div dojoType="dijit.layout.BorderContainer" class="mainContainer">
            <div id="headerPane" name="headerPane" dojoType="dijit.layout.ContentPane" region="top" refreshOnShow="true">
                ${msgHome}<br />
                <div style="position: relative; float: right;">
                    <form action="DispatchActionHeader" method="POST">
                        <input type="image" id="imgHome" src="images/home.png" onclick="$('#actionHeader').val('home');" />
                        <span dojoType="dijit.Tooltip" connectId="imgHome">Home</span>&nbsp;
                        <c:choose>
                            <c:when test="${sessionScope['guest'] == null}">
                                <img src="images/login.png" id="imgLogin" onclick="showConnectionDialog();" />
                                <div dojoType="dijit.Tooltip" connectId="imgLogin">Login</div>&nbsp;
                                <img src="images/signup.png" id="imgSignup" onclick="showSignupDialog(-1);" />
                                <div dojoType="dijit.Tooltip" connectId="imgSignup">Sign&nbsp;up</div>&nbsp;
                            </c:when>
                            <c:otherwise>
                                <a href="profil.jsp"><img src="images/profil.png" id="imgProfil" /></a>
                                <div dojoType="dijit.Tooltip" connectId="imgProfil">Profil</div>&nbsp;
                                <a href="DisconnectGuest"><img src="images/disconnect.png" id="imgDisconnect" /></a>
                                <div dojoType="dijit.Tooltip" connectId="imgDisconnect">Disconnect</div>&nbsp;
                                <c:if test="${sessionScope['guest'].email == 'admin@admin.fr'}">
                                    <a href="admin.jsp"><img src="images/admin.png" id="imgAdmin" /></a>
                                    <div dojoType="dijit.Tooltip" connectId="imgAdmin">Admin&nbsp;Console</div>&nbsp;
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        <a href="basket.jsp"><img src="images/caddie.png" id="imgBasket" /></a>
                        <div dojoType="dijit.Tooltip" connectId="imgBasket">Basket</div>
                        <input type="hidden" id="actionHeader" name="actionHeader" value="" />
                    </form>
                </div>
            </div>
            <div dojoType="dijit.Dialog" id="connectionDialog" jsId="connectionDialog" title="Connection">
                <div dojoType="dijit.form.Form" id="connectionForm" jsId="connectionForm" encType="multipart/form-data" action="" method="">
                    <table>
                        <tr>
                            <td>Email</td>
                            <td><input type="text" id="email_conn" name="email_conn" required="true" trim="true" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td>Mot de passe</td>
                            <td><input type="password" id="password_conn" name="password_conn" required="true" trim="true" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" style="float:right;position:relative;"><button dojoType="dijit.form.Button" name="connectGuestButton" onclick="connectGuest()">Connection</button></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div dojoType="dijit.Dialog" id="signupDialog" jsId="signupDialog" title="Sign up" style="height: 90%;overflow: auto;">
                <div dojoType="dijit.form.Form" id="signupForm" jsId="signupForm" encType="multipart/form-data" action="" method="">
                    <table>
                        <tr>
                            <td>Firstname</td>
                            <td><input type="text" id="firstname_sign" name="firstname_sign" required="true" trim="false" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td>Surname</td>
                            <td><input type="text" id="surname_sign" name="surname_sign" required="true" trim="false" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><input type="text" id="email_sign" name="email_sign" required="true" trim="true" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td>Mot de passe</td>
                            <td><input type="password" id="password_sign" name="password_sign" required="true" trim="true" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" style="font-weight: bold;">Contact information</td>
                        </tr>
                        <tr>
                            <td>Phone number</td>
                            <td><input type="text" id="phone_sign" name="phone_sign" required="false" trim="true" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td>Cellular</td>
                            <td><input type="text" id="cellular_sign" name="cellular_sign" required="false" trim="true" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td>Fax number</td>
                            <td><input type="text" id="fax_sign" name="fax_sign" required="false" trim="true" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" style="font-weight: bold;">Billing address</td>
                        </tr>
                        <tr>
                            <td>Number</td>
                            <td><input type="text" id="billingAddressNumber_sign" name="billingAddressNumber_sign" required="true" trim="true" dojoType="dijit.form.NumberTextBox" /></td>
                        </tr>
                        <tr>
                            <td>Road</td>
                            <td><input type="text" id="billingAddressRoad_sign" name="billingAddressRoad_sign" required="true" trim="false" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td>Extra informations</td>
                            <td><input type="text" id="billingAddressExtraInfos_sign" name="billingAddressExtraInfos_sign" required="false" trim="false" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td>Zip code</td>
                            <td><input type="text" id="billingAddressZipCode_sign" name="billingAddressZipCode_sign" required="true" trim="true" maxlength="5" style="width: 5em;" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td>City</td>
                            <td><input type="text" id="billingAddressCity_sign" name="billingAddressCity_sign" required="true" trim="false" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                        <tr>
                            <td>Countrie</td>
                            <td><input type="text" id="billingAddressCountrie_sign" name="billingAddressCountrie_sign" required="true" trim="false" dojoType="dijit.form.TextBox" /></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td colspan="2"><input type="checkbox" id="hasShippingAddress_sign" name="hasShippingAddress_sign" required="false" dojoType="dijit.form.CheckBox" onclick="addRemoveShippingAddress_sign();" />&nbsp;My shipping address is not my billing address.</td>
                        </tr>
                    </table>
                    <div id="divShippingAddress_sign" name="divShippingAddress_sign" style="visibility: hidden;">
                        <table>
                            <tr>
                                <td colspan="2" style="font-weight: bold;">Shipping address</td>
                            </tr>
                            <tr>
                                <td>Number</td>
                                <td><input type="text" id="shippingAddressNumber_sign" name="shippingAddressNumber_sign" required="true" trim="true" dojoType="dijit.form.NumberTextBox" /></td>
                            </tr>
                            <tr>
                                <td>Road</td>
                                <td><input type="text" id="shippingAddressRoad_sign" name="shippingAddressRoad_sign" required="true" trim="false" dojoType="dijit.form.TextBox" /></td>
                            </tr>
                            <tr>
                                <td>Extra informations</td>
                                <td><input type="text" id="shippingAddressExtraInfos_sign" name="shippingAddressExtraInfos_sign" required="false" trim="false" dojoType="dijit.form.TextBox" /></td>
                            </tr>
                            <tr>
                                <td>Zip code</td>
                                <td><input type="text" id="shippingAddressZipCode_sign" name="shippingAddressZipCode_sign" required="true" trim="true" maxlength="5" style="width: 5em;" dojoType="dijit.form.TextBox" /></td>
                            </tr>
                            <tr>
                                <td>City</td>
                                <td><input type="text" id="shippingAddressCity_sign" name="shippingAddressCity_sign" required="true" trim="false" dojoType="dijit.form.TextBox" /></td>
                            </tr>
                            <tr>
                                <td>Countrie</td>
                                <td><input type="text" id="shippingAddressCountrie_sign" name="shippingAddressCountrie_sign" required="true" trim="false" dojoType="dijit.form.TextBox" /></td>
                            </tr>
                        </table>
                    </div>
                    <button dojoType="dijit.form.Button" name="signupGuestButton" style="float: right;position: relative;" onclick="signupGuest(${sessionScope['guest'].id})">Sign up</button>
                </div>
            </div>
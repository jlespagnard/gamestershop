<%--
    Document   : admin
    Created on : 17 Mai 2011, 09h32
    Author     : Julien LESPAGNARD
    Author     : Anthony BONIN
--%>

<%@page pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="menuAdmin.jsp" %>
<div id="content" name="content" dojoType="dijit.layout.ContentPane" region="center">
    <div dojoType="dijit.form.Form" id="newProductForm" jsId="newProductForm" encType="multipart/form-data" action="" method="">
        <table>
            <tr>
                <td>Title</td>
                <td><input type="text" id="title_sign" name="title_sign" required="true" dojoType="dijit.form.TextBox" /></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" id="description_sign" name="description_sign" required="true"dojoType="dijit.form.TextBox" /></td>
            </tr>
            <tr>
                <td>Url Cover</td>
                <td><input type="text" id="cover_sign" name="cover_sign" required="true" dojoType="dijit.form.TextBox" /></td>
            </tr>
            <tr>
                <td>Developper</td>
                <td><input type="text" id="developper_sign" name="developper_sign" required="false" dojoType="dijit.form.TextBox" /></td>
            </tr>
            <tr>
                <td>Publisher</td>
                <td><input type="text" id="publisher_sign" name="publisher_sign" required="false" dojoType="dijit.form.TextBox" /></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" id="price_sign" name="price_sign" required="true"dojoType="dijit.form.NumberTextBox" /></td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td><input type="text" id="quantity_sign" name="quantity_sign" required="true" dojoType="dijit.form.NumberTextBox" /></td>
            </tr>
            <tr>
                <td>Screenshot </td>
                <td><input type="text" id="screenshot_sign" name="screenshot_sign" required="false" dojoType="dijit.form.TextBox" /></td>
            </tr>
            <tr>
                <td>Features</td>
                <td><input type="text" id="feature_sign" name="feature_sign" required="true" dojoType="dijit.form.TextBox" /></td>
            </tr>
            <tr>
                <td>Release date</td>
                <td><input type="text" name="release_sign" id="release_sign" dojoType="dijit.form.DateTextBox" required="true" /></td>
            </tr>
            <tr>
                <td>Gender</td>
                <td>
                    <select name="gender_sign" id="gender_sign" required="false" dojoType="dijit.form.MultiSelect">
                        <c:set var="id" value="0" />
                        <c:forEach var="gender" items="${sessionScope['genders']}">
                            <c:set var="id" value="${id + 1}" />
                            <option id="gender_${id}" value="${gender.name}">${gender.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Platforms</td>
                <td>
                   <select name="platforms_sign" id="platforms_sign" required="true" dojoType="dijit.form.MultiSelect">
                       <c:set var="id2" value="0" />
                        <c:forEach var="platform" items="${sessionScope['platforms']}">
                            <c:set var="id2" value="${id2 + 1}" />
                            <option id="plat_${id2}" value="${platform.name}">${platform.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>ESRB</td>
                <td>
                    <select name="ESRB_sign" id="ESRB_sign" dojoType="dijit.form.Select">
                         <c:forEach var="pegi" items="${sessionScope['pegis']}">
                            <option value="{pegi}">${pegi}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <button dojoType="dijit.form.Button" name="addProductButton" style="float: left;position: relative;" onclick="addProduct();">Save</button>
    </div>
</div>
<%@include file="footer.jsp" %>
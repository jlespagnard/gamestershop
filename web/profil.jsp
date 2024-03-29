<%--
    Document   : profil
    Created on : 23 avr. 2011, 20:44:12
    Author     : Julien LESPAGNARD
    Author     : Anthony BONIN
--%>

<%@page pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<c:set var="g" value="${sessionScope['guest']}" />
<div id="content" name="content" dojoType="dijit.layout.ContentPane" region="center">
    <div style="margin:auto;">
        <form action="UpdateGuestProfil" method="POST">
            <table>
                <tr>
                    <td>Surname</td>
                    <td><input type="text" id="surnameGuest" name="surnameGuest" value="${g.surname}" required="true" dojoType="dijit.form.ValidationTextBox" /></td>
                </tr>
                <tr>
                    <td>Firstname</td>
                    <td><input type="text" id="firstnameGuest" name="firstnameGuest" value="${g.firstname}" required="true" dojoType="dijit.form.ValidationTextBox" /></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" id="emailGuest" name="emailGuest" value="${g.email}" required="true" dojoType="dijit.form.ValidationTextBox" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" id="passwordGuest" name="passwordGuest" value="${g.password}" required="true" dojoType="dijit.form.ValidationTextBox" /></td>
                </tr>
            </table>
            <span>Contact informations</span>
            <c:set var="phone" value="" />
            <c:set var="cellular" value="" />
            <c:set var="fax" value="" />
            <c:if test="${g.contact != null}">
                <c:set var="phone" value="${g.contact.phone}" />
                <c:set var="cellular" value="${g.contact.cellular}" />
                <c:set var="fax" value="${g.contact.fax}" />
            </c:if>
            <table>
                <tr>
                    <td>Phone number</td>
                    <td><input type="text" id="phoneGuest" name="phoneGuest" value="${phone}" required="false" dojoType="dijit.form.NumberTextBox" /></td>
                </tr>
                <tr>
                    <td>Cellular</td>
                    <td><input type="text" id="cellularGuest" name="cellularGuest" value="${cellular}" required="false" dojoType="dijit.form.NumberTextBox" /></td>
                </tr>
                <tr>
                    <td>Fax number</td>
                    <td><input type="text" id="faxGuest" name="faxGuest" value="${fax}" required="false" dojoType="dijit.form.NumberTextBox" /></td>
                </tr>
            </table>
            <span>Billing address</span>
            <table>
                <tr>
                    <td>Number</td>
                    <td><input type="text" id="addressNumberGuest" name="addressNumberGuest" value="${g.billingAddress.number}" required="true" dojoType="dijit.form.NumberTextBox" /></td>
                </tr>
                <tr>
                    <td>Road</td>
                    <td><input type="text" id="addressRoadGuest" name="addressRoadGuest" value="${g.billingAddress.road}" required="true" dojoType="dijit.form.ValidationTextBox" /></td>
                </tr>
                <tr>
                    <td>Extra informations</td>
                    <td><input type="text" id="addressInfoSuppGuest" name="addressInfoSuppGuest" value="${g.billingAddress.suppInfos}" required="true" dojoType="dijit.form.ValidationTextBox" /></td>
                </tr>
                <tr>
                    <td>Zip code</td>
                    <td><input type="text" id="addressZipCodeGuest" name="addressZipCodeGuest" value="${g.billingAddress.zipCode}" required="true" dojoType="dijit.form.TextBox" maxlength="5" trim="true" style="width: 5em;" /></td>
                </tr>
                <tr>
                    <td>City</td>
                    <td><input type="text" id="addressCityGuest" name="addressCityGuest" value="${g.billingAddress.city}" required="true" dojoType="dijit.form.ValidationTextBox" /></td>
                </tr>
                <tr>
                    <td>Countrie</td>
                    <td><input type="text" id="addressCountrieGuest" name="addressCountrieGuest" value="${g.billingAddress.countrie}" required="true" dojoType="dijit.form.ValidationTextBox" /></td>
                </tr>
            </table>
            <table>
                <tr>
                    <c:choose>
                        <c:when test="${guest.shippingAddress != null}">
                            <td colspan="2"><input type="checkbox" id="hasShippingAddress" name="hasShippingAddress" required="false" dojoType="dijit.form.CheckBox" checked="checked" onclick="addRemoveShippingAddress();" />&nbsp;My shipping address is not my billing address.</td>
                        </c:when>
                        <c:otherwise>
                            <td colspan="2"><input type="checkbox" id="hasShippingAddress" name="hasShippingAddress" required="false" dojoType="dijit.form.CheckBox" onclick="addRemoveShippingAddress();" />&nbsp;My shipping address is not my billing address.</td>
                        </c:otherwise>
                        </c:choose>
                </tr>
            </table>
            <div id="divShippingAddress" name="divShippingAddress" <c:choose><c:when test="${guest.shippingAddress == null}">style="visibility: hidden;"</c:when><c:otherwise>style="visibility: visible;"</c:otherwise></c:choose>>
                <span>Shipping address</span>
                <table>
                    <tr>
                        <td>Number</td>
                        <td><input type="text" id="shippingAddressNumberGuest" name="shippingAddressNumberGuest" <c:if test="${guest.shippingAddress != null}">value="${g.shippingAddress.number}"</c:if> <c:if test="dijit.byId('hasShippingAddress').checked">required="true"</c:if> dojoType="dijit.form.NumberTextBox" /></td>
                    </tr>
                    <tr>
                        <td>Road</td>
                        <td><input type="text" id="shippingAddressRoadGuest" name="shippingAddressRoadGuest" <c:if test="${guest.shippingAddress != null}">value="${g.shippingAddress.road}"</c:if> <c:if test="dijit.byId('hasShippingAddress').checked">required="true"</c:if> dojoType="dijit.form.ValidationTextBox" /></td>
                    </tr>
                    <tr>
                        <td>Extra informations</td>
                        <td><input type="text" id="shippingAddressInfoSuppGuest" name="shippingAddressInfoSuppGuest" <c:if test="${guest.shippingAddress != null}">value="${g.shippingAddress.suppInfos}"</c:if> <c:if test="dijit.byId('hasShippingAddress').checked">required="true"</c:if> dojoType="dijit.form.ValidationTextBox" /></td>
                    </tr>
                    <tr>
                        <td>Zip code</td>
                        <td><input type="text" id="shippingAddressZipCodeGuest" name="shippingAddressZipCodeGuest" <c:if test="${guest.shippingAddress != null}">value="${g.shippingAddress.zipCode}"</c:if> <c:if test="dijit.byId('hasShippingAddress').checked">required="true"</c:if> dojoType="dijit.form.TextBox" maxlength="5" trim="true" style="width: 5em;" /></td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td><input type="text" id="shippingAddressCityGuest" name="shippingAddressCityGuest" <c:if test="${guest.shippingAddress != null}">value="${g.shippingAddress.city}"</c:if> <c:if test="dijit.byId('hasShippingAddress').checked">required="true"</c:if> dojoType="dijit.form.ValidationTextBox" /></td>
                    </tr>
                    <tr>
                        <td>Countrie</td>
                        <td><input type="text" id="shippingAddressCountrieGuest" name="shippingAddressCountrieGuest" <c:if test="${guest.shippingAddress != null}">value="${g.shippingAddress.countrie}"</c:if> <c:if test="dijit.byId('hasShippingAddress').checked">required="true"</c:if> dojoType="dijit.form.ValidationTextBox" /></td>
                    </tr>
                </table>
            </div>
            <button type="submit" dojoType="dijit.form.Button" style="float: right;position: relative;" name="updateProfil">Update</button>
        </form>
    </div>
</div>
<%@include file="footer.jsp" %>
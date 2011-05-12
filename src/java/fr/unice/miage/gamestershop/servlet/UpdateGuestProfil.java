/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.gamestershop.servlet;

import fr.unice.miage.gamestershop.entity.Address;
import fr.unice.miage.gamestershop.entity.Contact;
import fr.unice.miage.gamestershop.entity.Guest;
import fr.unice.miage.gamestershop.manager.GuestManager;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Julien LESPAGNARD
 * @author Anthony BONIN
 */
@WebServlet(name = "UpdateGuestProfil", urlPatterns = {"/UpdateGuestProfil"})
public class UpdateGuestProfil extends HttpServlet {

    @EJB
    private GuestManager guestManager;
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("TU PASSES TE KASSES !");
        HttpSession session = request.getSession();
        Guest guest = (Guest)session.getAttribute("guest");
        
        String firstname = request.getParameter("firstnameGuest");
        String surname = request.getParameter("surnameGuest");
        String email = request.getParameter("emailGuest");
        String password = request.getParameter("passwordGuest");
        guest.setFirstname(firstname);
        guest.setSurname(surname);
        guest.setEmail(email);
        guest.setPassword(password);
        
        String phoneGuest = request.getParameter("phoneGuest");
        String cellularGuest = request.getParameter("cellularGuest");
        String faxGuest = request.getParameter("faxGuest");
        if((phoneGuest != null && !phoneGuest.trim().isEmpty()) 
                || (cellularGuest != null && !cellularGuest.trim().isEmpty()) 
                || (faxGuest != null && !faxGuest.trim().isEmpty())) {
            Contact contact = guest.getContact();
            if(contact == null) {
                contact = new Contact();
                guest.setContact(contact);
            }
            contact.setPhone(phoneGuest);
            contact.setCellular(cellularGuest);
            contact.setFax(faxGuest);
        }
        
        String addressNumberGuest = request.getParameter("addressNumberGuest");
        String addressRoadGuest = request.getParameter("addressRoadGuest");
        String addressInfoSuppGuest = request.getParameter("addressInfoSuppGuest");
        String addressZipCodeGuest = request.getParameter("addressZipCodeGuest");
        String addressCityGuest = request.getParameter("addressCityGuest");
        String addressCountrieGuest = request.getParameter("addressCountrieGuest");
        Address billingAddress = guest.getBillingAddress();
        billingAddress.setNumber(Integer.parseInt(addressNumberGuest));
        billingAddress.setRoad(addressRoadGuest);
        billingAddress.setSuppInfos(addressInfoSuppGuest);
        billingAddress.setZipCode(addressZipCodeGuest);
        billingAddress.setCity(addressCityGuest);
        billingAddress.setCountrie(addressCountrieGuest);
        
        String hasShippingAddress = request.getParameter("hasShippingAddress");
        Address shippingAddress = null;
        if(hasShippingAddress != null && hasShippingAddress.equalsIgnoreCase("on")) {
            shippingAddress = guest.getShippingAddress();
            if(shippingAddress == null) {
                shippingAddress = new Address();
            }
            String shippingAddressNumberGuest = request.getParameter("shippingAddressNumberGuest");
            String shippingAddressRoadGuest = request.getParameter("shippingAddressRoadGuest");
            String shippingAddressInfoSuppGuest = request.getParameter("shippingAddressInfoSuppGuest");
            String shippingAddressZipCodeGuest = request.getParameter("shippingAddressZipCodeGuest");
            String shippingAddressCityGuest = request.getParameter("shippingAddressCityGuest");
            String shippingAddressCountrieGuest = request.getParameter("shippingAddressCountrieGuest");
            shippingAddress.setNumber(Integer.parseInt(shippingAddressNumberGuest));
            shippingAddress.setRoad(shippingAddressRoadGuest);
            shippingAddress.setSuppInfos(shippingAddressInfoSuppGuest);
            shippingAddress.setZipCode(shippingAddressZipCodeGuest);
            shippingAddress.setCity(shippingAddressCityGuest);
            shippingAddress.setCountrie(shippingAddressCountrieGuest);
        }
        guest.setShippingAddress(shippingAddress);
        
        guest = guestManager.save(guest);
        
        session.setAttribute("guest", guest);
        
        request.getRequestDispatcher("profil.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

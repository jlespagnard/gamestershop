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
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Julien LESPAGNARD
 * @author Anthony BONIN
 */
@WebServlet(name = "SignupGuest", urlPatterns = {"/SignupGuest"})
public class SignupGuest extends HttpServlet {

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
        Guest guest = new Guest();
        
        try {
            String firstname = request.getParameter("firstname");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            guest.setFirstname(firstname);
            guest.setSurname(surname);
            guest.setEmail(email);
            guest.setPassword(password);

            String phoneGuest = request.getParameter("phone");
            String cellularGuest = request.getParameter("cellular");
            String faxGuest = request.getParameter("fax");
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

            Address billingAddress = new Address();
            guest.setBillingAddress(billingAddress);
            String addressNumberGuest = request.getParameter("billingAddressNumber");
            String addressRoadGuest = request.getParameter("billingAddressRoad");
            String addressInfoSuppGuest = request.getParameter("billingAddressExtraInfos");
            String addressZipCodeGuest = request.getParameter("billingAddressZipCode");
            String addressCityGuest = request.getParameter("billingAddressCity");
            String addressCountrieGuest = request.getParameter("billingAddressCountrie");
            billingAddress.setNumber(Integer.parseInt(addressNumberGuest));
            billingAddress.setRoad(addressRoadGuest);
            billingAddress.setSuppInfos(addressInfoSuppGuest);
            billingAddress.setZipCode(addressZipCodeGuest);
            billingAddress.setCity(addressCityGuest);
            billingAddress.setCountrie(addressCountrieGuest);

            String hasShippingAddress = request.getParameter("hasShippingAddress");
            if(hasShippingAddress != null && hasShippingAddress.equalsIgnoreCase("on")) {
                Address shippingAddress = new Address();
                guest.setShippingAddress(shippingAddress);
                String shippingAddressNumberGuest = request.getParameter("shippingAddressNumber");
                String shippingAddressRoadGuest = request.getParameter("shippingAddressRoad");
                String shippingAddressInfoSuppGuest = request.getParameter("shippingAddressExtraInfos");
                String shippingAddressZipCodeGuest = request.getParameter("shippingAddressZipCode");
                String shippingAddressCityGuest = request.getParameter("shippingAddressCity");
                String shippingAddressCountrieGuest = request.getParameter("shippingAddressCountrie");
                shippingAddress.setNumber(Integer.parseInt(shippingAddressNumberGuest));
                shippingAddress.setRoad(shippingAddressRoadGuest);
                shippingAddress.setSuppInfos(shippingAddressInfoSuppGuest);
                shippingAddress.setZipCode(shippingAddressZipCodeGuest);
                shippingAddress.setCity(shippingAddressCityGuest);
                shippingAddress.setCountrie(shippingAddressCountrieGuest);
            }

            guest = guestManager.save(guest);

            if(guest.getId() > 0) {
                request.getSession().setAttribute("guest", guest);
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
        response.getWriter().print(guest.getId() > 0);
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

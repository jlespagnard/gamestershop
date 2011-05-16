/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.gamestershop.servlet;

import fr.unice.miage.gamestershop.entity.Guest;
import fr.unice.miage.gamestershop.entity.LineItem;
import fr.unice.miage.gamestershop.manager.PurchaseOrderManager;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * @author Julien LESPAGNARD
 * @author Anthony BONIN
 */
@WebServlet(name = "PurchaseOrder", urlPatterns = {"/PurchaseOrder"})
public class PurchaseOrder extends HttpServlet {

    @EJB
    private PurchaseOrderManager orderManager;
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean success = true;
        
        try {
            HttpSession session = request.getSession();
            
            LinkedList<LineItem> items = (LinkedList<LineItem>)session.getAttribute("basket");
            Guest guest = (Guest)session.getAttribute("guest");
            
            String itemsQuantities = request.getParameter("itemsQuantities");
            JSONObject itemsQuantitiesJson = new JSONObject(new JSONTokener(itemsQuantities));
            Iterator<String> idGames = itemsQuantitiesJson.keys();
            int idGame = 0;
            int quantity = 0;
            BigDecimal totalPrice = BigDecimal.ZERO;
            while(idGames.hasNext()) {
                idGame = Integer.parseInt(idGames.next());
                quantity = itemsQuantitiesJson.getInt(String.valueOf(idGame));
                
                for(LineItem item : items) {
                    if(item.getGame().getId() == idGame) {
                        item.setQuantity(quantity);
                        item.setSubTotal(item.getGame().getPrice().multiply(new BigDecimal(quantity)));
                        totalPrice = totalPrice.add(item.getSubTotal());
                        break;
                    }
                }
            }
            
            fr.unice.miage.gamestershop.entity.PurchaseOrder order = new fr.unice.miage.gamestershop.entity.PurchaseOrder(guest, new Timestamp(Calendar.getInstance().getTimeInMillis()), totalPrice);
            order.setItems(items);
            
            order = orderManager.save(order);
            if(order == null || order.getId() <= 0) {
                success = false;
            }
        }
        catch(Exception e) {
            System.out.println(e);
            success = false;
        }
        
        response.getWriter().println(success);
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

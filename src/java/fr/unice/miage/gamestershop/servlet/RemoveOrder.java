/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.gamestershop.servlet;

import fr.unice.miage.gamestershop.entity.Game;
import fr.unice.miage.gamestershop.entity.LineItem;
import fr.unice.miage.gamestershop.manager.GameManager;
import fr.unice.miage.gamestershop.manager.PurchaseOrderManager;
import java.io.IOException;
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
@WebServlet(name = "RemoveOrder", urlPatterns = {"/RemoveOrder"})
public class RemoveOrder extends HttpServlet {

    @EJB
    private PurchaseOrderManager orderManager;
    @EJB
    private GameManager gameManager;
    
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
            int idOrder = Integer.parseInt(request.getParameter("idOrder"));
            fr.unice.miage.gamestershop.entity.PurchaseOrder order = orderManager.remove(idOrder);
            
            Game game;
            for(LineItem item : order.getItems()) {
                game = item.getGame();
                game.setRemainingQuantity(game.getRemainingQuantity()+item.getQuantity());
                game.setIsAvailable(true);
                gameManager.save(game);
            }
        }
        catch(Exception e) {
            success = false;
            System.out.println(e);
        }
        
        response.getWriter().print(success);
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

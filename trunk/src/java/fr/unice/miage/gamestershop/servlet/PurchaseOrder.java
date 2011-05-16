/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.gamestershop.servlet;

import fr.unice.miage.gamestershop.entity.Game;
import fr.unice.miage.gamestershop.entity.Guest;
import fr.unice.miage.gamestershop.entity.LineItem;
import fr.unice.miage.gamestershop.manager.GameManager;
import fr.unice.miage.gamestershop.manager.PurchaseOrderManager;
import java.io.IOException;
import java.math.BigDecimal;
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
        int codeRetour = 0;
        
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
            Game game;
            while(codeRetour == 0 && idGames.hasNext()) {
                idGame = Integer.parseInt(idGames.next());
                quantity = itemsQuantitiesJson.getInt(String.valueOf(idGame));
                
                for(LineItem item : items) {
                    if(item.getGame().getId() == idGame) {
                        game = gameManager.getGameById(idGame);
                        System.out.println("GAME REMAINING QUANTITY = " + game.getRemainingQuantity());
                        System.out.println("QUANTITY NEEDED = " + quantity);
                        if(game.isIsAvailable() && game.getRemainingQuantity() >= quantity) {
                            item.setGame(game);
                            item.setQuantity(quantity);
                            item.setSubTotal(item.getGame().getPrice().multiply(new BigDecimal(quantity)));
                            totalPrice = totalPrice.add(item.getSubTotal());
                        }
                        else {
                            codeRetour = 1;
                        }
                        break;
                    }
                }
            }
            
            if(codeRetour == 0) {
                fr.unice.miage.gamestershop.entity.PurchaseOrder order = new fr.unice.miage.gamestershop.entity.PurchaseOrder(guest, new Timestamp(Calendar.getInstance().getTimeInMillis()), totalPrice);
                order.setItems(items);

                order = orderManager.save(order);
                if(order == null || order.getId() <= 0) {
                    codeRetour = 2;
                }
                else {
                    for(LineItem item : order.getItems()) {
                        game = item.getGame();
                        System.out.print("BEFORE setRemainingQuantity : remainingQuantity = " + game.getRemainingQuantity());
                        game.setRemainingQuantity(game.getRemainingQuantity()-item.getQuantity());
                        System.out.print("AFTER setRemainingQuantity : remainingQuantity = " + game.getRemainingQuantity());
                        if(game.getRemainingQuantity() == 0) {
                            game.setIsAvailable(false);
                        }
                        gameManager.save(game);
                    }
                    session.setAttribute("basket", new LinkedList<LineItem>());
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
            codeRetour = 2;
        }
        
        response.getWriter().println(codeRetour);
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

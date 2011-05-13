/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.servlet;

import fr.unice.miage.gamestershop.entity.Game;
import fr.unice.miage.gamestershop.entity.LineItem;
import fr.unice.miage.gamestershop.manager.GameManager;
import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 * @author Julien LESPAGNARD
 * @author Anthony BONIN
 */
@WebServlet(name="AddToBasket", urlPatterns={"/AddToBasket"})
public class AddToBasket extends HttpServlet {

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
        HttpSession session = request.getSession();
        Collection<LineItem> items = (Collection<LineItem>)session.getAttribute("basket");

        int idGame = Integer.parseInt(request.getParameter("idGame"));
        Game game = gameManager.getGameById(idGame);

        LineItem line = new LineItem(game, 1, game.getPrice());
        items.add(line);
        session.setAttribute("basket", items);

        JSONObject jsonObj = new JSONObject(game);
        response.getWriter().print(jsonObj);
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.gamestershop.servlet;

import com.amazonaws.util.json.JSONObject;
import fr.unice.miage.gamestershop.entity.Game;
import fr.unice.miage.gamestershop.manager.GameManager;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
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
@WebServlet(name = "GetListProducts", urlPatterns = {"/GetListProducts"})
public class GetListProducts extends HttpServlet {

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
        int firstResult = (request.getParameter("firstResult") == null) ? 0 : Integer.parseInt(request.getParameter("firstResult"));
        
        Collection<Game> games = gameManager.getAllGames(firstResult, 10);
        
        Map<String,Object> retour = new LinkedHashMap<String, Object>();
        retour.put("nbTotalProducts", gameManager.countGames());
        retour.put("nbProducts", games.size());
        retour.put("products", games);
        response.getWriter().print(new JSONObject(retour));
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

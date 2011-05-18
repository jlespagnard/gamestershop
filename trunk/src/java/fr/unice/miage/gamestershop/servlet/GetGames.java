/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.gamestershop.servlet;

import fr.unice.miage.gamestershop.entity.Game;
import fr.unice.miage.gamestershop.manager.GameManager;
import java.io.IOException;
import java.util.Collection;
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
@WebServlet(name = "GetGames", urlPatterns = {"/GetGames"})
public class GetGames extends HttpServlet {

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
        Collection<Game> games = null;
        
        int firstResult = (request.getParameter("firstResult") == null) ? 
                0 : Integer.parseInt(request.getParameter("firstResult"));
        
        int idPlatform = (request.getParameter("idPlatform") == null) ? 
                0 : Integer.parseInt(request.getParameter("idPlatform"));
        if(idPlatform > 0) {
            games = gameManager.getGamesByIdPlatform(idPlatform, firstResult, 10);
            request.setAttribute("nbTotalResults", gameManager.countGamesByIdPlatform(idPlatform));
            request.setAttribute("idPlatform", idPlatform);
        }
        else {
            String searchValue = (request.getParameter("searchGameValue") == null) ? 
                "" : request.getParameter("searchGameValue");
            
            games = gameManager.getGamesByName(searchValue, firstResult, 10);
        
            request.setAttribute("searchGameValue", searchValue);
            request.setAttribute("nbTotalResults", gameManager.countGamesByName(searchValue));
        }
        
        request.setAttribute("games", games);
        request.setAttribute("firstResult", firstResult);
        
        request.getRequestDispatcher("games.jsp").forward(request, response);
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

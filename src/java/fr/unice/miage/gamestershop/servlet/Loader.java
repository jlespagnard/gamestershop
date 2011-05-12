/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.servlet;

import fr.unice.miage.gamestershop.entity.Address;
import fr.unice.miage.gamestershop.entity.Game;
import fr.unice.miage.gamestershop.entity.GameGender;
import fr.unice.miage.gamestershop.entity.GamePlatform;
import fr.unice.miage.gamestershop.entity.Guest;
import fr.unice.miage.gamestershop.entity.LineItem;
import fr.unice.miage.gamestershop.enumeration.Pegi;
import fr.unice.miage.gamestershop.manager.GameGenderManager;
import fr.unice.miage.gamestershop.manager.GameManager;
import fr.unice.miage.gamestershop.manager.GamePlatformManager;
import fr.unice.miage.gamestershop.manager.GuestManager;
import fr.unice.miage.gamestershop.utils.GameParser;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
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
@WebServlet(name="Loader", urlPatterns={"/Loader"})
public class Loader extends HttpServlet {

    @EJB
    private GameManager gameManager;
    @EJB
    private GamePlatformManager platformManager;
    @EJB
    private GameGenderManager genderManager;
    @EJB
    private GuestManager guestMManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        Guest guest = new Guest("toto@titi.fr", "1234", "toto", "titi", null, new Address(21, "Jump Street", "#App 69", "56437", "New-York", "Les Stazunis", false), null);
        guestMManager.save(guest);
        
        GamePlatform platform = new GamePlatform("Nintendo DS");
        platformManager.save(platform);
        platform = new GamePlatform("Nintendo 3DS");
        platformManager.save(platform);
        platform = new GamePlatform("PlayStation Portable");
        platformManager.save(platform);
        platform = new GamePlatform("Xbox 360");
        platformManager.save(platform);
        platform = new GamePlatform("PlayStation 3");
        platformManager.save(platform);
        platform = new GamePlatform("PC");
        platformManager.save(platform);
        platform = new GamePlatform("Nintendo Wii");
        platformManager.save(platform);
        platform = new GamePlatform("PlayStation 2");
        platformManager.save(platform);
        platform = new GamePlatform("Autre");
        platformManager.save(platform);
        
        GameGender gender = new GameGender("Action");
        genderManager.save(gender);
        gender = new GameGender("Adventure");
        genderManager.save(gender);
        gender = new GameGender("Arcade");
        genderManager.save(gender);
        gender = new GameGender("Board Games");
        genderManager.save(gender);
        gender = new GameGender("Break Out");
        genderManager.save(gender);
        gender = new GameGender("Cards");
        genderManager.save(gender);
        gender = new GameGender("Casino");
        genderManager.save(gender);
        gender = new GameGender("Flying");
        genderManager.save(gender);
        gender = new GameGender("Mah Jong");
        genderManager.save(gender);
        gender = new GameGender("Matching Games");
        genderManager.save(gender);
        gender = new GameGender("Puzzle");
        genderManager.save(gender);
        gender = new GameGender("Racing");
        genderManager.save(gender);
        gender = new GameGender("Rhythm");
        genderManager.save(gender);
        gender = new GameGender("Role-Playing");
        genderManager.save(gender);
        gender = new GameGender("Seek & Find");
        genderManager.save(gender);
        gender = new GameGender("Simulation");
        genderManager.save(gender);
        gender = new GameGender("Sports");
        genderManager.save(gender);
        gender = new GameGender("Strategy");
        genderManager.save(gender);
        gender = new GameGender("Time Management");
        genderManager.save(gender);
        gender = new GameGender("Trivia");
        genderManager.save(gender);
        gender = new GameGender("Word Games");
        genderManager.save(gender);
        
        GameParser parser = new GameParser(platformManager.getAllPlatforms(), genderManager.getAllGenders());
        Iterator<Game> listeJeux = parser.listeJeux.iterator();
        while(listeJeux.hasNext()){
        System.out.println("PASSAGE 2");
            gameManager.save(listeJeux.next());
        }
    }
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Collection<GamePlatform> platforms = platformManager.getAllPlatforms();

        HttpSession session = request.getSession(true);
        session.setAttribute("platforms", platforms);

        session.setAttribute("basket", new LinkedList<LineItem>());
        
        request.getRequestDispatcher("home.jsp").forward(request, response);
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

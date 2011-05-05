/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.servlet;

import fr.unice.miage.gamestershop.entity.Game;
import fr.unice.miage.gamestershop.entity.GameGender;
import fr.unice.miage.gamestershop.entity.GamePlatform;
import fr.unice.miage.gamestershop.entity.LineItem;
import fr.unice.miage.gamestershop.enumeration.Pegi;
import fr.unice.miage.gamestershop.manager.GameGenderManager;
import fr.unice.miage.gamestershop.manager.GameManager;
import fr.unice.miage.gamestershop.manager.GamePlatformManager;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        GamePlatform platform = new GamePlatform("Nintendo DS");
        platformManager.save(platform);
        platform = new GamePlatform("Xbox360");
        platformManager.save(platform);
        platform = new GamePlatform("Playstation 3");
        platformManager.save(platform);
        platform = new GamePlatform("PC");
        platformManager.save(platform);

        GameGender gender = new GameGender("Action");
        genderManager.save(gender);
        gender = new GameGender("Adventure");
        genderManager.save(gender);
        gender = new GameGender("War");
        genderManager.save(gender);
        gender = new GameGender("Music");
        genderManager.save(gender);

        Calendar cal = Calendar.getInstance();
        cal.set(2008, 5, 17);
        Collection<GameGender> genders = new LinkedList<GameGender>();
        genders.add(gender);
        Game game = new Game("Call Of Duty", "I Love Zombie !", "http://www.micromania.fr/imagesprod/42714/42714_jaqr_COD_BO_PC_PACK_FR_129x171.jpg", "NeversoftDev", "CapcomPubli", cal.getTime(), new BigDecimal(69), true, 10, genders, platform, true, 2);
        game.addPegi(Pegi.PEGI_18);
        game.addPegi(Pegi.PEGI_VIOLENCE);
        game.addScreenshot("http://www.micromania.fr/zooms/PC/blackops/blackops016.jpg#/zooms/PC/blackops/blackops016.jpg");
        game.addScreenshot("http://www.micromania.fr/zooms/PC/blackops/blackops002.jpg#/zooms/PC/blackops/blackops002.jpg");
        game.addScreenshot("http://www.micromania.fr/zooms/PC/blackops/blackops008.jpg#/zooms/PC/blackops/blackops008.jpg");
        game.addScreenshot("http://www.micromania.fr/zooms/PC/blackops/blackops007.jpg#/zooms/PC/blackops/blackops007.jpg");
        game.addVideo("http://www.youtube.com/embed/OtRnpC7ddv8");
        gameManager.save(game);
        game = new Game("Call Of Duty", "I Love Zombie !", "http://www.micromania.fr/imagesprod/42714/42714_jaqr_COD_BO_PC_PACK_FR_129x171.jpg", "NeversoftDev", "CapcomPubli", cal.getTime(), new BigDecimal(69), true, 10, genders, platform, true, 2);
        game.addPegi(Pegi.PEGI_16);
        game.addPegi(Pegi.PEGI_VIOLENCE);
        game.addPegi(Pegi.PEGI_LANGUAGE);
        game.addPegi(Pegi.PEGI_SEX);
        gameManager.save(game);
        game = new Game("Call Of Duty", "I Love Zombie !", "http://www.micromania.fr/imagesprod/42714/42714_jaqr_COD_BO_PC_PACK_FR_129x171.jpg", "NeversoftDev", "CapcomPubli", cal.getTime(), new BigDecimal(69), true, 10, genders, platform, true, 2);
        game.addPegi(Pegi.PEGI_16);
        game.addPegi(Pegi.PEGI_VIOLENCE);
        gameManager.save(game);
        game = new Game("Call Of Duty", "I Love Zombie !", "http://www.micromania.fr/imagesprod/42714/42714_jaqr_COD_BO_PC_PACK_FR_129x171.jpg", "NeversoftDev", "CapcomPubli", cal.getTime(), new BigDecimal(69), true, 10, genders, platform, true, 2);
        game.addPegi(Pegi.PEGI_16);
        game.addPegi(Pegi.PEGI_VIOLENCE);
        gameManager.save(game);
        game = new Game("Call Of Duty", "I Love Zombie !", "http://www.micromania.fr/imagesprod/42714/42714_jaqr_COD_BO_PC_PACK_FR_129x171.jpg", "NeversoftDev", "CapcomPubli", cal.getTime(), new BigDecimal(69), true, 10, genders, platform, true, 2);
        game.addPegi(Pegi.PEGI_16);
        game.addPegi(Pegi.PEGI_VIOLENCE);
        gameManager.save(game);
        game = new Game("Call Of Duty", "I Love Zombie !", "http://www.micromania.fr/imagesprod/42714/42714_jaqr_COD_BO_PC_PACK_FR_129x171.jpg", "NeversoftDev", "CapcomPubli", cal.getTime(), new BigDecimal(69), true, 10, genders, platform, true, 2);
        game.addPegi(Pegi.PEGI_16);
        game.addPegi(Pegi.PEGI_VIOLENCE);
        gameManager.save(game);
        game = new Game("Call Of Duty", "I Love Zombie !", "http://www.micromania.fr/imagesprod/42714/42714_jaqr_COD_BO_PC_PACK_FR_129x171.jpg", "NeversoftDev", "CapcomPubli", cal.getTime(), new BigDecimal(69), true, 10, genders, platform, true, 2);
        game.addPegi(Pegi.PEGI_16);
        game.addPegi(Pegi.PEGI_VIOLENCE);
        gameManager.save(game);
        game = new Game("Call Of Duty", "I Love Zombie !", "http://www.micromania.fr/imagesprod/42714/42714_jaqr_COD_BO_PC_PACK_FR_129x171.jpg", "NeversoftDev", "CapcomPubli", cal.getTime(), new BigDecimal(69), true, 10, genders, platform, true, 2);
        game.addPegi(Pegi.PEGI_16);
        game.addPegi(Pegi.PEGI_VIOLENCE);
        gameManager.save(game);
        game = new Game("Call Of Duty", "I Love Zombie !", "http://www.micromania.fr/imagesprod/42714/42714_jaqr_COD_BO_PC_PACK_FR_129x171.jpg", "NeversoftDev", "CapcomPubli", cal.getTime(), new BigDecimal(69), true, 10, genders, platform, true, 2);
        game.addPegi(Pegi.PEGI_16);
        game.addPegi(Pegi.PEGI_VIOLENCE);
        gameManager.save(game);
        game = new Game("Call Of Duty", "I Love Zombie !", "http://www.micromania.fr/imagesprod/42714/42714_jaqr_COD_BO_PC_PACK_FR_129x171.jpg", "NeversoftDev", "CapcomPubli", cal.getTime(), new BigDecimal(69), true, 10, genders, platform, true, 2);
        game.addPegi(Pegi.PEGI_16);
        game.addPegi(Pegi.PEGI_VIOLENCE);
        gameManager.save(game);
        game = new Game("Call Of Duty", "I Love Zombie !", "http://www.micromania.fr/imagesprod/42714/42714_jaqr_COD_BO_PC_PACK_FR_129x171.jpg", "NeversoftDev", "CapcomPubli", cal.getTime(), new BigDecimal(69), true, 10, genders, platform, true, 2);
        game.addPegi(Pegi.PEGI_16);
        game.addPegi(Pegi.PEGI_VIOLENCE);
        gameManager.save(game);
        game = new Game("Call Of Duty", "I Love Zombie !", "http://www.micromania.fr/imagesprod/42714/42714_jaqr_COD_BO_PC_PACK_FR_129x171.jpg", "NeversoftDev", "CapcomPubli", cal.getTime(), new BigDecimal(69), true, 10, genders, platform, true, 2);
        game.addPegi(Pegi.PEGI_16);
        game.addPegi(Pegi.PEGI_VIOLENCE);
        gameManager.save(game);
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

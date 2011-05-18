/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.servlet;

import fr.unice.miage.gamestershop.entity.Game;
import fr.unice.miage.gamestershop.entity.GameGender;
import fr.unice.miage.gamestershop.entity.GamePlatform;
import fr.unice.miage.gamestershop.enumeration.Pegi;
import fr.unice.miage.gamestershop.manager.GameGenderManager;
import fr.unice.miage.gamestershop.manager.GameManager;
import fr.unice.miage.gamestershop.manager.GamePlatformManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mousztomania
 */
@WebServlet(name="AddProduct", urlPatterns={"/AddProduct"})
public class AddProduct extends HttpServlet {

@EJB
private GameManager gameManager;
@EJB
private GamePlatformManager platformManager;
@EJB
private GameGenderManager genderManager;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String title = request.getParameter("title_sign");
            String description = request.getParameter("description_sign");
            String urlCover = request.getParameter("cover_sign");
            String developper = request.getParameter("developper_sign");
            String publisher = request.getParameter("publisher_sign");
            BigDecimal price = new BigDecimal(request.getParameter("price_sign"));
            int quantity = Integer.parseInt(request.getParameter("quantity_sign"));
            Collection<String> screenshot = new ArrayList<String>(Arrays.asList(request.getParameter("screenshot_sign")));
            Collection<String>  features = new ArrayList<String>(Arrays.asList(request.getParameter("features_sign")));
            SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
            Date release = sdf.parse(request.getParameter("release_sign"));
            Collection<GameGender> genders = new ArrayList<GameGender>();
            System.out.println("TOOHOIHHOHO " + request.getParameterValues("gender_sign").length);
            String[] genderstmp = request.getParameterValues("gender_sign");
            for(int i=0; i<genderstmp.length; i++){
                genders.add(genderManager.getGenderByName(genderstmp[i]));
            }
             System.out.println("TOOHOIHHOHO " + request.getParameterValues("platforms_sign")[0]);
              System.out.println("TOOHOIHHOHO ESRB YO " + request.getParameter("ESRB_SIGN"));
            String[] platform = request.getParameterValues("platforms_sign");
            Collection<Pegi> pegis = new LinkedList<Pegi>();
            pegis.add(Pegi.getPegiByName(request.getParameter("ESRB_sign")));
            
            Game g = null;
            for(int i=0; i<platform.length; i++){
                GamePlatform plat = platformManager.getPlatformByName(platform[i]);
                g = new Game(title, description, urlCover, developper, publisher, release, price, true, quantity, genders, screenshot, features, plat, pegis);
            }
            gameManager.save(g);

        }catch (Exception e){
            System.out.println(e);
        }
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

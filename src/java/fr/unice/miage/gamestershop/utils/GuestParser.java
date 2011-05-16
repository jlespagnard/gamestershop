/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.utils;

import fr.unice.miage.gamestershop.entity.Address;
import fr.unice.miage.gamestershop.entity.Guest;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Julien LESPAGNARD
 * @author Anthony BONIN
 */
public class GuestParser {

    private String PATH = null;
    public static Collection<Guest> listeUtilisateurs = null;

    public GuestParser(String sourceProject) {
        listeUtilisateurs = new LinkedList<Guest>();
        PATH = sourceProject + "\\src\\java\\fr\\unice\\miage\\gamestershop\\misc\\guests.xml";
        parseXML();
    }

    public void parseXML(){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(PATH);
            NodeList nodeList = doc.getElementsByTagName("guest");
            for(int i = 0; i < nodeList.getLength(); i++){
                Element guest = (Element)nodeList.item(i);
                String email =  guest.getElementsByTagName("email").item(0).getTextContent();
                String password = guest.getElementsByTagName("password").item(0).getTextContent();
                String surname = guest.getElementsByTagName("surname").item(0).getTextContent();
                String firstname = guest.getElementsByTagName("firstname").item(0).getTextContent();
                int numberAdress = Integer.parseInt(guest.getElementsByTagName("numberAdress").item(0).getTextContent());
                String roadAdress = guest.getElementsByTagName("roadAdress").item(0).getTextContent();
                String zipCode = guest.getElementsByTagName("zipCode").item(0).getTextContent();
                String city = guest.getElementsByTagName("city").item(0).getTextContent();
                String country = guest.getElementsByTagName("country").item(0).getTextContent();

                 Guest newGuest = new Guest(email, password, surname, firstname, null, new Address(numberAdress, roadAdress, null, zipCode, city, country, true), null);
                listeUtilisateurs.add(newGuest);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

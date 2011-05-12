package fr.unice.miage.gamestershop.utils;

import fr.unice.miage.gamestershop.entity.Game;
import fr.unice.miage.gamestershop.entity.GameGender;
import fr.unice.miage.gamestershop.entity.GamePlatform;
import fr.unice.miage.gamestershop.enumeration.Pegi;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Julien Lespagnard & Anthony Bonin
 */

public class GameParser {

    public static Collection<Game> listeJeux = null;
    private static Collection<GamePlatform> listePlatforms = null;
    private static Collection<GameGender> listeGenders = null;

    /*
     * Amazon WebServices key, private, do not use without authorization.
     */
    private static final String AWS_ACCESS_KEY_ID = "AKIAIKA325ZD3VTUTBBA";
    private static final String AWS_SECRET_KEY = "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";

    /*
     * Use one of the following end-points, according to the region you are
     * interested in:
     *
     *      US: ecs.amazonaws.com
     *      CA: ecs.amazonaws.ca
     *      UK: ecs.amazonaws.co.uk
     *      DE: ecs.amazonaws.de
     *      FR: ecs.amazonaws.fr
     *      JP: ecs.amazonaws.jp
     *
     * Les informations disponibles sur la version française ne sont pas assez importantes, on prend donc la version US
     */
    private static final String ENDPOINT = "ecs.amazonaws.com";

    /*
     * Nombre de pages du catalogue de jeux vidéo d'Amazon qu'on veut ajouter à notre base de données
     */
    private static final int NB_PAGES = 10;

    
    public GameParser(Collection<GamePlatform> listePlatforms, Collection<GameGender> listeGenders) {
        this.listePlatforms = listePlatforms;
        this.listeGenders = listeGenders;
        this.listeJeux = new LinkedList<Game>();

         /*
         * Set up the signed requests helper
         */
        SignedRequestsHelper helper;
        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String requestUrl = null;

        /* The helper can sign requests in two forms - map form and string form */

        /*
         * Here is an example in map form, where the request parameters are stored in a map.
         */
        System.out.println("Map form example:");
        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemSearch");
        params.put("Condition","New");
        params.put("SearchIndex","VideoGames");
        params.put("BrowseNode","468642");
        //To get the most informations : ResponseGroup = Large
        params.put("ResponseGroup", "Large");

        for(int i = 1; i <= NB_PAGES; i++){
            params.put("ItemPage", Integer.toString(i));
            requestUrl = helper.sign(params);
            ajouterPageProduits(requestUrl);
            params.remove("ItemPage");
        }
    }

    /*
     * Utility function to fetch the response from the service and extract the
     * title from the XML.
     */
    private static void ajouterPageProduits(String requestUrl) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(requestUrl);
            NodeList nodeList = doc.getElementsByTagName("Item");
            for(int i = 0; i < nodeList.getLength(); i++){
                Element jeu = (Element)nodeList.item(i);

                String name =  jeu.getElementsByTagName("Title").item(0).getTextContent();
                String description =  (jeu.getElementsByTagName("Content").getLength()>1?jeu.getElementsByTagName("Content").item(1).getTextContent():jeu.getElementsByTagName("Content").getLength()>0?jeu.getElementsByTagName("Content").item(0).getTextContent():"No description");
                String developer =  jeu.getElementsByTagName("Manufacturer").item(0).getTextContent();
                String publisher =  jeu.getElementsByTagName("Publisher").item(0).getTextContent();
                SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
                Date release = sdf.parse(jeu.getElementsByTagName("ReleaseDate").getLength()>0?jeu.getElementsByTagName("ReleaseDate").item(0).getTextContent():"1337-12-12");
                BigDecimal price = new BigDecimal(jeu.getElementsByTagName("FormattedPrice").item(0).getTextContent().substring(1));
                boolean isAvailable = (Integer.parseInt(jeu.getElementsByTagName("Amount").item(0).getTextContent())>0?true:false);
                int remainingQuantity = Integer.parseInt(jeu.getElementsByTagName("Amount").item(0).getTextContent());
                String urlCover = null;
                Collection<String> imagesUrl = new LinkedList<String>();
                //On récupère les images
                NodeList gameImages =  jeu.getElementsByTagName("ImageSet");
                for(int j = 0; j < gameImages.getLength(); j++){
                    Element gamou = (Element)gameImages.item(j);
                    String attribute = gamou.getAttribute("Category");
                    if(attribute.equalsIgnoreCase("primary")){
                        urlCover = gamou.getElementsByTagName("URL").item(3).getTextContent();
                    } else if(attribute.equalsIgnoreCase("variant")){
                        imagesUrl.add(gamou.getElementsByTagName("URL").item(3).getTextContent());
                    }
                }
               Collection<String> features = new LinkedList<String>();
               NodeList featuresList = doc.getElementsByTagName("Feature");
               for(int n = 0; n < featuresList.getLength() ; n++)
                    features.add(featuresList.item(n).getTextContent());

                GamePlatform platform = null;
                GamePlatform tmp = null;
                String nomPlatform = (jeu.getElementsByTagName("Platform").getLength()>0?jeu.getElementsByTagName("Platform").item(0).getTextContent():"Autre");
                if(nomPlatform.contains("Windows")){
                    nomPlatform="PC";
                }
                System.out.println(nomPlatform);
                Iterator<GamePlatform> it = listePlatforms.iterator();
                boolean platformExist = false;
                while(it.hasNext()){
                    GamePlatform plat = it.next();
                    if(plat.getName().equalsIgnoreCase("Autre"))
                        tmp=plat;
                    if(plat.getName().equals(nomPlatform)){
                        platformExist = true;
                        platform = plat;
                    }
                }
                if(platformExist==false)
                    platform=tmp;
                

                Collection<GameGender> genders = new LinkedList<GameGender>();
                Element element = (Element)jeu.getElementsByTagName("BrowseNode").item(0);
                String nomGender = element.getElementsByTagName("Name").item(0).getTextContent();
                Iterator<GameGender> itt = listeGenders.iterator();
                while(itt.hasNext()){
                    GameGender platt = itt.next();
                    if(platt.getName().equals(nomGender)){
                        genders.add(platt);
                    }
                }

                Collection<Pegi> pegis = new LinkedList<Pegi>();
                pegis.add(Pegi.getPegiByName((jeu.getElementsByTagName("ESRBAgeRating").getLength()>0?jeu.getElementsByTagName("ESRBAgeRating").item(0).getTextContent():"NoPE")));

               Game g = new Game(name, description, urlCover, developer, publisher, release, price, isAvailable, remainingQuantity, genders, imagesUrl, features, platform, pegis);
               listeJeux.add(g);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
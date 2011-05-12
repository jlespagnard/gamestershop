/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.enumeration;

/**
 * @author Julien LESPAGNARD
 * @author Anthony BONIN
 */
public enum Pegi {
    ESRB_CHILDHOOD("images/pegis/ESRB_1.PNG","Titles rated EC (Early Childhood) have content that may be suitable for ages 3 and older. Contains no material that parents would find inappropriate.",false),
    ESRB_EVERYONE("images/pegis/ESRB_2.PNG","Titles rated E (Everyone) have content that may be suitable for ages 6 and older. Titles in this category may contain minimal cartoon, fantasy or mild violence and/or infrequent use of mild language.",false),
    ESRB_10("images/pegis/ESRB_3.PNG","Titles rated E10+ (Everyone 10 and older) have content that may be suitable for ages 10 and older. Titles in this category may contain more cartoon, fantasy or mild violence, mild language and/or minimal suggestive themes.",false),
    ESRB_TEEN("images/pegis/ESRB_4.PNG","Titles rated T (Teen) have content that may be suitable for ages 13 and older. Titles in this category may contain violence, suggestive themes, crude humor, minimal blood, simulated gambling, and/or infrequent use of strong language. ",false),
    ESRB_17("images/pegis/ESRB_5.PNG","Titles rated M (Mature) have content that may be suitable for persons ages 17 and older. Titles in this category may contain intense violence, blood and gore, sexual content and/or strong language.",false),
    ESRB_ADULTS("images/pegis/ESRB_6.PNG","Titles rated AO (Adults Only) have content that should only be played by persons 18 years and older. Titles in this category may include prolonged scenes of intense violence and/or graphic sexual content and nudity.",false),
    ESRB_PENDING("images/pegis/ESRB_7.PNG","Titles listed as RP (Rating Pending) have been submitted to the ESRB and are awaiting final rating. (This symbol appears only in advertising prior to a game's release.)",false);

    private String urlPictogram;
    private String altText;
    private boolean isDescriptor;

    private Pegi(String urlPictogram, String altText, boolean isDescriptor) {
        this.urlPictogram = urlPictogram;
        this.altText = altText;
        this.isDescriptor = isDescriptor;
    }

    public String getUrlPictogram() {
        return this.urlPictogram;
    }

    public String getAltText() {
        return this.altText;
    }

    public boolean isDescriptor() {
        return this.isDescriptor;
    }

    public static Pegi getPegiByName(String pegi){
        if(pegi.equalsIgnoreCase("Early Childhood"))
            return ESRB_CHILDHOOD;
        else if(pegi.equalsIgnoreCase("Everyone"))
            return ESRB_EVERYONE;
        else if(pegi.equalsIgnoreCase("Everyone 10+"))
            return ESRB_10;
        else if(pegi.equalsIgnoreCase("Teen"))
            return ESRB_TEEN;
        else if(pegi.equalsIgnoreCase("Mature"))
            return ESRB_17;
        else if(pegi.equalsIgnoreCase("Adults Only"))
            return ESRB_ADULTS;
        else
            return ESRB_PENDING;
    }
}
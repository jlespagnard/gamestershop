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
    PEGI_3("images/pegis/pegi_3.gif","S'adresse &agrave; des personnes de tous &acirc;ges",false),
    PEGI_7("images/pegis/pegi7.gif","Interdit aux moins de 7 ans",false),
    PEGI_12("images/pegis/pegi_12.gif","Interdit aux moins de 12 ans",false),
    PEGI_16("images/pegis/pegi_16.gif","Interdit aux moins de 16 ans",false),
    PEGI_18("images/pegis/pegi_18.gif","Interdit aux moins de 18 ans",false),
    PEGI_LANGUAGE("images/pegis/pegi_lang.gif","Ce jeu contient des expressions grossi&egrave;res.",true),
    PEGI_DISCRIMINATION("images/pegis/pegi_discrim.gif","Ce jeu contient des images ou des &eacute;l&eacute;ments susceptibles d’inciter &agrave; la discrimination.",true),
    PEGI_VIOLENCE("images/pegis/pegi_violence.gif","Ce jeu contient des sc&egrave;nes violentes.",true),
    PEGI_DRUG("images/pegis/pegi_drug.gif","Ce jeu illustre ou se r&eacute;f&egrave;re &agrave; la consommation de drogues.",true),
    PEGI_FEAR("images/pegis/pegi_fear.gif","Ce jeu risque de faire peur aux jeunes enfants.",true),
    PEGI_HASARD("images/pegis/pegi_hasard.gif","Ce jeu incite &agrave; jouer aux jeux de hasard ou enseigne leurs règles.",true),
    PEGI_SEX("images/pegis/pegi_sex.gif","Ce jeu montre des sc&egrave;nes de nudit&eacute; et/ou des comportements ou des allusions de nature sexuelle.",true),
    PEGI_ONLINE("images/pegis/pegi_online.gif","Possibilit&eacute; de jouer &agrave; ce jeu en ligne.",true);

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
}
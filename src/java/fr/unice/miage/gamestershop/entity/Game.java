/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.entity;

import fr.unice.miage.gamestershop.enumeration.Pegi;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Collection;
import java.util.LinkedList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 * @author Julien LESPAGNARD
 * @author Anthony BONIN
 */
@Entity
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable=false)
    private String name;
    @Column(length=20000)
    private String description;
    private String urlCover;
    @Column(nullable=false)
    private String developer;
    @Column(nullable=false)
    private String publisher;
    @Column(nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date release;
    @Column(nullable=false)
    private BigDecimal price;
    private boolean isAvailable;
    private int remainingQuantity;
    @ManyToMany
    private Collection<GameGender> genders;
    @JoinColumn(nullable=false)
    @ManyToOne
    private GamePlatform platform;
    private Collection<Pegi> pegis = null;
    private Collection<String> urlVideos = null;
    private Collection<String> urlScreenshots = null;
    private Collection<String> keyWords = null;
    private Collection<String> features = null;

    public Game() {
    }

    public Game(String name, String description, String urlCover, String developer, String publisher, Date release, BigDecimal price, boolean isAvailable, int remainingQuantity, Collection<GameGender> genders, Collection<String> urlScreenshots, Collection<String> features, GamePlatform platform, Collection<Pegi> pegis) {
        this.name = name;
        this.description = description;
        this.urlCover = urlCover;
        this.developer = developer;
        this.publisher = publisher;
        this.release = release;
        this.price = price;
        this.isAvailable = isAvailable;
        this.remainingQuantity = remainingQuantity;
        this.urlScreenshots = urlScreenshots;
        this.features = features;
        this.genders = genders;
        this.platform = platform;
        this.pegis = pegis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Collection<GameGender> getGenders() {
        return genders;
    }

    public void setGenders(Collection<GameGender> genders) {
        this.genders = genders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Collection<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(Collection<String> keyWords) {
        this.keyWords = keyWords;
    }

    public void addKeyword(String keyword) {
        if(this.keyWords == null) {
            this.keyWords = new LinkedList<String>();
        }
        if(!keyWords.contains(keyword)) {
            keyWords.add(keyword);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Pegi> getPegis() {
        return pegis;
    }

    public void setPegis(Collection<Pegi> pegis) {
        this.pegis = pegis;
    }

    public GamePlatform getPlatform() {
        return platform;
    }

    public void setPlatform(GamePlatform platform) {
        this.platform = platform;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getRelease() {
        return release;
    }

    public String getReleaseToString() {
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        return format.format(this.release);
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public String getUrlCover() {
        return urlCover;
    }

    public void setUrlCover(String urlCover) {
        this.urlCover = urlCover;
    }

    public Collection<String> getUrlScreenshots() {
        return urlScreenshots;
    }

    public void setUrlScreenshots(Collection<String> urlScreenshots) {
        this.urlScreenshots = urlScreenshots;
    }
    
    public Collection<String> getFeatures() {
        return features;
    }

    public void setFeatures(Collection<String> features) {
        this.features = features;
    }
    public Collection<String> getUrlVideos() {
        return urlVideos;
    }

    public void setUrlVideos(Collection<String> urlVideos) {
        this.urlVideos = urlVideos;
    }

    public void addPegi(Pegi pegi) {
        if(this.pegis == null) {
            pegis = new LinkedList<Pegi>();
        }
        if(!pegis.contains(pegi)) {
            if(!pegi.isDescriptor()) {
                Pegi oldPegi = null;
                for(Pegi currentPegi : pegis) {
                    if(!currentPegi.isDescriptor()) {
                        oldPegi = currentPegi;
                    }
                }
                if(oldPegi != null) {
                    this.removePegi(oldPegi);
                }
            }
            pegis.add(pegi);
        }
    }

    public void removePegi(Pegi pegi) {
        if(this.pegis != null) {
            if(pegis.contains(pegi)) {
                pegis.remove(pegi);
            }
        }
    }

    public void addVideo(String urlVideo) {
        if(this.urlVideos == null) {
            this.urlVideos = new LinkedList<String>();
        }
        if(!urlVideos.contains(urlVideo)) {
            urlVideos.add(urlVideo);
        }
    }

    public void removeVideo(String urlVideo) {
        if(this.urlVideos != null) {
            if(urlVideos.contains(urlVideo)) {
                urlVideos.remove(urlVideo);
            }
        }
    }

    public void addScreenshot(String urlScreenshot) {
        if(this.urlScreenshots == null) {
            this.urlScreenshots = new LinkedList<String>();
        }
        if(!urlScreenshots.contains(urlScreenshot)) {
            urlScreenshots.add(urlScreenshot);
        }
    }

    public void removeScreenshot(String urlScreenshot) {
        if(this.urlScreenshots != null) {
            if(urlScreenshots.contains(urlScreenshot)) {
                urlScreenshots.remove(urlScreenshot);
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.unice.miage.gamestershop.entity.Game[id=" + id + "]";
    }

}

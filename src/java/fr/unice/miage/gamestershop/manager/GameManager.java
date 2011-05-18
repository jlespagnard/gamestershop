/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.manager;

import fr.unice.miage.gamestershop.entity.Game;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Julien LESPAGNARD
 * @author Anthony BONIN
 */
@Stateless
public class GameManager {
    @PersistenceContext
    private EntityManager em;

    public Game save(Game game) {
        if(game.getId() > 0) {
            return em.merge(game);
        }
        em.persist(game);
        return game;
    }

    public void remove(int idGame) {
        em.remove(em.find(Game.class, idGame));
    }

    private String getWhereClause(String[] names, String[] developers, String[] publishers, Date releaseMin, Date releaseMax, BigDecimal priceMin, BigDecimal priceMax, String[] genderNames, String[] platformNames) {
        String where = "";
        if(names != null && names.length > 0) {
            where += "name IN (:names)";
        }
        if(developers != null && developers.length > 0) {
            if(!where.trim().isEmpty()) {
                where += " AND ";
            }
            where += "developer IN (:developers)";
        }
        if(publishers != null && publishers.length > 0) {
            if(!where.trim().isEmpty()) {
                where += " AND ";
            }
            where += "publisher IN (:publishers)";
        }
        if(releaseMin != null) {
            if(!where.trim().isEmpty()) {
                where += " AND ";
            }
            if(releaseMax == null) {
                where += "release >= :releaseMin";
            }
            else {
                where += "release BETWEEN :releaseMin AND releaseMax";
            }
        }
        else if (releaseMax != null) {
            if(!where.trim().isEmpty()) {
                where += " AND ";
            }
            where += "release <= :releaseMax";
        }
        if(priceMin != null) {
            if(!where.trim().isEmpty()) {
                where += " AND ";
            }
            if(priceMax == null) {
                where += "price >= :priceMin";
            }
            else {
                where += "price BETWEEN :priceMin AND priceMax";
            }
        }
        else if (priceMax != null) {
            if(!where.trim().isEmpty()) {
                where += " AND ";
            }
            where += "price <= :priceMax";
        }
        if(genderNames != null && genderNames.length > 0) {
            if(!where.trim().isEmpty()) {
                where += " AND ";
            }
            where += "genders.name IN (:genderNames)";
        }
        if(platformNames != null && platformNames.length > 0) {
            if(!where.trim().isEmpty()) {
                where += " AND ";
            }
            where += "platform.name IN (:platformNames)";
        }
        return where;
    }

    public void setQueryParameters(Query q, String[] names, String[] developers, String[] publishers, Date releaseMin, Date releaseMax, BigDecimal priceMin, BigDecimal priceMax, String[] genderNames, String[] platformNames) {
        String value;
        if(names != null && names.length > 0) {
            value = Arrays.toString(names);
            value = value.substring(1, value.length()-1);
            q.setParameter("names", value);
        }
        if(developers != null && developers.length > 0) {
            value = Arrays.toString(developers);
            value = value.substring(1, value.length()-1);
            q.setParameter("developers", value);
        }
        if(publishers != null && publishers.length > 0) {
            value = Arrays.toString(publishers);
            value = value.substring(1, value.length()-1);
            q.setParameter("publishers", value);
        }
        if(releaseMin != null) {
            q.setParameter("releaseMin", releaseMin);
        }
        if (releaseMax != null) {
            q.setParameter("releaseMax", releaseMax);
        }
        if(priceMin != null) {
            q.setParameter("priceMin", priceMin);
        }
        else if (priceMax != null) {
            q.setParameter("priceMax", priceMax);
        }
        if(genderNames != null && genderNames.length > 0) {
            value = Arrays.toString(genderNames);
            value = value.substring(1, value.length()-1);
            q.setParameter("genderNames", value);
        }
        if(platformNames != null && platformNames.length > 0) {
            value = Arrays.toString(platformNames);
            value = value.substring(1, value.length()-1);
            q.setParameter("platformNames", value);
        }
    }

    public Collection<Game> findGames(String[] names, String[] developers, String[] publishers, Date releaseMin, Date releaseMax, BigDecimal priceMin, BigDecimal priceMax, String[] genderNames, String[] platformNames, int debut, int nombre) {
        String where = this.getWhereClause(names, developers, publishers, releaseMin, releaseMax, priceMin, priceMax, genderNames, platformNames);

        String query = "SELECT g FROM Game g";
        if(!where.trim().isEmpty()) {
            query += " WHERE " + where;
        }

        Query q = em.createQuery(query);
        this.setQueryParameters(q, names, developers, publishers, releaseMin, releaseMax, priceMin, priceMax, genderNames, platformNames);
        
        if(debut >= 0) {
            q.setFirstResult(debut);
        }
        if(nombre > 0) {
            q.setMaxResults(nombre);
        }

        return q.getResultList();
    }

    public int countGames(String[] names, String[] developers, String[] publishers, Date releaseMin, Date releaseMax, BigDecimal priceMin, BigDecimal priceMax, String[] genderNames, String[] platformNames) {
        String where = this.getWhereClause(names, developers, publishers, releaseMin, releaseMax, priceMin, priceMax, genderNames, platformNames);

        String query = "SELECT COUNT(DISTINCT(g.id)) FROM Game g";
        if(!where.trim().isEmpty()) {
            query += " WHERE " + where;
        }

        Query q = em.createQuery(query);
        this.setQueryParameters(q, names, developers, publishers, releaseMin, releaseMax, priceMin, priceMax, genderNames, platformNames);

        return Integer.parseInt((String)q.getSingleResult());
    }

    public Collection<Game> getAllGames(int firstResult, int maxResults) {
        Query q = em.createQuery("SELECT g FROM Game g");
        q.setFirstResult(firstResult);
        q.setMaxResults(maxResults);
        return q.getResultList();
    }
    
    public long countGames() {
        Query q = em.createQuery("SELECT COUNT(DISTINCT g.id) FROM Game g");
        return (Long)q.getSingleResult();
    }
    
    public Collection<String> getAllDevelopers() {
        Query q = em.createQuery("SELECT DISTINCT(g.developer) FROM Game g");
        return q.getResultList();
    }

    public Collection<String> getAllPublishers() {
        Query q = em.createQuery("SELECT DISTINCT(g.publisher) FROM Game g");
        return q.getResultList();
    }
    
    public Game getGameById(int id) {
        return em.find(Game.class, id);
    }
    
    public Collection<String> getSuggestGameName(String name) {
        Query q = em.createQuery("SELECT DISTINCT(g.name) FROM Game g WHERE g.name LIKE :name");
        q.setParameter("name", name+"%");
        
        return q.getResultList();
    }
    
    public Collection<Game> getGamesByIdPlatform(int platformId, int firstResult, int maxResult) {
        Query q = em.createQuery("SELECT g FROM Game g WHERE g.platform.id = :id");        
        q.setParameter("id", platformId);
        
        q.setFirstResult(firstResult);
        q.setMaxResults(maxResult);

        return q.getResultList();
    }
    
    public long countGamesByIdPlatform(int platformId) {
        Query q = em.createQuery("SELECT COUNT(DISTINCT(g)) FROM Game g WHERE g.platform.id = :id");
        q.setParameter("id", platformId);

        return (Long)q.getSingleResult();
    }
    
    public Collection<Game> getGamesByName(String name, int firstResult, int maxResult) {
        Query q = em.createQuery("SELECT g FROM Game g WHERE g.name LIKE :name");
        if(name.isEmpty()) {
            q.setParameter("name", "%");
        }
        else {
            name = name.replaceAll(" ", "%");
            q.setParameter("name", name+"%");
        }
        q.setFirstResult(firstResult);
        q.setMaxResults(maxResult);
        
        return q.getResultList();
    }
    
    public long countGamesByName(String name) {
        Query q = em.createQuery("SELECT COUNT(DISTINCT(g.id)) FROM Game g WHERE g.name LIKE :name");
        if(name.isEmpty()) {
            q.setParameter("name", "%");
        }
        else {
            name = name.replaceAll(" ", "%");
            q.setParameter("name", name+"%");
        }
        
        return (Long)q.getSingleResult();
    }
}
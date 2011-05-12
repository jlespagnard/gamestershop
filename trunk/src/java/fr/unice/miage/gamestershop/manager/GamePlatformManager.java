/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.manager;

import fr.unice.miage.gamestershop.entity.GamePlatform;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Julien LESPAGNARD
 * @author Anthony BONIN
 */
@Stateless
public class GamePlatformManager {
    @PersistenceContext
    private EntityManager em;

    public GamePlatform save(GamePlatform platform) {
        if(platform.getId() > 0) {
            return em.merge(platform);
        }
        em.persist(platform);
        return platform;
    }

    public void remove(GamePlatform platform) {
        em.remove(platform);
    }

    public Collection<GamePlatform> getAllPlatforms() {
        Query q = em.createQuery("SELECT p FROM GamePlatform p");
        return q.getResultList();
    }

    public GamePlatform getPlatformByName(String name){
        Query q = em.createQuery("SELECT p FROM GamePlatform p WHERE p.name LIKE :name");
        q.setParameter("name", name);
        return (GamePlatform)q.getSingleResult();
    }

    
}
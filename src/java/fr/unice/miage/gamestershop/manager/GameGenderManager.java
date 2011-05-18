/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.manager;

import fr.unice.miage.gamestershop.entity.GameGender;
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
public class GameGenderManager {
    @PersistenceContext
    private EntityManager em;

    public GameGender save(GameGender gender) {
        if(gender.getId() > 0) {
            return em.merge(gender);
        }
        em.persist(gender);
        return gender;
    }

    public void remove(GameGender gender) {
        em.remove(gender);
    }

    public Collection<GameGender> getAllGenders() {
        Query q = em.createQuery("SELECT g FROM GameGender g");
        return q.getResultList();
    }

    public GameGender getGenderByName(String name){
        Query q = em.createQuery("SELECT p FROM GameGender p WHERE p.name LIKE :name");
        q.setParameter("name", name);
        return (GameGender)q.getSingleResult();
    }
}

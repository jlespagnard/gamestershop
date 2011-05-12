/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.manager;

import fr.unice.miage.gamestershop.entity.Guest;
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
public class GuestManager {
    @PersistenceContext
    private EntityManager em;

    public Guest save(Guest guest) {
        if(guest.getId() > 0) {
            return em.merge(guest);
        }
        em.persist(guest);
        return guest;
    }

    public void remove(Guest guest) {
        em.remove(guest);
    }

    public Collection<Guest> getAllGuests() {
        Query q = em.createQuery("SELECT g FROM Guest g");
        return q.getResultList();
    }
    
    public Guest getGuestByEmailAndPassword(String email, String password) {
        Query q = em.createQuery("SELECT g FROM Guest g WHERE g.email = :email AND g.password = :password");
        q.setParameter("email", email);
        q.setParameter("password", password);
        
        Collection<Guest> results = q.getResultList();
        if(results == null || results.isEmpty()) {
            return null;
        }
        return results.iterator().next();
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.gamestershop.manager;

import fr.unice.miage.gamestershop.entity.PurchaseOrder;
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
public class PurchaseOrderManager {
    
    @PersistenceContext
    private EntityManager em;
    
    public PurchaseOrder save(PurchaseOrder order) {
        em.persist(order);
        return order;
    }
    
    public PurchaseOrder remove(int idPurchaseOrder) {
        PurchaseOrder order = em.find(PurchaseOrder.class, idPurchaseOrder);
        em.remove(order);
        return order;
    }
    
    public Collection<PurchaseOrder> getAllOrders(int firstResult, int maxResult) {
        Query q = em.createQuery("SELECT o FROM PurchaseOrder o");
        q.setFirstResult(firstResult);
        q.setMaxResults(maxResult);
        return q.getResultList();
    }
    
    public long count() {
        Query q = em.createQuery("SELECT COUNT(DISTINCT o.id) FROM PurchaseOrder o");
        return (Long)q.getSingleResult();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Julien LESPAGNARD
 * @author Anthony BONIN
 */
@Entity
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(optional=false)
    private Guest guest;
    @Column(nullable=false)
    private Timestamp orderDate;
    @Column(nullable=false)
    private BigDecimal totalBasePrice;
    @OneToMany(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    private Collection<LineItem> items;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Guest guest, Timestamp orderDate, BigDecimal totalBasePrice) {
        this.guest = guest;
        this.orderDate = orderDate;
        this.totalBasePrice = totalBasePrice;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalBasePrice() {
        return totalBasePrice;
    }

    public void setTotalBasePrice(BigDecimal totalBasePrice) {
        this.totalBasePrice = totalBasePrice;
    }

    public Collection<LineItem> getItems() {
        return items;
    }

    public void setItems(Collection<LineItem> items) {
        this.items = items;
    }
    
    public int getNbItems() {
        return (this.items == null) ? 0 : this.items.size();
    }

    public void addItem(LineItem item) {
        if(this.items == null) {
            this.items = new LinkedList<LineItem>();
        }
        this.items.add(item);
    }

    public void removeItem(LineItem item) {
        if(this.items != null && this.items.contains(item)) {
            this.items.remove(item);
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
        if (!(object instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.unice.miage.gamestershop.entity.PurchaseOrder[id=" + id + "]";
    }

}

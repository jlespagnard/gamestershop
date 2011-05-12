/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.miage.gamestershop.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Julien LESPAGNARD
 * @author Anthony BONIN
 */
@Entity
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable=false)
    private int number;
    @Column(nullable=false)
    private String road;
    private String suppInfos;
    @Column(nullable=false)
    private String zipCode;
    @Column(nullable=false)
    private String city;
    @Column(nullable=false)
    private String countrie;
    private boolean isShippingAddress;

    public Address() {
    }

    public Address(int number, String road, String suppInfos, String zipCode, String city, String countrie, boolean isShippingAddress) {
        this.number = number;
        this.road = road;
        this.suppInfos = suppInfos;
        this.zipCode = zipCode;
        this.city = city;
        this.countrie = countrie;
        this.isShippingAddress = isShippingAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountrie() {
        return countrie;
    }

    public void setCountrie(String countrie) {
        this.countrie = countrie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsShippingAddress() {
        return isShippingAddress;
    }

    public void setIsShippingAddress(boolean isShippingAddress) {
        this.isShippingAddress = isShippingAddress;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getSuppInfos() {
        return suppInfos;
    }

    public void setSuppInfos(String suppInfos) {
        this.suppInfos = suppInfos;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.unice.miage.gamestershop.entity.Address[id=" + id + "]";
    }
}
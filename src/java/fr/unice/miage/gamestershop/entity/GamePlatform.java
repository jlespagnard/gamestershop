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
public class GamePlatform implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable=false)
    private String name;

    public GamePlatform() {
    }

    public GamePlatform(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof GamePlatform)) {
            return false;
        }
        GamePlatform other = (GamePlatform) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.unice.miage.gamestershop.entity.GamePlatform[id=" + id + "]";
    }

}

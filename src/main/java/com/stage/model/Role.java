package com.stage.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wital on 04.02.2017.
 */
@Entity
@Table(name = "roles", schema = "usersandroles")
public class Role implements Serializable{
    private int idR;
    private String nameR;

    @Id
    @Column(name = "idR", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    @Basic
    @Column(name = "nameR", nullable = false, length = 45)
    public String getNameR() {
        return nameR;
    }

    public void setNameR(String nameR) {
        this.nameR = nameR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (idR != role.idR) return false;
        if (nameR != null ? !nameR.equals(role.nameR) : role.nameR != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idR;
        result = 31 * result + (nameR != null ? nameR.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idR=" + idR +
                ", nameR='" + nameR + '\'' +
                '}';
    }
}

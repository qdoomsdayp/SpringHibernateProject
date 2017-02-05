package com.stage.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wital on 03.02.2017.
 */
@Entity
@Table(name = "users", schema = "usersandroles")
public class User implements Serializable{
    @Id
    @Column(name = "idU")
   // @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idU;
    @Basic
    @Column(name = "nameU", nullable = false, length = 45)
    private String nameU;
    @Basic
    @Column(name = "emailU", nullable = false, length = 45)
    private String emailU;
    @Column(name = "idR")
    private int idR;

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getIdR() {
        return idR;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }


    public String getNameU() {
        return nameU;
    }

    public void setNameU(String nameU) {
        this.nameU = nameU;
    }


    public String getEmailU() {
        return emailU;
    }

    public void setEmailU(String emailU) {
        this.emailU = emailU;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (idU != that.idU) return false;
        if (nameU != null ? !nameU.equals(that.nameU) : that.nameU != null) return false;
        if (emailU != null ? !emailU.equals(that.emailU) : that.emailU != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idU;
        result = 31 * result + (nameU != null ? nameU.hashCode() : 0);
        result = 31 * result + (emailU != null ? emailU.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "idU=" + idU +
                ", nameU='" + nameU + '\'' +
                ", emailU='" + emailU + '\'' +
                ", idR=" + idR +
                '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import com.codename1.ui.spinner.Picker;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Eleve extends User {

    @Override
    public String toString() {
        return "Eleve{" + "n_carte=" + n_carte + '}'+ super.toString();
    }

    public Eleve(int n_carte, String nom, String prenom, Date dateNaissance, String mail, int tel, String login, String password, String role) {
        super(nom, prenom, dateNaissance, mail, tel, login, password, role);
        this.n_carte = n_carte;
    }

    public Eleve(int n_carte, int id_user, String nom, String prenom, Date dateNaissance, String mail, int tel, String login, String password, String role) {
        super(id_user, nom, prenom, dateNaissance, mail, tel, login, password, role);
        this.n_carte = n_carte;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.n_carte;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Eleve other = (Eleve) obj;
        return this.n_carte == other.n_carte;
    }

    public int getN_carte() {
        return n_carte;
    }

    public void setN_carte(int n_carte) {
        this.n_carte = n_carte;
    }

    private int n_carte;

  
}

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
public class Enseignant extends User {

    public Enseignant(int matricule, String nom, String prenom, Date dateNaissance, String mail, int tel, String login, String password, String role) {
        super(nom, prenom, dateNaissance, mail, tel, login, password, role);
        this.matricule = matricule;
    }

    public Enseignant(int matricule, int id_user, String nom, String prenom, Date dateNaissance, String mail, int tel, String login, String password, String role) {
        super(id_user, nom, prenom, dateNaissance, mail, tel, login, password, role);
        this.matricule = matricule;
    }

    public int getMatricule() {
        return matricule;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.matricule;
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
        final Enseignant other = (Enseignant) obj;
        return this.matricule == other.matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    @Override
    public String toString() {
        return "Enseignant{" + "matricule=" + matricule + '}'+ super.toString();
       
    }

    
    private int matricule;

   

}

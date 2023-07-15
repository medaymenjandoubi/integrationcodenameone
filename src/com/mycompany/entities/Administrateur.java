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
public class Administrateur extends User {

    public Administrateur(String nom, String prenom, Date dateNaissance, String mail, int tel, String login, String password, String role) {
        super(nom, prenom, dateNaissance, mail, tel, login, password, role);
    }

    public Administrateur(int id_user, String nom, String prenom, Date dateNaissance, String mail, int tel, String login, String password, String role) {
        super(id_user, nom, prenom, dateNaissance, mail, tel, login, password, role);
    }

   

    @Override
    public String toString() {
        return "Administrateur{" + '}' + super.toString();
    }

}


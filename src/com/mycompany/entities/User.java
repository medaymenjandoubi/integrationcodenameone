/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;


import java.util.Date;

import java.util.Objects;

/**
 *
 * @author admin
 */
public abstract class User {

   
     private int id_user;
    private String nom;
     private String prenom;
    private  Date dateNaissance ;
    private String mail;
    private int tel;
    private String login;
    private String password;
    private String role;
    public Date getDateNaissance;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id_user;
        hash = 23 * hash + Objects.hashCode(this.nom);
        hash = 23 * hash + Objects.hashCode(this.prenom);
        hash = 23 * hash + Objects.hashCode(this.dateNaissance);
        hash = 23 * hash + Objects.hashCode(this.mail);
        hash = 23 * hash + this.tel;
        hash = 23 * hash + Objects.hashCode(this.login);
        hash = 23 * hash + Objects.hashCode(this.password);
        hash = 23 * hash + Objects.hashCode(this.role);
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
        final User other = (User) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.tel != other.tel) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return Objects.equals(this.dateNaissance, other.dateNaissance);
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", mail=" + mail + ", tel=" + tel + ", login=" + login + ", password=" + password + ", role=" + role + '}';
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String nom, String prenom, Date dateNaissance, String mail, int tel, String login, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
        this.tel = tel;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(int id_user, String nom, String prenom, Date dateNaissance, String mail, int tel, String login, String password, String role) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
        this.tel = tel;
        this.login = login;
        this.password = password;
        this.role = role;
    }
   

    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.entities.ChampExamensCombines;
import java.util.List;

/**
 *
 * @author 21628
 */
public interface IExamen<T> {
    public void ajouter(T m);
    public void modifier(T m);
    public void supprimer(T m);
    public List<ChampExamensCombines> afficher();
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.entities.SeConnectUserForm;

/**
 *
 * @author admin
 */
public class AccueilUserForm  extends Form {
   
      
    
    public AccueilUserForm()  {
        super("Accueil", BoxLayout.y());
        OnGui();
        addActions();
        // Obtient la barre d'outils de la forme
    Toolbar toolbar = getToolbar();

    // Obtient le composant du titre
    Component titleComponent = toolbar.getTitleComponent();

    // Obtient le style du titre
    Style titleStyle = titleComponent.getStyle();

    // Définit la couleur de premier plan du titre
    titleStyle.setFgColor(0xFF0000); // Remplacez 0xFF0000 par le code couleur de votre choix
    }

    AccueilUserForm(SeConnectUserForm aThis) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void OnGui() {
       
        
       
    }
    
    private void addActions() {
      

       
    }
    
    
}

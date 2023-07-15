/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.services.ServiceUser;

/**
 *
 * @author admin
 */
public class AfficheUserForm  extends Form {

    private Form previousForm;

    public AfficheUserForm(Form f) {
        super("Affichage", BoxLayout.y());
        previousForm = f;
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

    private void OnGui() {
       this.add(new SpanLabel(new ServiceUser().afficher().toString()));
    }

    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }
}


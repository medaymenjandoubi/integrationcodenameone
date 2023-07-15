package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;


public class HomeFormmm extends Form{
    
    private Button btnAddClasse;
    private Button btnShowClasse;
    

    public HomeFormmm() {
        super("GESTION DES CLASSES", BoxLayout.y());
        OnGui();
        addActions();
    }
    
    private void OnGui() {
        btnAddClasse = new Button("Ajouter");
        btnShowClasse = new Button("Afficher");
        this.addAll(new Label("Choisissez une option :"), btnAddClasse, btnShowClasse);
    }
    
    private void addActions() {
        btnAddClasse.addActionListener((evt) -> {
           new AjoutClasseForm(this).show();
        });
        
        btnShowClasse.addActionListener((evt) -> {
            new AfficheClasseForm(this).show();
        });
    }
    
}

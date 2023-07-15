package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.services.*;
import com.mycompany.entities.Classe;

public class AjoutClasseForm extends Form {
    
    private TextField tfNom_classe;
    private TextField tfNombre_eleve;
    private TextField tfNiveau;
    private Button btnAjout;
    
    private Form previousForm;
    
    public AjoutClasseForm(Form f) {
        super("Ajouter", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }
    
     private void OnGui() {
        tfNom_classe = new TextField(null, "Classe");
        tfNombre_eleve = new TextField(null, "Nombre Eleves");
        tfNiveau =new TextField(null, "Niveaux");
        btnAjout = new Button("Ajouter");
        this.addAll(tfNom_classe, tfNombre_eleve,tfNiveau, btnAjout);
    }
     
     private void addActions() {
        btnAjout.addActionListener((evt) -> {
            if (tfNom_classe.getText().isEmpty() || tfNombre_eleve.getText().isEmpty()) {
                Dialog.show("Alerte", "Veillez remplir tous les champs", "OK", null);
            } else {
                ClasseService sp = new ClasseService();
                String nom = tfNom_classe.getText();
                int num = (int) Float.parseFloat(tfNombre_eleve.getText());
                String niv = tfNiveau.getText();
                if (sp.ajouter(new Classe(nom, num, niv))) 
                
                {
                    Dialog.show("SUCCESS", "Classe ajoutée !", "OK", null);
                } else {
                    Dialog.show("ERROR", "Erreur serveur", "OK", null);
                }

            }
        });
        
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {previousForm.showBack(); });
     }
    
    
    
    
}

package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.services.ExamenService;
import com.mycompany.entities.ChampExamensCombines;
import java.util.List;
import java.io.IOException;
import com.codename1.io.FileSystemStorage;
public class AfficherExamenForm extends Form {
    private ExamenService examenService;
    private Form previousForm;
    long lastClickTime = 0;
    private List<ChampExamensCombines> exams;
    public AfficherExamenForm() {
        super("Liste Examen", BoxLayout.y());     
        examenService = new ExamenService(); // Initialize the examenService object
        exams = examenService.afficher();
        OnGui();
        addActions();      
    }

    public void rafraichirListe() {
    this.removeAll();
    OnGui();
    revalidate();
}

private void OnGui() {
    List<ChampExamensCombines> exams = new ExamenService().afficher();

    for (ChampExamensCombines exam : exams) {
        MultiButton examButton = new MultiButton();
        examButton.setTextLine1(exam.getDateExamen().toString());
        examButton.setTextLine2(exam.getNomMatiere());
        examButton.setTextLine3(exam.getNomClasse());
        examButton.setTextLine4(String.valueOf(exam.getNumeroSalle()));
        
        examButton.addActionListener((evt) -> {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime <= 500) {
                ModifierExamenForm modifierExamenForm = new ModifierExamenForm(exam, this);
                modifierExamenForm.show();
            }
            lastClickTime = currentTime;
        });

        Button deleteButton = new Button();
        deleteButton.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, deleteButton.getStyle()));
        deleteButton.addActionListener((evt) -> {
            boolean deleted = new ExamenService().supprimer(exam.getIdExamen());
            if (deleted) {
                examButton.remove();
                revalidate();
            } else {
                Dialog.show("Erreur", "Une erreur s'est produite lors de la suppression", "OK", null);
            }
        });

        // Perform the PDF conversion


        this.add(examButton);
        this.add(deleteButton);
    }
}

        private void addActions() {
         getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            new HomeForm().show();
        });
        getToolbar().addMaterialCommandToSideMenu("Add", FontImage.MATERIAL_ADD, e -> {
            new AjouterExamenForm(AfficherExamenForm.this).show();
        });

        }

}

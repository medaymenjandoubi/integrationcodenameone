package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import static com.codename1.ui.events.ActionEvent.Type.Calendar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.spinner.DateSpinner;
import com.codename1.ui.spinner.Picker;
import com.mycompany.services.ExamenService;
import com.mycompany.entities.ChampExamensCombines;
import java.util.Date;
public class ModifierExamenForm extends Form {
    
    private Form previousForm;
    private ChampExamensCombines examen;

    public ModifierExamenForm(ChampExamensCombines examen, Form previousForm) {
        super("Modification", BoxLayout.y());
        this.examen = examen;
        this.previousForm = previousForm;
        OnGui();
        addActions();
    }

    private void OnGui() {
        ComboBox<String> nomMatiereComboBox = new ComboBox<>();
        ComboBox<String> nomClasseComboBox = new ComboBox<>();
        ComboBox<Integer> numeroSalleComboBox = new ComboBox<>();
        
        // Remplir les ComboBox avec les données
        ExamenService examenService = new ExamenService();
        nomMatiereComboBox.setModel(new DefaultListModel<>(examenService.retrieveNomMatieres()));
        nomClasseComboBox.setModel(new DefaultListModel<>(examenService.retrieveNomClasses()));
        numeroSalleComboBox.setModel(new DefaultListModel<>(examenService.retrieveNumerosSalles()));
        
        // Sélectionner les valeurs existantes
        nomMatiereComboBox.setSelectedItem(examen.getNomMatiere());
        nomClasseComboBox.setSelectedItem(examen.getNomClasse());
        numeroSalleComboBox.setSelectedItem(examen.getNumeroSalle());
        
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);

        Date defaultDate = examen.getDateExamen();
        datePicker.setDate(defaultDate);


        
        Button modifierButton = new Button("Modifier");
        modifierButton.addActionListener((evt) -> {
            String nouveauNomMatiere = (String) nomMatiereComboBox.getSelectedItem();
            String nouveauNomClasse = (String) nomClasseComboBox.getSelectedItem();
            int nouveauNumeroSalle = (int) numeroSalleComboBox.getSelectedItem();
            Date selectedTime = datePicker.getDate();
            
            examen.setNomMatiere(nouveauNomMatiere);
            examen.setNomClasse(nouveauNomClasse);
            examen.setNumeroSalle(nouveauNumeroSalle);
            examen.setDateExamen(selectedTime);
            
    boolean updated = new ExamenService().modifierr(examen);
    if (updated) {
        Dialog.show("Succès", "Examen modifié avec succès", "OK", null);
        previousForm.showBack();
        if (previousForm instanceof AfficherExamenForm) {
            ((AfficherExamenForm) previousForm).rafraichirListe();
        }
    } else {
        Dialog.show("Erreur", "Une erreur s'est produite lors de la modification", "OK", null);
    }
        });
        
        this.addAll(nomMatiereComboBox, nomClasseComboBox, numeroSalleComboBox, datePicker, modifierButton);
    }

    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }
}

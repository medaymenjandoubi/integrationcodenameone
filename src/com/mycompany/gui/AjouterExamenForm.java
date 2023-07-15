/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.spinner.DateSpinner;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Examen;
import com.mycompany.services.ExamenService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 21628
 */
public class AjouterExamenForm extends Form{
    private ExamenService examenService;
    private DateSpinner dateSpinner;
    private Form previousForm;
    private Picker numSallePicker;    
    private TextField nomMatiereField;
    private TextField nomClasseField;
    private Button btnAjout;
    private Picker datePicker;
    private ComboBox<String> nomMatiereComboBox;
    private ComboBox<String> nomClasseComboBox;
    private ComboBox<Integer> numSalleComboBox;
    public AjouterExamenForm(Form previousForm) {
        super("Ajouter Examen", BoxLayout.y());
        examenService = new ExamenService(); 
        this.previousForm = previousForm;
        OnGui();
        addActions();
    }

    private void OnGui() {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        List<String> nomMatieres = examenService.retrieveNomMatieres();
        nomMatiereComboBox = new ComboBox<>();
        ListModel<String> nomMatiereListModel = new DefaultListModel<>(nomMatieres);
        nomMatiereComboBox.setModel(nomMatiereListModel);

        
        List<String> nomClasses = examenService.retrieveNomClasses();
        nomClasseComboBox = new ComboBox<>();
        ListModel<String> nomClasseListModel = new DefaultListModel<>(nomClasses);
        nomClasseComboBox.setModel(nomClasseListModel);
        
        List<Integer> numSalles = examenService.retrieveNumerosSalles();
        numSalleComboBox = new ComboBox<>();
        ListModel<Integer> numSalleListModel = new DefaultListModel<>(numSalles);
        numSalleComboBox.setModel(numSalleListModel);
        
        numSallePicker = createNumSallePicker();
        datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        nomClasseField = new TextField(null,"Nom classe");
        nomMatiereField = new TextField(null, "nom matiere");
        btnAjout = new Button("Ajouter");
        this.addAll(datePicker,nomMatiereComboBox,nomClasseComboBox,numSalleComboBox,btnAjout);
    }

private void addActions() {
    btnAjout.addActionListener((evt) -> {
        if (nomClasseComboBox.getSelectedItem() == null || nomMatiereComboBox.getSelectedItem() == null || numSalleComboBox.getSelectedItem() == null) {
            Dialog.show("Alerte", "Veuillez remplir tous les champs", "OK", null);
        } else {
            ExamenService es = new ExamenService();
            String nomMatiere = (String) nomMatiereComboBox.getSelectedItem();
            String nomClasse = (String) nomClasseComboBox.getSelectedItem();
            int numeroSalle = (int) numSalleComboBox.getSelectedItem();
            int idclasse = examenService.retrieveClasseId(nomClasse);
            int idmatiere = examenService.retrieveMatiereId(nomMatiere);
            int idsalle = examenService.retrieveSalleId(numeroSalle);
            Date selectedDate = datePicker.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(selectedDate);
            try {
                Date date = dateFormat.parse(formattedDate);
                es.insererExamen(date, idmatiere, idclasse, idsalle);
                Dialog.show("Succès", "Examen ajouté avec succès", "OK", null);
                if (previousForm instanceof AfficherExamenForm) {
                    AfficherExamenForm afficherExamenForm = (AfficherExamenForm) previousForm;
                    afficherExamenForm.rafraichirListe();
                }
                previousForm.showBack();
            } catch (ParseException e) {
                e.printStackTrace(); // or handle the exception appropriately
            }
        }
    });

    this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
        previousForm.showBack();
    });
}


private Picker createNumSallePicker() {
    Picker picker = new Picker();
    picker.setType(Display.PICKER_TYPE_STRINGS);
    String[] strings = new String[100]; // Create an array to hold the strings
    for (int i = 1; i <= 100; i++) {
        strings[i - 1] = String.valueOf(i); // Store each value in the array
    }
    picker.setStrings(strings); // Set the strings array to the picker
    picker.setSelectedString(String.valueOf(1)); // Set the default selected value
    return picker;
}

}



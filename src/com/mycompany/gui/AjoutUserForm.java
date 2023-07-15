/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;



import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Administrateur;
import com.mycompany.entities.Eleve;
import com.mycompany.entities.Enseignant;
import com.mycompany.entities.User;


import com.mycompany.services.ServiceUser;




/**
 *
 * @author admin
 */
public class AjoutUserForm extends Form {

    private TextField tfNom;
    private TextField tfPrenom;
    private Picker tfDatenaissance;
    private TextField tfMail;
    private TextField tfTel;
    private TextField tfLogin;
    private TextField tfPassword; 
    private ComboBox tfRole;
    private TextField tfN_carte;
    private TextField tfMatricule;
    private Button btnAjout;
    private Button btnModif;

    private Form previousForm;

    public AjoutUserForm(Form f) {
        super("Inscription", BoxLayout.y());
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
    tfNom = new TextField("", "Nom");
    tfPrenom = new TextField("", "Prénom");
    tfDatenaissance = new Picker();
    tfMail = new TextField("", "Mail");
    tfTel = new TextField("", "Tel");
    tfLogin = new TextField("", "Login");
    tfPassword = new TextField("", "Password");
    tfRole = new ComboBox<>();
    tfMatricule = new TextField("", "Matricule");
    tfN_carte = new TextField("", "N_carte");
    btnAjout = new Button("Ajouter");
    btnModif = new Button("Modifier");
    
    
    tfRole.addItem("Administrateur");
    tfRole.addItem("Eleve");
    tfRole.addItem("Enseignant");
    
    this.addAll(tfNom, tfPrenom, tfDatenaissance, tfMail, tfTel, tfLogin, tfPassword, tfRole, tfMatricule, tfN_carte, btnAjout, btnModif );
}   
    private void addActions() {
    btnAjout.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent evt) {
            if (tfNom.getText().isEmpty() || tfPrenom.getText().isEmpty() || tfDatenaissance.getText().isEmpty() || tfMail.getText().isEmpty() || tfPassword.getText().isEmpty()) {
                Dialog.show("Alerte", "Veuillez remplir tous les champs", "OK", null);
                
            } else {
                ServiceUser sp = new ServiceUser();
                if (tfRole.getSelectedItem().equals("Administrateur")) {
                       User u = new Administrateur( tfNom.getText() , tfPrenom.getText(), tfDatenaissance.getDate(), tfMail.getText(),Integer.parseInt(tfTel.getText()),tfLogin.getText(),tfPassword.getText(),tfRole.getSelectedItem().toString() );
                       u.setRole("Administrateur");
                       sp.ajouter(u);
                        Dialog.show("SUCCESS", "Administrateur ajouté !", "OK", null);
                        
                    } else {
                    Dialog.show("ERROR", "Erreur serveur", "OK", null);
                }
                   if (tfRole.getSelectedItem().equals("Enseignant")) {
                       User u = new Enseignant( Integer.parseInt(tfMatricule.getText()), tfNom.getText() , tfPrenom.getText(), tfDatenaissance.getDate(), tfMail.getText(),Integer.parseInt(tfTel.getText()),tfLogin.getText(),tfPassword.getText(),tfRole.getSelectedItem().toString() );
                       u.setRole("Enseignant");
                       sp.ajouter(u);
                        Dialog.show("SUCCESS", "Enseignant ajouté !", "OK", null);
                    } else {
                    Dialog.show("ERROR", "Erreur serveur", "OK", null);
                }
                    if (tfRole.getSelectedItem().equals("Eleve")) {
                       User u = new Eleve( Integer.parseInt(tfN_carte.getText()), tfNom.getText() , tfPrenom.getText(), tfDatenaissance.getDate(), tfMail.getText(),Integer.parseInt(tfTel.getText()),tfLogin.getText(),tfPassword.getText(),tfRole.getSelectedItem().toString() );
                       u.setRole("Eleve");
                       sp.ajouter(u);
                        Dialog.show("SUCCESS", "Eleve ajouté !", "OK", null);
                    } else {
                    Dialog.show("ERROR", "Erreur serveur", "OK", null);
                }
             
            }
        }
    });

    Command returnCommand = new Command("Retour") {
       
        public void actionPerformed(ActionEvent evt) {
            previousForm.showBack();
        }
    };
    this.getToolbar().addCommandToLeftBar(returnCommand);
}
}



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
import com.codename1.ui.List;
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

public class ModifiUserForm extends Form {
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
    private ComboBox<String> user = new ComboBox<>();
    private int idduser = 0;
    ServiceUser sp = new ServiceUser();
    List<String> listUser = (List<String>) sp.afficher();
    private Form previousForm;

    public ModifiUserForm(Form f, User u) {
        super("Inscription", BoxLayout.y());
        previousForm = f;
        OnGui(u);
        addActions(u);
        System.out.println(listUser.toString());
        idduser = u.getId_user();
        // Obtient la barre d'outils de la forme
        Toolbar toolbar = getToolbar();

        // Obtient le composant du titre
        Component titleComponent = toolbar.getTitleComponent();

        // Obtient le style du titre
        Style titleStyle = titleComponent.getStyle();

        // Définit la couleur de premier plan du titre
        titleStyle.setFgColor(0xFF0000); // Remplacez 0xFF0000 par le code couleur de votre choix
    }

    ModifiUserForm(HomeForm aThis) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void OnGui(User u) {
        tfNom = new TextField(u.getNom());
        tfPrenom = new TextField(u.getPrenom());
        tfDatenaissance = new Picker();
        tfDatenaissance.setDate(u.getDateNaissance());
        tfMail = new TextField(u.getMail());
        tfTel = new TextField(u.getTel());
        tfLogin = new TextField(u.getLogin());
        tfPassword = new TextField(u.getPassword());
        tfRole = new ComboBox<>();
        tfMatricule = new TextField("", "Matricule");
        tfN_carte = new TextField("", "N_carte");
        btnAjout = new Button("Ajouter");
        btnModif = new Button("Modifier");

        tfRole.addItem("Administrateur");
        tfRole.addItem("Eleve");
        tfRole.addItem("Enseignant");

        this.addAll(tfNom, tfPrenom, tfDatenaissance, tfMail, tfTel, tfLogin, tfPassword, tfRole, tfMatricule, tfN_carte, btnAjout, btnModif);
    }

    private void addActions(User u) {
        btnAjout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().isEmpty() || tfPrenom.getText().isEmpty() || tfDatenaissance.getText().isEmpty() || tfMail.getText().isEmpty() || tfPassword.getText().isEmpty()) {
                    Dialog.show("Alerte", "Veuillez remplir tous les champs", "OK", null);
                } else {
                    ServiceUser sp = new ServiceUser();
                    if (tfRole.getSelectedItem().equals("Administrateur")) {
                        User admin = new Administrateur(u.getId_user(), tfNom.getText(), tfPrenom.getText(), tfDatenaissance.getDate(), tfMail.getText(), Integer.parseInt(tfTel.getText()), tfLogin.getText(), tfPassword.getText(), tfRole.getSelectedItem().toString());
                        admin.setRole("Administrateur");
                        sp.modifier(admin);
                        Dialog.show("SUCCESS", "Administrateur modifié !", "OK", null);
                    } else if (tfRole.getSelectedItem().equals("Enseignant")) {
                        User enseignant = new Enseignant(u.getId_user(), Integer.parseInt(tfMatricule.getText()), tfNom.getText(), tfPrenom.getText(), tfDatenaissance.getDate(), tfMail.getText(), Integer.parseInt(tfTel.getText()), tfLogin.getText(), tfPassword.getText(), tfRole.getSelectedItem().toString());
                        enseignant.setRole("Enseignant");
                        sp.modifier(enseignant);
                        Dialog.show("SUCCESS", "Enseignant modifié !", "OK", null);
                    } else if (tfRole.getSelectedItem().equals("Eleve")) {
                        User eleve = new Eleve(u.getId_user(), Integer.parseInt(tfN_carte.getText()), tfNom.getText(), tfPrenom.getText(), tfDatenaissance.getDate(), tfMail.getText(), Integer.parseInt(tfTel.getText()), tfLogin.getText(), tfPassword.getText(), tfRole.getSelectedItem().toString());
                        eleve.setRole("Eleve");
                        sp.modifier(eleve);
                        Dialog.show("SUCCESS", "Eleve modifié !", "OK", null);
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
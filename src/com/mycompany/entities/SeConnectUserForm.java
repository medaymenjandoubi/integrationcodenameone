/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import com.codename1.ui.Button;
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
import com.mycompany.entities.Administrateur;
import com.mycompany.entities.Eleve;
import com.mycompany.entities.Enseignant;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;
import java.util.ArrayList;
import java.util.List;

public class SeConnectUserForm extends Form {

    ServiceUser sp = new ServiceUser();
    List<User> listUser = new ArrayList<>(sp.afficher());

    private TextField tfMail;
    private TextField tfLogin;
    private TextField tfPassword;

    private Button btnSeconnect;
    private Button btnOblier;

    private Form previousForm;

    public SeConnectUserForm(Form f) {
        super("Se Connecter", BoxLayout.y());
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
        tfMail = new TextField("", "Mail");
        tfLogin = new TextField("", "Login");
        tfPassword = new TextField("", "Password");
        tfPassword.setConstraint(TextField.PASSWORD); // Définit le champ de mot de passe
        btnSeconnect = new Button("Se Connecter");
        btnOblier = new Button("Mot Passe Oublié");

        this.addAll(tfMail, tfLogin, tfPassword, btnSeconnect, btnOblier);
    }

    private void addActions() {
        btnSeconnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                String login = tfLogin.getText();
                String password = tfPassword.getText();

                if (tfLogin.getText().isEmpty() || tfPassword.getText().isEmpty()) {
                    Dialog.show("Alerte", "Veuillez remplir tous les champs", "OK", null);
                } else {
                    User e = null;
                    if (!(login.isEmpty() || password.isEmpty())) {
                        for (User u : listUser) {
                            if (u.getLogin().equals(login) && verifierMotDePasse(password, u.getPassword())) {
                                e = u;
                                break;
                            }
                        }

                        if (e == null) {
                            Dialog.show("Informations incorrectes", "Le login ou le mot de passe est incorrect.", "OK", null);
                        } else {
                            if (e instanceof Administrateur) {
                                System.out.println("Administrateur");
                                Dialog.show("Succès", "Administrateur connecté", "OK", null);

                            } else if (e instanceof Enseignant) {
                                System.out.println("Enseignant");
                                Dialog.show("Succès", "Enseignant connecté", "OK", null);

                            } else if (e instanceof Eleve) {
                                System.out.println("Eleve");
                                Dialog.show("Succès", "Eleve connecté", "OK", null);
                               

                                // Connecté avec succès, effectuez les actions nécessaires ici
                            }
                            tfPassword.setText("");
                        }
                    } else {
                        Dialog.show("Champs vides", "Veuillez remplir tous les champs.", "OK", null);
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

    public boolean verifierMotDePasse(String password, String password0) {
        // Implémenter ici la logique de vérification du mot de passe
        return password.equals(password0);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;

import com.mycompany.entities.Administrateur;
import com.mycompany.entities.Eleve;
import com.mycompany.entities.Enseignant;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MotPassOublierForm extends Form {
    private ServiceUser sp = new ServiceUser();
    private List<User> listUser = new ArrayList<>(sp.afficher());
    private TextField tfCode;
    private TextField tfMail;
    private TextField tfLogin;
    private TextField tfNewPassword;
     private TextField tfConfiNewPassword;
    private Button btnModifier;
    private Button btnOublier;
    private Form previousForm;
    private Timer timer;
    private TimerTask disableTask;
    private User user;
    private int code;

    public MotPassOublierForm(Form f) {
        super("Mot de passe oublié", BoxLayout.y());
        previousForm = f;
        onGui();
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

    private void onGui() {
        tfMail = new TextField("", "Mail");
        tfCode = new TextField("", "Code");
        tfLogin = new TextField("", "Login");
        tfNewPassword = new TextField("", "New Password");
         tfConfiNewPassword = new TextField("", "Confirm New Password");

        tfNewPassword.setConstraint(TextField.PASSWORD); // Définit le champ de mot de passe
        tfConfiNewPassword.setConstraint(TextField.PASSWORD); // Définit le champ de mot de passe
        btnModifier = new Button("Modifier MDP");
        btnOublier = new Button("Envoi Code");

        this.addAll(tfMail, tfCode, tfLogin, tfNewPassword, tfConfiNewPassword, btnModifier, btnOublier);
    }

    private void addActions() {
        btnOublier.addActionListener((e) -> {
            String email = tfMail.getText();

            // Vérifier si le champ email est vide
            if (email.isEmpty()) {
                Dialog.show("Champ vide", "Veuillez remplir le champ email.", "OK", null);
                return;
            }

            // Rechercher l'utilisateur correspondant à l'adresse e-mail donnée
            ServiceUser userService = new ServiceUser();
            List<User> userList = new ArrayList<>(userService.afficher());
            User foundUser = null;
            for (User user : userList) {
                if (user.getMail().equals(email)) {
                    foundUser = user;
                    break;
                }
            }

            if (foundUser != null) {
                // Générer un code aléatoire
                Random random = new Random();
                code = random.nextInt(9000) + 1000;
                System.out.println(code);

                // Envoyer l'e-mail avec le code de réinitialisation
                sendCode(code, foundUser.getNom(), foundUser.getPrenom(), foundUser.getMail());

                user = foundUser;
            } else {
                Dialog.show("Utilisateur introuvable", "Aucun utilisateur trouvé avec l'adresse e-mail spécifiée.", "OK", null);
            }

            // Effacer le champ email après l'envoi du code
            tfMail.clear();
        });

        btnModifier.addActionListener((e) -> {
            if (tfCode.getText().equals(String.valueOf(code))) {
                if (user instanceof Administrateur || user instanceof Enseignant || user instanceof Eleve) {
                    user.setPassword(tfNewPassword.getText());
                    sp.modifier(user);
                    Dialog.show("Mot de passe modifié", "Mot de passe modifié avec succès.", "OK", null);
                }
            } else {
                Dialog.show("Code incorrect", "Le code de réinitialisation de mot de passe est incorrect.", "OK", null);
            }
        });
    }

    private void sendCode(int code, String nom, String prenom, String recepteur) {
        String emetteur = "ibrahim.fartoun@gmail.com";
        String host = "smtp.gmail.com";
        String mail = "ibrahim.fartoun@gmail.com";
        String password = "wwlkavqimfihpnsn";

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");

        properties.setProperty("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emetteur));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepteur));
            message.setSubject("Code de réinitialisation de mot de passe de votre compte");

            message.setText("Bonjour cher(e) " + nom + " " + prenom + ",\n\nVotre code de réinitialisation de mot de passe est : " + code);

            Transport.send(message);

            Dialog.show("Mail envoyé", "L'e-mail contenant le code de réinitialisation de mot de passe a été envoyé à l'adresse " + recepteur, "OK", null);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            // Afficher une alerte en cas d'erreur lors de l'envoi du message
            Dialog.show("Erreur", "Une erreur s'est produite lors de l'envoi de l'e-mail. Veuillez réessayer plus tard.", "OK", null);
            
            if (tfNewPassword.getText().equals(tfConfiNewPassword.getText())) {
            if (user instanceof Administrateur || user instanceof Enseignant || user instanceof Eleve) {
                user.setPassword(tfNewPassword.getText());
                sp.modifier(user);
                // boolean name = JOptionPane.showConfirmDialog(null,"Voulez vous supprimer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION;	
              Dialog.show("Erreur", "mot pase modifier avec succès", "OK", null);
                

            }
        } else {
                Dialog.show("Erreur", "Erreur", "OK", null);
            

            // Mots de passe différents
        }
        }
    }
}
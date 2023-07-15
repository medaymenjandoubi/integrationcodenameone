/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;

import com.mycompany.entities.Administrateur;
import com.mycompany.entities.Eleve;
import com.mycompany.entities.Enseignant;
import com.mycompany.entities.User;
import com.mycompany.utils.Statics;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceUser implements IService<User> {

    private boolean responseResult;
    private List<User> users;

    private final String URI = Statics.BASE_URL + "/user/";

    public ServiceUser() {
        users = new ArrayList<>();
    }

    public boolean ajouter(User u) {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI);
        request.setHttpMethod("POST");

        request.addArgument("nom", u.getNom());
        request.addArgument("prenom", u.getPrenom());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateoff = sdf.format(u.getDateNaissance());

        request.addArgument("dateNaissance", formattedDateoff);
        request.addArgument("mail", u.getMail());
        request.addArgument("tel", String.valueOf(u.getTel()));
        request.addArgument("login", u.getLogin());
        request.addArgument("password", u.getPassword());

        if (u instanceof Administrateur) {
            request.addArgument("role", "administrateur");
        } else if (u instanceof Eleve) {
            request.addArgument("role", "eleve");
            request.addArgument("n_carte", String.valueOf(((Eleve) u).getN_carte()));
        } else if (u instanceof Enseignant) {
            request.addArgument("role", "enseignant");
            request.addArgument("matricule", String.valueOf(((Enseignant) u).getMatricule()));
        }

        request.addResponseListener((NetworkEvent evt) -> {
            byte[] responseData = request.getResponseData();
            String response = new String(responseData);

            responseResult = request.getResponseCode() == 201; // HTTP 201 Created
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean modifier(User u) {
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(URI + u.getId_user());
        request.setHttpMethod("PUT");

        request.addArgument("nom", u.getNom());
        request.addArgument("prenom", u.getPrenom());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateoff = sdf.format(u.getDateNaissance());
        request.addArgument("dateNaissance", formattedDateoff);
        request.addArgument("mail", u.getMail());
        request.addArgument("tel", String.valueOf(u.getTel()));
        request.addArgument("login", u.getLogin());
        request.addArgument("password", u.getPassword());

        if (u instanceof Eleve) {
            System.out.println("Eleve modifié !");
            Dialog.show("Succès", "Eleve modifié avec succès", "OK", null);
        } else if (u instanceof Enseignant) {
            System.out.println("Enseignant modifié !");
            Dialog.show("Succès", "Enseignant modifié avec succès", "OK", null);
        } else if (u instanceof Administrateur) {
            System.out.println("Administrateur modifié !");
            Dialog.show("Succès", "Administrateur modifié avec succès", "OK", null);
        }
        request.addResponseListener((NetworkEvent evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean supprimer(User u) {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI + u.getId_user());
        request.setHttpMethod("DELETE");

        request.addResponseListener((NetworkEvent evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public List<User> afficher() {
        List<User> list = new ArrayList<>();

        ConnectionRequest connectionRequest = new ConnectionRequest() {
            protected void readResponse(InputStream input) throws IOException {
                JSONParser parser = new JSONParser();
                Map<String, Object> result = parser.parseJSON(new InputStreamReader(input, "UTF-8"));
                if (result.containsKey("root")) {
                    List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("root");
                    for (Map<String, Object> row : rows) {
                        String role = (String) row.get("role");
                        int id_user = ((Double) row.get("id_user")).intValue();
                        String nom = row.get("nom").toString();
                        String prenom = row.get("prenom").toString();
                        String mail = row.get("mail").toString();
                        int tel = ((Double) row.get("tel")).intValue();
                        String login = row.get("login").toString();
                        String password = row.get("password").toString();

                        User u;
                        switch (role) {
                            case "administrateur":
                                u = new Administrateur(
                                        id_user,
                                        nom,
                                        prenom,
                                        new Date(),
                                        mail,
                                        tel,
                                        login,
                                        password,
                                        role
                                );
                                break;
                            case "eleve":
                                int n_carte = ((Double) row.get("n_carte")).intValue();
                                u = new Eleve(
                                        id_user,
                                        n_carte,
                                        nom,
                                        prenom,
                                        new Date(),
                                        mail,
                                        tel,
                                        login,
                                        password,
                                        role
                                );
                                break;
                            case "enseignant":
                                int matricule = ((Double) row.get("matricule")).intValue();
                                u = new Enseignant(
                                        id_user,
                                        matricule,
                                        nom,
                                        prenom,
                                        new Date(),
                                        mail,
                                        tel,
                                        login,
                                        password,
                                        role
                                );
                                break;
                            default:
                                // Gérer le cas où le rôle n'est pas reconnu ou non pris en charge
                                continue;
                        }
                        list.add(u);
                    }
                }
            }
        };
        connectionRequest.setUrl(URI); // Utilisez l'URL appropriée pour votre API
        connectionRequest.setHttpMethod("GET");
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);

        return list;
    }
}
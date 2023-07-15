/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.mycompany.entities.ChampExamensCombines;
import com.mycompany.entities.Examen;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import java.util.Map;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 21628
 */
public class ExamenService implements IExamen<Examen>{
        private List<ChampExamensCombines> ChampExamensCombines;
        private final String URI = Statics.BASE_URL + "/Examen/";
        
public int retrieveClasseId(String nomClasse) {
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(URI + "retrieveClasseId?nomClasse=" + nomClasse);
    request.setHttpMethod("GET");

    try {
        NetworkManager.getInstance().addToQueueAndWait(request);

        if (request.getResponseData() != null) {
            InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
            JSONParser parser = new JSONParser();
            Map<String, Object> result = parser.parseJSON(jsonText);

            List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

            if (list != null && !list.isEmpty()) {
                Map<String, Object> item = list.get(0);
                Object idClasseObj = item.get("id_classe");

                if (idClasseObj != null) {
                    double idClasseDouble = Double.parseDouble(idClasseObj.toString());
                    return (int) idClasseDouble;
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return -1; // Return a default value or handle the error appropriately
}
public int retrieveMatiereId(String nomMatiere) {
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(URI + "retrieveMatiereId?nomMatiere=" + nomMatiere);
    request.setHttpMethod("GET");

    try {
        NetworkManager.getInstance().addToQueueAndWait(request);

        if (request.getResponseData() != null) {
            InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
            JSONParser parser = new JSONParser();
            Map<String, Object> result = parser.parseJSON(jsonText);

            List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

            if (list != null && !list.isEmpty()) {
                Map<String, Object> item = list.get(0);
                Object idClasseObj = item.get("id_matiere");

                if (idClasseObj != null) {
                    double idClasseDouble = Double.parseDouble(idClasseObj.toString());
                    return (int) idClasseDouble;
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return -1; // Return a default value or handle the error appropriately
}
public int retrieveSalleId(int numSalle) {
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(URI + "retrieveSalleId?numSalle=" + numSalle);
    request.setHttpMethod("GET");

    try {
        NetworkManager.getInstance().addToQueueAndWait(request);

        if (request.getResponseData() != null) {
            InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
            JSONParser parser = new JSONParser();
            Map<String, Object> result = parser.parseJSON(jsonText);

            List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

            if (list != null && !list.isEmpty()) {
                Map<String, Object> item = list.get(0);
                Object idClasseObj = item.get("id_salle");

                if (idClasseObj != null) {
                    double idClasseDouble = Double.parseDouble(idClasseObj.toString());
                    return (int) idClasseDouble;
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return -1; // Return a default value or handle the error appropriately
}

public List<String> retrieveNomMatieres() {
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(URI + "retrieveNomMatieres");
        request.setHttpMethod("GET");

        List<String> nomMatieres = new ArrayList<>();

        try {
            NetworkManager.getInstance().addToQueueAndWait(request);

            if (request.getResponseData() != null) {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                JSONParser parser = new JSONParser();
                Map<String, Object> result = parser.parseJSON(jsonText);

                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("matieres");

                if (list != null && !list.isEmpty()) {
                    for (Map<String, Object> item : list) {
                        String nomMatiere = (String) item.get("nom_matiere");
                        nomMatieres.add(nomMatiere);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nomMatieres;
    }

    public List<String> retrieveNomClasses() {
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(URI + "retrieveNomClasses");
        request.setHttpMethod("GET");

        List<String> nomClasses = new ArrayList<>();

        try {
            NetworkManager.getInstance().addToQueueAndWait(request);

            if (request.getResponseData() != null) {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                JSONParser parser = new JSONParser();
                Map<String, Object> result = parser.parseJSON(jsonText);

                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("classes");

                if (list != null && !list.isEmpty()) {
                    for (Map<String, Object> item : list) {
                        String nomClasse = (String) item.get("nom_classe");
                        nomClasses.add(nomClasse);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nomClasses;
    }

public List<Integer> retrieveNumerosSalles() {
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(URI + "retrieveNumerosSalles");
    request.setHttpMethod("GET");

    List<Integer> numerosSalles = new ArrayList<>();

    try {
        NetworkManager.getInstance().addToQueueAndWait(request);

        if (request.getResponseData() != null) {
            JSONParser parser = new JSONParser();
            Map<String, Object> result = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData())));

            List<Double> salles = (List<Double>) result.get("salles");
            if (salles != null && !salles.isEmpty()) {
                for (Double salle : salles) {
                    numerosSalles.add(salle.intValue());
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return numerosSalles;
}


    public void insererExamen(Date formattedDate, int idmatiere, int idclasse, int idsalle) {
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(URI); // Set the appropriate URL for the insertion
        request.setHttpMethod("POST");

        // Add the necessary parameters for the insertion
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(formattedDate);
        request.addArgument("date_examen", dateString);
        request.addArgument("idmatiere", String.valueOf(idmatiere));
        request.addArgument("idclasse", String.valueOf(idclasse));
        request.addArgument("idsalle", String.valueOf(idsalle));

        request.addResponseListener((evt) -> {
            // Handle the response if needed
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
    }
    
    
    
    
    
    
    
    
public List<ChampExamensCombines> afficher() {
    ConnectionRequest request = new ConnectionRequest();

    request.setUrl(URI);
    request.setHttpMethod("GET");

    List<ChampExamensCombines> champExamensCombinesList = new ArrayList<>();

    request.addResponseListener((evt) -> {
        try {
            InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
            Map<String, Object> result = new JSONParser().parseJSON(jsonText);
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : list) {
                int id = (int) Float.parseFloat(obj.get("id_examen").toString());
                String nom_matiere = obj.get("nom_matiere").toString();
                String nom_classe = obj.get("nom_classe").toString();
                String dateString = obj.get("date_examen").toString(); // Get the date as a String
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Specify the date format
                Date datee = dateFormat.parse(dateString); 
                int num_salle = (int) Float.parseFloat(obj.get("numero_salle").toString());
                champExamensCombinesList.add(new ChampExamensCombines(id, datee, nom_matiere, nom_classe, num_salle));
            }

        } catch (IOException | ParseException ex) {
            System.out.println(ex.getMessage());
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(request);
    System.out.println(champExamensCombinesList);
    return champExamensCombinesList;
}
    public boolean modifier(ChampExamensCombines t) {
            return false;
    }
    
    public boolean supprimer(int idExamen) {
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(URI + "examen/" + idExamen);
        request.setHttpMethod("DELETE");

        NetworkManager.getInstance().addToQueueAndWait(request);

        if (request.getResponseCode() == 200) {
            return true;
        }

        return false;
    }


 public boolean modifierr(ChampExamensCombines examen) {
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(URI + "examen/" + examen.getIdExamen());
    request.setHttpMethod("PUT");

    // Retrieve the corresponding IDs
    int idclasse = retrieveClasseId(examen.getNomClasse());
    int idmatiere = retrieveMatiereId(examen.getNomMatiere());
    int idsalle = retrieveSalleId(examen.getNumeroSalle());

    // Set the request body or parameters with the updated examen details
    request.addArgument("id_examen", String.valueOf(examen.getIdExamen()));
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = dateFormat.format(examen.getDateExamen());
    request.addArgument("date", formattedDate);
    request.addArgument("idmatiere", String.valueOf(idmatiere));
    request.addArgument("idclasse", String.valueOf(idclasse));
    request.addArgument("idsalle", String.valueOf(idsalle));

    NetworkManager.getInstance().addToQueueAndWait(request); // Handle the exception appropriately
    if (request.getResponseCode() == 200) {
        // Exam modified successfully
        System.out.println("Examen modified successfully");
        return true;
    } else {
        // Handle the case where exam modification failed
        System.out.println("Failed to modify examen");
        return false;
    }
}

    @Override
    public void ajouter(Examen m) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void supprimer(Examen m) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modifier(Examen m) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

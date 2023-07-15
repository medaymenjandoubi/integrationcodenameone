package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.entities.Classe;
import com.mycompany.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ClasseService implements IService <Classe> {
    
    
    private boolean responseResult;
    private List<Classe> classes;
    
    private final String URI = Statics.BASE_URL + "/classe/";

    public ClasseService() {
       classes = new ArrayList();
    }


    public boolean ajouter(Classe t) {
        
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI);
        request.setHttpMethod("POST");

        request.addArgument("nom_classe", t.getNom_classe());
        request.addArgument("nombre_eleve",String.valueOf(t.getNombre_eleve()));
        request.addArgument("niveau", t.getNiveau());
        

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 201; // Code HTTP 201 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

   
    public boolean modifier(Classe t) {
        
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI + t.getId_classe());
        request.setHttpMethod("PUT");

        request.addArgument("Classe", t.getNom_classe());
        request.addArgument("Nombre Elves", String.valueOf(t.getNombre_eleve()));
        request.addArgument("niveau", t.getNiveau());

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    
    public boolean supprimer(Classe t) {
        
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(URI + t.getId_classe());
        request.setHttpMethod("DELETE");

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    
    public List<Classe> afficher() {
        
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI);
        request.setHttpMethod("GET");

        request.addResponseListener((evt) -> {
            try {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

                for (Map<String, Object> obj : list) {
                    int id_classe = (int) Float.parseFloat(obj.get("id_classe").toString());
                    String nom_classe = obj.get("nom_classe").toString();
                    int nombre_eleve= (int) Float.parseFloat(obj.get("nombre_eleve").toString());
                    String niveau = obj.get("niveau").toString();
                    
                    classes.add(new Classe(id_classe, nom_classe, nombre_eleve, niveau));
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return classes;
    }
    }
    
    
    
    


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.db.Database;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import java.io.IOException;


/**
 *
 * @author 21628
 */
public class BaseForm extends Form{
        protected Database db;

    public BaseForm(String title, Layout l) {
        super(title, l);
        addActions();
        try {
            db = Database.openOrCreate("ecole");
            String req = "create table if not exists contact(id INTEGER PRIMARY KEY AUTOINCREMENT,nom VARCHAR, num VARCHAR, image VARCHAR)";
            db.execute(req);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void addActions() {
        getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            new BaseForm("Home",BoxLayout.y()).show();
        });
        getToolbar().addMaterialCommandToSideMenu("Add", FontImage.MATERIAL_ADD, e -> {
            new AjouterExamenForm(this).show();
        });

    }
}

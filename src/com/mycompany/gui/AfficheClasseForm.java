package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

import com.mycompany.services.*;

/**
 *
 * @author Admin
 */
public class AfficheClasseForm extends Form {
      private Form previousForm;

    public AfficheClasseForm(Form f) {
        super("Affichage", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

    private void OnGui() {
        this.add(new SpanLabel(new ClasseService().afficher().toString()));
    }

    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }
}
    

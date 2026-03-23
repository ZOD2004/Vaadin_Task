package com.example.ui;

import com.example.anno.AddBorderImple;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.AttachEvent;

public abstract class BaseForm extends FormLayout {

    private boolean initialized = false;

    @Override
    public void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if(!initialized){
            new AddBorderImple().apply(this);
            initialized = true;
        }
    }

//    public void applyAnnotationStyles() {
//        new AddBorderImple().apply(this);
//    }
}
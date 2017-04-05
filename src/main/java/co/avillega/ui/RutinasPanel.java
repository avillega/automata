package co.avillega.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Andres Villegas on 2017-04-03.
 */
@UIScope
@SpringComponent
public class RutinasPanel extends VerticalLayout implements View {


    public RutinasPanel() {
        this.addComponent(new Label("Rutinas"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        System.out.println("Entered");
    }
}

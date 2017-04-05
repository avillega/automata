package co.avillega.ui;

import com.vaadin.server.FileResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by Andres Villegas on 2017-04-03.
 */
@UIScope
@SpringComponent
public class AuxPanel extends VerticalLayout {

    Logger log = LoggerFactory.getLogger(AuxPanel.class);
    private MainUI parent;

    public AuxPanel(MainUI parent) {
        this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        this.parent = parent;
        //this.setSizeFull();
        setUserImg();
        addActionButtons();


    }

    private void setUserImg() {
        Image profileImage = new Image("", new FileResource(new File("./src/main/resources/static/generic.png")));
        profileImage.setAlternateText("User profile");
        profileImage.setWidth("70%");
        profileImage.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        Label userName = new Label("Test User");


        this.addComponents(profileImage, userName);

    }

    private void addActionButtons() {
        VerticalLayout buttonLayout = new VerticalLayout();
        buttonLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        Button myRoutines = new Button("Mis rutinas");
        myRoutines.setWidth("100%");
        myRoutines.addClickListener(event -> parent.getNavigator().navigateTo(MainUI.RUTINASVIEW));
        //myRoutines.addStyleName(ValoTheme.BUTTON_BORDERLESS);

        Button irControl = new Button("Control");
        irControl.setWidth("100%");
        irControl.addClickListener(event -> parent.getNavigator().navigateTo(MainUI.CONTROLVIEW));


        buttonLayout.addComponents(myRoutines, irControl);

        this.addComponent(buttonLayout);

    }


}

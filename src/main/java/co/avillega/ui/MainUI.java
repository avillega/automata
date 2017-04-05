package co.avillega.ui;


import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;


@SpringUI
public class MainUI extends UI {

    public static final String CONTROLVIEW = "";
    public static final String RUTINASVIEW = "rutinas";
    AuxPanel auxPanel;
    @Autowired
    ControlPanel controlPanel;
    @Autowired
    RutinasPanel rutinasPanel;
    VerticalLayout contentLayout;
    Navigator navigator;
    private GridLayout root;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();

    }

    private void setupLayout() {
        auxPanel = new AuxPanel(this);
        contentLayout = new VerticalLayout();


        root = new GridLayout(2, 1);

        navigator = new Navigator(this, contentLayout);
        navigator.addView(CONTROLVIEW, controlPanel);
        navigator.addView(RUTINASVIEW, rutinasPanel);


        root.setColumnExpandRatio(0, 0.3f);
        root.setColumnExpandRatio(1, 1);

        root.setSizeFull();
        root.addComponents(auxPanel);
        root.addComponent(contentLayout);


        setContent(root);
    }

    public Navigator getNavigator() {
        return navigator;
    }

}

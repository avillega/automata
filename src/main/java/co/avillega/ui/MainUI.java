package co.avillega.ui;


import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
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
    Panel contentPanel;
    Navigator navigator;
    private GridLayout root;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();

    }

    private void setupLayout() {
        auxPanel = new AuxPanel(this);
        contentPanel = new Panel();



        root = new GridLayout(2, 1);

        navigator = new Navigator(this, contentPanel);
        navigator.addView(CONTROLVIEW, controlPanel);
        navigator.addView(RUTINASVIEW, rutinasPanel);


        root.setColumnExpandRatio(0, 0.3f);
        root.setColumnExpandRatio(1, 1);

        root.setSizeFull();
        root.addComponents(auxPanel);
        root.addComponent(contentPanel);
        contentPanel.setHeight("100%");

        setContent(root);
    }

    public Navigator getNavigator() {
        return navigator;
    }

}

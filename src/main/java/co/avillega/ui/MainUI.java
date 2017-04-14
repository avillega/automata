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

    static final String CONTROLVIEW = "";
    static final String RUTINASVIEW = "rutinas";
    private final ControlPanel controlPanel;
    private final RutinasPanel rutinasPanel;
    private Navigator navigator;

    @Autowired
    public MainUI(ControlPanel controlPanel, RutinasPanel rutinasPanel) {
        this.controlPanel = controlPanel;
        this.rutinasPanel = rutinasPanel;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();

    }

    private void setupLayout() {
        AuxPanel auxPanel = new AuxPanel(this);
        Panel contentPanel = new Panel();


        GridLayout root = new GridLayout(2, 1);

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

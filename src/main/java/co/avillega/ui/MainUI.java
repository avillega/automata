package co.avillega.ui;


import co.avillega.services.ConveyorService;
import co.avillega.services.RoutineService;
import co.avillega.services.UserService;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;


@SpringUI
public class MainUI extends UI {

    static final String CONTROLVIEW = "";
    static final String RUTINASVIEW = "rutinas";
    private final UserService userService;
    private final ConveyorService conveyorService;
    private final RoutineService routineService;
    private ControlPanel controlPanel;
    private RutinasPanel rutinasPanel;
    private Navigator navigator;

    @Autowired
    public MainUI(UserService userService, ConveyorService conveyorService, RoutineService routineService) {
        this.userService = userService;
        this.conveyorService = conveyorService;
        this.routineService = routineService;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VaadinSession.getCurrent().setAttribute("user", userService.getDefaultUser());
        controlPanel = new ControlPanel(conveyorService);
        rutinasPanel = new RutinasPanel(routineService, userService);
        setupLayout();


    }

    private void setupLayout() {
        AuxPanel auxPanel = new AuxPanel(this, userService);
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

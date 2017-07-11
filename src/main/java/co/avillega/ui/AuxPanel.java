package co.avillega.ui;

import co.avillega.entities.AppUser;
import co.avillega.services.UserService;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@UIScope
public class AuxPanel extends VerticalLayout {

    private final UserService userService;
    Logger log = LoggerFactory.getLogger(AuxPanel.class);
    private MainUI parent;
    private AppUser logged;


    public AuxPanel(MainUI parent, UserService userService) {
        this.userService = userService;
        this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        this.parent = parent;
        //this.setSizeFull();
        refresh();


    }

    private void refresh() {
        this.removeAllComponents();
        setUserImg();
        addActionButtons();
    }
    private void setUserImg() {
        logged = (AppUser) VaadinSession.getCurrent().getAttribute("user");

        Image profileImage = new Image("", new ExternalResource("http://media.fooducate.com/assets/users/generic-dude360-circle-white.png"));
        profileImage.setAlternateText("User profile");
        profileImage.setWidth("70%");
        profileImage.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        Label userName = new Label(logged.getUserName());


        this.addComponents(profileImage, userName);

    }

    private void addActionButtons() {


        VerticalLayout buttonLayout = new VerticalLayout();
        buttonLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        Button myRoutines = new Button("Mis rutinas");
        myRoutines.setSizeFull();
        myRoutines.addClickListener(event -> parent.getNavigator().navigateTo(MainUI.RUTINASVIEW));
        //myRoutines.addStyleName(ValoTheme.BUTTON_BORDERLESS);

        Button irControl = new Button("Control");
        irControl.setSizeFull();
        irControl.addClickListener(event -> parent.getNavigator().navigateTo(MainUI.CONTROLVIEW));

        buttonLayout.addComponentsAndExpand(myRoutines, irControl);

        if (logged.equals(userService.getDefaultUser())) {
            Button logIn = new Button("Log in");
            logIn.addStyleName(ValoTheme.BUTTON_PRIMARY);
            logIn.addClickListener(event -> logginWindow());
            logIn.setSizeFull();
            buttonLayout.addComponents(logIn);

            Button register = new Button("Registarse");
            register.setSizeFull();
            register.addClickListener(event -> registerWindow());
            buttonLayout.addComponent(register);
        } else {
            Button cerrarSesion = new Button("Cerrar sesión");
            cerrarSesion.addStyleName(ValoTheme.BUTTON_PRIMARY);
            cerrarSesion.setSizeFull();
            cerrarSesion.addClickListener(event -> setUser(userService.getDefaultUser()));
            buttonLayout.addComponent(cerrarSesion);
        }

        this.addComponent(buttonLayout);

    }

    private void registerWindow() {
        Window window = new RegisterWindow(this);
        UI.getCurrent().addWindow(window);
    }

    private void logginWindow() {
        Window window = new LogInWindow(this);
        UI.getCurrent().addWindow(window);
    }

    public void authenticate(String userName, String password) {
        AppUser user = userService.authenticateUser(userName, password);
        if (user == null) {
            Window window = new LogInWindow(this);
            UI.getCurrent().addWindow(window);
            Notification.show("El usuario o contraseña son incorrectos", Notification.Type.WARNING_MESSAGE);
            return;
        }
        setUser(user);

    }

    public void registerUser(String userName, String password) {
        AppUser user = userService.createUser(userName, password);
        if (user == null) {
            Window window = new RegisterWindow(this);
            UI.getCurrent().addWindow(window);
            Notification.show("un usuario con ese nombre de usuario ya existe", Notification.Type.WARNING_MESSAGE);
            return;
        }
        setUser(user);
    }

    public void setUser(AppUser user) {
        VaadinSession.getCurrent().setAttribute("user", user);
        refresh();
        parent.getNavigator().getCurrentView().enter(null);
    }


}

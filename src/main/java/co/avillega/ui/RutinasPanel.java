package co.avillega.ui;

import co.avillega.entities.AppUser;
import co.avillega.entities.Routine;
import co.avillega.services.RoutineService;
import co.avillega.services.UserService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;


@UIScope
public class RutinasPanel extends VerticalLayout implements View {

    private final UserService userService;

    private final RoutineService routineService;
    private String currentRoutine = "";
    private Accordion accordion = new Accordion();


    public RutinasPanel(RoutineService routineService, UserService userService) {

        setDefaultComponentAlignment(Alignment.TOP_LEFT);
        Label lbRutinas = new Label("Rutinas");
        lbRutinas.addStyleName(ValoTheme.LABEL_H1);
        this.addComponents(lbRutinas, accordion);

        this.routineService = routineService;
        this.userService = userService;

        setRoutines();
        setAddDeleteButtons();
    }


    private void setRoutines() {
        currentRoutine = "";
        accordion.removeAllComponents();

        AppUser user = (AppUser) VaadinSession.getCurrent().getAttribute("user");
        List<Routine> routines = routineService.getRoutinesByUserName(user.getUserName());

        accordion.addTab(new HorizontalLayout(), "Cerrar todas");
        routines.forEach(routine -> accordion.addTab(new RutinaLayout(routine, this), routine.getName()));

        accordion.addSelectedTabChangeListener(event -> {
            if (event.getTabSheet().getSelectedTab() instanceof RutinaLayout) {
                currentRoutine = ((RutinaLayout) event.getTabSheet().getSelectedTab()).getRoutineId();
            } else {
                currentRoutine = "";
            }
        });

    }

    private void setAddDeleteButtons() {
        HorizontalLayout buttons = new HorizontalLayout();

        buttons.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Button add = new Button("Agregar Rutina", VaadinIcons.PLUS);
        Button delete = new Button("Borrar rutina seleccionada", VaadinIcons.TRASH);

        add.addStyleName(ValoTheme.BUTTON_PRIMARY);
        add.addClickListener(this::addRoutineButton);

        delete.addClickListener(event -> {
            if (currentRoutine.isEmpty()) {
                Notification.show("Select routine",
                        "Seleccione una rutina para eliminar",
                        Notification.Type.WARNING_MESSAGE);
            } else {
                routineService.deleteRoutine(currentRoutine);
                currentRoutine = "";
                setRoutines();
            }

        });

        buttons.addComponents(add, delete);
        this.addComponent(buttons);

    }

    private void addRoutineButton(Object event) {
        Window window = new AddRoutineWindow(this);
        UI.getCurrent().addWindow(window);
    }

    public void addRoutine(Routine routine, boolean keepOpen) {
        routineService.addRoutine(routine);

    }

    public void addRoutine(Routine routine) {
        routineService.addRoutine(routine);
        setRoutines();
    }

    public void executeRoutine(Routine routine) {
        routineService.startRoutine(routine.getId());
    }

    public void stopBtn() {
        routineService.emergencyStop();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setRoutines();

    }


}

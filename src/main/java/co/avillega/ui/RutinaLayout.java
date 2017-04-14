package co.avillega.ui;

import co.avillega.entities.Command;
import co.avillega.entities.Routine;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;


@UIScope
public class RutinaLayout extends VerticalLayout {



    private Routine routine;
    private RutinasPanel panel;
    private VerticalLayout commandsLayout = new VerticalLayout();

    public RutinaLayout(Routine routine, RutinasPanel panel) {
        this.panel = panel;
        setDefaultComponentAlignment(Alignment.TOP_LEFT);
        this.routine = routine;
        setActionButtons();
        setCommands();
        this.addComponent(commandsLayout);
    }


    public String getRoutineId() {
        return routine.getId();
    }


    private void setActionButtons() {
        HorizontalLayout buttonsLayout = new HorizontalLayout();

        Button startBtn = new Button("Iniciar Rutina");
        startBtn.addClickListener(e -> panel.executeRoutine(routine));

        startBtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        Button stopBtn = new Button("Parar Rutina");
        stopBtn.addClickListener(e -> panel.stopBtn());
        stopBtn.addStyleName(ValoTheme.BUTTON_DANGER);

        Button descriptionBtn = new Button("Descripción");
        descriptionBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        descriptionBtn.addClickListener(event -> {
            Notification notif = new Notification("Descripción", routine.getDescription(), Notification.Type.HUMANIZED_MESSAGE);
            notif.setDelayMsec(Notification.DELAY_FOREVER);
            notif.setPosition(Position.MIDDLE_CENTER);
            notif.show(Page.getCurrent());
        });

        buttonsLayout.addComponentsAndExpand(startBtn, stopBtn, descriptionBtn);
        addComponent(buttonsLayout);

    }


    private void setCommands() {
        commandsLayout.removeAllComponents();
        commandsLayout.setDefaultComponentAlignment(Alignment.TOP_LEFT);

        List<Command> commands = routine.getCommands();
        if (commands.size() == 0) {
            commandsLayout.addComponent(new Label("No tienes comandos en esta rutina. Agrega algunos!"));
        }

        for (int i = 0; i < commands.size(); i++) {
            Command command = commands.get(i);
            commandsLayout.addComponent(createCommandLayout(command, i));
        }

        commandsLayout.addComponent(addCommandLast());

    }

    private HorizontalLayout addCommandLast() {
        HorizontalLayout horizontal = new HorizontalLayout();
        horizontal.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        horizontal.addComponentsAndExpand(new Label(""));

        Button addBtn = new Button("Agregar Comando");
        addBtn.addClickListener(e -> addCommandButton(routine.getCommands().size()));
        addBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        horizontal.addComponent(addBtn);

        return horizontal;
    }

    private HorizontalLayout createCommandLayout(Command command, int pos) {
        HorizontalLayout horizontal = new HorizontalLayout();
        horizontal.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        horizontal.addComponentsAndExpand(new Label(command.pretty()));

        Button addBtn = new Button("Agregar");
        addBtn.addClickListener(e -> addCommandButton(pos));
        addBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        horizontal.addComponent(addBtn);

        Button del = new Button(VaadinIcons.TRASH);
        del.addClickListener(event -> {
            routine.getCommands().remove(command);
            panel.addRoutine(routine, true);
            setCommands();
        });
        del.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        horizontal.addComponent(del);


        return horizontal;
    }


    private void addCommandButton(int pos) {
        Window addCommandWindow = new AddCommandWindow(this, pos);
        UI.getCurrent().addWindow(addCommandWindow);

    }

    public void addCommand(int pos, Command command) {
        routine.getCommands().add(pos, command);
        panel.addRoutine(routine, true);
        setCommands();
    }


}

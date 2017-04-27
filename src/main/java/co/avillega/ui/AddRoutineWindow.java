package co.avillega.ui;


import co.avillega.entities.AppUser;
import co.avillega.entities.Routine;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;


class AddRoutineWindow extends Window {

    AddRoutineWindow(RutinasPanel panel) {
        super("Agregar Rutina");

        VerticalLayout form = new VerticalLayout();


        TextField nameTxt = new TextField("Nombre");
        nameTxt.setSizeFull();
        form.addComponent(nameTxt);

        TextArea descArea = new TextArea("Descripción");
        descArea.setSizeFull();
        form.addComponent(descArea);

        HorizontalLayout btns = new HorizontalLayout();

        Button addBtn = new Button("Agregar");
        addBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        addBtn.addClickListener(event -> {
            if (nameTxt.isEmpty()) {
                Notification.show("Atención", "El nombre no puede estar vacio", Notification.Type.WARNING_MESSAGE);
            } else {
                AppUser user = (AppUser) VaadinSession.getCurrent().getAttribute("user");

                panel.addRoutine(new Routine(descArea.getValue(), nameTxt.getValue(), user));
                this.close();
            }
        });
        btns.addComponent(addBtn);

        Button cancelBtn = new Button("Cancelar");
        cancelBtn.addClickListener(clickEvent -> this.close());
        btns.addComponent(cancelBtn);
        form.addComponent(btns);

        this.setContent(form);
        this.setResizable(false);
        this.setModal(true);
        this.setWidth("50%");

    }
}

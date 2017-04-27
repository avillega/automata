package co.avillega.ui;


import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


class LogInWindow extends Window {

    LogInWindow(AuxPanel auxPanel) {
        super("Log In");

        VerticalLayout form = new VerticalLayout();
        form.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);


        TextField nameTxt = new TextField("Nombre de usuario");

        form.addComponent(nameTxt);

        PasswordField passwordField = new PasswordField("Contraseña");

        form.addComponent(passwordField);

        HorizontalLayout btns = new HorizontalLayout();

        Button logginBtn = new Button("Log In");
        logginBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        logginBtn.addClickListener(event -> {
            if (nameTxt.isEmpty() || passwordField.isEmpty()) {
                Notification.show("Atención", "El nombre o contraseña no pueden estar vacios", Notification.Type.WARNING_MESSAGE);
                return;
            }
            auxPanel.authenticate(nameTxt.getValue(), passwordField.getValue());
            this.close();

        });
        logginBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btns.addComponent(logginBtn);

        Button cancelBtn = new Button("Cancelar");
        cancelBtn.addClickListener(clickEvent -> this.close());
        btns.addComponent(cancelBtn);
        form.addComponent(btns);

        this.setContent(form);
        this.setResizable(false);
        this.setModal(true);
        this.setWidth("30%");

    }
}

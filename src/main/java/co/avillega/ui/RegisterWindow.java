package co.avillega.ui;


import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


class RegisterWindow extends Window {

    RegisterWindow(AuxPanel auxPanel) {
        super("Registro");

        VerticalLayout form = new VerticalLayout();
        form.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);


        TextField nameTxt = new TextField("Nombre de usuario");

        form.addComponent(nameTxt);

        PasswordField passwordField = new PasswordField("Contraseña");
        PasswordField repetirField = new PasswordField("Repeitir Contraseña");

        form.addComponents(passwordField, repetirField);

        HorizontalLayout btns = new HorizontalLayout();

        Button logginBtn = new Button("Log In");
        logginBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        logginBtn.addClickListener(event -> {
            if (nameTxt.isEmpty() || passwordField.isEmpty() || repetirField.isEmpty()) {
                Notification.show("Atención", "El nombre o contraseña no pueden estar vacios", Notification.Type.WARNING_MESSAGE);
                return;
            } else if (!passwordField.getValue().equals(repetirField.getValue())) {
                Notification.show("Atención", "Las contraseñas no coinciden", Notification.Type.WARNING_MESSAGE);
                return;
            }
            auxPanel.registerUser(nameTxt.getValue(), passwordField.getValue());
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

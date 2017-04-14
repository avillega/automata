package co.avillega.ui;


import co.avillega.entities.Routine;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;


class AddRoutineWindow extends Window {

    AddRoutineWindow(RutinasPanel panel) {
        super("Add Routine");

        VerticalLayout form = new VerticalLayout();


        TextField nameTxt = new TextField("Name");
        nameTxt.setSizeFull();
        form.addComponent(nameTxt);

        TextArea descArea = new TextArea("Description");
        descArea.setSizeFull();
        form.addComponent(descArea);

        HorizontalLayout btns = new HorizontalLayout();

        Button addBtn = new Button("Add");
        addBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        addBtn.addClickListener(event -> {
            if (nameTxt.isEmpty()) {
                Notification.show("Warning", "Name must not be empty", Notification.Type.WARNING_MESSAGE);
            } else {
                panel.addRoutine(new Routine(nameTxt.getValue(), descArea.getValue()));
                this.close();
            }
        });
        btns.addComponent(addBtn);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.addClickListener(clickEvent -> this.close());
        btns.addComponent(cancelBtn);
        form.addComponent(btns);

        this.setContent(form);
        this.setResizable(false);
        this.setModal(true);
        this.setWidth("50%");

    }
}

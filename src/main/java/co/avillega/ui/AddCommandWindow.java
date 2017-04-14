package co.avillega.ui;

import co.avillega.entities.Command;
import co.avillega.entities.Instruction;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Arrays;

class AddCommandWindow extends Window {

    private Instruction instruction;
    private RutinaLayout parent;
    private int pos;
    private TextField parameter;


    AddCommandWindow(RutinaLayout parent, int pos) {
        super("Agregar Comando");
        this.parent = parent;
        this.pos = pos;
        VerticalLayout form = new VerticalLayout();


        form.addComponent(this.creteCommandSelection());

        HorizontalLayout btns = new HorizontalLayout();

        Button addBtn = new Button("Agregar");
        addBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        addBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        addBtn.addClickListener(event -> {
            Command newCommand;
            if (parameter.isVisible()) {
                try {
                    long param = Long.parseLong(parameter.getValue());
                    newCommand = new Command(instruction.name(), param);

                } catch (Exception e) {
                    Notification.show("ERROR", "El parametro debe ser u numero valido", Notification.Type.ERROR_MESSAGE);
                    return;
                }
            } else {
                newCommand = new Command(instruction.name(), 0);

            }
            this.parent.addCommand(pos, newCommand);
            this.close();
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

    private HorizontalLayout creteCommandSelection() {
        HorizontalLayout commandSel = new HorizontalLayout();
        instruction = Instruction.STOP;
        parameter = new TextField("Parametro");
        parameter.setRequiredIndicatorVisible(true);
        parameter.setVisible(false);
        Instruction[] data = Arrays.copyOf(Instruction.values(), Instruction.values().length - 1);

        NativeSelect<Instruction> instructions = new NativeSelect<>("Seleccionar comando");

        instructions.setItems(data);
        instructions.setItemCaptionGenerator(Instruction::toString);
        instructions.setEmptySelectionAllowed(false);
        instructions.setSelectedItem(Instruction.STOP);
        instructions.addSelectionListener(event -> {
            Instruction actual = event.getValue();
            if (actual == Instruction.SPEED) {
                parameter.clear();
                parameter.setVisible(true);
                parameter.setPlaceholder("m/s");


            } else if (actual == Instruction.WAIT) {
                parameter.clear();
                parameter.setVisible(true);
                parameter.setPlaceholder("segundos");
            } else {
                parameter.setVisible(false);
            }
            instruction = actual;

        });

        commandSel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        commandSel.addComponents(instructions);
        commandSel.addComponents(parameter);
        return commandSel;
    }


}

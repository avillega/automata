package co.avillega.ui;

import co.avillega.entities.Instruction;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Arrays;

/**
 * Created by Andres Villegas on 2017-04-10.
 */
public class AddCommandWindow extends Window {

    private Instruction instruction;


    AddCommandWindow() {
        super("Agregar Comando");

        VerticalLayout form = new VerticalLayout();


        form.addComponent(this.creteCommandSelection());

        HorizontalLayout btns = new HorizontalLayout();

        Button addBtn = new Button("Agregar");
        addBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        addBtn.addClickListener(event -> {

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

    public HorizontalLayout creteCommandSelection() {
        HorizontalLayout commandSel = new HorizontalLayout();
        TextField parameter = new TextField("Parametro");
        parameter.setRequiredIndicatorVisible(true);
        parameter.setVisible(false);
        Instruction[] data = Arrays.copyOf(Instruction.values(), Instruction.values().length - 1);

        NativeSelect<Instruction> instructions = new NativeSelect<>("Seleccionar comando");

        instructions.setItems(data);
        instructions.setItemCaptionGenerator(caption -> caption.toString());
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

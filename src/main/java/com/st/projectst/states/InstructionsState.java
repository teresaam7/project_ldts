package com.st.projectst.states;

import com.st.projectst.controller.Controller;
import com.st.projectst.controller.menu.InstructionsController;
import com.st.projectst.model.menu.Instructions;
import com.st.projectst.viewer.Viewer;
import com.st.projectst.viewer.menu.InstructionsViewer;

public class InstructionsState extends State<Instructions>{
    public InstructionsState(Instructions instructions) {
        super(instructions);
    }

    @Override
    protected Controller<Instructions> getController() {
        return new InstructionsController(getModel());
    }
    @Override
    protected Viewer<Instructions> getViewer() {
        return new InstructionsViewer(getModel());
    }
}

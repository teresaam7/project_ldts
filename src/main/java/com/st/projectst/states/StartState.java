package com.st.projectst.states;

import com.st.projectst.controller.Controller;
import com.st.projectst.controller.menu.StartController;
import com.st.projectst.model.menu.Start;
import com.st.projectst.viewer.Viewer;
import com.st.projectst.viewer.menu.StartViewer;

public class StartState extends State<Start> {
    public StartState(Start start) {
        super(start);
    }

    @Override
    protected Controller<Start> getController() {
        return new StartController(getModel());
    }
    @Override
    protected Viewer<Start> getViewer() {
        return new StartViewer(getModel());
    }
}

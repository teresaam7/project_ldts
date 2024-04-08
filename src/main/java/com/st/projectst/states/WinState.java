package com.st.projectst.states;

import com.st.projectst.controller.Controller;
import com.st.projectst.controller.menu.WinController;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.menu.Win;
import com.st.projectst.viewer.Viewer;
import com.st.projectst.viewer.menu.WinViewer;

public class WinState extends State<Win>{
    public WinState(Win win) {
        super(win);
    }

    @Override
    protected Controller<Win> getController() {
        return new WinController(getModel());
    }
    @Override
    protected Viewer<Win> getViewer() {
        return new WinViewer(getModel());
    }
}

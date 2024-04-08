package com.st.projectst.states;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.viewer.Viewer;
import com.st.projectst.gui.GUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract Controller<T> getController();
    protected abstract Viewer<T> getViewer();
    public T getModel() {
        return model;
    }

    public void step(Main main, GUI gui, long time) throws IOException, FontFormatException, URISyntaxException {
        GUI.ACTION action = gui.getNextAction();
        controller.step(main, action, time);
        viewer.draw(gui);
    }
}

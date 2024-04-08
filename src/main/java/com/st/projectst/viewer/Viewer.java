package com.st.projectst.viewer;

import com.st.projectst.gui.GUI;

import java.awt.*;
import java.io.IOException;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) { this.model = model; }

    public T getModel() { return model; }

    public void draw(GUI gui) throws IOException, FontFormatException {
        gui.clear();
        drawObject(gui);
        gui.refresh();
    }

    protected abstract void drawObject (GUI gui) throws IOException, FontFormatException;
}

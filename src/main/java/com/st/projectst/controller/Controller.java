package com.st.projectst.controller;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Main main, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException;
}

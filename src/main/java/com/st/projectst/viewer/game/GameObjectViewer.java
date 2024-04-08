package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.GameObject;

import java.awt.*;
import java.io.IOException;

public interface GameObjectViewer<T extends GameObject> {
    void draw(T gameObject, GUI gui) throws IOException, FontFormatException;
}
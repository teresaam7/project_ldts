package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Key;

public class KeyViewer implements GameObjectViewer<Key> {
    @Override
    public void draw(Key key, GUI gui) {
        gui.drawKey(key.getPosition());
    }
}
package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Door;
import com.st.projectst.model.game.Key;
import com.st.projectst.model.menu.GameOver;
import com.st.projectst.model.menu.Pause;
import com.st.projectst.viewer.Viewer;

public class DoorViewer implements GameObjectViewer<Door> {
    @Override
    public void draw(Door door, GUI gui) {
        gui.drawDoor(door.getPosition());
    }
}

package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Wall;


public class WallViewer implements GameObjectViewer<Wall> {
    @Override
    public void draw(Wall wall, GUI gui) {
        gui.drawWall(wall.getPosition());
    }
}
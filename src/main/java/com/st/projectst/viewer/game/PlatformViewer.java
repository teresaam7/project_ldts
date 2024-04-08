package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Platform;
import com.st.projectst.model.game.Wall;

public class PlatformViewer implements GameObjectViewer<Platform> {
    @Override
    public void draw(Platform platform, GUI gui) {
        for (Wall wall : platform.getConnectedPlatforms()) {
            gui.drawWall(wall.getPosition());
        }
    }
}

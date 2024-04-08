package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.GhostEnemy;

public class GhostEnemyViewer implements GameObjectViewer<GhostEnemy> {
    @Override
    public void draw(GhostEnemy ghost, GUI gui) {
        gui.drawGhostEnemy(ghost.getPosition());
    }
}
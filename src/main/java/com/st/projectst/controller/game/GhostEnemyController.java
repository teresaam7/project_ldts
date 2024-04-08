package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.GhostEnemy;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.Position;

import java.io.IOException;

public class GhostEnemyController extends LevelController{
    private long lastMove;

    public GhostEnemyController(Map map) {
        super(map);
        this.lastMove = 0;
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {
        if ((time - lastMove) > 300) {
            for (GhostEnemy enemy : getModel().getGhostEnemies())  {
                Position newPosition = new Position(enemy.move());
                if (getModel().isEmpty(newPosition))
                    enemy.setPosition(newPosition);
            }
            this.lastMove = time;
        }
    }

    public void setLastMove(long lastMove) {
        this.lastMove = lastMove;
    }
}

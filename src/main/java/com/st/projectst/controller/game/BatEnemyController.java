package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.BatEnemy;
import com.st.projectst.model.game.Map;

import java.io.IOException;

public class BatEnemyController extends LevelController {
    private long lastMove;

    public BatEnemyController(Map map) {
        super(map);
        this.lastMove = 0;
    }

    public void setLastMove(long lastMove) { this.lastMove = lastMove; }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {
        if (time - lastMove > 150) {
            for (BatEnemy enemy : getModel().getBatEnemies())
                enemy.setPosition(enemy.move());
            this.lastMove = time;
        }
    }
}

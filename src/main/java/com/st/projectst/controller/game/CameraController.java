package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CameraController extends LevelController {
    public CameraController(Map map) {
        super(map);
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {
        Map map = getModel();

        for (GhostEnemy ghostEnemy : map.getGhostEnemies()) {
            Position enemyScreenPosition = new Position(ghostEnemy.getPosition().getX() - 100,
                                                                ghostEnemy.getPosition().getY());
            ghostEnemy.setPosition(enemyScreenPosition);
        }
        for (BatEnemy batEnemy : map.getBatEnemies()) {
            Position enemyScreenPosition = new Position(batEnemy.getPosition().getX() - 100,
                                                                batEnemy.getPosition().getY());
            batEnemy.setPosition(enemyScreenPosition);
        }
        for (Wall wall : map.getWalls()) {
            Position wallScreenPosition = new Position(wall.getPosition().getX() - 100,
                                                                wall.getPosition().getY());
            wall.setPosition(wallScreenPosition);
        }
        for (Platform platform: map.getPlatforms()){
            Position platformScreenPosition = new Position(platform.getPosition().getX() - 100,
                                                                    platform.getPosition().getY());
            platform.setPosition(platformScreenPosition);
        }
        for (Trap trap : map.getTraps()){
            Position trapScreenPosition = new Position(trap.getPosition().getX() - 100,
                                                                    trap.getPosition().getY());
            trap.setPosition(trapScreenPosition);
        }
        for (Potion potion : map.getPotions()){
            Position potionScreenPosition = new Position(potion.getPosition().getX() - 100,
                                                                    potion.getPosition().getY());
            potion.setPosition(potionScreenPosition);
        }
        if (map.getKey() != null) {
            Key key = map.getKey();
            Position keyScreenPosition = new Position(key.getPosition().getX() - 100,
                                                            key.getPosition().getY());
            key.setPosition(keyScreenPosition);
        }
        Door door = map.getDoor();
        Position doorScreenPosition = new Position(door.getPosition().getX() - 100,
                                                            door.getPosition().getY());
        door.setPosition(doorScreenPosition);

        Mari mari = map.getMari();
        Position mariScreenPosition = new Position(mari.getPosition().getX() - 100,
                                                                mari.getPosition().getY());
        mari.setPosition(mariScreenPosition);
    }
}


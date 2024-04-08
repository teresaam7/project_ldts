package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.GameObject;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.Platform;
import com.st.projectst.model.game.Wall;
import com.st.projectst.viewer.Viewer;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class LevelViewer extends Viewer<Map> {
    public LevelViewer(Map model) {
        super(model);
    }

    @Override
    public void drawObject(GUI gui) throws IOException, FontFormatException {

        gui.setBackgroundColor("#432121");
        if (getModel().getMari().getRemainingLives() == 3){
            gui.drawImage(new Position(1, -2), "gameObjects/life3.png", 1);
        }
        else if (getModel().getMari().getRemainingLives() == 2){
            gui.drawImage(new Position(1, -2), "gameObjects/life2.png", 1);
        }
        else if (getModel().getMari().getRemainingLives() == 1){
            gui.drawImage(new Position(1, -2), "gameObjects/life1.png", 1);
        }

        if (getModel().getMari().getIsWithPotion()){
            gui.drawText(new Position(2, 58), "Powered jumps:" + getModel().getMari().getRemainingJumps(), "#FFFFFF");
        }
        drawGameObjects(gui, getModel().getWalls(), new WallViewer());
        drawGameObjects(gui, getModel().getPlatforms(), new PlatformViewer());
        drawGameObjects(gui, getModel().getTraps(), new TrapViewer());
        drawGameObject(gui, getModel().getDoor(), new DoorViewer());
        drawGameObject(gui, getModel().getMari(), new MariViewer());
        drawGameObjects(gui, getModel().getBatEnemies(), new BatEnemyViewer());
        drawGameObjects(gui, getModel().getGhostEnemies(), new GhostEnemyViewer());
        drawGameObjects(gui, getModel().getPotions(), new PotionViewer());


        if (getModel().getKey() != null)
            drawGameObject(gui, getModel().getKey(), new KeyViewer());
    }

    private <T extends GameObject> void drawGameObjects(GUI gui, List<T> gameObjects, GameObjectViewer<T> viewer) throws IOException, FontFormatException {
        for (T object : gameObjects)
            drawGameObject(gui, object, viewer);
    }

    private <T extends GameObject> void drawGameObject(GUI gui, T element, GameObjectViewer<T> viewer) throws IOException, FontFormatException {
        viewer.draw(element, gui);
    }
}

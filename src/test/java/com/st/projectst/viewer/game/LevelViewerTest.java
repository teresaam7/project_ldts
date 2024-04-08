package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class LevelViewerTest {
    private Map map;
    private LevelViewer levelViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        map = new Map(320, 60, 1);
        gui = Mockito.mock(GUI.class);

        map.setMari(new Mari(new Position(10,10)));
        map.setKey(new Key(new Position(15,10)));
        map.setDoor(new Door(new Position(40,10)));
        map.setWalls(Arrays.asList(new Wall(new Position(0,0))));
        map.setTraps(Arrays.asList(new Trap(new Position(20,20))));
        map.setBatEnemies(Arrays.asList(new BatEnemy(new Position(20,5))));
        map.setPotions(Arrays.asList(new Potion(new Position(25,25))));
        map.setPlatforms(Arrays.asList(new Platform(new Position(39,45)), new Platform(new Position(40,45))));
        map.setGhostEnemies(Arrays.asList(new GhostEnemy(new Position(25,10))));

        levelViewer = new LevelViewer(map);
    }

    @Test
    void testDrawMari() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawMari(new Position(10,10));

    }

    @Test
    void testDrawMariJump() throws IOException, FontFormatException {
        map.getMari().setWithPotion(false);
        map.getMari().setJumping(true);
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawMariJump(new Position(10,10));

        map.getMari().setWithPotion(true);
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawMariDoubleJump(new Position(10,10));
    }


    @Test
    void testDrawKey() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawKey(new Position(15,10));
    }

    @Test
    void testDrawDoor() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawDoor(new Position(40,10));
    }

    @Test
    void testDrawWalls() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawWall(new Position(0,0));
    }

    @Test
    void testDrawPotions() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawPotion(new Position(25,25));
    }

    @Test
    void testDrawPlatforms() throws IOException, FontFormatException {
        for (Platform platform : map.getPlatforms())
            for (Platform otherPlatform : map.getPlatforms())
                if (platform != otherPlatform && platform.isOnSameLevel(otherPlatform))
                    platform.addConnectedPlatform(otherPlatform);

        levelViewer.drawObject(gui);
        for (Platform platform : map.getPlatforms()) {
            if (platform.getPosition().equals(new Position(39,45)))
                Mockito.verify(gui, times(1)).drawWall(new Position(40,45));
            if (platform.getPosition().equals(new Position(40,45)))
                Mockito.verify(gui, times(1)).drawWall(new Position(39,45));
        }
    }

    @Test
    void testDrawTraps() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawTrap(new Position(20,20));
    }

    @Test
    void testDrawBatEnemies() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawBatEnemy(new Position(20,5));
    }

    @Test
    void testDrawGhostEnemies() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawGhostEnemy(new Position(25,10));
    }

    @Test
    void testDrawLives() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawImage(new Position(1, -2), "gameObjects/life3.png", 1);

        map.getMari().decreaseLives();
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawImage(new Position(1, -2), "gameObjects/life2.png", 1);

        map.getMari().decreaseLives();
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawImage(new Position(1, -2), "gameObjects/life1.png", 1);
    }

    @Test
    void testDrawPowerAction() throws IOException, FontFormatException {
        map.getMari().setWithPotion(true);
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).drawText(new Position(2, 58), "Powered jumps:" + map.getMari().getRemainingJumps(), "#FFFFFF");
    }

    @Test
    void testDrawBackground() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, times(1)).setBackgroundColor("#432121");
    }
}

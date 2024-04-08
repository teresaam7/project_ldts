package com.st.projectst.controller.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.*;
import com.st.projectst.model.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CameraControllerTest {
    private CameraController controller;
    private Map map;

    @BeforeEach
    void setUp() {
        map = mock(Map.class);
        controller = new CameraController(map);
    }

    @Test
    void testStep() throws IOException {
        GhostEnemy ghostEnemy = new GhostEnemy(new Position(100, 100));
        BatEnemy batEnemy = new BatEnemy(new Position(200, 200));
        Wall wall = new Wall(new Position(150, 150));
        Trap trap = new Trap(new Position(250, 250));
        Key key = new Key(new Position(320, 300));
        Door door = new Door(new Position(350, 350));
        Platform platform = new Platform(new Position(120, 130));
        Potion potion = new Potion(new Position(210, 210));
        Mari mari = new Mari(new Position(400, 400));

        when(map.getGhostEnemies()).thenReturn(Arrays.asList(ghostEnemy));
        when(map.getBatEnemies()).thenReturn(Arrays.asList(batEnemy));
        when(map.getWalls()).thenReturn(Arrays.asList(wall));
        when(map.getTraps()).thenReturn(Arrays.asList(trap));
        when(map.getPotions()).thenReturn(Arrays.asList(potion));
        when(map.getPlatforms()).thenReturn(Arrays.asList(platform));
        when(map.getKey()).thenReturn(key);
        when(map.getDoor()).thenReturn(door);
        when(map.getMari()).thenReturn(mari);

        controller.step(null, GUI.ACTION.NONE, 1000);

        assertEquals(new Position(0, 100), ghostEnemy.getPosition());
        assertEquals(new Position(100, 200), batEnemy.getPosition());
        assertEquals(new Position(50, 150), wall.getPosition());
        assertEquals(new Position(150, 250), trap.getPosition());
        assertEquals(new Position(110, 210), potion.getPosition());
        assertEquals(new Position(20, 130), platform.getPosition());
        assertEquals(new Position(220, 300), key.getPosition());
        assertEquals(new Position(250, 350), door.getPosition());
        assertEquals(new Position(300, 400), mari.getPosition());
    }
}
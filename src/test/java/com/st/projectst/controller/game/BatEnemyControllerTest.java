package com.st.projectst.controller.game;

import com.st.projectst.model.game.Mari;
import com.st.projectst.model.game.Trap;
import org.junit.jupiter.api.BeforeEach;
import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.BatEnemy;
import com.st.projectst.model.game.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BatEnemyControllerTest {
    private BatEnemyController controller;
    private Map map;
    private Main main;

    @BeforeEach
    void setUp() {
        map = mock(Map.class);
        controller = new BatEnemyController(map);
        main = mock(Main.class);
    }

    @Test
    void testStepNoMove() throws IOException {
        BatEnemy batEnemy = new BatEnemy(new Position(5, 5));
        when(map.getBatEnemies()).thenReturn(Arrays.asList(batEnemy));

        controller.step(main, GUI.ACTION.NONE, 100);

        assertEquals(new Position(5, 5), batEnemy.getPosition());
    }

    @Test
    void testStepTimeDelay() throws IOException {
        BatEnemy batEnemy = new BatEnemy(new Position(5, 5));
        when(map.getBatEnemies()).thenReturn(Arrays.asList(batEnemy));

        controller.step(main, GUI.ACTION.NONE, 200);
        Position firstPosition = batEnemy.getPosition();

        controller.step(main, GUI.ACTION.NONE, 500);
        Position secondPosition = batEnemy.getPosition();

        assertEquals(firstPosition, secondPosition);
    }

    @Test
    void testStepMovement() throws IOException {
        BatEnemy batEnemy = new BatEnemy(new Position(5, 5));
        batEnemy.setFinalPosition(new Position(5,8));
        List<BatEnemy> batEnemies = Arrays.asList(batEnemy);
        when(map.getBatEnemies()).thenReturn(batEnemies);

        controller.step(main, GUI.ACTION.NONE, 150);
        for (BatEnemy bat: batEnemies)
            assertEquals(new Position(5, 5), bat.getPosition());

        controller.step(main, GUI.ACTION.NONE, 151);
        for (BatEnemy bat: batEnemies)
            assertEquals(new Position(5, 8), bat.getPosition());

        controller.step(main, GUI.ACTION.NONE, 200);
        for (BatEnemy bat: batEnemies)
            assertEquals(new Position(5, 8), bat.getPosition());
    }

    @Test
    void testStepMovement2() throws IOException {
        BatEnemy batEnemy = new BatEnemy(new Position(5, 5));
        batEnemy.setFinalPosition(new Position(5,8));
        List<BatEnemy> batEnemies = Arrays.asList(batEnemy);
        when(map.getBatEnemies()).thenReturn(batEnemies);

        controller.setLastMove(50);
        controller.step(main, GUI.ACTION.NONE, 200);
        for (BatEnemy bat: batEnemies)
            assertEquals(new Position(5, 5), bat.getPosition());

        controller.step(main, GUI.ACTION.NONE, 201);
        for (BatEnemy bat: batEnemies)
            assertEquals(new Position(5, 8), bat.getPosition());
    }
}



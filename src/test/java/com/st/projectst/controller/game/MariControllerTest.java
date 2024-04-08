package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MariControllerTest {
    private Map map;
    private MariController mariController;
    private Mari mari;
    private Main main;

    @BeforeEach
    public void setup() {
        map = new Map(320, 60, 1);
        Position position = new Position(10, 10);
        mari = new Mari(position);
        map.setMari(mari);
        map.setKey(new Key(new Position(1,1)));
        map.setTraps(Arrays.asList());
        map.setBatEnemies(Arrays.asList());
        map.setGhostEnemies(Arrays.asList());
        map.setWalls(Arrays.asList());
        map.setPlatforms(Arrays.asList());
        map.setPotions(Arrays.asList());
        mariController = new MariController(map);
        main = mock(Main.class);
    }

    @Test
    public void testMoveMariRight() {
        Position initialPosition = mari.getPosition();
        mariController.moveMariRight();
        Position newPosition = mari.getPosition();

        assertEquals(initialPosition.getX() + 1, newPosition.getX());
        assertEquals(initialPosition.getY(), newPosition.getY());
    }

    @Test
    public void testMoveMariLeft() {
        Position initialPosition = mari.getPosition();
        mariController.moveMariLeft();
        Position newPosition = mari.getPosition();

        assertEquals(initialPosition.getX() - 1, newPosition.getX());
        assertEquals(initialPosition.getY(), newPosition.getY());
    }

    @Test
    public void testMoveMariUp() {
        Mari mariMock = mock(Mari.class);
        map.setMari(mariMock);
        MariController mariController2 = new MariController(map);

        when(mariController2.getModel().getMari().getPosition()).thenReturn(new Position(10,10));
        mariController2.moveMariUp();
        verify(mariController2.getModel().getMari(), times(1)).jump();
    }

    @Test
    public void testMoveMariWhenEmpty() {
        Position newPosition = new Position(15, 15);
        mariController.moveMari(newPosition);
        assertEquals(newPosition, mariController.getModel().getMari().getPosition());
    }

    @Test
    public void testMoveMariWhenEmpty2() {
        Position initialPosition = map.getMari().getPosition();
        Position newPosition = new Position(-1, 15);
        mariController.moveMari(newPosition);
        assertEquals(initialPosition, mariController.getModel().getMari().getPosition());

        Position newPosition2 = new Position(0, 15);
        mariController.moveMari(newPosition2);
        assertEquals(newPosition2, mariController.getModel().getMari().getPosition());
        assertEquals(false, mariController.getModel().getMari().getWithKey());
    }

    @Test
    public void testMoveMariWhenNotEmpty() {
        Position newPosition = new Position(20, 20);
        Wall wall = new Wall(newPosition);
        map.setWalls(List.of(wall));
        mariController.moveMari(newPosition);
        assertNotEquals(newPosition, mariController.getModel().getMari().getPosition());
    }

    @Test
    public void testMoveRemoveKey() {
        Position keyPosition = new Position(10, 17);
        Key key = new Key(keyPosition);
        mariController.getModel().setKey(key);

        Position mariPosition = new Position(10, 10);
        mariController.moveMari(mariPosition);

        assertEquals(null, mariController.getModel().getKey());
    }

    @Test
    public void testMoveMariTopHead() {
        Position mariPosition = new Position(40, 40);

        for (int x = 1; x <= 11; x++) {
            Position wallPosition = new Position(40+x, 40);
            Wall wall = new Wall(wallPosition);
            mariController.getModel().setWalls(List.of(wall));

            Position initialPosition = mariController.getModel().getMari().getPosition();
            mariController.moveMari(mariPosition);
            assertEquals(initialPosition, mariController.getModel().getMari().getPosition());
        }

        Position wallPosition = new Position(40+12, 40);
        Wall wall = new Wall(wallPosition);
        mariController.getModel().setWalls(List.of(wall));

        Position initialPosition = mariController.getModel().getMari().getPosition();
        mariController.moveMari(mariPosition);
        assertNotEquals(initialPosition, mariController.getModel().getMari().getPosition());
    }

    @Test
    public void testMoveMariSidesHead() {
        Position mariPosition = new Position(40, 40);

        for (int y = 0; y <= 10; y++) {
            Position wallPosition = new Position(40, 40+y);
            Wall wall = new Wall(wallPosition);
            mariController.getModel().setWalls(List.of(wall));

            Position initialPosition = mariController.getModel().getMari().getPosition();
            mariController.moveMari(mariPosition);
            assertEquals(initialPosition, mariController.getModel().getMari().getPosition());
        }

        Position wallPosition = new Position(40, 40+11);
        Wall wall = new Wall(wallPosition);
        mariController.getModel().setWalls(List.of(wall));

        Position initialPosition = mariController.getModel().getMari().getPosition();
        mariController.moveMari(mariPosition);
        assertNotEquals(initialPosition, mariController.getModel().getMari().getPosition());
    }

    @Test
    public void testMoveMariSidesHead2() {
        Position mariPosition = new Position(40, 40);

        for (int y = 0; y <= 10; y++) {
            Position wallPosition = new Position(40+11, 40+y);
            Wall wall = new Wall(wallPosition);
            mariController.getModel().setWalls(List.of(wall));

            Position initialPosition = mariController.getModel().getMari().getPosition();
            mariController.moveMari(mariPosition);
            assertEquals(initialPosition, mariController.getModel().getMari().getPosition());
        }

        Position wallPosition = new Position(40+11, 40+11);
        Wall wall = new Wall(wallPosition);
        mariController.getModel().setWalls(List.of(wall));

        Position initialPosition = mariController.getModel().getMari().getPosition();
        mariController.moveMari(mariPosition);
        assertNotEquals(initialPosition, mariController.getModel().getMari().getPosition());
    }

    @Test
    public void testMoveMariSides() {
        Position mariPosition = new Position(40, 40);

        for (int y = 11; y <= 13; y++) {
            Position wallPosition = new Position(43, 40+y);
            Wall wall = new Wall(wallPosition);
            mariController.getModel().setWalls(List.of(wall));

            Position initialPosition = mariController.getModel().getMari().getPosition();
            mariController.moveMari(mariPosition);
            assertEquals(initialPosition, mariController.getModel().getMari().getPosition());
        }

        Position wallPosition = new Position(43, 40+14);
        Wall wall = new Wall(wallPosition);
        mariController.getModel().setWalls(List.of(wall));

        Position initialPosition = mariController.getModel().getMari().getPosition();
        mariController.moveMari(mariPosition);
        assertNotEquals(initialPosition, mariController.getModel().getMari().getPosition());
    }

    @Test
    public void testMoveMariSides2() {
        Position mariPosition = new Position(40, 40);

        for (int y = 11; y <= 13; y++) {
            Position wallPosition = new Position(48, 40+y);
            Wall wall = new Wall(wallPosition);
            mariController.getModel().setWalls(List.of(wall));

            Position initialPosition = mariController.getModel().getMari().getPosition();
            mariController.moveMari(mariPosition);
            assertEquals(initialPosition, mariController.getModel().getMari().getPosition());
        }

        Position wallPosition = new Position(48, 40+14);
        Wall wall = new Wall(wallPosition);
        mariController.getModel().setWalls(List.of(wall));

        Position initialPosition = mariController.getModel().getMari().getPosition();
        mariController.moveMari(mariPosition);
        assertNotEquals(initialPosition, mariController.getModel().getMari().getPosition());
    }

    @Test
    public void testUpdateMariGrounded() {
        Position newPosition = new Position(43, 18);
        Wall wall = new Wall(newPosition);
        mariController.getModel().setWalls(List.of(wall));

        Position initialPosition = new Position(40, 4);
        mariController.getModel().getMari().setPosition(initialPosition);
        mariController.updateMari(100);

        assertTrue(mariController.getModel().mariIsGrounded());

        newPosition = new Position(49, 18);
        wall = new Wall(newPosition);
        mariController.getModel().setWalls(List.of(wall));

        assertFalse(mariController.getModel().mariIsGrounded());
    }

    @Test
    void testUpdateMariWithPotion() {
        Position newPosition = new Position(10, 10);
        Potion potion = new Potion(newPosition);
        mariController.getModel().setPotions(List.of(potion));

        Position initialPosition = new Position(10, 10);
        mariController.getModel().getMari().setPosition(initialPosition);
        mariController.updateMari(100);

        assertTrue(mariController.getModel().getMari().getIsWithPotion());
    }

    @Test
    void testUpdateMariDoubleJump() {
        Mari mockMari = mock(Mari.class);
        map.setMari(mockMari);
        Position initialPosition = new Position(10, 10);
        when(mockMari.getPosition()).thenReturn(initialPosition);
        when(mockMari.doubleJump()).thenReturn(initialPosition);

        when(mockMari.getIsWithPotion()).thenReturn(true);
        when(mockMari.getRemainingJumps()).thenReturn(0);
        mariController.updateMari(100);

        verify(mockMari, times(1)).doubleJump();
    }

    @Test
    void testUpdateMariDoubleJump2() {
        Position wallPosition = new Position(14, 24);
        Wall wall = new Wall(wallPosition);
        mariController.getModel().setWalls(List.of(wall));

        mariController.getModel().getMari().setJumping(true);
        mariController.getModel().getMari().setWithPotion(true);
        mariController.getModel().getMari().setRemainingJumps(0);

        mariController.updateMari(100);

        Position expected = new Position(11,9);
        assertEquals(expected, mariController.getModel().getMari().getPosition());
    }

    @Test
    void testUpdateMariResetJumps() {
        Mari mockMari = mock(Mari.class);
        map.setMari(mockMari);
        Position initialPosition = new Position(10, 10);
        when(mockMari.getPosition()).thenReturn(initialPosition);
        when(mockMari.update()).thenReturn(initialPosition);

        when(mockMari.getIsWithPotion()).thenReturn(false);
        when(mockMari.getRemainingJumps()).thenReturn(0);
        mariController.updateMari(100);

        verify(mockMari, times(1)).resetJumps();
    }

    @Test
    void testUpdateMariResetJumps2() {
        Mari mockMari = mock(Mari.class);
        map.setMari(mockMari);
        Position initialPosition = new Position(10, 10);
        when(mockMari.getPosition()).thenReturn(initialPosition);
        when(mockMari.update()).thenReturn(initialPosition);

        when(mockMari.getIsWithPotion()).thenReturn(true);
        when(mockMari.getRemainingJumps()).thenReturn(-1);
        mariController.updateMari(100);

        verify(mockMari, times(1)).resetJumps();
    }

    @Test
    void testMoveMariWithKey() {
        Position newPosition = new Position(10, 18);
        Key key = new Key(newPosition);
        mariController.getModel().setKey(key);

        Position initialPosition = new Position(10, 10);
        mariController.getModel().getMari().setPosition(initialPosition);
        mariController.updateMari(100);

        assertTrue(mariController.getModel().getMari().getWithKey());
    }

     @Test
     public void testIsAtPlatformPosition() {
         Position newPosition = new Position(45, 18);
         Wall wall = new Wall(newPosition);
         Platform platform = new Platform(newPosition);
         platform.addConnectedPlatform(wall);
         mariController.getModel().setPlatforms(List.of(platform));

         Position initialPosition = new Position(40, 5);
         mariController.getModel().getMari().setPosition(initialPosition);

         assertTrue(mariController.getModel().isAtPlatform(mariController.getModel().getMari().getPosition()));

         mariController.updateMari(0);
         Position expectedPosition = new Position(40, 4);
         assertEquals(mariController.getModel().getMari().getPosition(), expectedPosition);
     }


    @Test
    void testStepMoveToEnemyPosition() {
        MariController mariController = new MariController(map);

        GhostEnemy ghostEnemy = Mockito.mock(GhostEnemy.class);
        BatEnemy batEnemy = Mockito.mock(BatEnemy.class);

        Position position = new Position(10, 10);
        Position position2 = new Position(10, 10);

        Mockito.when(ghostEnemy.getPosition()).thenReturn(position);
        Mockito.when(batEnemy.getPosition()).thenReturn(position2);

        mariController.getModel().setGhostEnemies(List.of(ghostEnemy));
        mariController.getModel().setBatEnemies(List.of(batEnemy));

        mariController.step(main, GUI.ACTION.NONE, 100);

        assertTrue(mariController.getModel().isEnemy(new Position(14, 10)));
        assertTrue(mariController.getModel().isEnemy(new Position(17, 10)));
        assertFalse(mariController.getModel().isEnemy(new Position(15, 10)));
    }

    @Test
    void testAttackMari() {
        Position newPosition = new Position(14, 24);
        Wall wall = new Wall(newPosition);
        mariController.getModel().setWalls(List.of(wall));

        GhostEnemy ghostEnemy = Mockito.mock(GhostEnemy.class);
        Position ghostPosition = new Position(6, 10);
        Mockito.when(ghostEnemy.getPosition()).thenReturn(ghostPosition);

        mariController.getModel().setGhostEnemies(List.of(ghostEnemy));

        mariController.updateMari(1010);
        assertEquals(2, mariController.getModel().getMari().getRemainingLives());
        assertEquals(1010, mariController.getLastAttack());
    }

    @Test
    void testLastAttackMari() {
        Position newPosition = new Position(14, 24);
        Wall wall = new Wall(newPosition);
        mariController.getModel().setWalls(List.of(wall));

        GhostEnemy ghostEnemy = Mockito.mock(GhostEnemy.class);
        Position ghostPosition = new Position(6, 10);
        Mockito.when(ghostEnemy.getPosition()).thenReturn(ghostPosition);

        mariController.getModel().setGhostEnemies(List.of(ghostEnemy));

        mariController.setLastAttack(350);
        mariController.updateMari(1350);
        assertEquals(3, mariController.getModel().getMari().getRemainingLives());
        mariController.updateMari(1351);
        assertEquals(2, mariController.getModel().getMari().getRemainingLives());
    }

    @Test
    public void testStepActionUp() {
        Wall groundWall = new Wall(new Position(43, 18));
        map.setWalls(List.of(groundWall));

        Position initialPosition = new Position(40, 18 - 14);
        mari.setPosition(initialPosition);

        Main main = mock(Main.class);
        mariController.updateMari(100);
        mari.jump();
        assertTrue(mari.getIsJumping());
        for (int i = 0; i < 6; i++)
            mari.update();

        mariController.step(main, GUI.ACTION.UP, 100);

        mari.setGrounded(true);
        for (int i = 0; i < 5; i++)
            mari.update();

        mariController.step(main, GUI.ACTION.UP, 100);
        assertFalse(mari.getIsJumping());
    }

    @Test
    public void testStepActionUpPotion() {
        Mari mockMari = mock(Mari.class);
        map.setMari(mockMari);
        Position initialPosition = new Position(10, 10);
        when(mockMari.getPosition()).thenReturn(initialPosition);
        when(mockMari.update()).thenReturn(initialPosition);

        when(mockMari.getIsWithPotion()).thenReturn(true);
        when(mockMari.getRemainingJumps()).thenReturn(-1);

        Main main = mock(Main.class);
        mariController.step(main, GUI.ACTION.UP, 100);

        verify(mockMari, times(1)).decreaseJumps();
    }

    @Test
    public void testStepActionUpJump() {
        Position wallPosition = new Position(14, 24);
        Wall wall = new Wall(wallPosition);
        mariController.getModel().setWalls(List.of(wall));

        Main main = mock(Main.class);
        mariController.step(main, GUI.ACTION.UP, 100);

        assertTrue(mariController.getModel().getMari().getIsJumping());

        mariController.step(main, GUI.ACTION.UP, 100);
        Position expected = new Position(11,9);
        assertEquals(expected, mariController.getModel().getMari().getPosition());
    }

    @Test
    public void testStepActionRight() {
        Position initialPosition = new Position(40, 4);
        mari.setPosition(initialPosition);

        Main main = mock(Main.class);

        mariController.step(main, GUI.ACTION.RIGHT, 100);
        assertEquals(41, mari.getPosition().getX());
    }

    @Test
    public void testStepActionLeft() {
        Position initialPosition = new Position(40, 4);
        mari.setPosition(initialPosition);

        Main main = mock(Main.class);

        mariController.step(main, GUI.ACTION.LEFT, 100);
        assertEquals(39, mari.getPosition().getX());
    }
}
package com.st.projectst.model.game;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import com.st.projectst.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MapTest {
    private Map map;
    private Mari mari;

    @BeforeEach
    public void setup() {
        map = new Map(320, 60, 1);
        Position position = new Position(10, 10);
        mari = new Mari(position);
        map.setMari(mari);
        map.setKey(new Key(new Position(1,1)));
        map.setTraps(List.of());
        map.setBatEnemies(List.of());
        map.setGhostEnemies(List.of());
        map.setWalls(List.of());
        map.setPlatforms(List.of());
        map.setPotions(List.of());
    }

    @Test
    public void testIsTrap() {
        Position trapPosition = new Position(19, 33);
        Trap trap = new Trap(trapPosition);
        List<Trap> traps = new ArrayList<>();
        traps.add(trap);
        map.setTraps(traps);

        Position mariPosition = new Position(10, 20);
        map.getMari().setPosition(mariPosition);
        assertFalse(map.isTrap());

        for (int i = 3; i < 9; i++) {
            mariPosition.setX(mariPosition.getX()+1);
            map.getMari().setPosition(mariPosition);
            assertTrue(map.isTrap());
        }

        mariPosition.setX(mariPosition.getX()+1);
        map.getMari().setPosition(mariPosition);
        assertFalse(map.isTrap());

        mariPosition.setX(mariPosition.getX()-1);
        mariPosition.setY(mariPosition.getY()+1);
        map.getMari().setPosition(mariPosition);
        assertFalse(map.isTrap());

    }


    @Test
    public void testIsTrapMock() {
        Mari mari = new Mari(new Position(10, 20));
        map.setMari(mari);

        Trap mockTrap = Mockito.mock(Trap.class);
        Mockito.when(mockTrap.getPosition()).thenReturn(new Position(13, 33));
        List<Trap> traps = new ArrayList<>();
        traps.add(mockTrap);
        map.setTraps(traps);

        assertTrue(map.isTrap());
        verify(mockTrap, times(1)).notifyObservers();
    }



    @Test
    public void testIsKey() {
        Key key = new Key(new Position(1,7));
        map.setKey(key);
        assertTrue(map.isKey(new Position(1, 0)));
        assertFalse(map.isKey(new Position(2, 0)));
        map.removeKey();
        assertFalse(map.isKey(new Position(1, 0)));
    }

    @Test
    public void testIsAtPlatform() {
        List<Platform> platforms = new ArrayList<>();
        Platform platform = new Platform(new Position(19,34));
        platforms.add(platform);
        map.setPlatforms(platforms);

        Position mariPosition = new Position(10, 21);
        assertFalse(map.isAtPlatform(mariPosition));

        for (int i = 3; i < 9; i++) {
            mariPosition.setX(mariPosition.getX()+1);
            assertTrue(map.isAtPlatform(mariPosition));
        }

        mariPosition.setX(mariPosition.getX()+1);
        assertFalse(map.isAtPlatform(mariPosition));
    }

    @Test
    public void testTouchPotion() {
        Potion potion = new Potion(new Position(7,7));
        List<Potion> potions = new ArrayList<>();
        potions.add(potion);
        map.setPotions(potions);
        assertTrue(map.touchPotion(new Position(7,7)));
        assertFalse(map.touchPotion(new Position(6,7)));
    }

    @Test
    public void testIsAtDoor() {
        Position doorPosition = new Position(12, 10);
        Door door = new Door(doorPosition);
        map.setDoor(door);
        Position expectedPosition = new Position(1, 10);
        assertTrue(map.isAtDoor(expectedPosition));
        assertFalse(map.isAtDoor(doorPosition));
    }

    @Test
    public void testGroundedWalls() {
        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(new Position(19, 24)));
        map.setWalls(walls);

        Position mariPosition = new Position(10, 10);
        map.setMari(new Mari(mariPosition));
        assertFalse(map.mariIsGrounded());

        for (int i = 3; i < 9; i++) {
            mariPosition.setX(mariPosition.getX()+1);
            map.getMari().setPosition(mariPosition);
            assertTrue(map.mariIsGrounded());
        }

        mariPosition.setX(mariPosition.getX()+1);
        map.getMari().setPosition(mariPosition);
        assertFalse(map.mariIsGrounded());
    }

    @Test
    public void testGroundedPlatforms(){
        Position mariPosition = new Position(9, 12);
        map.setMari(new Mari(mariPosition));

        List<Platform> platforms = new ArrayList<>();
        Platform platform = new Platform(new Position(18, 26));
        platforms.add(platform);
        map.setPlatforms(platforms);
        assertFalse(map.mariIsGrounded());

        for (int i = 3; i < 9; i++) {
            mariPosition.setX(mariPosition.getX()+1);
            map.getMari().setPosition(mariPosition);
            assertTrue(map.mariIsGrounded());
        }

        mariPosition.setX(mariPosition.getX()+1);
        map.getMari().setPosition(mariPosition);
        assertFalse(map.mariIsGrounded());
    }

    @Test
    public void testGroundedPlatforms2(){
        Position mariPosition = new Position(9, 12);
        map.setMari(new Mari(mariPosition));

        List<Platform> platforms = new ArrayList<>();
        Platform platform = new Platform(new Position(18, 25));
        platforms.add(platform);
        map.setPlatforms(platforms);
        assertFalse(map.mariIsGrounded());

        for (int i = 3; i < 9; i++) {
            mariPosition.setX(mariPosition.getX()+1);
            map.getMari().setPosition(mariPosition);
            assertTrue(map.mariIsGrounded());
        }

        mariPosition.setY(mariPosition.getY()-1);
        map.getMari().setPosition(mariPosition);
        assertTrue(map.mariIsGrounded());

        mariPosition.setY(mariPosition.getY()-1);
        map.getMari().setPosition(mariPosition);
        assertFalse(map.mariIsGrounded());
    }

    @Test
    public void testIsEmpty() {
        Position emptyPosition = new Position(10, 10);
        assertTrue(map.isEmpty(emptyPosition));

        Position wallPosition = new Position(5, 5);
        Wall wall = new Wall(wallPosition);
        List<Wall> walls = new ArrayList<>();
        walls.add(wall);
        map.setWalls(walls);
        assertFalse(map.isEmpty(wallPosition));

        Platform platform = new Platform(new Position(8, 8));
        List<Platform> platforms = new ArrayList<>();
        platforms.add(platform);
        map.setPlatforms(platforms);

        for (Platform plat: map.getPlatforms()) {
            assertFalse(map.isEmpty(plat.getPosition()));
        }
    }

    @Test
    public void testGetCurrentLevel() {
        int initialLevel = 5;
        Map map = new Map(10, 10, initialLevel);
        Assertions.assertEquals(initialLevel, map.getCurrentLevel());
        int updatedLevel = 8;
        map.setCurrentLevel(updatedLevel);
        Assertions.assertEquals(updatedLevel, map.getCurrentLevel());
    }

    @Test
    public void testGetPlatformsNotNull() {
        assertEquals(map.getPlatforms(), Collections.emptyList());

        List<Platform> platforms = Arrays.asList(new Platform(new Position(10, 10)));
        map.setPlatforms(platforms);
        assertNotEquals(map.getPlatforms(), Collections.emptyList());
        assertNotNull(platforms);
    }

    @Test
    public void testGetPotionsNotNull() {
        List<Potion> potions = Arrays.asList(new Potion(new Position(15, 15)));
        map.setPotions(potions);
        assertNotEquals(potions, Collections.emptyList());
        assertNotNull(potions);
    }

    @Test
    void testIsEnemy() {
        GhostEnemy ghostEnemy = new GhostEnemy(new Position(5, 5));
        BatEnemy batEnemy = new BatEnemy(new Position(10, 10));
        List<GhostEnemy> ghostEnemies = new ArrayList<>();
        ghostEnemies.add(ghostEnemy);
        map.setGhostEnemies(ghostEnemies);

        List<BatEnemy> batEnemies = new ArrayList<>();
        batEnemies.add(batEnemy);
        map.setBatEnemies(batEnemies);
        assertTrue(map.isEnemy(new Position(9, 5)));
        assertTrue(map.isEnemy(new Position(17, 10)));
        assertFalse(map.isEnemy(new Position(15, 15)));
        assertTrue(map.isEnemy(new Position(-4, 5)));
    }
}

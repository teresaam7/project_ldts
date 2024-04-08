package com.st.projectst.model.game;

import com.st.projectst.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class MapBuilderTest {
    private MapBuilder mapBuilder;

    @BeforeEach
    public void setup() throws IOException, URISyntaxException {
        mapBuilder = new MapBuilder(1);
    }

    @Test
    public void testMapBuilder() {
        int level = 1;
        try {
            MapBuilder mapBuilder = new MapBuilder(level);
            Map map = mapBuilder.buildMap();

            assertNotNull(map);
            assertEquals(map.getWidth(), 320);
            assertEquals(map.getHeight(),61);

        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testLoadFromFile() {
        URL resource = MapBuilder.class.getResource("/levels/map1.txt");

        try (BufferedReader buff = Files.newBufferedReader(Paths.get(resource.toURI()), Charset.defaultCharset());) {
            MapBuilder mapBuilder = new MapBuilder(1);
            List<String> lines = mapBuilder.loadFromFile(buff);

            assertNotNull(lines);
            assertFalse(lines.isEmpty());

        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreateMari() throws IOException, URISyntaxException {
        Mari mari = mapBuilder.createMari();
        assertNull(mari);
        Map map = mapBuilder.buildMap();
        mari = mapBuilder.createMari();
        map.setMari(mari);
        assertNotNull(mari);

        List<String> lineMaps = new ArrayList<>();
        lineMaps.add("---");
        lineMaps.add("-M-");

        MapBuilder mapBuilder2 = new MapBuilder(1);
        mapBuilder2.setLinesMap(lineMaps);
        mapBuilder2.buildMap();
        Mari mari2 = mapBuilder2.createMari();

        assertEquals(mari2.getPosition(), new Position(1, 1), "Mari should be at position (1, 1)");
    }

    @Test
    public void testCreateGhostEnemies() throws IOException, URISyntaxException {
        List<GhostEnemy> ghostEnemies = mapBuilder.createGhostEnemies();
        assertNotNull(ghostEnemies);

        List<String> lineMaps = new ArrayList<>();
        lineMaps.add("G--");
        lineMaps.add("-G-");
        lineMaps.add("--G");

        MapBuilder mapBuilder2 = new MapBuilder(1);
        mapBuilder2.setLinesMap(lineMaps);
        mapBuilder2.buildMap();
        List<GhostEnemy> ghostEnemies2 = mapBuilder2.createGhostEnemies();

        assertEquals(3, ghostEnemies2.size(), "Number of ghost enemies created should match");

        assertEquals(ghostEnemies2.get(0).getPosition(), new Position(0, 0), "Ghost enemy should be at position (0, 0)");
        assertEquals(ghostEnemies2.get(1).getPosition(), new Position(1, 1), "Ghost enemy should be at position (1, 1)");
        assertEquals(ghostEnemies2.get(2).getPosition(), new Position(2, 2), "Ghost enemy should be at position (2, 2)");
    }

    @Test
    public void testCreateBatEnemies() throws IOException, URISyntaxException {
        List<BatEnemy> batEnemies = mapBuilder.createBatEnemies();
        assertNotNull(batEnemies);

        List<String> lineMaps = new ArrayList<>();
        lineMaps.add("B--");
        lineMaps.add("-B-");
        lineMaps.add("--B");

        MapBuilder mapBuilder2 = new MapBuilder(1);
        mapBuilder2.setLinesMap(lineMaps);
        mapBuilder2.buildMap();
        List<BatEnemy> batEnemies2 = mapBuilder2.createBatEnemies();

        assertEquals(3, batEnemies2.size(), "Number of bat enemies created should match");

        assertEquals(batEnemies2.get(0).getPosition(), new Position(0, 0), "Bat enemy should be at position (0, 0)");
        assertEquals(batEnemies2.get(1).getPosition(), new Position(1, 1), "Bat enemy should be at position (1, 1)");
        assertEquals(batEnemies2.get(2).getPosition(), new Position(2, 2), "Bat enemy should be at position (2, 2)");
    }

    @Test
    public void testCreatePotions() throws IOException, URISyntaxException {
        Map map = mapBuilder.buildMap();
        List<Potion> potions = mapBuilder.createPotions();
        map.setPotions(potions);
        assertNotNull(potions);

        List<String> lineMaps = new ArrayList<>();
        lineMaps.add("P--");
        lineMaps.add("-P-");
        lineMaps.add("--P");

        MapBuilder mapBuilder2 = new MapBuilder(1);
        mapBuilder2.setLinesMap(lineMaps);
        mapBuilder2.buildMap();
        List<Potion> potions2 = mapBuilder2.createPotions();

        assertEquals(3, potions2.size(), "Number of potions created should match");

        assertEquals(potions2.get(0).getPosition(), new Position(0, 0), "Potion should be at position (0, 0)");
        assertEquals(potions2.get(1).getPosition(), new Position(1, 1), "Potion should be at position (1, 1)");
        assertEquals(potions2.get(2).getPosition(), new Position(2, 2), "Potion should be at position (2, 2)");
    }

    @Test
    public void testCreateWalls() throws IOException, URISyntaxException {
        Map map = mapBuilder.buildMap();
        List<Wall> walls = mapBuilder.createWalls();
        map.setWalls(walls);
        assertNotNull(walls);

        List<String> lineMaps = new ArrayList<>();
        lineMaps.add("W--");
        lineMaps.add("-W-");
        lineMaps.add("--W");

        MapBuilder mapBuilder2 = new MapBuilder(1);
        mapBuilder2.setLinesMap(lineMaps);
        mapBuilder2.buildMap();
        List<Wall> walls2 = mapBuilder2.createWalls();

        assertEquals(3, walls2.size(), "Number of walls created should match");

        assertEquals(walls2.get(0).getPosition(), new Position(0, 0), "Wall should be at position (0, 0)");
        assertEquals(walls2.get(1).getPosition(), new Position(1, 1), "Wall enemy should be at position (1, 1)");
        assertEquals(walls2.get(2).getPosition(), new Position(2, 2), "Wall enemy should be at position (2, 2)");
    }

    @Test
    public void testCreatePlatforms() throws IOException, URISyntaxException {
        Map map = mapBuilder.buildMap();
        List<Platform> platforms = mapBuilder.createPlatforms();
        map.setPlatforms(platforms);
        assertNotNull(platforms);

        List<String> lineMaps = new ArrayList<>();
        lineMaps.add("FF-");
        lineMaps.add("-F-");
        lineMaps.add("--F");

        MapBuilder mapBuilder2 = new MapBuilder(1);
        mapBuilder2.setLinesMap(lineMaps);
        mapBuilder2.buildMap();
        List<Platform> platforms2 = mapBuilder2.createPlatforms();

        assertEquals(4, platforms2.size(), "Number of platforms created should match");

        assertEquals(platforms2.get(0).getPosition(), new Position(0, 0), "Platform should be at position (0, 0)");
        assertEquals(platforms2.get(1).getPosition(), new Position(1, 0), "Platform should be at position (1, 0)");
        assertEquals(platforms2.get(2).getPosition(), new Position(1, 1), "Platform should be at position (1, 1)");
        assertEquals(platforms2.get(3).getPosition(), new Position(2, 2), "Platform should be at position (2, 2)");

        assertTrue(platforms2.get(0).isOnSameLevel(platforms2.get(1)) && !platforms2.get(0).equals(platforms2.get(1)));
        assertEquals(platforms2.get(0).getConnectedPlatforms().size(), 1);
        assertEquals(platforms2.get(2).getConnectedPlatforms().size(), 0);
        assertFalse(platforms2.get(0).isOnSameLevel(platforms2.get(1)) && !platforms2.get(0).equals(platforms2.get(0)));
        assertFalse(platforms2.get(0).isOnSameLevel(platforms2.get(2)));

        assertNotEquals(platforms2.get(0), platforms2.get(1));
        assertEquals(platforms2.get(0), platforms2.get(0));
    }

    @Test
    public void testCreateDoor() throws IOException, URISyntaxException {
        Door door = mapBuilder.createDoor();
        assertNull(door);
        Map map = mapBuilder.buildMap();
        door = mapBuilder.createDoor();
        map.setDoor(door);
        assertNotNull(door);

        List<String> lineMaps = new ArrayList<>();
        lineMaps.add("---");
        lineMaps.add("-D-");

        MapBuilder mapBuilder2 = new MapBuilder(1);
        mapBuilder2.setLinesMap(lineMaps);
        mapBuilder2.buildMap();
        Door door2 = mapBuilder2.createDoor();

        assertEquals(door2.getPosition(), new Position(1, 1), "Door should be at position (1, 1)");
    }

    @Test
    public void testCreateKey() throws IOException, URISyntaxException {
        Key key = mapBuilder.createKey();
        assertNull(key);
        Map map = mapBuilder.buildMap();
        key = mapBuilder.createKey();
        map.setKey(key);
        assertNotNull(key);

        List<String> lineMaps = new ArrayList<>();
        lineMaps.add("---");
        lineMaps.add("-K-");

        MapBuilder mapBuilder2 = new MapBuilder(1);
        mapBuilder2.setLinesMap(lineMaps);
        mapBuilder2.buildMap();
        Key key2 = mapBuilder2.createKey();

        assertEquals(key2.getPosition(), new Position(1, 1), "Key should be at position (1, 1)");
    }

    @Test
    void testCreateTraps() throws IOException, URISyntaxException {
        List<String> lineMaps = new ArrayList<>();
        lineMaps.add("---");
        lineMaps.add("-XB");
        lineMaps.add("---");


        MapBuilder mapBuilder2 = new MapBuilder(1);
        mapBuilder2.setLinesMap(lineMaps);
        Map map = mapBuilder2.buildMap();
        List<Trap> traps = mapBuilder2.createTraps(map);
        List<BatEnemy> batEnemies = mapBuilder2.createBatEnemies();
        map.setTraps(traps);
        map.setBatEnemies(batEnemies);
        assertEquals(1, traps.size(), "Number of traps created should match");
        assertEquals(1, batEnemies.size(), "Number of bat enemies created should match");
        assertEquals(traps.get(0).getObservers().size(),batEnemies.size());
    }

    @Test
    public void testSets() throws IOException {
        Map map = mapBuilder.buildMap();

        assertNotNull(map.getMari());
        assertNotNull(map.getGhostEnemies());
        assertNotNull(map.getBatEnemies());
        assertNotNull(map.getWalls());
        assertNotNull(map.getKey());
        assertNotNull(map.getTraps());
        assertNotNull(map.getDoor());
        assertNotNull(map.getPlatforms());
        assertNotNull(map.getPotions());
    }
}

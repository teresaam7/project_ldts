package com.st.projectst.model.game;

import com.st.projectst.model.Position;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlatformTest {
    @Test
    void sameLevelPlatformsTest() {
        Position position1 = new Position(0, 5);
        Position position2 = new Position(10, 5);
        Platform platform1 = new Platform(position1);
        Platform platform2 = new Platform(position2);

        boolean result = platform1.isOnSameLevel(platform2);

        assertTrue(result, "Platforms should be on the same level");
    }

    @Test
    void addConnectedPlatformTest() {
        Position position = new Position(0, 0);
        Platform platform1 = new Platform(position);
        Platform platform2 = new Platform(position);

        platform1.addConnectedPlatform(platform2);

        List<Wall> connectedPlatforms = platform1.getConnectedPlatforms();
        assertTrue(connectedPlatforms.contains(platform2));
    }

    @Test
    void movePlatformsTest() {
        Position position = new Position(0, 0);
        Position expected = new Position(0, 0);
        Platform platform = new Platform(position);
        double initialY = position.getY();
        for (int i = 0; i < 34; i++) {
            platform.moveAllPlatforms();

            // Testing the positions
            if (i <= 16 || i >= 33) {
                expected.setY(expected.getY()-1);
                assertEquals(expected, platform.getPosition());
            }
            else {
                expected.setY(expected.getY()+1);
                assertEquals(expected, platform.getPosition());
            }

            // Testing the movement
            if (i <= 15) {
                assertTrue(platform.isMovingUp());
                assertEquals(platform.getMoveCounter(), i+1);
            } else if (i == 16) {
                assertFalse(platform.isMovingUp());
            } else if (i == 31) {
                assertFalse(platform.isMovingUp());
                assertEquals(16, platform.getMoveCounter());
            } else if (i == 32) {
                assertTrue(platform.isMovingUp());
                assertEquals(1, platform.getMoveCounter());
            }

        }

        double newY = platform.getPosition().getY();
        assertTrue(initialY != newY);
    }

    @Test
    void testIsOnSameLevel_Walls() {
        Wall wall1 = new Wall(new Position(5, 10));
        Wall wall2 = new Wall(new Position(8, 10));

        Platform platform = new Platform(new Position(5, 10));

        assertTrue(platform.isOnSameLevel(wall1));
        assertTrue(platform.isOnSameLevel(wall2));
    }

    @Test
    void testIsMovingUp() {
        Platform platformMovingUp = new Platform(new Position(5, 10));
        platformMovingUp.setMovingUp(true);

        Platform platformNotMovingUp = new Platform(new Position(5, 10));
        platformNotMovingUp.setMovingUp(false);

        assertTrue(platformMovingUp.isMovingUp());
        assertFalse(platformNotMovingUp.isMovingUp());
    }

    @Test
    void testGetMoveCounter_MutationSurvived() {
        Platform platform = new Platform(new Position(5, 10));
        assertEquals(0, platform.getMoveCounter());
    }

    @Test
    void testMoveAllPlatforms_PositionChanged() {
        Platform platform = new Platform(new Position(5, 10));
        Position initialPosition = platform.getPosition();

        platform.moveAllPlatforms();

        assertNotEquals(initialPosition, platform.getPosition());
    }

    @Test
    void testGetConnectedPlatforms() {
        Platform platform = new Platform(new Position(5, 10));
        List<Wall> connectedPlatforms = platform.getConnectedPlatforms();
        assertEquals(Collections.emptyList(), connectedPlatforms);
    }

}

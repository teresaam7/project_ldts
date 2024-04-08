package com.st.projectst.model.game;

import com.st.projectst.model.Position;
import com.st.projectst.model.game.Mari;
import com.st.projectst.model.game.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class MariTest {
    Mari mari;

    @BeforeEach
    public void setup() {
        Position initialPosition = new Position(10, 10);
        mari = new Mari(initialPosition);
    }


    @Test
    public void mariMoveRight() {
        Position position = mari.moveRight();
        Position expected = new Position(11, 10);

        assertEquals(expected, position);
    }

    @Test
    public void mariMoveLeft() {
        Position position = mari.moveLeft();
        Position expected = new Position(9, 10);

        assertEquals(expected, position);
    }

    @Test
    public void testMoveLeftWithSubtraction() {
        mari.setPosition(new Position(0, 0));

        Position position = mari.moveLeft();
        Position expected = new Position(-1, 0);

        assertEquals(expected, position);
    }

    @Test
    public void mariUpdateJumpingRight() {
        assertFalse(mari.getIsJumping());
        mari.setGrounded(true);
        assertTrue(mari.getJumpRight());

        mari.jump();
        assertTrue(mari.getIsJumping());

        // Testing the character moving up on the jump
        Position expected = new Position(10,10);
        for (int i = 1; i <= 7; i++) {
            mari.setPosition(mari.update());

            assertTrue(mari.getIsJumping());
            assertFalse(mari.getGrounded());
            assertEquals(i, mari.getJumpCounter());

            expected.setX(expected.getX()+1);
            expected.setY(expected.getY()-1);
            assertEquals(expected, mari.getPosition());
        }

        // Testing the character reaching the highest position on the jump
        mari.setPosition(mari.update());

        assertFalse(mari.getIsJumping());
        assertFalse(mari.getGrounded());
        assertEquals(0, mari.getJumpCounter());

        expected.setX(expected.getX()+1);
        expected.setY(expected.getY()-1);
        assertEquals(expected, mari.getPosition());

        // Testing the character moving down
        for (int i = 1; i <= 4; i++) {
            mari.setPosition(mari.update());

            assertFalse(mari.getIsJumping());
            assertEquals(0, mari.getJumpCounter());

            expected.setY(expected.getY()+1);
            assertEquals(expected, mari.getPosition());
        }

        mari.setGrounded(true);
        assertFalse(mari.getIsJumping());
        mari.setPosition(mari.update());
        assertEquals(expected, mari.getPosition());
    }

    @Test
    public void mariUpdateJumpingLeft() {
        assertFalse(mari.getIsJumping());
        mari.setGrounded(true);
        mari.setJumpRight(false);

        mari.jump();
        assertTrue(mari.getIsJumping());

        // Testing the character moving up on the jump
        Position expected = new Position(10,10);
        for (int i = 1; i <= 7; i++) {
            mari.setPosition(mari.update());

            assertTrue(mari.getIsJumping());
            assertFalse(mari.getGrounded());
            assertEquals(i, mari.getJumpCounter());

            expected.setX(expected.getX()-1);
            expected.setY(expected.getY()-1);
            assertEquals(expected, mari.getPosition());
        }

    }

    @Test
    public void mariDoubleJumpRight() {
        mari.setJumping(true);
        assertTrue(mari.getJumpRight());

        Position expected = new Position(10,10);
        for (int i = 1; i <= 7; i++) {
            mari.setPosition(mari.doubleJump());

            assertTrue(mari.getIsJumping());
            assertFalse(mari.getGrounded());
            assertEquals(i, mari.getJumpCounter());

            expected.setX(expected.getX()+1);
            expected.setY(expected.getY()-1);
            assertEquals(expected, mari.getPosition());
        }

        mari.setPosition(mari.doubleJump());
        expected.setX(expected.getX()+1);
        expected.setY(expected.getY()+1);
        assertEquals(expected, mari.getPosition());

        for (int i = 9; i <= 15; i++) {
            mari.setPosition(mari.doubleJump());

            assertTrue(mari.getIsJumping());
            assertFalse(mari.getGrounded());
            assertEquals(i, mari.getJumpCounter());

            expected.setX(expected.getX()+1);
            expected.setY(expected.getY()-1);
            assertEquals(expected, mari.getPosition());
        }

        mari.setPosition(mari.doubleJump());
        assertFalse(mari.getIsJumping());
        assertFalse(mari.getGrounded());
        assertEquals(0, mari.getJumpCounter());
        assertEquals(expected, mari.getPosition());

        mari.setPosition(mari.doubleJump());
        expected.setY(expected.getY()+1);
        assertEquals(expected, mari.getPosition());

        mari.setGrounded(true);
        assertFalse(mari.getIsJumping());
        mari.setPosition(mari.doubleJump());
        assertEquals(expected, mari.getPosition());
    }

    @Test
    public void mariDoubleJumpLeft() {
        mari.setJumping(true);
        mari.setJumpRight(false);

        Position expected = new Position(10,10);
        for (int i = 1; i <= 7; i++) {
            mari.setPosition(mari.doubleJump());

            assertTrue(mari.getIsJumping());
            assertFalse(mari.getGrounded());
            assertEquals(i, mari.getJumpCounter());

            expected.setX(expected.getX()-1);
            expected.setY(expected.getY()-1);
            assertEquals(expected, mari.getPosition());
        }

        mari.setPosition(mari.doubleJump());
        expected.setX(expected.getX()-1);
        expected.setY(expected.getY()+1);
        assertEquals(expected, mari.getPosition());
    }

    @Test
    public void mariJumpNotGrounded() {
        mari.jump();
        assertFalse(mari.getIsJumping());

        mari.setGrounded(false);
        mari.jump();
        assertFalse(mari.getIsJumping());
    }

    @Test
    public void mariDoubleJumpNotGrounded() {
        mari.doubleJump();
        assertFalse(mari.getIsJumping());

        mari.setGrounded(false);
        mari.doubleJump();
        assertFalse(mari.getIsJumping());
    }

    @Test
    public void mariJumpGrounded() {
        mari.setGrounded(true);
        mari.jump();
        assertTrue(mari.getIsJumping());
    }

    @Test
    public void mariUpdateNoJumpingNoGrounded() {
        Position position = mari.update();
        Position expected = new Position(10, 11);
        assertEquals(expected, position);
        assertNotNull(position);
    }

    @Test
    public void mariUpdateNoJumpingGrounded() {
        mari.setGrounded(true);
        mari.update();
        Position expected = new Position(10, 10);

        assertEquals(expected, mari.getPosition());
    }

    @Test
    void testDoubleJump() {
        Mari mari = new Mari(new Position(0, 0));
        mari.setGrounded(true);
        mari.jump();
        mari.update();
        Position newPosition = mari.doubleJump();
        assertTrue(mari.getIsJumping());
        assertFalse(mari.getGrounded());
        assertEquals(-1, newPosition.getY());
        assertNotNull(newPosition);
    }

    @Test
    public void mariLives() {
        assertEquals(3, mari.getRemainingLives());

        for (int i = 2; i >= -2; i--) {
            mari.decreaseLives();
            assertEquals(i, mari.getRemainingLives());
        }
    }

    @Test
    public void mariJumps() {
        assertEquals(2, mari.getRemainingJumps());

        for (int i = 1; i >= -2; i--) {
            mari.decreaseJumps();
            assertEquals(i, mari.getRemainingJumps());
        }
    }

    @Test
    public void mariResetJumps() {
        assertFalse(mari.getIsWithPotion());
        assertEquals(2, mari.getRemainingJumps());

        mari.setWithPotion(true);
        mari.setRemainingJumps(0);
        assertTrue(mari.getIsWithPotion());
        assertEquals(0, mari.getRemainingJumps());

        mari.resetJumps();
        assertFalse(mari.getIsWithPotion());
        assertEquals(2, mari.getRemainingJumps());
    }

    @Test
    public void mariKey() {
        assertFalse(mari.getWithKey());
        mari.setWithKey(true);
        assertTrue(mari.getWithKey());
    }

    @Test
    public void mariGetJumpRight() {
        assertTrue(mari.getJumpRight());

        mari.setJumpRight(false);
        assertFalse(mari.getJumpRight());

        mari.setJumpRight(true);
        assertTrue(mari.getJumpRight());
    }

    @Test
    public void mariGetGrounded() {
        assertFalse(mari.getGrounded());

        mari.setGrounded(true);
        assertTrue(mari.getGrounded());

        mari.setGrounded(false);
        assertFalse(mari.getGrounded());
    }


}

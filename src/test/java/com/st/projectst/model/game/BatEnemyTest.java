package com.st.projectst.model.game;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.BatEnemy;
import com.st.projectst.model.game.Enemy;
import com.st.projectst.model.game.Trap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BatEnemyTest {
    @Test
    void testMoveWhenBelowFinalPosition() {
        Position initialPosition = new Position(10, 10);
        Position finalPosition = new Position(10, 20);
        BatEnemy batEnemy = new BatEnemy(initialPosition);
        batEnemy.setFinalPosition(finalPosition);
        Position newPosition = batEnemy.move();
        assertEquals(initialPosition.getY() + 3, newPosition.getY());
    }

    @Test
    void testUpdate() {
        Position initialPosition = new Position(10, 10);
        BatEnemy batEnemy = new BatEnemy(initialPosition);

        Trap mockTrap = mock(Trap.class);
        Position trapPosition = new Position(5, 5);
        when(mockTrap.getPosition()).thenReturn(trapPosition);

        batEnemy.update(mockTrap);
        assertEquals(trapPosition.getY() - 13, batEnemy.getFinalPosition().getY());
    }

    @Test
    void testMoveWhenAboveFinalPosition() {
        Position initialPosition = new Position(10, 20);
        Position finalPosition = new Position(10, 10);
        BatEnemy batEnemy = new BatEnemy(initialPosition);
        batEnemy.setFinalPosition(finalPosition);
        Position newPosition = batEnemy.move();
        assertEquals(finalPosition.getY(), newPosition.getY());
    }

    @Test
    void testMoveWhenFinalPositionIsOnTheSameHeight() {
        Position initialPosition = new Position(10, 10);
        Position finalPosition = new Position(10, 10);
        BatEnemy batEnemy = new BatEnemy(initialPosition);
        batEnemy.setFinalPosition(finalPosition);
        Position newPosition = batEnemy.move();
        assertEquals(finalPosition.getY(), newPosition.getY());
    }

    @Test
    public void testGetFinalPosition() {
        Position position = new Position(5, 5);
        BatEnemy batEnemy = new BatEnemy(position);
        batEnemy.setFinalPosition(null);
        assertEquals(null, batEnemy.getFinalPosition());
    }

}

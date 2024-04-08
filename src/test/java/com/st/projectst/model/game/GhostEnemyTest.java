package com.st.projectst.model.game;

import com.st.projectst.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class GhostEnemyTest {
    @Test
    public void testMoveReturnsRandomHorizontal() {
        Position mockPosition = mock(Position.class);
        when(mockPosition.getRandomHorizontal()).thenReturn(new Position(0, 0));
        GhostEnemy ghostEnemy = new GhostEnemy(mockPosition);
        Position result = ghostEnemy.move();
        assertNotNull(result);
    }
}

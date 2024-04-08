package com.st.projectst.model.game;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.EnemyObserver;
import com.st.projectst.model.game.Trap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

public class TrapTest {

    private Trap trap;
    private EnemyObserver mockObserver1;
    private EnemyObserver mockObserver2;

    @BeforeEach
    void setUp() {
        Position trapPosition = new Position(5, 5);
        trap = new Trap(trapPosition);
        mockObserver1 = Mockito.mock(EnemyObserver.class);
        mockObserver2 = Mockito.mock(EnemyObserver.class);
    }

    @Test
    void testNotifyObservers() {
        trap.addObserver(mockObserver1);
        trap.addObserver(mockObserver2);
        trap.notifyObservers();
        Mockito.verify(mockObserver1, times(1)).update(trap);
        Mockito.verify(mockObserver2, times(1)).update(trap);
    }
}

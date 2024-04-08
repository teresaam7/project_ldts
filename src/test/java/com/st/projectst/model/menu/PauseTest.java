package com.st.projectst.model.menu;
import com.st.projectst.model.game.Map;
import com.st.projectst.states.LevelState;
import com.st.projectst.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PauseTest {
    private Map map;

    @BeforeEach
    void setUp() {
        map = mock(Map.class);
    }

    @Test
    void testInitialSelectedOptions() {
        State mockGameState = new LevelState(map);
        Pause pause = new Pause(mockGameState);

        assertTrue(pause.isSelectedContinue());
        assertFalse(pause.isSelectedExit());
    }

    @Test
    void testNextOption() {
        State mockGameState = new LevelState(map);
        Pause pause = new Pause(mockGameState);

        pause.nextOption();
        assertFalse(pause.isSelectedContinue());
        assertTrue(pause.isSelectedExit());

        pause.nextOption();
        assertTrue(pause.isSelectedExit());
    }

    @Test
    void testPreviousOption() {
        State mockGameState = new LevelState(map);
        Pause pause = new Pause(mockGameState);

        pause.previousOption();
        assertTrue(pause.isSelectedContinue());

        pause.nextOption();
        pause.previousOption();
        assertTrue(pause.isSelectedContinue());
    }

    @Test
    void testGetNumberOptions() {
        State mockGameState = new LevelState(map);
        Pause pause = new Pause(mockGameState);

        assertEquals(2, pause.getNumberOptions());
    }

    @Test
    void testGetOption() {
        State mockGameState = new LevelState(map);
        Pause pause = new Pause(mockGameState);

        assertEquals("Continue", pause.getOption(0));
        assertEquals("Go back to menu", pause.getOption(1));
    }

    @Test
    void testGetGameState() {
        State mockGameState = new LevelState(map);
        Pause pause = new Pause(mockGameState);

        assertEquals(mockGameState, pause.getGameState());
    }

}


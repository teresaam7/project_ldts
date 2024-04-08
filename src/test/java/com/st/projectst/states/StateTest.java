package com.st.projectst.states;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.controller.MapController;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.*;
import com.st.projectst.model.menu.*;
import com.st.projectst.viewer.Viewer;
import com.st.projectst.viewer.game.LevelViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StateTest {

    @Test
    void testStepWithStartState() throws IOException, URISyntaxException, FontFormatException {
        Start start = Mockito.mock(Start.class);
        StartState startState = new StartState(start);

        Main main = mock(Main.class);
        GUI gui = Mockito.mock(GUI.class);
        long time = 0;

        when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);
        startState.step(main, gui, time);
    }

    @Test
    void testStepWithPauseState() throws IOException, URISyntaxException, FontFormatException {
        Pause pause = Mockito.mock(Pause.class);
        PauseState pauseState = new PauseState(pause);

        Main main = mock(Main.class);
        GUI gui = Mockito.mock(GUI.class);
        long time = 0;

        when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);
        pauseState.step(main, gui, time);
    }

    @Test
    void testStepWithGameOverState() throws IOException, URISyntaxException, FontFormatException {
        GameOver gameOver = Mockito.mock(GameOver.class);
        GameOverState gameOverState = new GameOverState(gameOver);

        Main main = mock(Main.class);
        GUI gui = Mockito.mock(GUI.class);
        long time = 0;

        when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);
        gameOverState.step(main, gui, time);
    }

    @Test
    void testStepWithInstructionsState() throws IOException, URISyntaxException, FontFormatException {
        Instructions instructions = Mockito.mock(Instructions.class);
        InstructionsState instructionsState = new InstructionsState(instructions);

        Main main = mock(Main.class);
        GUI gui = Mockito.mock(GUI.class);
        long time = 0;

        when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);
        instructionsState.step(main, gui, time);
    }

    @Test
    void testStepWithWinState() throws IOException, URISyntaxException, FontFormatException {
        Win win = Mockito.mock(Win.class);
        WinState winState = new WinState(win);

        Main main = mock(Main.class);
        GUI gui = Mockito.mock(GUI.class);
        long time = 0;

        when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);
        winState.step(main, gui, time);
    }

    @Test
    void testStepWithLevelState()  {
        Map map = mock(Map.class);
        LevelState levelState = new LevelState(map);
        Controller<Map> controller = levelState.getController();
        assertEquals(MapController.class, controller.getClass());

        Viewer<Map> viewer = levelState.getViewer();
        assertEquals(LevelViewer.class, viewer.getClass());
    }

    @Test
    void testStateStep() throws IOException, FontFormatException, URISyntaxException {
        Main main = mock(Main.class);
        GUI gui = mock(GUI.class);
        Controller mockController = mock(Controller.class);
        Viewer mockViewer = mock(Viewer.class);

        State<Object> state = new State<>(new Object()) {
            @Override
            protected Controller<Object> getController() {
                return mockController;
            }

            @Override
            protected Viewer<Object> getViewer() {
                return mockViewer;
            }
        };

        when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);
        state.step(main, gui, 100L);
        verify(gui).getNextAction();
        verify(mockController).step(eq(main), eq(GUI.ACTION.NONE), eq(100L));
        verify(mockViewer).draw(eq(gui));
    }
}

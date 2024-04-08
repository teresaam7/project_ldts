package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.menu.Pause;
import com.st.projectst.states.LevelState;
import com.st.projectst.states.StartState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.FontFormatException;

import static org.mockito.Mockito.*;

public class PauseControllerTest {

    private Pause pause;
    private PauseController pauseController;
    private Main main;
    private LanternaGUI gui;

    @BeforeEach
    public void setUp() {
        pause = mock(Pause.class);
        pauseController = new PauseController(pause);
        main = mock(Main.class);
        gui = mock(LanternaGUI.class);
        when(main.getGui()).thenReturn(gui);
    }

    @Test
    public void testStepSelectContinue() throws IOException, URISyntaxException, FontFormatException {
        when(pause.isSelectedContinue()).thenReturn(true);
        LevelState levelState = mock(LevelState.class);
        when(pause.getGameState()).thenReturn(levelState);

        pauseController.step(main, GUI.ACTION.SELECT, 1000);

        verify(gui, times(1)).close();
        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(levelState);
    }

    @Test
    public void testStepDown() throws IOException, URISyntaxException, FontFormatException {
        pauseController.step(main, GUI.ACTION.DOWN, 1000);
        verify(pause, times(1)).nextOption();
    }
    @Test
    public void testStepSelectExit() throws IOException, URISyntaxException, FontFormatException {
        when(pause.isSelectedExit()).thenReturn(true);

        pauseController.step(main, GUI.ACTION.SELECT, 1000);

        verify(gui, times(1)).close();
        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(StartState.class));
    }

    @Test
    public void testStepOtherAction() throws IOException, URISyntaxException, FontFormatException {
        pauseController.step(main, GUI.ACTION.UP, 1000);

        verify(gui, never()).close();
        verify(main, never()).setGui(any(LanternaGUI.class));
        verify(main, never()).setState(any(LevelState.class));
    }
}


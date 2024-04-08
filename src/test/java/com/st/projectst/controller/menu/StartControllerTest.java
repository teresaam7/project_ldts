package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.MapBuilder;
import com.st.projectst.model.menu.Start;
import com.st.projectst.states.InstructionsState;
import com.st.projectst.states.LevelState;
import com.st.projectst.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.FontFormatException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StartControllerTest {

    private Start start;
    private StartController startController;
    private Main main;
    private LanternaGUI gui;

    @BeforeEach
    public void setUp() {
        start = mock(Start.class);
        startController = new StartController(start);
        main = mock(Main.class);
        gui = mock(LanternaGUI.class);
        when(main.getGui()).thenReturn(gui);
    }

    @Test
    public void testStepSelectStart() throws IOException, URISyntaxException, FontFormatException {
        when(start.isSelectedStart()).thenReturn(true);
        startController.step(main, GUI.ACTION.SELECT, 1000);

        verify(gui, times(1)).close();
        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(LevelState.class));
    }

    @Test
    public void testStepDown() throws IOException, URISyntaxException, FontFormatException {
        startController.step(main, GUI.ACTION.DOWN, 1000);
        verify(start, times(1)).nextOption();
    }
    @Test
    public void testStepSelectInstructions() throws IOException, URISyntaxException, FontFormatException {
        when(start.isSelectedInstructions()).thenReturn(true);
        startController.step(main, GUI.ACTION.SELECT, 1000);
        verify(gui, times(1)).close();
        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(InstructionsState.class));
    }

    @Test
    public void testStepSelectExit() throws IOException, URISyntaxException, FontFormatException {
        when(start.isSelectedExit()).thenReturn(true);
        startController.step(main, GUI.ACTION.SELECT, 1000);
        verify(main, never()).setGui(any(LanternaGUI.class));
        verify(main).setState(null);
    }

    @Test
    public void testStepOtherAction() throws IOException, URISyntaxException, FontFormatException {
        startController.step(main, GUI.ACTION.UP, 1000);
        verify(gui, never()).close();
        verify(main, never()).setGui(any(LanternaGUI.class));
        verify(main, never()).setState(any(State.class));
    }
}


package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.MapBuilder;
import com.st.projectst.model.menu.Start;
import com.st.projectst.model.menu.Win;
import com.st.projectst.states.GameOverState;
import com.st.projectst.states.LevelState;
import com.st.projectst.states.StartState;
import com.st.projectst.states.State;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class WinControllerTest {

    @Test
    public void testStepGoBackToLevels() throws IOException, URISyntaxException, FontFormatException {
        Win win = mock(Win.class);
        when(win.isSelectedGoBackToLevels()).thenReturn(true);

        Main main = mock(Main.class);
        LanternaGUI gui = mock(LanternaGUI.class);
        when(main.getGui()).thenReturn(gui);

        WinController winController = new WinController(win);

        winController.step(main, GUI.ACTION.SELECT, 1000);

        verify(gui).close();
        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(StartState.class));
    }

    @Test
    public void testStepDownAndUp() throws IOException, URISyntaxException, FontFormatException {
        Win win = mock(Win.class);
        WinController winController = new WinController(win);
        Main main = mock(Main.class);
        winController.step(main, GUI.ACTION.DOWN, 1000);
        verify(win, times(1)).nextOption();
        winController.step(main, GUI.ACTION.UP, 1000);
        verify(win, times(1)).nextOption();
    }

    @Test
    public void testStepContinue() throws IOException, URISyntaxException, FontFormatException {
        Win win = mock(Win.class);
        when(win.isSelectedContinue()).thenReturn(true);
        when(win.getLevel()).thenReturn(1);
        Main main = mock(Main.class);
        LanternaGUI gui = mock(LanternaGUI.class);
        when(main.getGui()).thenReturn(gui);

        WinController winController = new WinController(win);

        winController.step(main, GUI.ACTION.SELECT, 1000);

        verify(gui).close();
        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(LevelState.class));
    }

    @Test
    public void testStepContinue_Level3() throws IOException, URISyntaxException, FontFormatException {
        Win win = mock(Win.class);
        when(win.isSelectedContinue()).thenReturn(true);
        when(win.getLevel()).thenReturn(3);

        Main main = mock(Main.class);
        LanternaGUI gui = mock(LanternaGUI.class);
        when(main.getGui()).thenReturn(gui);

        WinController winController = new WinController(win);
        winController.step(main, GUI.ACTION.SELECT, 1000);

        verify(gui).close();
    }

    @Test
    public void testStepContinue_Level2() throws IOException, URISyntaxException, FontFormatException {
        Win win = mock(Win.class);
        when(win.isSelectedContinue()).thenReturn(true);
        when(win.getLevel()).thenReturn(2);

        Main main = mock(Main.class);
        LanternaGUI gui = mock(LanternaGUI.class);
        when(main.getGui()).thenReturn(gui);

        WinController winController = new WinController(win);

        winController.step(main, GUI.ACTION.SELECT, 1000);

        verify(gui).close();
        verify(main).setState(any(LevelState.class));
    }

    @Test
    public void testStepContinue_ReturnToStart() throws IOException, URISyntaxException, FontFormatException {
        Win win = mock(Win.class);
        when(win.isSelectedContinue()).thenReturn(true);
        when(win.getLevel()).thenReturn(4);

        Main main = mock(Main.class);
        LanternaGUI gui = mock(LanternaGUI.class);
        when(main.getGui()).thenReturn(gui);

        WinController winController = new WinController(win);
        winController.step(main, GUI.ACTION.SELECT, 1000);

        verify(gui).close();
        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(State.class));
    }

}


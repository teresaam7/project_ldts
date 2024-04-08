package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.menu.GameOver;
import com.st.projectst.states.StartState;
import com.st.projectst.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.FontFormatException;

import static org.mockito.Mockito.*;

public class GameOverControllerTest {

    private GameOver gameOver;
    private GameOverController gameOverController;
    private Main main;
    private LanternaGUI gui;

    @BeforeEach
    public void setUp() {
        gameOver = mock(GameOver.class);
        gameOverController = new GameOverController(gameOver);
        main = mock(Main.class);
        gui = mock(LanternaGUI.class);
        when(main.getGui()).thenReturn(gui);
    }


    @Test
    public void testStepSelectGoBack() throws IOException, URISyntaxException, FontFormatException {
        when(gameOver.isSelectedGoBack()).thenReturn(true);
        gameOverController.step(main, GUI.ACTION.SELECT, 1000);
        verify(gui, times(1)).close();
        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(StartState.class));
    }

    @Test
    public void testStepSelectNotGoBack() throws IOException, URISyntaxException, FontFormatException {
        when(gameOver.isSelectedGoBack()).thenReturn(false);
        gameOverController.step(main, GUI.ACTION.SELECT, 1000);
        verify(gui, never()).close();
        verify(main, never()).setGui(any(LanternaGUI.class));
        verify(main, never()).setState(any(StartState.class));
    }

}


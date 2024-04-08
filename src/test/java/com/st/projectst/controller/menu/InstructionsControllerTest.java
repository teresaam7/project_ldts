package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.menu.Instructions;
import com.st.projectst.states.StartState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.FontFormatException;
import static org.mockito.Mockito.*;

public class InstructionsControllerTest {

    private Instructions instructions;
    private InstructionsController instructionsController;
    private Main main;
    private LanternaGUI gui;

    @BeforeEach
    public void setUp() {
        instructions = mock(Instructions.class);
        instructionsController = new InstructionsController(instructions);
        main = mock(Main.class);
        gui = mock(LanternaGUI.class);
        when(main.getGui()).thenReturn(gui);
    }

    @Test
    public void testStepSelectBack() throws IOException, URISyntaxException, FontFormatException {
        when(instructions.isSelectedBack()).thenReturn(true);
        instructionsController.step(main, GUI.ACTION.SELECT, 1000);
        verify(gui, times(1)).close();
        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(StartState.class));
    }

    @Test
    public void testStepSelectNotBack() throws IOException, URISyntaxException, FontFormatException {
        when(instructions.isSelectedBack()).thenReturn(false);
        instructionsController.step(main, GUI.ACTION.SELECT, 1000);
        verify(gui, never()).close();
        verify(main, never()).setGui(any(LanternaGUI.class));
        verify(main, never()).setState(any(StartState.class));
    }

    @Test
    public void testStepOtherAction() throws IOException, URISyntaxException, FontFormatException {
        instructionsController.step(main, GUI.ACTION.UP, 1000);
        verify(gui, never()).close();
        verify(main, never()).setGui(any(LanternaGUI.class));
        verify(main, never()).setState(any(StartState.class));
    }
}


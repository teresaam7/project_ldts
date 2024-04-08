package com.st.projectst;

import com.st.projectst.gui.LanternaGUI;

import com.st.projectst.states.StartState;
import com.st.projectst.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MainTest {
    Main main;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        main = new Main();
    }

    @Test
    void testMainInitialization() {
        assertEquals(StartState.class, main.getState().getClass());
    }

    @Test
    void testSetState() {
        State newState = mock(State.class);
        main.setState(newState);

        assertEquals(newState, main.getState());
    }

    @Test
    void testSetGui() {
        LanternaGUI newGui = mock(LanternaGUI.class);
        main.setGui(newGui);

        assertEquals(newGui, main.getGui());
    }

}

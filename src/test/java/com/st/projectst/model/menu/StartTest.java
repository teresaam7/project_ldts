package com.st.projectst.model.menu;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StartTest {

    @Test
    void testPreviousOption() {
        Start start = new Start(1);
        start.previousOption();
        assertTrue(start.isSelectedStart());
        start.setCurrentOption(0);
        start.previousOption();
        assertEquals(start.getCurrentOption(), 0);
        start.setCurrentOption(1);
        start.previousOption();
        assertEquals(start.getCurrentOption(), 0);
    }

    @Test
    void testNextOption() {
        Start start = new Start(0);
        start.nextOption();
        assertTrue(start.isSelectedInstructions());
        assertFalse(start.isSelectedStart());
        assertFalse(start.isSelectedExit());
        start.setCurrentOption(0);
        start.nextOption();
        assertEquals(start.getCurrentOption(), 1);
        start.nextOption();
        assertEquals(start.getCurrentOption(), 2);
        start.nextOption();
        assertEquals(start.getCurrentOption(), 2);
        start.setCurrentOption(4);
        start.nextOption();
        assertEquals(start.getCurrentOption(), 4);
    }

    @Test
    void testGetOption() {
        Start start = new Start(2);
        String option = start.getOption(0);
        assertEquals("Start", option);
    }
    @Test
    void testIsSelectedExit() {
        Start start = new Start(2);
        assertTrue(start.isSelectedExit());
        start = new Start(0);
        assertFalse(start.isSelectedExit());
        start = new Start(1);
        assertFalse(start.isSelectedExit());
        assertEquals(start.isSelected(2), start.isSelectedExit());
    }

    @Test
    void testIsSelectedInstructions() {
        Start start = new Start(1);
        assertTrue(start.isSelectedInstructions());
        start = new Start(0);
        assertFalse(start.isSelectedInstructions());
        start = new Start(2);
        assertFalse(start.isSelectedInstructions());
        assertEquals(start.isSelected(1), start.isSelectedInstructions());
    }

    @Test
    void testGetNumberOptions() {
        Start start = new Start(0);
        int numberOfOptions = start.getNumberOptions();
        assertEquals(3, numberOfOptions);
    }
}


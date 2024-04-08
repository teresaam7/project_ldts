package com.st.projectst.model.menu;

import com.st.projectst.model.Position;
import com.st.projectst.model.menu.Instructions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InstructionsTest {

    @Test
    void testOptionsInitialization() {
        Instructions instructions = new Instructions();
        assertNotNull(instructions);
        assertFalse(instructions.getNumber() == 0);
        assertEquals(1, instructions.getNumber());
    }

    @Test
    void testInitialSelection() {
        Instructions instructions = new Instructions();
        assertTrue(instructions.isSelectedBack());
    }

    @Test
    void testIsSelected() {
        Instructions instructions = new Instructions();
        assertTrue(instructions.isSelected(0));
        assertFalse(instructions.isSelected(1));
        instructions.setCurrentOption(1);
        assertTrue(instructions.isSelected(1));
        assertFalse(instructions.isSelected(0));
    }

    @Test
    void testSelectBack() {
        Instructions instructions = new Instructions();
        assertEquals(instructions.isSelectedBack(), instructions.isSelected(0));
        instructions.setCurrentOption(1);
        assertFalse(instructions.isSelectedBack());
    }
}


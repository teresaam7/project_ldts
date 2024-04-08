package com.st.projectst.viewer.menu;

import com.groupcdg.pitest.annotations.DoNotMutate;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.menu.Instructions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.verify;

public class InstructionsViewerTest {
    private GUI gui;
    private Instructions instructions;

    @BeforeEach
    void setup() {
        gui = Mockito.mock(GUI.class);
        instructions = Mockito.mock(Instructions.class);
    }

    @DoNotMutate
    @Test
    void testDrawObject() throws IOException, FontFormatException {
        InstructionsViewer instructionsViewer = new InstructionsViewer(instructions);
        instructionsViewer.drawObject(gui);

        verify(gui).setBackgroundColor("#BA6156");
        verify(gui).drawImage(new Position(52, 11), "images/cat.png", 1);
        verify(gui).drawText(new Position(20, 3), "OO  OO OOOOO OO    OOOOO", "#f9dbbe");
        verify(gui).drawText(new Position(20, 4), "OO  OO OO    OO    OO OO", "#ffbc6e");
        verify(gui).drawText(new Position(20, 5), "OOOOOO OOOO  OO    OOOOO", "#FF9966");
        verify(gui).drawText(new Position(20, 6), "OO  OO OO    OO    OO   ", "#ff8066");
        verify(gui).drawText(new Position(20, 7), "OO  OO OOOOO OOOOO OO   ", "#ff9aab");
        verify(gui).drawText(new Position(5, 11), "-> Mari, our heroine, is looking for her kitten.", "#FFFFFF");
        verify(gui).drawText(new Position(5, 13), "To find her you will have to face the bats and", "#FFFFFF");
        verify(gui).drawText(new Position(5, 15), "ghosts present in the library. Just be careful", "#FFFFFF");
        verify(gui).drawText(new Position(5, 17), "with the traps, because the bats are skillfull.", "#FFFFFF");
        verify(gui).drawText(new Position(5, 19), "-> To avoid being trapped in the rooms,", "#FFFFFF");
        verify(gui).drawText(new Position(5, 21), "Mari has to collect a key.", "#FFFFFF");
        verify(gui).drawText(new Position(5, 25), "Rules:", "#FFA212");
        verify(gui).drawText(new Position(5, 27), "  -> ESC - Pause the game", "#FFFFFF");
        verify(gui).drawText(new Position(5, 29), "  -> Arrow Up    - Jump          ", "#FFFFFF");
        verify(gui).drawText(new Position(5, 31), "  -> Arrow Left  - Move left     ", "#FFFFFF");
        verify(gui).drawText(new Position(5, 33), "  -> Arrow Right - Move right    ", "#FFFFFF");
        verify(gui).drawText(new Position(5, 35), "  -> Touch a potion - Double jump", "#FFFFFF");
        verify(gui).drawText(new Position(5, 38), "  Click Enter to go back to the Menu", "#FFFFFF");
        verify(gui).drawText(new Position(48, 28), "--------------------", "#ff9aab");
        verify(gui).drawText(new Position(50, 30), " Game made by: ", "#FFFFFF");
        verify(gui).drawText(new Position(49, 32), " Sofia Goncalves ", "#FFFFFF");
        verify(gui).drawText(new Position(48, 34), " Teresa Mascarenhas ", "#FFFFFF");
        verify(gui).drawText(new Position(48, 36), "--------------------", "#ff9aab");
    }
}

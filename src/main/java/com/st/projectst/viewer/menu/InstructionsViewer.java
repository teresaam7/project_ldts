package com.st.projectst.viewer.menu;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.menu.Instructions;
import com.st.projectst.viewer.Viewer;

import java.awt.*;
import java.io.IOException;

public class InstructionsViewer extends Viewer<Instructions> {
    public InstructionsViewer(Instructions instructions) {
        super(instructions);
    }

    @Override
    public void drawObject(GUI gui) throws IOException, FontFormatException {

        gui.setBackgroundColor("#BA6156");
        gui.drawImage(new Position(52, 11), "images/cat.png", 1);

        gui.drawText(new Position(20, 3), "OO  OO OOOOO OO    OOOOO", "#f9dbbe");
        gui.drawText(new Position(20, 4), "OO  OO OO    OO    OO OO", "#ffbc6e");
        gui.drawText(new Position(20, 5), "OOOOOO OOOO  OO    OOOOO", "#FF9966");
        gui.drawText(new Position(20, 6), "OO  OO OO    OO    OO   ", "#ff8066");
        gui.drawText(new Position(20, 7), "OO  OO OOOOO OOOOO OO   ", "#ff9aab");

        gui.drawText(new Position(5, 11), "-> Mari, our heroine, is looking for her kitten.", "#FFFFFF");
        gui.drawText(new Position(5, 13), "To find her you will have to face the bats and", "#FFFFFF");
        gui.drawText(new Position(5, 15), "ghosts present in the library. Just be careful", "#FFFFFF");
        gui.drawText(new Position(5, 17), "with the traps, because the bats are skillfull.", "#FFFFFF");
        gui.drawText(new Position(5, 19), "-> To avoid being trapped in the rooms,", "#FFFFFF");
        gui.drawText(new Position(5, 21), "Mari has to collect a key.", "#FFFFFF");

        gui.drawText(new Position(5, 25), "Rules:", "#FFA212");
        gui.drawText(new Position(5, 27), "  -> ESC - Pause the game", "#FFFFFF");
        gui.drawText(new Position(5, 29), "  -> Arrow Up    - Jump          ", "#FFFFFF");
        gui.drawText(new Position(5, 31), "  -> Arrow Left  - Move left     ", "#FFFFFF");
        gui.drawText(new Position(5, 33), "  -> Arrow Right - Move right    ", "#FFFFFF");
        gui.drawText(new Position(5, 35), "  -> Touch a potion - Double jump", "#FFFFFF");
        gui.drawText(new Position(5, 38), "  Click Enter to go back to the Menu", "#FFFFFF");

        gui.drawText(new Position(48, 28), "--------------------", "#ff9aab");
        gui.drawText(new Position(50, 30), " Game made by: ", "#FFFFFF");
        gui.drawText(new Position(49, 32), " Sofia Goncalves ", "#FFFFFF");
        gui.drawText(new Position(48, 34), " Teresa Mascarenhas ", "#FFFFFF");
        gui.drawText(new Position(48, 36), "--------------------", "#ff9aab");
    }
}

package com.st.projectst.viewer.menu;

import com.st.projectst.model.menu.Start;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.viewer.Viewer;

import java.awt.*;
import java.io.IOException;

public class StartViewer extends Viewer<Start> {
    public StartViewer(Start start) {
        super(start);
    }

    @Override
    public void drawObject(GUI gui) throws IOException, FontFormatException {
        gui.setBackgroundColor("#BA6156");
        gui.drawImage(new Position(22, 3), "images/key.png", 1);

        gui.drawText(new Position(5, 5), "Searching For", "#FFFFFF");
        gui.drawText(new Position(5, 6), "   Key-Ty    ", "#DAA520");

        for (int i = 0; i < getModel().getNumberOptions(); i++) {
            gui.drawText(
                    new Position(5, 10 + i),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFFFFF" : "#F1A55E");
        }
    }
}

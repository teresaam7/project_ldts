package com.st.projectst.viewer.menu;

import com.st.projectst.model.menu.Win;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.viewer.Viewer;

import java.awt.*;
import java.io.IOException;

public class WinViewer extends Viewer<Win> {
    public WinViewer(Win win) {
        super(win);
    }

    @Override
    public void drawObject(GUI gui) throws IOException, FontFormatException {
        gui.setBackgroundColor("#BA6156");

        gui.drawText(new Position(5, 5), "OO        OO  OO  OO    OOO", "#f9dbbe");
        gui.drawText(new Position(5, 6), " OO      OO   OO  OOO   OO ", "#ffbc6e");
        gui.drawText(new Position(5, 7), "  OO OO OO    OO  OO OO OO ", "#FF9966");
        gui.drawText(new Position(5, 8), "   OO  OO     OO  OO   OOO ", "#ff8066");
        gui.drawText(new Position(5, 9), "   OO  OO     OO  OO    OO ", "#ff9aab");

        gui.drawImage(new Position(29, 4), "images/sword.png", 1.5);
        gui.drawText(new Position(7, 12), " *** Level Complete *** ", "#FFFFFF");

        for (int i = 0; i < getModel().getNumberOptions(); i++){
            gui.drawText(
                    new Position(14, 14 + i),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFFFFF" : "#F1A55E");
        }
    }
}
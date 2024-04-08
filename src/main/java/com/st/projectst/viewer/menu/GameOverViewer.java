package com.st.projectst.viewer.menu;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.menu.GameOver;
import com.st.projectst.viewer.Viewer;

public class GameOverViewer extends Viewer<GameOver> {
    public GameOverViewer(GameOver gameOver) {
        super(gameOver);
    }

    @Override
    public void drawObject(GUI gui) {
        gui.setBackgroundColor("#BA6156");

        gui.drawText(new Position(15, 3), "  OOOO                                  " , "#f9dbbe");
        gui.drawText(new Position(15, 4), " OOOOOOO                              " , "#f9dbbe");
        gui.drawText(new Position(15, 5), "OOOO      OOOOOO   OOOOO OOO    OOO     " , "#ffbc6e");
        gui.drawText(new Position(15, 6), "OO       OOOOOO    OOOOOOOOO   OOOOO    " , "#ffbc6e");
        gui.drawText(new Position(15, 7), "OO  OOO  OOO OO    OOO OOO OO  OO  OO  " , "#FF9966");
        gui.drawText(new Position(15, 8), "OO OOOO  OO  OO    OO  OO  OO  OOOOOO  " , "#FF9966");
        gui.drawText(new Position(15, 9), "OO   OO  OO OOO    OO  OO  OO  OOO      " , "#ff8066");
        gui.drawText(new Position(15, 10), "OOOOOOO  OOOOOOO  OOO OOO OOO  OOOOO  " , "#ff9aab");
        gui.drawText(new Position(15, 11), " OOOOO   OOOOO OO OOO OOO OOO   OOOO " , "#ff9aab");

        gui.drawText(new Position(18, 15), "  OOOO                            " , "#f9dbbe");
        gui.drawText(new Position(18, 16), " OOOOOOO                          " , "#f9dbbe");
        gui.drawText(new Position(18, 17), "OOO  OOO  OO  OOO   OOO    OOOO   " , "#ffbc6e");
        gui.drawText(new Position(18, 18), "OOO   OO  OO OOOO  OOOOOO  OOOOOO  " , "#ffbc6e");
        gui.drawText(new Position(18, 19), "OO    OO  OO  OO   OO  OO  OOO     " , "#FF9966");
        gui.drawText(new Position(18, 20), "OO    OO  OOOOO   OOOOOO   OO      " , "#FF9966");
        gui.drawText(new Position(18, 21), "OOO   OO   OOOO    OOO     OO      " , "#ff8066");
        gui.drawText(new Position(18, 22), " OOOOOOO   OOOO    OOOOO  OOO      " , "#ff9aab");
        gui.drawText(new Position(18, 23), "  OOOO     OOO      OOOO  OOO      " , "#ff9aab");


        gui.drawText(
                new Position(24, 29 ),
                getModel().getOption(0),
                getModel().isSelected(0) ? "#F1A55E" : "#FFFFFF"
        );

        gui.drawText(new Position(28, 31), "Click ENTER" , "#FFFFFF");
    }
}

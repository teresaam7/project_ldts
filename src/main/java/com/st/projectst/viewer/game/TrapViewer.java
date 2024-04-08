package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Trap;

public class TrapViewer implements GameObjectViewer<Trap> {
    @Override
    public void draw(Trap trap, GUI gui) {
        gui.drawTrap(trap.getPosition());
    }
}

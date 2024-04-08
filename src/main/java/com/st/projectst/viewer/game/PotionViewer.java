package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Potion;

public class PotionViewer implements GameObjectViewer<Potion>{
    @Override
    public void draw(Potion potion, GUI gui) {
        gui.drawPotion(potion.getPosition());
    }
}

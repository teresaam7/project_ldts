package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Mari;

import java.awt.*;
import java.io.IOException;

public class MariViewer implements GameObjectViewer<Mari> {
    @Override
    public void draw(Mari mari, GUI gui) throws IOException, FontFormatException {
        if (mari.getIsJumping()){
            if (mari.getIsWithPotion()){
                gui.drawMariDoubleJump(mari.getPosition());
            }
            else{
                gui.drawMariJump(mari.getPosition());
            }
        }
        else {
            gui.drawMari(mari.getPosition());
        }
    }
}
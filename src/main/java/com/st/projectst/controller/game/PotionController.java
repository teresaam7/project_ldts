package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.Potion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PotionController extends LevelController {
    private long lastToggle;
    private boolean isVisible;
    private List<Position> potionPositions;

    public PotionController(Map map) {
        super(map);
        this.lastToggle = 0;
        this.isVisible = true;
        this.potionPositions = new ArrayList<>();
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {
        if ((time - lastToggle) > 3000) {
            isVisible = !isVisible;

            if (isVisible) {
                for (int i = 0; i < getModel().getPotions().size(); i++) {
                    Potion potion = getModel().getPotions().get(i);
                    if (i < potionPositions.size()) {
                        Position previousPosition = potionPositions.get(i);
                        potion.setPosition(previousPosition);
                    }
                }
                potionPositions.clear();
            } else {
                potionPositions.clear();
                for (Potion potion : getModel().getPotions()) {
                    Position currentPosition = potion.getPosition();
                    potionPositions.add(currentPosition);
                    potion.setPosition(new Position(-10, -10));
                }
            }

            this.lastToggle = time;
        }
    }
    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean visible) {
        isVisible = visible;
    }

    public void setLastToggle(long lastToggle) {
        this.lastToggle = lastToggle;
    }

    public void setPotionPositions(List<Position> potionPositions) {
        this.potionPositions = potionPositions;
    }

    public List<Position> getPotionPositions() {
        return potionPositions;
    }
}

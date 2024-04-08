package com.st.projectst.model.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Enemy;

public class GhostEnemy extends Enemy {
    public GhostEnemy(Position position) {
        super(position);
    }

    @Override
    public Position move() {
        Position newPosition = new Position(getPosition());
        return newPosition.getRandomHorizontal();
    }
}

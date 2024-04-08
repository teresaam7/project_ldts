package com.st.projectst.model.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Position;

public abstract class Enemy extends GameObject {
    public Enemy(Position position) {
        super(position);
    }
    public abstract Position move();
}

package com.st.projectst.model.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Position;

public abstract class GameObject {
    private Position position;
    public GameObject(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
}

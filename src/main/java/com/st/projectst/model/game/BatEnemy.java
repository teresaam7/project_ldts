package com.st.projectst.model.game;

import com.st.projectst.model.Position;


public class BatEnemy extends Enemy implements EnemyObserver {
    private Position finalPosition;

    public BatEnemy(Position position) {
        super(position);
        finalPosition = position;
    }

    @Override
    public Position move() {
        Position newPosition = new Position(getPosition());
        if (getPosition().getY()<finalPosition.getY()) {
            newPosition.setY(newPosition.getY()+3);
        } else
            newPosition.setY(finalPosition.getY());

        return newPosition;
    }

    @Override
    public void update(Trap trap) {
        Position FinalP = new Position(trap.getPosition());
        FinalP.setY(trap.getPosition().getY()-13);
        this.finalPosition = FinalP;
    }

    public Position getFinalPosition() {
        return finalPosition;
    }
    public void setFinalPosition(Position finalPosition) {
        this.finalPosition = finalPosition;
    }
}

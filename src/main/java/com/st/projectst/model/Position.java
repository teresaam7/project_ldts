package com.st.projectst.model;

import java.util.Random;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Position(Position pos){
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public Position getLeft() {
        return new Position(x - 1, y);
    }
    public Position getRight() {
        return new Position(x + 1, y);
    }

    @SuppressWarnings("EqualsHashCode")
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Position))return false;

        Position position = (Position) other;

        if (Double.compare(position.x, x) != 0) return false;
        return Double.compare(position.y, y) == 0;
    }

    public Position getRandomHorizontal() {
        int n = (int) (random() * 2);
        switch (n) {
            case 0:
                return getRight();
            default:
                return getLeft();
        }
    }

    public double random() {
        Random random = new Random();
        return random.nextDouble();
    }
}

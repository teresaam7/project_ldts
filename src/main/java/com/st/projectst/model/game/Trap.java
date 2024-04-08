package com.st.projectst.model.game;

import com.st.projectst.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Trap extends GameObject {
    private List<EnemyObserver> observers;

    public Trap(Position position) {
        super(position);
        observers = new ArrayList<>();
    }

    public void addObserver(EnemyObserver observer) {
        observers.add(observer);
    }
    public void notifyObservers() {
        for (EnemyObserver observer : observers)
            observer.update(this);
    }
    public List<EnemyObserver> getObservers() {
        return observers;
    }
}

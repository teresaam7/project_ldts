package com.st.projectst.model.menu;

import com.st.projectst.states.LevelState;
import com.st.projectst.states.State;

import java.util.Arrays;
import java.util.List;

public class Pause {
    private State gameState;
    private final List<String> options;
    int currentOption = 0;

    public Pause(State gameState) {
        this.options = Arrays.asList("Continue", "Go back to menu");
        this.gameState = gameState;
    }

    public void previousOption() {
        if (currentOption > 0)
            currentOption--;
    }
    public void nextOption() {
        if (currentOption < (options.size() - 1))
            currentOption++;
    }
    public String getOption(int i) {
        return options.get(i);
    }

    public boolean isSelected(int i) {
        return currentOption == i;
    }
    public boolean isSelectedContinue() {
        return isSelected(0);
    }
    public boolean isSelectedExit() {
        return isSelected(1);
    }

    public int getNumberOptions() {
        return this.options.size();
    }
    public State getGameState() {
        return gameState;
    }
}

package com.st.projectst.model.menu;

import com.st.projectst.model.Position;

import java.util.Arrays;
import java.util.List;

public class Instructions {
    private final List<Position> options;
    private int currentOption = 0;

    public Instructions() {
        Position positionBack = new Position(5, 10);
        options = Arrays.asList(positionBack);
    }

    public boolean isSelected(int i) {
        return currentOption == i;
    }
    public boolean isSelectedBack() {
        return isSelected(0);
    }

    public int getNumber() {
        return options.size();
    }

    public void setCurrentOption(int currentOption) {
        this.currentOption = currentOption;
    }
}

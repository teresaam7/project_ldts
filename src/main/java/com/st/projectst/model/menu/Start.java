package com.st.projectst.model.menu;

import com.st.projectst.model.Position;

import java.util.Arrays;
import java.util.List;

public class Start {
    private final List<String> options;
    private int currentOption;

    public Start(int selectedOption) {
        this.currentOption = selectedOption;
        this.options = Arrays.asList("Start", "Instructions", "Exit");
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
    public boolean isSelectedStart() {
        return isSelected(0);
    }
    public boolean isSelectedInstructions() {
        return isSelected(1);
    }
    public boolean isSelectedExit() {
        return isSelected(2);
    }

    public int getCurrentOption() {
        return currentOption;
    }
    public void setCurrentOption(int currentOption) {
        this.currentOption = currentOption;
    }

    public int getNumberOptions() {
        return options.size();
    }
}

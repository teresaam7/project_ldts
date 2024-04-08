package com.st.projectst.model.menu;

import java.util.Arrays;
import java.util.List;

public class Win {
    private final List<String> options;
    private int currentOption = 0;
    private int level;

    public Win(int level) {
        this.options = Arrays.asList(" Go Back ", "Next Level");
        this.level = level;
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

    public int getCurrentOption() {
        return currentOption;
    }
    public void setCurrentOption(int currentOption) {
        this.currentOption = currentOption;
    }

    public boolean isSelected(int i) {
        return currentOption == i;
    }
    public boolean isSelectedGoBackToLevels() {
        return isSelected(0);
    }
    public boolean isSelectedContinue() {
        return isSelected(1);
    }

    public int getNumberOptions() {
        return this.options.size();
    }
    public int getLevel() {
        return level;
    }
}

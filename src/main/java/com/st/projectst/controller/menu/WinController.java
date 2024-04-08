package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.MapBuilder;
import com.st.projectst.model.menu.Start;
import com.st.projectst.model.menu.Win;
import com.st.projectst.states.LevelState;
import com.st.projectst.states.StartState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinController extends Controller<Win> {

    public WinController(Win win) {
        super(win);
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException {
        switch (action) {
            case UP:
                getModel().previousOption();
                break;
            case DOWN:
                getModel().nextOption();
                break;
            case SELECT:
                if (getModel().isSelectedGoBackToLevels()) {
                    main.getGui().close();
                    LanternaGUI gui = new LanternaGUI(40, 20, 24);
                    main.setGui(gui);
                    main.setState(new StartState(new Start(0)));
                }
                else if (getModel().isSelectedContinue()){
                    if (getModel().getLevel() == 4){
                        // Go back to the beginning of the game
                        main.getGui().close();
                        LanternaGUI gui = new LanternaGUI(40, 20, 24);
                        main.setGui(gui);
                        main.setState(new StartState(new Start(0)));
                    }
                    else {
                        main.getGui().close();
                        LanternaGUI gui = new LanternaGUI(120, 60, 8);
                        main.setGui(gui);

                        if (getModel().getLevel() == 3){
                            main.setState(new LevelState(new MapBuilder(3).buildMap()));
                        }
                        else if (getModel().getLevel() == 2){
                            main.setState(new LevelState(new MapBuilder(2).buildMap()));
                        }
                        else if (getModel().getLevel() == 1){
                            main.setState(new LevelState(new MapBuilder(1).buildMap()));
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}

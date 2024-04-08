package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.MapBuilder;
import com.st.projectst.model.menu.Instructions;
import com.st.projectst.model.menu.Start;
import com.st.projectst.states.InstructionsState;
import com.st.projectst.states.LevelState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class StartController extends Controller<Start> {
    public StartController(Start start) {
        super(start);
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
                if (getModel().isSelectedStart()) {
                    main.getGui().close();
                    LanternaGUI gui = new LanternaGUI(120, 60, 8);
                    main.setGui(gui);
                    main.setState(new LevelState(new MapBuilder(1).buildMap()));
                }
                else if (getModel().isSelectedInstructions()) {
                    main.getGui().close();
                    LanternaGUI gui = new LanternaGUI(74, 40, 13);
                    main.setGui(gui);
                    main.setState(new InstructionsState(new Instructions()));
                }
                else if (getModel().isSelectedExit()) main.setState(null);
                break;
            default:
                break;
        }
    }
}

package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.menu.Pause;
import com.st.projectst.model.menu.Start;
import com.st.projectst.states.StartState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PauseController extends Controller<Pause> {
    public PauseController(Pause pause) {
        super(pause);
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
                if (getModel().isSelectedContinue()) {
                    main.getGui().close();
                    LanternaGUI gui = new LanternaGUI(120, 60, 8);
                    main.setGui(gui);
                    main.setState(getModel().getGameState());
                }
                if (getModel().isSelectedExit()){
                    main.getGui().close();
                    LanternaGUI gui = new LanternaGUI(40, 20, 24);
                    main.setGui(gui);
                    main.setState(new StartState(new Start(0)));
                }
                break;
            default:
                break;
        }
    }
}

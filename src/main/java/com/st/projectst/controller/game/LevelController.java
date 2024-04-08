package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.MapBuilder;
import com.st.projectst.model.menu.Start;

import java.io.IOException;

public abstract class LevelController extends Controller<Map> {
    public LevelController(Map map) {
        super(map);
    }

}


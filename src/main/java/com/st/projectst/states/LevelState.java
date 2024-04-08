package com.st.projectst.states;

import com.st.projectst.controller.Controller;
import com.st.projectst.controller.MapController;
import com.st.projectst.model.game.Map;
import com.st.projectst.viewer.Viewer;
import com.st.projectst.viewer.game.LevelViewer;

public class LevelState extends State<Map>{
    public LevelState(Map map) {
        super(map);
    }

    @Override
    protected Controller<Map> getController() {
        return new MapController(getModel());
    }
    @Override
    protected Viewer<Map> getViewer() {
        return new LevelViewer(getModel());
    }
}
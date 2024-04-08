package com.st.projectst.states;

import com.st.projectst.controller.Controller;
import com.st.projectst.controller.menu.GameOverController;
import com.st.projectst.model.menu.GameOver;
import com.st.projectst.viewer.Viewer;
import com.st.projectst.viewer.menu.GameOverViewer;

public class GameOverState extends State<GameOver> {
    public GameOverState(GameOver gameover) {
        super(gameover);
    }

    @Override
    protected Viewer<GameOver> getViewer() {return new GameOverViewer(getModel());}

    @Override
    protected Controller<GameOver> getController() {
        return new GameOverController(getModel());
    }
}

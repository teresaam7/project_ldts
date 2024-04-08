package com.st.projectst.controller;

import com.st.projectst.Main;
import com.st.projectst.controller.game.*;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.menu.GameOver;
import com.st.projectst.model.menu.Pause;
import com.st.projectst.model.menu.Start;
import com.st.projectst.model.menu.Win;
import com.st.projectst.states.GameOverState;
import com.st.projectst.states.PauseState;
import com.st.projectst.states.StartState;
import com.st.projectst.states.WinState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class  MapController extends LevelController {
    private MariController mariController;
    private GhostEnemyController ghostController;
    private BatEnemyController batController;
    private PlatformController platformController;
    private CameraController cameraController;
    private PotionController potionController;
    private int cameraCount;

    public MapController(Map map) {
        super(map);

        this.mariController = new MariController(map);
        this.ghostController = new GhostEnemyController(map);
        this.batController = new BatEnemyController(map);
        this.platformController = new PlatformController(map);
        this.cameraController = new CameraController(map);
        this.potionController = new PotionController(map);
        this.cameraCount = 0;
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException {
        if (action == GUI.ACTION.QUIT) {
            main.setState(new StartState(new Start(0)));

        } else if (action == GUI.ACTION.PAUSE) {
            main.getGui().close();
            LanternaGUI gui = new LanternaGUI(53, 27, 18);
            main.setGui(gui);
            main.setState(new PauseState(new Pause(main.getState())));

        } else if (getModel().isAtDoor(getModel().getMari().getPosition()) && getModel().getMari().getWithKey()) {
            main.getGui().close();
            LanternaGUI gui = new LanternaGUI(53, 27, 18);
            main.setGui(gui);
            main.setState(new WinState(new Win(getModel().getCurrentLevel() + 1)));

        } else if (getModel().getMari().getRemainingLives() == 0) {
            main.getGui().close();
            LanternaGUI gui = new LanternaGUI(74, 40, 13);
            main.setGui(gui);
            main.setState(new GameOverState(new GameOver()));

        } else {
            mariController.step(main, action, time);
            ghostController.step(main, action, time);
            batController.step(main, action, time);
            platformController.step(main, action, time);
            potionController.step(main, action, time);

            if (cameraCount < 2 && (getModel().getMari().getPosition().getX() == 100)){
                cameraController.step(main, action, time);
                cameraCount++;
            }
        }
    }


    public void setMariController(MariController mariController){
        this.mariController = mariController;
    }
    public MariController getMariController() { return this.mariController; }

    public void setGhostController(GhostEnemyController ghostController) { this.ghostController = ghostController; }
    public GhostEnemyController getGhostController() { return this.ghostController; }

    public void setBatController(BatEnemyController batController) { this.batController = batController; }
    public BatEnemyController getBatController() { return this.batController; }

    public void setPlatformController(PlatformController platformController) { this.platformController = platformController; }
    public PlatformController getPlatformController() { return this.platformController; }

    public void setPotionController(PotionController potionController) { this.potionController = potionController; }
    public PotionController getPotionController() { return this.potionController; }

    public void setCameraController(CameraController cameraController) { this.cameraController = cameraController; }
    public CameraController getCameraController() { return this.cameraController; }

    public void setCameraCount(int cameraCount) {this.cameraCount = cameraCount; }
    public int getCameraCount() { return this.cameraCount; }
}

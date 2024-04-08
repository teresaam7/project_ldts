package com.st.projectst;

import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.menu.Start;
import com.st.projectst.states.StartState;
import com.st.projectst.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    private LanternaGUI gui;
    private State state;

    public Main() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(40, 20, 24);
        this.state = new StartState(new Start(0));
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        new Main().start();
    }

    public LanternaGUI getGui() { return this.gui; }
    public State getState() {
        return state;
    }
    public void setGui(LanternaGUI gui) { this.gui = gui; }
    public void setState(State state) {
        this.state = state;
    }

    public void start() throws IOException, FontFormatException, URISyntaxException {
        int FPS = 30;
        int frameTime = 400 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                // Caught exception
            }
        }

        gui.close();
        System.exit(0);
    }
}
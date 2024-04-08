package com.st.projectst.controller;
import com.st.projectst.Main;

import com.st.projectst.controller.game.*;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.*;
import com.st.projectst.model.game.*;
import com.st.projectst.states.GameOverState;
import com.st.projectst.states.PauseState;
import com.st.projectst.states.StartState;
import com.st.projectst.states.WinState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.FontFormatException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MapControllerTest {
    private Map map;
    private MapController mapController;
    private Main main;

    @BeforeEach
    void setUp() {
        map = mock(Map.class);
        mapController = new MapController(map);

        MariController mockMariController = mock(MariController.class);
        mapController.setMariController(mockMariController);

        GhostEnemyController mockGhostController = mock(GhostEnemyController.class);
        mapController.setGhostController(mockGhostController);

        BatEnemyController mockBatController = mock(BatEnemyController.class);
        mapController.setBatController(mockBatController);

        PlatformController mockPlatformController = mock(PlatformController.class);
        mapController.setPlatformController(mockPlatformController);

        PotionController mockPotionController = mock(PotionController.class);
        mapController.setPotionController(mockPotionController);

        CameraController mockCameraController = mock(CameraController.class);
        mapController.setCameraController(mockCameraController);

        main = mock(Main.class);
    }

    @Test
    void testStepQuit() throws IOException, URISyntaxException, FontFormatException {
        when(map.isAtDoor(any())).thenReturn(false);

        mapController.step(main, GUI.ACTION.QUIT, 1000);

        verify(main).setState(any(StartState.class));
    }

    @Test
    void testStepPause() throws IOException, URISyntaxException, FontFormatException {
        when(main.getGui()).thenReturn(mock(LanternaGUI.class));

        mapController.step(main, GUI.ACTION.PAUSE, 1000);

        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(PauseState.class));
    }

    @Test
    void testStepWin() throws IOException, URISyntaxException, FontFormatException {
        when(map.isAtDoor(any())).thenReturn(true);
        when(map.getMari()).thenReturn(mock(Mari.class));
        when(map.getCurrentLevel()).thenReturn(1);
        when(main.getGui()).thenReturn(mock(LanternaGUI.class));

        Mari mari = mock(Mari.class);
        when(map.getMari()).thenReturn(mari);
        when(mari.getWithKey()).thenReturn(true);

        mapController.step(main, GUI.ACTION.NONE, 1000);

        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(WinState.class));
    }

    @Test
    void testStepGameOver() throws IOException, URISyntaxException, FontFormatException {
        Mari mari = mock(Mari.class);
        when(map.getMari()).thenReturn(mari);
        when(mari.getRemainingLives()).thenReturn(0);
        when(main.getGui()).thenReturn(mock(LanternaGUI.class));

        mapController.step(main, GUI.ACTION.NONE, 1000);

        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(GameOverState.class));
    }

    @Test
    void testStepUp() throws IOException, URISyntaxException, FontFormatException {
        Mari mari = mock(Mari.class);
        when(map.getMari()).thenReturn(mari);
        when(mari.getRemainingLives()).thenReturn(1);
        when(mari.getPosition()).thenReturn(new Position(0,0));
        when(map.isAtDoor(any())).thenReturn(false);
        when(main.getGui()).thenReturn(mock(LanternaGUI.class));

        mapController.step(main, GUI.ACTION.UP, 1000);

        verify(mapController.getMariController(), times(1)).step(main, GUI.ACTION.UP, 1000);
        verify(mapController.getGhostController(), times(1)).step(main, GUI.ACTION.UP, 1000);
        verify(mapController.getBatController(), times(1)).step(main, GUI.ACTION.UP, 1000);
        verify(mapController.getPlatformController(), times(1)).step(main, GUI.ACTION.UP, 1000);
        verify(mapController.getPotionController(), times(1)).step(main, GUI.ACTION.UP, 1000);
    }

    @Test
    void testStepLeft() throws IOException, URISyntaxException, FontFormatException {
        Mari mari = mock(Mari.class);
        when(map.getMari()).thenReturn(mari);
        when(mari.getRemainingLives()).thenReturn(1);
        when(mari.getPosition()).thenReturn(new Position(0,0));
        when(map.isAtDoor(any())).thenReturn(false);
        when(main.getGui()).thenReturn(mock(LanternaGUI.class));

        mapController.step(main, GUI.ACTION.LEFT, 1000);

        verify(mapController.getMariController(), times(1)).step(main, GUI.ACTION.LEFT, 1000);
        verify(mapController.getGhostController(), times(1)).step(main, GUI.ACTION.LEFT, 1000);
        verify(mapController.getBatController(), times(1)).step(main, GUI.ACTION.LEFT, 1000);
        verify(mapController.getPlatformController(), times(1)).step(main, GUI.ACTION.LEFT, 1000);
        verify(mapController.getPotionController(), times(1)).step(main, GUI.ACTION.LEFT, 1000);
    }

    @Test
    void testStepRight() throws IOException, URISyntaxException, FontFormatException {
        Mari mari = mock(Mari.class);
        when(map.getMari()).thenReturn(mari);
        when(mari.getRemainingLives()).thenReturn(1);
        when(mari.getPosition()).thenReturn(new Position(0,0));
        when(map.isAtDoor(any())).thenReturn(false);
        when(main.getGui()).thenReturn(mock(LanternaGUI.class));

        mapController.step(main, GUI.ACTION.RIGHT, 1000);

        verify(mapController.getMariController(), times(1)).step(main, GUI.ACTION.RIGHT, 1000);
        verify(mapController.getGhostController(), times(1)).step(main, GUI.ACTION.RIGHT, 1000);
        verify(mapController.getBatController(), times(1)).step(main, GUI.ACTION.RIGHT, 1000);
        verify(mapController.getPlatformController(), times(1)).step(main, GUI.ACTION.RIGHT, 1000);
        verify(mapController.getPotionController(), times(1)).step(main, GUI.ACTION.RIGHT, 1000);
    }

    @Test
    void testMoveCamera() throws IOException, URISyntaxException, FontFormatException {
        Mari mari = mock(Mari.class);
        when(map.getMari()).thenReturn(mari);
        when(mari.getRemainingLives()).thenReturn(1);
        when(mari.getPosition()).thenReturn(new Position(100,0));
        when(map.isAtDoor(any())).thenReturn(false);
        when(main.getGui()).thenReturn(mock(LanternaGUI.class));

        mapController.setCameraCount(0);

        mapController.step(main, GUI.ACTION.RIGHT, 1000);

        verify(mapController.getCameraController(), times(1)).step(main, GUI.ACTION.RIGHT, 1000);
        assertEquals(1, mapController.getCameraCount());
    }

}


package com.st.projectst.controller.game;
import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class PlatformControllerTest {
    private Main main;
    private Map mockedMap;
    private PlatformController platformController;

    @BeforeEach
    void setUp() {
        main = Mockito.mock(Main.class);
        mockedMap = Mockito.mock(Map.class);
        platformController = new PlatformController(mockedMap);
    }

    @Test
    void testStep() throws IOException {
        List<Platform> platformList = new ArrayList<>();
        Platform mockedPlatform1 = Mockito.mock(Platform.class);
        Platform mockedPlatform2 = Mockito.mock(Platform.class);
        platformList.add(mockedPlatform1);
        platformList.add(mockedPlatform2);

        when(mockedMap.getPlatforms()).thenReturn(platformList);

        long time = 200;
        GUI.ACTION action = GUI.ACTION.NONE;
        platformController.step(main, action, time);
        verify(mockedPlatform1, times(1)).moveAllPlatforms();
        verify(mockedPlatform2, times(1)).moveAllPlatforms();

    }

    @Test
    void testLongSubtractionWithAddition() throws IOException {
        Platform platform = new Platform(new Position(5, 5));
        List<Platform> platforms = Arrays.asList(platform);
        when(mockedMap.getPlatforms()).thenReturn(platforms);

        platformController.setLastMove(50);
        platformController.step(main, GUI.ACTION.NONE, 150);

        for(Platform p: platforms){
            assertEquals(new Position(5,5), p.getPosition());
        }
        platformController.step(main, GUI.ACTION.NONE, 151);
        for(Platform p: platforms){
            assertNotEquals(5, p.getPosition().getY());
        }
    }
}

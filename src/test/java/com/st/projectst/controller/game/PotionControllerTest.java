package com.st.projectst.controller.game;
import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.Potion;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PotionControllerTest {
    private PotionController controller;

    private Map map;
    private Main main;

    @BeforeEach
    void setUp() {
        map = mock(Map.class);
        controller = new PotionController(map);
        main = mock(Main.class);
    }

    @Test
    void testPotionVisibilityToggle() throws IOException {
        Map mockedMap = Mockito.mock(Map.class);
        Potion potion1 = Mockito.mock(Potion.class);
        Potion potion2 = Mockito.mock(Potion.class);

        List<Potion> potions = new ArrayList<>();
        potions.add(potion1);
        potions.add(potion2);

        when(mockedMap.getPotions()).thenReturn(potions);

        Position position1 = new Position(10, 10);
        Position position2 = new Position(20, 20);

        when(potion1.getPosition()).thenReturn(position1);
        when(potion2.getPosition()).thenReturn(position2);

        PotionController potionController = new PotionController(mockedMap);
        long currentTime = 0;
        boolean initialVisibility = true;

        for (int i = 0; i < 10; i++) {
            currentTime += 1000;
            potionController.step(null, null, currentTime);

            boolean isVisible = arePotionsVisible(potion1, potion2);
            if (i < 4){
                assertEquals(initialVisibility, isVisible);
            }
            else if (i == 6 || i == 8){
                assertNotEquals(initialVisibility, isVisible);
            }
            if (i >= 3) {
                initialVisibility = !initialVisibility;
            }
        }
    }
    private boolean arePotionsVisible(Potion potion1, Potion potion2) {
        return potion1.getPosition().getX() != -10 && potion2.getPosition().getX() != -10;
    }

    @Test
    void testChangedConditionalBoundary() throws IOException {
        Map mockedMap = Mockito.mock(Map.class);
        PotionController potionController = new PotionController(mockedMap);

        potionController.step(null, null, 4000);

        boolean isVisibleAfterToggle = potionController.isVisible();
        assertFalse(isVisibleAfterToggle);
    }

    @Test
    void testReplacedBooleanReturn() throws IOException {
        Map mockedMap = Mockito.mock(Map.class);
        PotionController potionController = new PotionController(mockedMap);

        potionController.step(null, GUI.ACTION.NONE, 4000);

        assertFalse(potionController.isVisible());
    }

    @Test
    void testPotionNonVisibilityLoop() throws IOException {
        Map mockedMap = Mockito.mock(Map.class);
        PotionController potionController = new PotionController(mockedMap);

        List<Position> mockedPotionPositions = Mockito.mock(List.class);
        potionController.setPotionPositions(mockedPotionPositions);

        Position position1 = new Position(1, 1);
        Position position2 = new Position(2, 2);

        when(mockedPotionPositions.size()).thenReturn(2);
        when(mockedPotionPositions.get(0)).thenReturn(position1);
        when(mockedPotionPositions.get(1)).thenReturn(position2);

        List<Potion> mockedPotions = new ArrayList<>();
        Potion potion1 = Mockito.mock(Potion.class);
        Potion potion2 = Mockito.mock(Potion.class);
        mockedPotions.add(potion1);
        mockedPotions.add(potion2);
        when(mockedMap.getPotions()).thenReturn(mockedPotions);

        when(potion1.getPosition()).thenReturn(new Position(3, 3));
        when(potion2.getPosition()).thenReturn(new Position(4, 4));


        potionController.setIsVisible(true);
        potionController.setLastToggle(0);
        assertTrue(potionController.isVisible());
        potionController.step(null, GUI.ACTION.NONE, 4000);
        verify(mockedPotionPositions, times(1)).clear();
        assertFalse(potionController.isVisible());
        verify(potion1, times(1)).setPosition(any(Position.class));
        verify(potion2, times(1)).setPosition(any(Position.class));
        assertFalse(potionController.getPotionPositions().isEmpty());
    }

    @Test
    void testToggleVisibilityAndUpdatePositions() throws IOException {
        Potion potion1 = Mockito.mock(Potion.class);
        Potion potion2 = Mockito.mock(Potion.class);
        List<Potion> potions = new ArrayList<>();
        potions.add(potion1);

        when(map.getPotions()).thenReturn(potions);

        Position position1 = new Position(10, 10);
        Position position3 = new Position(20, 20);

        when(potion1.getPosition()).thenReturn(position1);

        controller.setLastToggle(50);
        controller.step(main, GUI.ACTION.NONE, 3050);

        for (Potion p: potions){
            assertEquals( new Position(10, 10), p.getPosition());
        }
        List<Position> mockedPotionPositions = Mockito.mock(List.class);
        controller.setPotionPositions(mockedPotionPositions);

        Position position2 = new Position(1, 1);
        potions.add(potion2);

        when(potion2.getPosition()).thenReturn(position3);

        when(mockedPotionPositions.size()).thenReturn(1);
        when(mockedPotionPositions.get(0)).thenReturn(position2);

        controller.setIsVisible(false);
        assertFalse(controller.isVisible());
        controller.step(main, GUI.ACTION.NONE, 3051);

        assertTrue(controller.isVisible());

        verify(mockedPotionPositions, times(1)).clear();
        verify(potion1, times(1)).setPosition(any(Position.class));
        verify(potion2, never()).setPosition(any(Position.class));

        controller.setLastToggle(0);
        controller.step(main, GUI.ACTION.NONE, 3051);
        assertFalse(controller.isVisible());
    }

    @Test
    void testVisibilityAndNonUpdatePositions() throws IOException {
        Potion potion1 = Mockito.mock(Potion.class);
        List<Potion> potions = new ArrayList<>();
        potions.add(potion1);

        when(map.getPotions()).thenReturn(potions);

        Position position1 = new Position(10, 10);

        when(potion1.getPosition()).thenReturn(position1);

        controller.setLastToggle(50);
        controller.step(main, GUI.ACTION.NONE, 3050);

        for (Potion p: potions){
            assertEquals( new Position(10, 10), p.getPosition());
        }
        List<Position> mockedPotionPositions = Mockito.mock(List.class);
        controller.setPotionPositions(mockedPotionPositions);

        Position position2 = new Position(1, 1);

        when(mockedPotionPositions.size()).thenReturn(1);
        when(mockedPotionPositions.get(0)).thenReturn(position2);

        controller.setIsVisible(false);
        assertFalse(controller.isVisible());
        controller.step(main, GUI.ACTION.NONE, 3051);

        verify(mockedPotionPositions, times(1)).clear();
        verify(potion1, times(1)).setPosition(any(Position.class));

        controller.setLastToggle(0);
        controller.step(main, GUI.ACTION.NONE, 3051);
        assertFalse(controller.isVisible());

        List<Position> mockedPotionPositions2 = Mockito.mock(List.class);
        controller.setPotionPositions(mockedPotionPositions2);

        when(mockedPotionPositions2.size()).thenReturn(0);

        controller.setLastToggle(0);
        controller.step(main, GUI.ACTION.NONE, 3051);
        assertTrue(controller.isVisible());

        verify(potion1, times(2)).setPosition(any(Position.class));
    }
}
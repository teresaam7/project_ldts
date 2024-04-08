package com.st.projectst.model.menu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameOverTest {

    @Test
    void testOptionsInitialization() {
        GameOver gameOver = new GameOver();
        assertNotNull(gameOver);
        assertFalse(gameOver.getNumber() == 0);
        assertEquals(1, gameOver.getNumber());
    }
    @Test
    void testInitialSelection() {
        GameOver gameOver = new GameOver();
        assertTrue(gameOver.isSelectedGoBack());
    }


    @Test
    void testGetOption() {
        GameOver gameOver = new GameOver();
        String expectedOption = " Return to the Menu ";
        String retrievedOption = gameOver.getOption(0);

        assertEquals(expectedOption, retrievedOption);
    }
    @Test
    void testIsSelected() {
        GameOver gameOver = new GameOver();
        assertTrue(gameOver.isSelected(0));
        assertFalse(gameOver.isSelected(1));
        gameOver.setCurrentOption(1);
        assertTrue(gameOver.isSelected(1));
        assertFalse(gameOver.isSelected(0));
    }

    @Test
    void testSelectBack() {
        GameOver gameOver = new GameOver();
        assertEquals(gameOver.isSelectedGoBack(), gameOver.isSelected(0));
        gameOver.setCurrentOption(1);
        assertFalse(gameOver.isSelectedGoBack());
    }
}


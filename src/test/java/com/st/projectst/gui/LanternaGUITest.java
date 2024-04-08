package com.st.projectst.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Mari;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


public class LanternaGUITest {

    private Screen screen;
    private LanternaGUI lanternaGUI;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        lanternaGUI = new LanternaGUI(screen);

        tg = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
    }

    @Test
    void testDrawImage() {
        BufferedImage testImage = new BufferedImage(5, 5, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = testImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 5, 5);
        graphics.setColor(Color.RED);
        graphics.fillRect(1, 1, 3, 3);

        Position position = new Position(10, 10);

        lanternaGUI.drawImage(position, "gameObjects/mari1.png", 1);
    }

    @Test
    void testDrawMari() throws IOException, FontFormatException {
        Position position = new Position(10, 10);
        lanternaGUI.drawMari(position);
        lanternaGUI.drawMariDoubleJump(position);
        lanternaGUI.drawMariJump(position);
    }

    @Test
    void testDrawEnemies() throws IOException, FontFormatException {
        Position position = new Position(10, 10);
        lanternaGUI.drawGhostEnemy(position);
        lanternaGUI.drawBatEnemy(position);
    }

    @Test
    void testDrawObjects() throws IOException, FontFormatException {
        Position position = new Position(10, 10);
        lanternaGUI.drawKey(position);
        lanternaGUI.drawPotion(position);
        lanternaGUI.drawDoor(position);
    }

    @Test
    void testDrawWall() {
        Position position = new Position(10, 10);
        lanternaGUI.drawWall(position);
        verify(tg, times(1)).setForegroundColor(TextColor.Factory.fromString("#663B17"));
        verify(tg, times(1)).setBackgroundColor(TextColor.Factory.fromString("#CB762E"));
        verify(tg, times(1)).putString(10, 10, "W");
    }

    @Test
    void testDrawTrap() {
        Position position = new Position(10, 10);
        lanternaGUI.drawTrap(position);
        verify(tg, times(1)).setForegroundColor(TextColor.Factory.fromString("#663B17"));
        verify(tg, times(1)).setBackgroundColor(TextColor.Factory.fromString("#CB762E"));
        verify(tg, times(1)).putString(10, 10, "X");
    }

    @Test
    void testSetBackgroundColor() {
        String color = "#BA6156";
        lanternaGUI.setBackgroundColor(color);

        verify(tg, times(1)).setForegroundColor(new TextColor.RGB(186, 97, 86));
        verify(tg, times(1)).setBackgroundColor(new TextColor.RGB(186, 97, 86));
        verify(tg, times(1)).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), eq(' '));
    }

    @Test
    void testDrawText() {
        String color = "#FF0000";
        Position position = new Position(10, 10);
        String text = "Hello";
        lanternaGUI.drawText(position, text, color);
        verify(tg, times(1)).setForegroundColor(TextColor.Factory.fromString(color));
        verify(tg, times(1)).setBackgroundColor(TextColor.Factory.fromString("#BA6156"));
        verify(tg, times(1)).putString((int) position.getX(), (int) position.getY(), text);
    }

    @Test
    void testDrawCharacter() {
        String color = "#FF0000";
        lanternaGUI.drawCharacter(0,0, ' ', color, "#BA6156");
        verify(tg, times(1)).setForegroundColor(TextColor.Factory.fromString(color));
        verify(tg, times(1)).setBackgroundColor(TextColor.Factory.fromString("#BA6156"));
        verify(tg, times(1)).putString(0, 0, " ");
    }

    @Test
    void testLoadImage() {
        BufferedImage image = lanternaGUI.loadImage("/teste", 1);
        assertNull(image);
    }

    @Test
    void testNextActionNone() throws IOException {
        when(screen.pollInput()).thenReturn(null);

        GUI.ACTION action = lanternaGUI.getNextAction();
        GUI.ACTION expected = GUI.ACTION.NONE;

        assertEquals(expected, action);
    }

    @Test
    void testNextActionEOF() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.EOF));

        GUI.ACTION action = lanternaGUI.getNextAction();
        GUI.ACTION expected = GUI.ACTION.QUIT;

        assertEquals(expected, action);
    }

    @Test
    void testNextActionArrowRight() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowRight));

        GUI.ACTION action = lanternaGUI.getNextAction();
        GUI.ACTION expected = GUI.ACTION.RIGHT;

        assertEquals(expected, action);
    }

    @Test
    void testNextActionArrowLeft() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft));

        GUI.ACTION action = lanternaGUI.getNextAction();
        GUI.ACTION expected = GUI.ACTION.LEFT;

        assertEquals(expected, action);
    }

    @Test
    void testNextActionArrowUp() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowUp));

        GUI.ACTION action = lanternaGUI.getNextAction();
        GUI.ACTION expected = GUI.ACTION.UP;

        assertEquals(expected, action);
    }

    @Test
    void testNextActionArrowDown() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowDown));

        GUI.ACTION action = lanternaGUI.getNextAction();
        GUI.ACTION expected = GUI.ACTION.DOWN;

        assertEquals(expected, action);
    }

    @Test
    void testNextActionEscape() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.Escape));

        GUI.ACTION action = lanternaGUI.getNextAction();
        GUI.ACTION expected = GUI.ACTION.PAUSE;

        assertEquals(expected, action);
    }

    @Test
    void testNextActionEnter() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.Enter));

        GUI.ACTION action = lanternaGUI.getNextAction();
        GUI.ACTION expected = GUI.ACTION.SELECT;

        assertEquals(expected, action);
    }

    @Test
    void testNextActionOther() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.Backspace));

        GUI.ACTION action = lanternaGUI.getNextAction();
        GUI.ACTION expected = GUI.ACTION.NONE;

        assertEquals(expected, action);
    }

    @Test
    void testClear() {
        lanternaGUI.clear();
        verify(screen, times(1)).clear();
    }

    @Test
    void testRefresh() throws IOException {
        lanternaGUI.refresh();
        verify(screen, times(1)).refresh();
    }

    @Test
    void testClose() throws IOException {
        lanternaGUI.close();
        verify(screen, times(1)).close();
    }

}
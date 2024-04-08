package com.st.projectst.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.st.projectst.model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;

public class LanternaGUI implements GUI {
    private Screen screen;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI(int width, int height, int size) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont(size);
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();

        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont(int fontSize) throws FontFormatException, IOException {
        InputStream fontStream = getClass().getClassLoader().getResourceAsStream("Super Mario Bros. 2.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, fontSize);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);

        return fontConfig;
    }

    @Override
    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        switch (keyStroke.getKeyType()) {
            case EOF:
                return ACTION.QUIT;
            case ArrowRight:
                return ACTION.RIGHT;
            case ArrowLeft:
                return ACTION.LEFT;
            case ArrowUp:
                return ACTION.UP;
            case ArrowDown:
                return ACTION.DOWN;
            case Escape:
                return ACTION.PAUSE;
            case Enter:
                return ACTION.SELECT;
            default:
                break;
        }
        return ACTION.NONE;
    }


    @Override
    public void drawMari(Position position) {
        drawImage(position, "gameObjects/mari1.png", 1);
    }

    @Override
    public void drawMariJump(Position position) {
        drawImage(position, "gameObjects/mari2.png", 1);
    }

    @Override
    public void drawMariDoubleJump(Position position) {
        drawImage(position, "gameObjects/mari3.png", 1);
    }

    @Override
    public void drawGhostEnemy(Position position) {
        drawImage(position, "gameObjects/ghost.png", 1);
    }

    @Override
    public void drawBatEnemy(Position position) {
        drawImage(position, "gameObjects/bat.png", 1);
    }

    @Override
    public void drawKey(Position position) { drawImage(position, "gameObjects/miniKey.png", 1); }

    @Override
    public void drawDoor(Position position) { drawImage(position, "gameObjects/door.png", 1); }

    @Override
    public void drawPotion(Position position) { drawImage(position, "gameObjects/potion.png", 1); }

    @Override
    public void drawWall(Position position) { drawCharacter((int) position.getX(), (int) position.getY(), 'W', "#663B17", "#CB762E"); }

    @Override
    public void drawTrap(Position position) { drawCharacter((int) position.getX(), (int) position.getY(), 'X', "#663B17", "#CB762E"); }


    @Override
    public void setBackgroundColor(String color){
        TextGraphics tg = screen.newTextGraphics();
        setTextColor(tg, color);
        tg.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(1024, 512), ' ');
    }

    @Override
    public void setTextColor(TextGraphics tg, String color) {
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.setBackgroundColor(TextColor.Factory.fromString(color));
    }

    @Override
    public void drawText (Position position, String text, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.setBackgroundColor(TextColor.Factory.fromString("#BA6156"));
        tg.putString((int) position.getX(), (int) position.getY(), text);
    }

    @Override
    public void drawPixel(int x, int y, String color, TextGraphics tg) {
        setTextColor(tg, color);
        tg.putString(x, y, ".");
    }

    public void drawCharacter(int x, int y, char c, String color, String back) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.setBackgroundColor(TextColor.Factory.fromString(back));
        tg.putString(x, y, "" + c);
    }

    @Override
    public void drawImage(Position pos, String filename, double value) {
        BufferedImage image = loadImage(filename, value);
        TextGraphics tg = screen.newTextGraphics();

        Color backgroundColor = new Color(image.getRGB(0, 0));
        String backgroundHex = "#" + Integer.toHexString(backgroundColor.getRGB()).substring(2);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                String colorHex = "#" + Integer.toHexString(pixelColor.getRGB()).substring(2);

                if (!colorHex.equals(backgroundHex)) {
                    drawPixel((int) pos.getX() + x, (int) pos.getY() + y, colorHex, tg);
                }
            }
        }
    }

    @Override
    public BufferedImage loadImage(String filename, double value) {
        try (InputStream imageStream = getClass().getResourceAsStream("/" + filename)) {
            if (imageStream != null) {
                BufferedImage originalImage = ImageIO.read(imageStream);

                int targetWidth = (int) (originalImage.getWidth() / value);
                int targetHeight = (int) (originalImage.getHeight() / value);
                double aspectRatio = (double) targetWidth / targetHeight;

                int newWidth = targetWidth;
                int newHeight = targetHeight;

                if (aspectRatio > 1) {
                    newWidth = (int) (targetHeight * aspectRatio);
                } else {
                    newHeight = (int) (targetWidth / aspectRatio);
                }

                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
                g.dispose();

                return resizedImage;
            }

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return null;
    }


    @Override
    public void clear() { screen.clear(); }
    @Override
    public void refresh() throws IOException { screen.refresh(); }
    @Override
    public void close() throws IOException { screen.close(); }
}
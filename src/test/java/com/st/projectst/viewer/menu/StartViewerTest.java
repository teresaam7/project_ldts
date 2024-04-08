package com.st.projectst.viewer.menu;

import com.groupcdg.pitest.annotations.DoNotMutate;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.menu.Start;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class StartViewerTest {
    private GUI gui;
    private Start start;
    private StartViewer startViewer;

    @BeforeEach
    void setup() {
        gui = Mockito.mock(GUI.class);
        start = Mockito.mock(Start.class);
        startViewer = new StartViewer(start);

        when(start.getNumberOptions()).thenReturn(3);
        when(start.getOption(0)).thenReturn("Start");
        when(start.getOption(1)).thenReturn("Instructions");
        when(start.getOption(2)).thenReturn("Exit");
    }


    @Test
    void testDraw() throws IOException, FontFormatException {
        startViewer.draw(gui);

        verify(gui, times(1)).clear();
        verify(gui, times(1)).refresh();
    }

    @Test
    void testDrawObject() throws IOException, FontFormatException {
        startViewer.drawObject(gui);

        verify(gui).setBackgroundColor("#BA6156");
        verify(gui).drawImage(new Position(22, 3), "images/key.png", 1);

        verify(gui).drawText(new Position(5, 5), "Searching For", "#FFFFFF");
        verify(gui).drawText(new Position(5, 6), "   Key-Ty    ", "#DAA520");
    }

    @Test
    void testDrawSelected1() throws IOException, FontFormatException {
        when(start.isSelected(0)).thenReturn(true);
        when(start.isSelected(1)).thenReturn(false);
        when(start.isSelected(2)).thenReturn(false);

        startViewer.drawObject(gui);
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                verify(gui).drawText(
                        new Position(5, 10),
                        "Start",
                        "#FFFFFF");
            if (i == 1)
                verify(gui).drawText(
                        new Position(5, 10 + 1),
                        "Instructions",
                        "#F1A55E");
            if (i == 2)
                verify(gui).drawText(
                        new Position(5, 10 + 2),
                        "Exit",
                        "#F1A55E");
        }
        verify(start, times(0)).getOption(3);
        verify(start, times(0)).isSelected(3);
    }

    @Test
    void testDrawSelected2() throws IOException, FontFormatException {
        when(start.isSelected(0)).thenReturn(false);
        when(start.isSelected(1)).thenReturn(true);
        when(start.isSelected(2)).thenReturn(false);

        startViewer.drawObject(gui);
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                verify(gui).drawText(
                        new Position(5, 10),
                        "Start",
                        "#F1A55E");
            if (i == 1)
                verify(gui).drawText(
                        new Position(5, 10 + 1),
                        "Instructions",
                        "#FFFFFF");
            if (i == 2)
                verify(gui).drawText(
                        new Position(5, 10 + 2),
                        "Exit",
                        "#F1A55E");
        }
        verify(start, times(0)).getOption(3);
        verify(start, times(0)).isSelected(3);
    }

    @Test
    void testDrawSelected3() throws IOException, FontFormatException {
        when(start.isSelected(0)).thenReturn(false);
        when(start.isSelected(1)).thenReturn(false);
        when(start.isSelected(2)).thenReturn(true);

        startViewer.drawObject(gui);
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                verify(gui).drawText(
                        new Position(5, 10),
                        "Start",
                        "#F1A55E");
            if (i == 1)
                verify(gui).drawText(
                        new Position(5, 10 + 1),
                        "Instructions",
                        "#F1A55E");
            if (i == 2)
                verify(gui).drawText(
                        new Position(5, 10 + 2),
                        "Exit",
                        "#FFFFFF");
        }
        verify(start, times(0)).getOption(3);
        verify(start, times(0)).isSelected(3);
    }

}

package com.st.projectst.viewer.menu;

import com.groupcdg.pitest.annotations.DoNotMutate;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.menu.Pause;
import com.st.projectst.model.menu.Win;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class WinViewerTest {
    private GUI gui;
    private Win win;
    private WinViewer winViewer;

    @BeforeEach
    void setup() {
        gui = Mockito.mock(GUI.class);
        win = Mockito.mock(Win.class);
        winViewer = new WinViewer(win);

        when(win.getNumberOptions()).thenReturn(2);
        when(win.getOption(0)).thenReturn(" Go Back ");
        when(win.getOption(1)).thenReturn("Next Level");
    }


    @Test
    void testDrawObject() throws IOException, FontFormatException {
        winViewer.drawObject(gui);
        verify(gui).setBackgroundColor("#BA6156");

        verify(gui).drawText(new Position(5, 5), "OO        OO  OO  OO    OOO", "#f9dbbe");
        verify(gui).drawText(new Position(5, 6), " OO      OO   OO  OOO   OO ", "#ffbc6e");
        verify(gui).drawText(new Position(5, 7), "  OO OO OO    OO  OO OO OO ", "#FF9966");
        verify(gui).drawText(new Position(5, 8), "   OO  OO     OO  OO   OOO ", "#ff8066");
        verify(gui).drawText(new Position(5, 9), "   OO  OO     OO  OO    OO ", "#ff9aab");

        verify(gui).drawImage(new Position(29, 4), "images/sword.png", 1.5);
        verify(gui).drawText(new Position(7, 12), " *** Level Complete *** ", "#FFFFFF");
    }

    @Test
    void testDrawSelected1() throws IOException, FontFormatException {
        when(win.isSelected(0)).thenReturn(true);
        when(win.isSelected(1)).thenReturn(false);

        winViewer.drawObject(gui);
        for (int i = 0; i < 2; i++) {
            if (i == 0)
                verify(gui, times(1)).drawText(
                        new Position(14, 14),
                        " Go Back ",
                        "#FFFFFF");
            if (i == 1)
                verify(gui, times(1)).drawText(
                        new Position(14, 14 + 1),
                        "Next Level",
                        "#F1A55E");
        }
        verify(win, times(0)).getOption(2);
        verify(win, times(0)).isSelected(2);
    }

    @Test
    void testDrawSelected2() throws IOException, FontFormatException {
        when(win.isSelected(0)).thenReturn(false);
        when(win.isSelected(1)).thenReturn(true);

        winViewer.drawObject(gui);
        for (int i = 0; i < 2; i++) {
            if (i == 0)
                verify(gui, times(1)).drawText(
                        new Position(14, 14),
                        " Go Back ",
                        "#F1A55E");
            if (i == 1)
                verify(gui, times(1)).drawText(
                        new Position(14, 14 + 1),
                        "Next Level",
                        "#FFFFFF");
        }
        verify(win, times(0)).getOption(2);
        verify(win, times(0)).isSelected(2);
    }

}

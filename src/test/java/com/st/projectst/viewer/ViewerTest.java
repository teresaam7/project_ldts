package com.st.projectst.viewer;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.menu.Pause;
import com.st.projectst.model.menu.Start;
import com.st.projectst.states.StartState;
import com.st.projectst.viewer.menu.PauseViewer;
import com.st.projectst.viewer.menu.StartViewer;
import org.junit.jupiter.api.Test;


import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ViewerTest {
    @Test
    public void testDraw() throws IOException, FontFormatException {
        Start testModel = new Start(0);
        StartViewer concreteViewer = new StartViewer(testModel);
        GUI mockGUI = mock(GUI.class);
        StartViewer spyViewer = spy(concreteViewer);
        spyViewer.draw(mockGUI);
        verify(spyViewer).drawObject(any());

        Pause testModel2 = new Pause(new StartState(testModel));
        PauseViewer concreteViewer2 = new PauseViewer(testModel2);
        PauseViewer spyViewer2 = spy(concreteViewer2);
        spyViewer2.draw(mockGUI);
        verify(spyViewer2).drawObject(any());
    }
}


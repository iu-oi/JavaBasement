package javaDungeon.screen;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

public class WinScreen extends Screen {

    public WinScreen(AsciiPanel mainPanel, AsciiPanel subPanel) {
        super(mainPanel, subPanel);
        addBar(QUIT, Screen.ACTIVE_OPTION_COLOR, "menu", -1);
    }

    @Override
    public void refresh() {
        displayTitle("You won!");
        displayOptions();
    }

    @Override
    public Screen keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            selectPreviousBar();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            selectNextBar();
        }
        return this;
    }

    @Override
    public Screen keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            int value = getSelection().getValue();
            if (value == -1) {
                return new StartScreen(mainPaniel, subPanel);
            }
        }
        return this;
    }

}

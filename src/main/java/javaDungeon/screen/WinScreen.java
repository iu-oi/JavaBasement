package javaDungeon.screen;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

public class WinScreen implements Screen {

    @Override
    public void refresh(AsciiPanel terminal) {
        terminal.writeCenter("-- You won! --", 15);
        terminal.writeCenter("Press Enter to continue", 16);
    }

    @Override
    public Screen keyPressed(KeyEvent key) {
        return this;
    }

    @Override
    public Screen keyReleased(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ENTER) {
            return new StartScreen();
        } else {
            return this;
        }
    }

}

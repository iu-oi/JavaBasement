package javaDungeon.screen;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

public class LoseScreen implements Screen {

    @Override
    public void refresh(AsciiPanel terminal) {
        terminal.writeCenter("-- You lost --", 15);
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

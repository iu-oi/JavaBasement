package javaDungeon.screens;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import javaDungeon.Dungeon;

public class WinScreen implements Screen {

    private Dungeon frontend;

    public WinScreen(Dungeon frontend) {
        this.frontend = frontend;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("-- You won! --", 15);
        terminal.writeCenter("Press Enter to continue", 16);
    }

    @Override
    public Screen respondToKeyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ENTER) {
            return new StartScreen(frontend);
        }
        return this;
    }

    @Override
    public Screen respondToKeyReleased(KeyEvent key) {
        return this;
    }

}
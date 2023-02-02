package javaDungeon.screens;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import javaDungeon.MainFrame;

public class WinScreen implements Screen {

    private MainFrame mainFrame;

    WinScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("-- You won! --", 15);
        terminal.writeCenter("Press Enter to continue", 16);
    }

    @Override
    public Screen respondToKeyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ENTER) {
            return new StartScreen(mainFrame);
        }
        return this;
    }

    @Override
    public Screen respondToKeyReleased(KeyEvent key) {
        return this;
    }

}
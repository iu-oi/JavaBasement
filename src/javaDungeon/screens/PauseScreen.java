package javaDungeon.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import javaDungeon.MainFrame;

public class PauseScreen implements Screen {

    private MainFrame mainFrame;
    private PlayScreen playScreen;

    PauseScreen(PlayScreen playScreen, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.playScreen = playScreen;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("-- Game paused --", 15);
        terminal.writeCenter("0 Save", 16);
        terminal.writeCenter("1 Menu", 17);
        terminal.writeCenter("2 Resume", 18);
    }

    @Override
    public Screen respondToKeyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_0:
                playScreen.saveGame();
                return this;
            case KeyEvent.VK_1:
                return new StartScreen(mainFrame);
            case KeyEvent.VK_2:
                playScreen.resumeGame();
                return playScreen;
        }
        return this;
    }

    @Override
    public Screen respondToKeyReleased(KeyEvent key) {
        return this;
    }
}
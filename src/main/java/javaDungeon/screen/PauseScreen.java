package javaDungeon.screen;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class PauseScreen implements Screen {

    private PlayScreen playScreen;

    PauseScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    @Override
    public void refresh(AsciiPanel terminal) {
        terminal.writeCenter("-- Game paused --", 15);
        terminal.writeCenter("0 Save", 16);
        terminal.writeCenter("1 Menu", 17);
        terminal.writeCenter("2 Resume", 18);
    }

    @Override
    public Screen keyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_0:
                playScreen.saveWorld();
                return this;
            case KeyEvent.VK_1:
                return new StartScreen();
            case KeyEvent.VK_2:
                return playScreen;
            default:
                return this;
        }
    }

    @Override
    public Screen keyReleased(KeyEvent key) {
        return this;
    }

}

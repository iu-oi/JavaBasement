package javaDungeon.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import javaDungeon.Dungeon;

public class StartScreen implements Screen {

    private Dungeon frontend;

    public StartScreen(Dungeon frontend) {
        this.frontend = frontend;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("-- Java Dungeon --", 15);
        terminal.writeCenter("Choose your character", 16);
        terminal.write((char) 0x2, 10, 17, AsciiPanel.red);
        terminal.write((char) 0x2, 12, 17, AsciiPanel.yellow);
        terminal.write((char) 0x2, 14, 17, AsciiPanel.brightYellow);
        terminal.write((char) 0x2, 16, 17, AsciiPanel.green);
        terminal.write((char) 0x2, 18, 17, AsciiPanel.cyan);
        terminal.write((char) 0x2, 20, 17, AsciiPanel.brightBlue);
        terminal.write((char) 0x2, 22, 17, AsciiPanel.blue);
        terminal.writeCenter("1 2 3 4 5 6 7", 18);
    }

    @Override
    public Screen respondToKeyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_1:
                return new PlayScreen(1, frontend);
            case KeyEvent.VK_2:
                return new PlayScreen(2, frontend);
            case KeyEvent.VK_3:
                return new PlayScreen(3, frontend);
            case KeyEvent.VK_4:
                return new PlayScreen(4, frontend);
            case KeyEvent.VK_5:
                return new PlayScreen(5, frontend);
            case KeyEvent.VK_6:
                return new PlayScreen(6, frontend);
            case KeyEvent.VK_7:
                return new PlayScreen(7, frontend);
        }
        return this;
    }

    @Override
    public Screen respondToKeyReleased(KeyEvent key) {
        return this;
    }
}
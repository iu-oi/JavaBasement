package javaDungeon.screens;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

public interface Screen {
    public void displayOutput(AsciiPanel terminal);

    public Screen respondToKeyPressed(KeyEvent key);

    public Screen respondToKeyReleased(KeyEvent key);
}
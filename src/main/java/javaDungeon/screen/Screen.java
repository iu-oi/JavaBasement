package javaDungeon.screen;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import javaDungeon.event.Defeat;
import javaDungeon.event.Victory;

public interface Screen {

    public void refresh(AsciiPanel terminal) throws Victory, Defeat;

    public Screen keyPressed(KeyEvent key);

    public Screen keyReleased(KeyEvent key);

}

package javaDungeon;

import asciiPanel.AsciiPanel;
import javaDungeon.game.Thing;

public class Empty extends Thing {

    public Empty() {
        super(AsciiPanel.white, (char) 0x20);
    }

}

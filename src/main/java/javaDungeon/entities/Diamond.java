package javaDungeon.entities;

import asciiPanel.AsciiPanel;
import javaDungeon.game.Thing;

public class Diamond extends Thing {

    public Diamond() {
        super(AsciiPanel.yellow, (char) 0x4);
    }

}
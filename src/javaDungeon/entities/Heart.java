package javaDungeon.entities;

import asciiPanel.AsciiPanel;
import javaDungeon.game.Thing;

public class Heart extends Thing {

    public Heart() {
        super(AsciiPanel.red, (char) 0x3);
    }

}
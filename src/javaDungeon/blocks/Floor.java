package javaDungeon.blocks;

import asciiPanel.AsciiPanel;
import javaDungeon.game.Thing;

public class Floor extends Thing {

    public Floor() {
        super(AsciiPanel.white, (char) 0xfa);
    }
}

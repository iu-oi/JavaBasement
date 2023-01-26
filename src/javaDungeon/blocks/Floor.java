package javaDungeon.blocks;

import asciiPanel.AsciiPanel;
import javaDungeon.game.Thing;
import javaDungeon.game.World;

public class Floor extends Thing {

    public Floor(World world) {
        super(AsciiPanel.white, (char) 0xfa, world);
    }
}

package javaDungeon.blocks;

import asciiPanel.AsciiPanel;
import javaDungeon.game.Thing;
import javaDungeon.game.World;

public class Empty extends Thing {

    public Empty(World world) {
        super(AsciiPanel.white, (char) 0x20, world);
    }

}

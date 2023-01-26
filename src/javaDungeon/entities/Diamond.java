package javaDungeon.entities;

import asciiPanel.AsciiPanel;
import javaDungeon.game.Thing;
import javaDungeon.game.World;

public class Diamond extends Thing {

    public Diamond(World world) {
        super(AsciiPanel.yellow, (char) 0x4, world);
    }

}
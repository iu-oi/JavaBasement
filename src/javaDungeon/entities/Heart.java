package javaDungeon.entities;

import asciiPanel.AsciiPanel;
import javaDungeon.game.Thing;
import javaDungeon.game.World;

public class Heart extends Thing {

    public Heart(World world) {
        super(AsciiPanel.red, (char) 0x3, world);
    }

}
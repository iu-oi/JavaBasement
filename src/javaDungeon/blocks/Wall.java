package javaDungeon.blocks;

import asciiPanel.AsciiPanel;
import javaDungeon.game.Thing;
import javaDungeon.game.World;

public class Wall extends Thing {

    public Wall(char glyph, World world) {
        super(AsciiPanel.white, glyph, world);
    }
}

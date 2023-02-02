package javaDungeon.blocks;

import asciiPanel.AsciiPanel;
import javaDungeon.game.Thing;

public class Wall extends Thing {

    public Wall(char glyph) {
        super(AsciiPanel.white, glyph);
    }
}

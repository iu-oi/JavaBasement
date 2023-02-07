package javaDungeon.game.floor;

import java.awt.Color;

import javaDungeon.game.Thing;

public abstract class Floor extends Thing {

    public static final Color COLOR = new Color(8, 8, 8);

    Floor(char glyph) {
        super(COLOR, glyph);
    }

}

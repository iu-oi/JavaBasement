package javaDungeon.game.floor;

import java.awt.Color;

import javaDungeon.game.Thing;

public abstract class Floor extends Thing {

    public static final Color COLOR = Color.darkGray;

    Floor(char glyph) {
        super(COLOR, glyph);
    }

}

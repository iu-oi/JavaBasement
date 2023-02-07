package javaDungeon.game.wall;

import java.awt.Color;

import javaDungeon.game.Thing;

public abstract class Wall extends Thing {

    public static final Color COLOR = new Color(255, 255, 255);

    Wall(char glyph) {
        super(COLOR, glyph);
    }

}

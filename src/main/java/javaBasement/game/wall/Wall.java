package javaBasement.game.wall;

import java.awt.Color;

import javaBasement.game.Thing;

public abstract class Wall extends Thing {

    public static final Color COLOR = Color.WHITE;

    Wall(char glyph) {
        super(COLOR, glyph);
    }

}

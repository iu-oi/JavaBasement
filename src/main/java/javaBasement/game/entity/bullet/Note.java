package javaBasement.game.entity.bullet;

import java.awt.Color;

import javaBasement.game.*;

public class Note extends Bullet {

    public static final char GLYPH = (char) 0xd;
    public static final int STEP_INTERVAL = 1;
    public static final int DAMAGE = 2;

    public Note(Color color, World world, Direction direction) {
        super(color, GLYPH, world, STEP_INTERVAL, DAMAGE, direction);
    }

}

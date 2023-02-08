package javaDungeon.game.entity.Bullet;

import java.awt.Color;

import javaDungeon.game.*;

public class Grenade extends Bullet {

    public static final char GLYPH = (char) 0xed;
    public static final int STEP_INTERVAL = 2;
    public static final int DAMAGE = 4;

    public Grenade(Color color, World world, Direction direction) {
        super(color, GLYPH, world, STEP_INTERVAL, DAMAGE, direction);
    }

}

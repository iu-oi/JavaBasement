package javaDungeon.game.entity.Bullet;

import java.awt.Color;

import javaDungeon.game.*;

public class FlyingAxe extends Bullet {

    public static final char GLYPH = (char) 0x2f;
    public static final int STEP_INTERVAL = 1;
    public static final int DAMAGE = 3;
    public static final int RANGE = 5;

    protected int steps = 0;

    public FlyingAxe(Color color, World world, Direction direction) {
        super(color, GLYPH, world, STEP_INTERVAL, DAMAGE, direction);
    }

    @Override
    public boolean isObsolete() {
        return steps >= RANGE || super.isObsolete();
    }

    @Override
    public boolean takeStep(Direction direction, int frame) {
        boolean result = super.takeStep(direction, frame);
        if (result) {
            steps++;
        }
        return result;
    }

}

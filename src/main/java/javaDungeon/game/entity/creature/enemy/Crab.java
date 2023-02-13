package javaDungeon.game.entity.creature.enemy;

import java.awt.Color;
import java.util.Date;
import java.util.Random;

import javaDungeon.game.*;
import javaDungeon.game.entity.creature.Creature;
import javaDungeon.game.weapon.GrenadeLauncher;

public class Crab extends RangedEnemy<GrenadeLauncher> {

    public static final Color COLOR = Color.red;
    public static final char GLYPH = (char) 0x9d;
    public static final int HEALTH = 20;
    public static final int STEP_INTERVAL = 6;
    public static final int DAMAGE = 1;
    public static final int RANGED_ATTACK_INTERVAL = 20;

    protected final Random generator = new Random(new Date().getTime());
    protected Creature creatureTracked;
    protected Direction direction;

    public Crab(World world, Creature creatureTracked) {
        super(COLOR, GLYPH, world, HEALTH, STEP_INTERVAL, DAMAGE, RANGED_ATTACK_INTERVAL, new GrenadeLauncher(world));
        this.creatureTracked = creatureTracked;
        if (generator.nextBoolean()) {
            direction = Direction.RIGHT;
        } else {
            direction = Direction.LEFT;
        }
    }

    @Override
    public Direction nextStep() {
        if (world.testForeground(this, direction)) {
            return direction;
        } else if (world.testForeground(this, direction.opposite())) {
            direction = direction.opposite();
            return direction;
        } else {
            return null;
        }
    }

    @Override
    public Direction nextRangedAttack() {
        int diffY = getY() - creatureTracked.getY();
        if (diffY > 0 && world.testForeground(this, Direction.UP)) {
            return Direction.UP;
        } else if (diffY < 0 && world.testForeground(this, Direction.DOWN)) {
            return Direction.DOWN;
        } else {
            return null;
        }
    }

}

package javaBasement.game.entity.creature.enemy;

import java.awt.Color;

import javaBasement.game.*;
import javaBasement.game.entity.creature.Creature;
import javaBasement.game.weapon.Axe;

public class Chopper extends RangedEnemy<Axe> {

    public static final Color COLOR = Color.green;
    public static final char GLYPH = (char) 0x8f;
    public static final int HEALTH = 20;
    public static final int STEP_INTERVAL = 1000;
    public static final int DAMAGE = 1;
    public static final int RANGED_ATTACK_INTERVAL = 20;

    protected Creature creatureTracked;

    public Chopper(World world, Creature creatureTracked) {
        super(COLOR, GLYPH, world, HEALTH, STEP_INTERVAL, DAMAGE, RANGED_ATTACK_INTERVAL, new Axe(world));
        this.creatureTracked = creatureTracked;
    }

    @Override
    public Direction nextStep() {
        return null;
    }

    @Override
    public Direction nextRangedAttack() {
        Direction result = null;
        int diffX = getX() - creatureTracked.getX();
        int diffY = getY() - creatureTracked.getY();
        if (diffY == 0) {
            if (diffX > 0) {
                result = Direction.LEFT;
            } else {
                result = Direction.RIGHT;
            }
        } else if (diffX == 0) {
            if (diffY > 0) {
                result = Direction.UP;
            } else {
                result = Direction.DOWN;
            }
        }
        if (result != null && world.testForeground(this, result)) {
            return result;
        } else {
            return null;
        }
    }

}

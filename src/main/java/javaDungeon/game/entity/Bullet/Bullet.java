package javaDungeon.game.entity.Bullet;

import java.awt.Color;

import javaDungeon.game.*;
import javaDungeon.game.behavior.*;
import javaDungeon.game.entity.Entity;

public abstract class Bullet extends Entity implements Mobile, Aggressive {

    protected final int stepInterval;
    protected int lastStepFrame = 0;
    protected final int damage;
    protected final Direction direction;

    @Override
    public int getDamage(Entity victim) {
        if (world.getForeground(this, direction) == victim) {
            return damage;
        } else {
            return 0;
        }
    }

    public Bullet(Color color, char glyph, World world, int stepInterval, int damage, Direction direction) {
        super(color, glyph, world);
        this.damage = damage;
        this.stepInterval = stepInterval;
        this.direction = direction;
    }

    @Override
    public boolean isObsolete() {
        if (!world.testForeground(this, direction)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Direction nextStep() {
        if (world.testForeground(this, direction)) {
            return direction;
        } else {
            return null;
        }
    }

    @Override
    public boolean takeStep(Direction direction, int frame) {
        if (direction != null && (frame == 1 || frame - lastStepFrame >= stepInterval)) {
            lastStepFrame = frame;
            int newX = getX() + direction.diffX();
            int newY = getY() + direction.diffY();
            world.removeForeground(getX(), getY());
            world.putForeground(this, newX, newY);
            return true;
        } else {
            return false;
        }
    }

}

package javaBasement.game.entity.bullet;

import java.awt.Color;

import javaBasement.game.*;
import javaBasement.game.behavior.*;
import javaBasement.game.entity.Entity;

public abstract class Bullet extends Entity implements Mobile, Aggressive {

    protected final int stepInterval;
    protected int lastStepFrame = 0;
    protected final int damage;
    protected final Direction direction;

    @Override
    public int getDamage(Passive victim) {
        if (world.getForeground(this, direction) == victim) {
            return damage;
        } else {
            return 0;
        }
    }

    Bullet(Color color, char glyph, World world, int stepInterval, int damage, Direction direction) {
        super(color, glyph, world);
        this.damage = damage;
        this.stepInterval = stepInterval;
        this.direction = direction;
    }

    @Override
    public boolean isObsolete() {
        return !world.testForeground(this, direction);
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
        if (direction != null && (lastStepFrame == 0 || frame - lastStepFrame >= stepInterval)) {
            lastStepFrame = frame;
            int x = getX(), y = getY();
            int newX = x + direction.diffX(), newY = y + direction.diffY();
            world.removeForeground(x, y);
            world.putForeground(this, newX, newY);
            return true;
        } else {
            return false;
        }
    }

}

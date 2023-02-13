package javaDungeon.game.entity.creature;

import java.awt.Color;

import javaDungeon.game.*;
import javaDungeon.game.behavior.*;
import javaDungeon.game.entity.Entity;

public abstract class Creature extends Entity implements Mobile, Passive {

    protected final int stepInterval;
    protected int lastStepFrame = 0;
    protected final int health;
    protected int currentHealth;

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void changeCurrentHealth(int health) {
        currentHealth += health;
        if (currentHealth > this.health) {
            currentHealth = this.health;
        } else if (currentHealth < 0) {
            currentHealth = 0;
        }
        world.consoleLog(this, String.format("%d", currentHealth));
    }

    public Creature(Color color, char glyph, World world, int health, int stepInterval) {
        super(color, glyph, world);
        this.stepInterval = stepInterval;
        this.health = health;
        currentHealth = health;
    }

    @Override
    public boolean isObsolete() {
        return currentHealth == 0;
    }

    @Override
    public abstract Direction nextStep();

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

    @Override
    public abstract int detectDamage();

    @Override
    public abstract boolean takeDamage(int damage, int frame);

}

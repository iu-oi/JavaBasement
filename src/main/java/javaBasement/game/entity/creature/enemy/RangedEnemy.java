package javaBasement.game.entity.creature.enemy;

import java.awt.Color;

import javaBasement.game.*;
import javaBasement.game.behavior.Ranged;
import javaBasement.game.weapon.Weapon;

public abstract class RangedEnemy<W extends Weapon> extends Enemy implements Ranged {

    protected final int rangedAttackInterval;
    protected int lastRangedAttackFrame = 0;
    protected W weapon;

    public W getWeapon() {
        return weapon;
    }

    public void setWeapon(W weapon) {
        this.weapon = weapon;
    }

    RangedEnemy(Color color, char glyph, World world, int health, int stepInterval, int damage,
            int rangedAttackInterval, W weapon) {
        super(color, glyph, world, health, stepInterval, damage);
        this.rangedAttackInterval = rangedAttackInterval;
        this.weapon = weapon;
    }

    @Override
    public abstract Direction nextRangedAttack();

    @Override
    public boolean rangedAttack(Direction direction, int frame) {
        if (direction != null && (lastRangedAttackFrame == 0 ||
                frame - lastRangedAttackFrame >= rangedAttackInterval)) {
            lastRangedAttackFrame = frame;
            weapon.rangedAttack(color, getX(), getY(), direction);
            return true;
        } else {
            return false;
        }
    }

}

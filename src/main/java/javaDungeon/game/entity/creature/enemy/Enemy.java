package javaDungeon.game.entity.creature.enemy;

import java.awt.Color;

import javaDungeon.game.*;
import javaDungeon.game.behavior.*;
import javaDungeon.game.entity.Entity;
import javaDungeon.game.entity.creature.Creature;
import javaDungeon.game.entity.creature.player.Player;

public abstract class Enemy extends Creature implements Aggressive {

    protected final int damage;

    @Override
    public int getDamage(Passive victim) {
        if (victim instanceof Player) {
            return damage;
        } else {
            return 0;
        }
    }

    @Override
    public int detectDamage() {
        int damageTaken = 0;
        for (Direction probe : Direction.values()) {
            Thing front = world.getForeground(this, probe);
            if (front instanceof Entity && front instanceof Aggressive) {
                damageTaken += ((Aggressive) front).getDamage(this);
            }
        }
        return damageTaken;
    }

    @Override
    public boolean takeDamage(int damage, int frame) {
        if (damage > 0) {
            changeCurrentHealth(-damage);
            return true;
        } else {
            return false;
        }
    }

    Enemy(Color color, char glyph, World world, int health, int stepInterval, int damage) {
        super(color, glyph, world, health, stepInterval);
        this.damage = damage;
    }

}

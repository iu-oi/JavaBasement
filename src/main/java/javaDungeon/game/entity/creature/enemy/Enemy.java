package javaDungeon.game.entity.creature.enemy;

import java.awt.Color;

import javaDungeon.game.*;
import javaDungeon.game.behavior.*;
import javaDungeon.game.entity.Entity;
import javaDungeon.game.entity.creature.Creature;

public abstract class Enemy extends Creature implements Aggressive {

    protected final int damage;

    @Override
    public int getDamage(Entity victim) {
        return damage;
    }

    Enemy(Color color, char glyph, World world, int health, int stepInterval, int damage) {
        super(color, glyph, world, health, stepInterval);
        this.damage = damage;
    }

}

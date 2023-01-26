package javaDungeon.entities;

import java.awt.Color;

import javaDungeon.blocks.Wall;
import javaDungeon.game.Game;
import javaDungeon.game.Thing;

public class Bullet extends Mob {

    public Creature<? extends Bullet> owner;

    private final int damage;

    public int getDamage() {
        return damage;
    }

    Bullet(Color color, char glyph, Game game, int speed, int damage) {
        super(color, glyph, game, 1, speed);
        this.damage = damage;
    }

    @Override
    public void see() {
        Thing front = queryAdjacent(getDir());
        if (front instanceof Bullet) {
            if (((Mob) front).getDir() != getDir()) {
                loseHealth(getHealth());
            }
        } else if (front instanceof Creature) {
            if (owner instanceof Player && !(front instanceof Player)) {
                ((Mob) front).loseHealth(damage);
            } else if (!(owner instanceof Player) && front instanceof Player) {
                ((Mob) front).loseHealth(damage);
            }
            loseHealth(getHealth());
        } else if (front instanceof Wall) {
            loseHealth(getHealth());
        }
    }

    @Override
    public Bullet clone() {
        Bullet newBullet = new Bullet(getColor(), getGlyph(), game, getSpeed(), damage);
        newBullet.owner = owner;
        return newBullet;
    }
}
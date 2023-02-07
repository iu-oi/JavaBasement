package javaDungeon.game.mob.bullet;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.Thing;
import javaDungeon.game.mob.Mob;
import javaDungeon.game.mob.creature.Creature;
import javaDungeon.game.mob.creature.player.Player;
import javaDungeon.game.wall.Wall;

public class Bullet extends Mob {

    public static final int HEALTH = 1;

    private final int damage;
    private Creature<? extends Bullet> owner;

    public Bullet(Color color, char glyph, Game game, int speed, int damage, Creature<? extends Bullet> owner) {
        super(color, glyph, game, HEALTH, speed);
        this.damage = damage;
        this.owner = owner;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void born(int x, int y) {
        startMoving();
        super.born(x, y);
    }

    @Override
    public synchronized void see() {
        Thing front = getAdjacent(getDirection());
        if (front instanceof Bullet) {
            if (((Mob) front).getDirection() != getDirection()) {
                loseHealth(currentHealth());
            }
        } else if (front instanceof Creature) {
            if (owner instanceof Player && !(front instanceof Player)) {
                ((Mob) front).loseHealth(damage);
            } else if (!(owner instanceof Player) && front instanceof Player) {
                ((Mob) front).loseHealth(damage);
            }
            loseHealth(currentHealth());
        } else if (front instanceof Wall) {
            loseHealth(currentHealth());
        }
    }

    @Override
    public Bullet clone() {
        return new Bullet(getColor(), getGlyph(), game, getSpeed(), damage, owner);
    }

}
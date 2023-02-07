package javaDungeon.game.mob.creature.monster;

import java.awt.Color;

import javaDungeon.game.Direction;
import javaDungeon.game.Game;
import javaDungeon.game.Thing;
import javaDungeon.game.mob.Mob;
import javaDungeon.game.mob.bullet.Bullet;
import javaDungeon.game.mob.creature.Creature;
import javaDungeon.game.mob.creature.player.Player;

public class Monster<T extends Bullet> extends Creature<T> {

    public static final Color COLOR = new Color(192, 128, 0);

    protected MonsterAI ai;

    private final int damage;

    public int getDamage() {
        return damage;
    }

    Monster(MonsterAI ai, char glyph, Game game, int health, int speed, int damage, int attackSpeed) {
        super(COLOR, glyph, game, health, speed, attackSpeed);
        this.damage = damage;
        this.ai = ai;
    }

    @Override
    public void born(int x, int y) {
        super.born(x, y);
        startMoving();
    }

    @Override
    public void move() {
        ai.calculateNextDirection(this);
        super.move();
    }

    @Override
    public void see() {
        for (Direction probe : ai.directions) {
            Thing entity = getAdjacent(probe);
            if (damage > 0 && entity instanceof Player)
                ((Mob) entity).loseHealth(damage);
        }
    }

}

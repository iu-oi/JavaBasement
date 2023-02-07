package javaDungeon.game.mob.creature.player;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.mob.bullet.Bullet;
import javaDungeon.game.mob.creature.Creature;

public class Player<T extends Bullet> extends Creature<T> {

    public static final char GLYPH = (char) 0x2;

    Player(Color color, Game game, int health, int speed, int attackSpeed) {
        super(color, (char) 0x2, game, health, speed, attackSpeed);
    }

    @Override
    public synchronized void loseHealth(int damage) {
        super.loseHealth(damage);
        game.refreshStatusBar();
    }

}

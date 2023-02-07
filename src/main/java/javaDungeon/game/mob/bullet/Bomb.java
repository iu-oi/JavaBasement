package javaDungeon.game.mob.bullet;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.mob.creature.Creature;

public class Bomb extends Bullet {

    public static final char GLYPH = (char) 0xed;
    public static final int SPEED = 20;
    public static final int DAMAGE = 4;

    public Bomb(Color color, Game game, Creature<? extends Bullet> owner) {
        super(color, GLYPH, game, SPEED, DAMAGE, owner);
    }

}

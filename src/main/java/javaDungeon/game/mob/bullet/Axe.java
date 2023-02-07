package javaDungeon.game.mob.bullet;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.mob.creature.Creature;

public class Axe extends Bullet {

    public static final char GLYPH = (char) 0x2f;
    public static final int SPEED = 25;
    public static final int DAMAGE = 3;

    public Axe(Color color, Game game, Creature<? extends Bullet> owner) {
        super(color, GLYPH, game, SPEED, DAMAGE, owner);
    }

}

package javaDungeon.game.mob.bullet;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.mob.creature.Creature;

public class Note extends Bullet {

    public static final char GLYPH = (char) 13;
    public static final int SPEED = 30;
    public static final int DAMAGE = 2;

    public Note(Color color, Game game, Creature<? extends Bullet> owner) {
        super(color, GLYPH, game, SPEED, DAMAGE, owner);
    }

}

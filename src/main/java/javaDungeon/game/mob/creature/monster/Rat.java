package javaDungeon.game.mob.creature.monster;

import javaDungeon.game.Game;
import javaDungeon.game.mob.bullet.Bullet;
import javaDungeon.game.mob.creature.player.Player;

class RatAI extends MonsterAI {

    RatAI(Player<? extends Bullet> player) {
        super(player);
    }
}

public class Rat<T extends Bullet> extends Monster<T> {

    public static final char GLYPH = (char) 0xf9;
    public static final int HEALTH = 10;
    public static final int SPEED = 5;
    public static final int DAMAGE = 1;
    public static final int ATTACK_SPEED = 0;

    public Rat(Game game, Player<? extends Bullet> player) {
        super(new RatAI(player), GLYPH, game, HEALTH, SPEED, DAMAGE, ATTACK_SPEED);
    }

}

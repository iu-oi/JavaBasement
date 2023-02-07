package javaDungeon.game.mob.creature.monster;

import javaDungeon.game.Game;
import javaDungeon.game.mob.bullet.Bullet;
import javaDungeon.game.mob.creature.player.Player;

class DwarfAI extends MonsterAI {

    DwarfAI(Player<? extends Bullet> player) {
        super(player);
    }

    @Override
    public void calculateNextDirection(Monster<? extends Bullet> creature) {
        if (player.currentHealth() != 0) {
            creature.setDirection(approach(creature.getX(), creature.getY(), player.getX(), player.getY()));
        }
    }
}

public class Dwarf<T extends Bullet> extends Monster<T> {

    public static final char GLYPH = (char) 0x91;
    public static final int HEALTH = 100;
    public static final int SPEED = 12;
    public static final int DAMAGE = 2;
    public static final int ATTACK_SPEED = 0;

    public Dwarf(Game game, Player<? extends Bullet> player) {
        super(new DwarfAI(player), GLYPH, game, HEALTH, SPEED, DAMAGE, ATTACK_SPEED);
    }

}

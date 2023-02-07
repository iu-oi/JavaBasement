package javaDungeon.game.mob.creature.monster;

import javaDungeon.game.Game;
import javaDungeon.game.mob.bullet.Bullet;
import javaDungeon.game.mob.creature.player.Player;

class ChopperAI extends MonsterAI {

    ChopperAI(Player<? extends Bullet> player) {
        super(player);
    }

    @Override
    public void calculateNextDirection(Monster<? extends Bullet> creature) {
        if (player.currentHealth() != 0) {
            int xPos = creature.getX();
            int yPos = creature.getY();
            int playerxPos = player.getX();
            int playeryPos = player.getY();

            if (xPos != playerxPos && yPos != playeryPos) {
                creature.setDirection(approach(xPos, yPos, playerxPos, playeryPos));
            } else {
                if (Math.abs(xPos - playerxPos) + Math.abs(yPos - playeryPos) < 8) {
                    creature.setDirection(escape(xPos, yPos, playerxPos, playeryPos));
                } else {
                    creature.setDirection(approach(xPos, yPos, playerxPos, playeryPos));
                }
            }
        }
    }

}

public class Chopper<T extends Bullet> extends Monster<T> {

    public static final char GLYPH = (char) 0x8f;
    public static final int HEALTH = 50;
    public static final int SPEED = 8;
    public static final int DAMAGE = 2;
    public static final int ATTACK_SPEED = 2;

    public Chopper(Game game, Player<? extends Bullet> player) {
        super(new ChopperAI(player), GLYPH, game, HEALTH, SPEED, DAMAGE, ATTACK_SPEED);
    }

    @Override
    public void born(int x, int y) {
        super.born(x, y);
        startShooting();
    }

    @Override
    public void move() {
        ((ChopperAI) ai).calcNextShootDirection(this);
        super.move();
    }

}

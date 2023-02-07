package javaDungeon.game.mob.creature.monster;

import javaDungeon.game.Direction;
import javaDungeon.game.Game;
import javaDungeon.game.Thing;
import javaDungeon.game.floor.Floor;
import javaDungeon.game.mob.bullet.Bullet;
import javaDungeon.game.mob.creature.player.Player;

class CrabAI extends MonsterAI {

    CrabAI(Player<? extends Bullet> player) {
        super(player);
    }

    @Override
    public void calculateNextDirection(Monster<? extends Bullet> creature) {
        if (creature.getDirection() == Direction.DIR_UP || creature.getDirection() == Direction.DIR_DOWN) {
            if (randomizer.nextBoolean()) {
                creature.setDirection(Direction.DIR_RIGHT);
            } else {
                creature.setDirection(Direction.DIR_LEFT);
            }
        }

        Thing front = creature.getAdjacent(creature.getDirection());
        if (!(front instanceof Floor) && !(front instanceof Bullet)) {
            if (creature.getDirection() == Direction.DIR_LEFT) {
                creature.setDirection(Direction.DIR_RIGHT);
            } else if (creature.getDirection() == Direction.DIR_RIGHT) {
                creature.setDirection(Direction.DIR_LEFT);
            }
        }
    }

    @Override
    public void calcNextShootDirection(Monster<? extends Bullet> creature) {
        if (player.currentHealth() != 0) {
            if (creature.getY() <= player.getY()) {
                creature.setShootDirection(Direction.DIR_DOWN);
            } else {
                creature.setShootDirection(Direction.DIR_UP);
            }
        }
    }

}

public class Crab<T extends Bullet> extends Monster<T> {

    public static final char GLYPH = (char) 0x9d;
    public static final int HEALTH = 20;
    public static final int SPEED = 10;
    public static final int DAMAGE = 0;
    public static final int ATTACK_SPEED = 1;

    public Crab(Game game, Player<? extends Bullet> player) {
        super(new CrabAI(player), GLYPH, game, HEALTH, SPEED, DAMAGE, ATTACK_SPEED);
    }

    @Override
    public void born(int x, int y) {
        super.born(x, y);
        startShooting();
    }

    @Override
    public void move() {
        ((CrabAI) ai).calcNextShootDirection(this);
        super.move();
    }

}

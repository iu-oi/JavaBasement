package javaDungeon.entities;

import javaDungeon.blocks.Floor;
import javaDungeon.game.Direction;
import javaDungeon.game.Game;
import javaDungeon.game.Thing;

class BatAI extends MonsterAI {

    BatAI(Player<? extends Bullet> player) {
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

public class Bat<T extends Bullet> extends Monster<T> {

    Bat(Game game, Player<? extends Bullet> player) {
        super(new BatAI(player), (char) 0xec, game, 20, 10, 0, 1);
    }

    @Override
    public void born(int x, int y) {
        super.born(x, y);
        startShooting();
    }

    @Override
    public void move() {
        ((BatAI) ai).calcNextShootDirection(this);
        super.move();
    }

}
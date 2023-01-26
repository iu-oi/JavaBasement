package javaDungeon.entities;

import java.util.Date;
import java.util.Random;

import javaDungeon.blocks.Floor;
import javaDungeon.game.Direction;
import javaDungeon.game.Game;
import javaDungeon.game.Thing;

class BatAI implements MonsterAI {

    private static Random randomizer = new Random(new Date().getTime() / 1000);
    private Player<? extends Bullet> player;

    BatAI(Player<? extends Bullet> player) {
        this.player = player;
    }

    @Override
    public void calcNextDir(Monster<? extends Bullet> creature) {
        if (creature.getDir() == Direction.DIR_UP || creature.getDir() == Direction.DIR_DOWN) {
            if (randomizer.nextBoolean()) {
                creature.setDir(Direction.DIR_RIGHT);
            } else {
                creature.setDir(Direction.DIR_LEFT);
            }
        }

        Thing front = creature.queryAdjacent(creature.getDir());
        if (!(front instanceof Floor) && !(front instanceof Bullet)) {
            if (creature.getDir() == Direction.DIR_LEFT) {
                creature.setDir(Direction.DIR_RIGHT);
            } else if (creature.getDir() == Direction.DIR_RIGHT) {
                creature.setDir(Direction.DIR_LEFT);
            }
        }
    }

    public void calcNextBulletDir(Bat<? extends Bullet> creature) {
        if (player.getHealth() != 0) {
            if (creature.getY() <= player.getY()) {
                creature.setAttackDir(Direction.DIR_DOWN);
            } else {
                creature.setAttackDir(Direction.DIR_UP);
            }
        }
    }

}

public class Bat<T extends Bullet> extends Monster<T> {

    Bat(Game game, Player<? extends Bullet> player) {
        super(new BatAI(player), (char) 0xec, game, 20, 10, 0, 1);
    }

    @Override
    public void move() {
        ((BatAI) ai).calcNextBulletDir(this);
        super.move();
    }

}
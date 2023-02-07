package javaDungeon.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javaDungeon.game.Direction;

public class MonsterAI implements Serializable{

    public static final Direction[] directions = Direction.values();

    protected Random randomizer;
    protected Player<? extends Bullet> player;

    MonsterAI(Player<? extends Bullet> player) {
        this.player = player;
        this.randomizer = new Random(new Date().getTime() / 1000);
    }

    public void calculateNextDirection(Monster<? extends Bullet> creature) {
        creature.setDirection(directions[randomizer.nextInt(directions.length)]);
    }

    public void calcNextShootDirection(Monster<? extends Bullet> creature) {
        if (player.currentHealth() > 0) {
            creature.setShootDirection(approach(creature.getX(), creature.getY(), player.getX(), player.getY()));
        }
    }

    public static Direction approach(int xPos1, int yPos1, int xPos2, int yPos2) {
        int xDiff = xPos1 - xPos2;
        int yDiff = yPos1 - yPos2;

        if (Math.abs(xDiff) < Math.abs(yDiff)) {
            if (yPos1 < yPos2) {
                return Direction.DIR_DOWN;
            } else {
                return Direction.DIR_UP;
            }
        } else {
            if (xPos1 < xPos2) {
                return Direction.DIR_RIGHT;
            } else {
                return Direction.DIR_LEFT;
            }
        }
    }

    public static Direction escape(int xPos1, int yPos1, int xPos2, int yPos2) {
        return approach(xPos1, yPos1, xPos2, yPos2).opposite();
    }

}
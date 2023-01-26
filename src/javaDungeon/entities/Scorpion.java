package javaDungeon.entities;

import javaDungeon.game.Direction;
import javaDungeon.game.Game;

class ScorpionAI implements MonsterAI {

    private Player<? extends Bullet> player;

    ScorpionAI(Player<? extends Bullet> player) {
        this.player = player;
    }

    @Override
    public void calcNextDir(Monster<? extends Bullet> creature) {
        if (player.getHealth() != 0) {
            int xPos = creature.getX();
            int yPos = creature.getY();
            int playerxPos = player.getX();
            int playeryPos = player.getY();

            if (xPos != playerxPos && yPos != playeryPos) {
                creature.setDir(Direction.calcApproachDir(xPos, yPos, playerxPos, playeryPos));
            } else {
                if (Math.abs(xPos - playerxPos) + Math.abs(yPos - playeryPos) < 8) {
                    creature.setDir(Direction.calcEscapeDir(xPos, yPos, playerxPos, playeryPos));
                } else {
                    creature.setDir(Direction.calcApproachDir(xPos, yPos, playerxPos, playeryPos));
                }
            }
        }
    }

    public void calcNextBulletDir(Scorpion<? extends Bullet> creature) {
        if (player.getHealth() != 0) {
            creature.setAttackDir(
                    Direction.calcApproachDir(creature.getX(), creature.getY(), player.getX(), player.getY()));
        }
    }
}

public class Scorpion<T extends Bullet> extends Monster<T> {

    Scorpion(Game game, Player<? extends Bullet> player) {
        super(new ScorpionAI(player), (char) 0x9d, game, 50, 8, 2, 2);
    }

    @Override
    public void move() {
        ((ScorpionAI) ai).calcNextBulletDir(this);
        super.move();
    }

}

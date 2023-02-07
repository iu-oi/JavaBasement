package javaDungeon.entities;

import javaDungeon.game.Game;

class ScorpionAI extends MonsterAI {

    ScorpionAI(Player<? extends Bullet> player) {
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

public class Scorpion<T extends Bullet> extends Monster<T> {

    Scorpion(Game game, Player<? extends Bullet> player) {
        super(new ScorpionAI(player), (char) 0x9d, game, 50, 8, 2, 2);
    }

    @Override
    public void born(int x, int y) {
        super.born(x, y);
        startShooting();
    }

    @Override
    public void move() {
        ((ScorpionAI) ai).calcNextShootDirection(this);
        super.move();
    }

}

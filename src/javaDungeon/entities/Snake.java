package javaDungeon.entities;

import javaDungeon.game.Game;

class SnakeAI extends MonsterAI {

    SnakeAI(Player<? extends Bullet> player) {
        super(player);
    }

    @Override
    public void calculateNextDirection(Monster<? extends Bullet> creature) {
        if (player.currentHealth() != 0) {
            creature.setDirection(approach(creature.getX(), creature.getY(), player.getX(), player.getY()));
        }
    }
}

public class Snake<T extends Bullet> extends Monster<T> {

    Snake(Game game, Player<? extends Bullet> player) {
        super(new SnakeAI(player), 'S', game, 100, 12, 2, 0);
    }
}

package javaDungeon.entities;

import javaDungeon.game.Direction;
import javaDungeon.game.Game;

class SnakeAI implements MonsterAI {

    private Player<? extends Bullet> player;

    SnakeAI(Player<? extends Bullet> player) {
        this.player = player;
    }

    @Override
    public void calcNextDir(Monster<? extends Bullet> creature) {
        if (player.getHealth() != 0) {
            creature.setDir(Direction.calcApproachDir(creature.getX(), creature.getY(), player.getX(), player.getY()));
        }
    }
}

public class Snake<T extends Bullet> extends Monster<T> {

    Snake(Game game, Player<? extends Bullet> player) {
        super(new SnakeAI(player), 'S', game, 100, 12, 2, 0);
    }
}

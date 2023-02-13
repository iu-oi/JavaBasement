package javaDungeon.game.behavior;

import javaDungeon.game.Direction;

public interface Ranged {

    Direction nextRangedAttack();

    boolean rangedAttack(Direction direction, int frame);

}

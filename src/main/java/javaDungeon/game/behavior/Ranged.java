package javaDungeon.game.behavior;

import javaDungeon.game.Direction;

public interface Ranged {

    public Direction nextRangedAttack();

    public boolean rangedAttack(Direction direction, int frame);

}

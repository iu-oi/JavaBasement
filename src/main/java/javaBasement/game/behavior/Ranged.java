package javaBasement.game.behavior;

import javaBasement.game.Direction;

public interface Ranged {

    Direction nextRangedAttack();

    boolean rangedAttack(Direction direction, int frame);

}

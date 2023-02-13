package javaDungeon.game.behavior;

import javaDungeon.game.Direction;

public interface Mobile {

    Direction nextStep();

    boolean takeStep(Direction direction, int frame);

}

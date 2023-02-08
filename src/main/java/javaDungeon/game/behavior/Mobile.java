package javaDungeon.game.behavior;

import javaDungeon.game.Direction;

public interface Mobile {

    public Direction nextStep();

    public boolean takeStep(Direction direction, int frame);

}

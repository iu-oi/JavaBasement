package javaBasement.game.behavior;

import javaBasement.game.Direction;

public interface Mobile {

    Direction nextStep();

    boolean takeStep(Direction direction, int frame);

}

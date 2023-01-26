package javaDungeon.entities;

import java.util.Date;
import java.util.Random;

import javaDungeon.game.Game;

class RatAI implements MonsterAI {

    private static Random randomizer = new Random(new Date().getTime() / 1000);

    @Override
    public void calcNextDir(Monster<? extends Bullet> creature) {
        creature.setDir(directions[randomizer.nextInt(directions.length)]);
    }

}

public class Rat<T extends Bullet> extends Monster<T> {

    Rat(Game game) {
        super(new RatAI(), (char) 0xeb, game, 10, 5, 1, 0);
    }

}
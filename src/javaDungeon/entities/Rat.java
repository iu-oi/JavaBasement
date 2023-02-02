package javaDungeon.entities;

import javaDungeon.game.Game;

class RatAI extends MonsterAI {

    RatAI(Player<? extends Bullet> player) {
        super(player);
    }
}

public class Rat<T extends Bullet> extends Monster<T> {

    Rat(Game game, Player<? extends Bullet> player) {
        super(new RatAI(player), (char) 0xeb, game, 10, 5, 1, 0);
    }

}
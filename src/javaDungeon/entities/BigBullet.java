package javaDungeon.entities;

import java.awt.Color;

import javaDungeon.game.Game;

public class BigBullet extends Bullet {

    BigBullet(Color color, Game game) {
        super(color, (char) 0xf, game, 20, 4);
    }
}

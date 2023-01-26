package javaDungeon.entities;

import java.awt.Color;

import javaDungeon.game.Game;

public class SmallBullet extends Bullet {

    SmallBullet(Color color, Game game) {
        super(color, '*', game, 30, 2);
    }
}

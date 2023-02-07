package javaDungeon.entities;

import java.awt.Color;

import javaDungeon.game.Game;

public class MiddleBullet extends Bullet {

    MiddleBullet(Color color, Game game, Creature<? extends Bullet> owner) {
        super(color, 'o', game, 25, 3, owner);
    }

}

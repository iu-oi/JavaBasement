package javaDungeon.entities;

import java.awt.Color;

import javaDungeon.game.Game;

public class BulletFactory {

    private final Game game;

    public BulletFactory(Game game) {
        this.game = game;
    }

    public BigBullet makeBigBullet(Color color) {
        return new BigBullet(color, game);
    }

    public MiddleBullet makeMiddleBullet(Color color) {
        return new MiddleBullet(color, game);
    }

    public SmallBullet makeSmallBullet(Color color) {
        return new SmallBullet(color, game);
    }

}
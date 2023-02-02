package javaDungeon.entities;

import java.awt.Color;

import javaDungeon.game.Game;

public class Player<T extends Bullet> extends Creature<T> {

    Player(Color color, Game game, int health, int speed, int attackSpeed) {
        super(color, (char) 0x2, game, health, speed, attackSpeed);
    }

    @Override
    public synchronized void loseHealth(int damage) {
        super.loseHealth(damage);
        game.refreshStatusBar();
    }

}
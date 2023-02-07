package javaDungeon.game.mob.creature.player;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.mob.bullet.Note;

public class Player6 extends Player<Note> {

    public static final Color COLOR = new Color(0, 0, 255);
    public static final int HEALTH = 3;
    public static final int SPEED = 20;
    public static final int ATTACK_SPEED = 8;

    public Player6(Game game) {
        super(COLOR, game, HEALTH, SPEED, ATTACK_SPEED);
    }

}

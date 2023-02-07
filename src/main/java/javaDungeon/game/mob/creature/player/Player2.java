package javaDungeon.game.mob.creature.player;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.mob.bullet.Note;

public class Player2 extends Player<Note> {

    public static final Color COLOR = new Color(255, 128, 0);
    public static final int HEALTH = 4;
    public static final int SPEED = 20;
    public static final int ATTACK_SPEED = 4;

    public Player2(Game game) {
        super(COLOR, game, HEALTH, SPEED, ATTACK_SPEED);
    }

}

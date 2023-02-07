package javaDungeon.game.mob.creature;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.floor.Floor;
import javaDungeon.game.mob.bullet.Bomb;
import javaDungeon.game.mob.bullet.Bullet;
import javaDungeon.game.mob.bullet.Axe;
import javaDungeon.game.mob.bullet.Note;
import javaDungeon.game.mob.creature.monster.Crab;
import javaDungeon.game.mob.creature.monster.Rat;
import javaDungeon.game.mob.creature.monster.Chopper;
import javaDungeon.game.mob.creature.monster.Dwarf;
import javaDungeon.game.mob.creature.player.Player;
import javaDungeon.game.mob.creature.player.Player1;
import javaDungeon.game.mob.creature.player.Player2;
import javaDungeon.game.mob.creature.player.Player3;
import javaDungeon.game.mob.creature.player.Player4;
import javaDungeon.game.mob.creature.player.Player5;
import javaDungeon.game.mob.creature.player.Player6;
import javaDungeon.game.mob.creature.player.Player7;

public class CreatureFactory {

    private final Game game;
    private final BulletFactory bulletFactory;

    class BulletFactory {

        public Bomb makeBomb(Color color, Creature<Bomb> owner) {
            return new Bomb(color, game, owner);
        }

        public Axe makeAxe(Color color, Creature<Axe> owner) {
            return new Axe(color, game, owner);
        }

        public Note makeNote(Color color, Creature<Note> owner) {
            return new Note(color, game, owner);
        }

    }

    public CreatureFactory(Game game) {
        this.game = game;
        this.bulletFactory = new BulletFactory();
    }

    public Player<Bomb> createPlayer1() {
        Player<Bomb> player = new Player1(game);
        player.setBullet(bulletFactory.makeBomb(player.getColor(), player));
        player.born(10, 17);
        return player;
    }

    public Player<Note> createPlayer2() {
        Player<Note> player = new Player2(game);
        player.setBullet(bulletFactory.makeNote(player.getColor(), player));
        player.born(12, 17);
        return player;
    }

    public Player<Note> createPlayer3() {
        Player<Note> player = new Player3(game);
        player.setBullet(bulletFactory.makeNote(player.getColor(), player));
        player.born(14, 17);
        return player;
    }

    public Player<Axe> createPlayer4() {
        Player<Axe> player = new Player4(game);
        player.setBullet(bulletFactory.makeAxe(player.getColor(), player));
        player.born(16, 17);
        return player;
    }

    public Player<Axe> createPlayer5() {
        Player<Axe> player = new Player5(game);
        player.setBullet(bulletFactory.makeAxe(player.getColor(), player));
        player.born(18, 17);
        return player;
    }

    public Player<Note> createPlayer6() {
        Player<Note> player = new Player6(game);
        player.setBullet(bulletFactory.makeNote(player.getColor(), player));
        player.born(20, 17);
        return player;
    }

    public Player<Axe> createPlayer7() {
        Player<Axe> player = new Player7(game);
        player.setBullet(bulletFactory.makeAxe(player.getColor(), player));
        player.born(22, 17);
        return player;
    }

    public Creature<Bullet> createRat(Player<? extends Bullet> playerTracked, int xPos, int yPos) {
        if (game.getActiveThing(xPos, yPos) instanceof Floor) {
            Rat<Bullet> newRat = new Rat<>(game, playerTracked);
            newRat.born(xPos, yPos);
            return newRat;
        }
        return null;
    }

    public Creature<Note> createBat(Player<? extends Bullet> playerTracked, int xPos, int yPos) {
        if (game.getActiveThing(xPos, yPos) instanceof Floor) {
            Crab<Note> newBat = new Crab<>(game, playerTracked);
            newBat.setBullet(bulletFactory.makeNote(newBat.getColor(), newBat));
            newBat.born(xPos, yPos);
            return newBat;
        }
        return null;
    }

    public Creature<Bullet> createSnake(Player<? extends Bullet> playerTracked, int xPos, int yPos) {
        if (game.getActiveThing(xPos, yPos) instanceof Floor) {
            Dwarf<Bullet> newSnake = new Dwarf<>(game, playerTracked);
            newSnake.born(xPos, yPos);
            return newSnake;
        }
        return null;
    }

    public Creature<Axe> createScorpion(Player<? extends Bullet> playerTracked, int xPos, int yPos) {
        if (game.getActiveThing(xPos, yPos) instanceof Floor) {
            Chopper<Axe> newScorpion = new Chopper<>(game, playerTracked);
            newScorpion.setBullet(bulletFactory.makeAxe(newScorpion.getColor(), newScorpion));
            newScorpion.born(xPos, yPos);
            return newScorpion;
        }
        return null;
    }
}

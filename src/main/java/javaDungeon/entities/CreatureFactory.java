package javaDungeon.entities;

import java.awt.Color;

import asciiPanel.AsciiPanel;
import javaDungeon.blocks.Floor;
import javaDungeon.game.Game;

public class CreatureFactory {

    private final Game game;
    private final BulletFactory bulletFactory;

    class BulletFactory {

        public BigBullet makeBigBullet(Color color, Creature<BigBullet> owner) {
            return new BigBullet(color, game, owner);
        }

        public MiddleBullet makeMiddleBullet(Color color, Creature<MiddleBullet> owner) {
            return new MiddleBullet(color, game, owner);
        }

        public SmallBullet makeSmallBullet(Color color, Creature<SmallBullet> owner) {
            return new SmallBullet(color, game, owner);
        }

    }

    public CreatureFactory(Game game) {
        this.game = game;
        this.bulletFactory = new BulletFactory();
    }

    public Player<BigBullet> createPlayer1() {
        Player<BigBullet> player = new Player<>(AsciiPanel.red, game, 4, 20, 4);
        player.setBullet(bulletFactory.makeBigBullet(player.getColor(), player));
        player.born(10, 17);
        return player;
    }

    public Player<SmallBullet> createPlayer2() {
        Player<SmallBullet> player = new Player<>(AsciiPanel.yellow, game, 4, 30, 4);
        player.setBullet(bulletFactory.makeSmallBullet(player.getColor(), player));
        player.born(12, 17);
        return player;
    }

    public Player<SmallBullet> createPlayer3() {
        Player<SmallBullet> player = new Player<>(AsciiPanel.brightYellow, game, 8, 20, 4);
        player.setBullet(bulletFactory.makeSmallBullet(player.getColor(), player));
        player.born(14, 17);
        return player;
    }

    public Player<MiddleBullet> createPlayer4() {
        Player<MiddleBullet> player = new Player<>(AsciiPanel.green, game, 4, 20, 4);
        player.setBullet(bulletFactory.makeMiddleBullet(player.getColor(), player));
        player.born(16, 17);
        return player;
    }

    public Player<MiddleBullet> createPlayer5() {
        Player<MiddleBullet> player = new Player<>(AsciiPanel.cyan, game, 4, 20, 4);
        player.setBullet(bulletFactory.makeMiddleBullet(player.getColor(), player));
        player.born(18, 17);
        return player;
    }

    public Player<SmallBullet> createPlayer6() {
        Player<SmallBullet> player = new Player<>(AsciiPanel.brightBlue, game, 3, 20, 8);
        player.setBullet(bulletFactory.makeSmallBullet(player.getColor(), player));
        player.born(20, 17);
        return player;
    }

    public Player<MiddleBullet> createPlayer7() {
        Player<MiddleBullet> player = new Player<>(AsciiPanel.blue, game, 1, 20, 4);
        player.setBullet(bulletFactory.makeMiddleBullet(player.getColor(), player));
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

    public Creature<SmallBullet> createBat(Player<? extends Bullet> playerTracked, int xPos, int yPos) {
        if (game.getActiveThing(xPos, yPos) instanceof Floor) {
            Bat<SmallBullet> newBat = new Bat<>(game, playerTracked);
            newBat.setBullet(bulletFactory.makeSmallBullet(newBat.getColor(), newBat));
            newBat.born(xPos, yPos);
            return newBat;
        }
        return null;
    }

    public Creature<Bullet> createSnake(Player<? extends Bullet> playerTracked, int xPos, int yPos) {
        if (game.getActiveThing(xPos, yPos) instanceof Floor) {
            Snake<Bullet> newSnake = new Snake<>(game, playerTracked);
            newSnake.born(xPos, yPos);
            return newSnake;
        }
        return null;
    }

    public Creature<MiddleBullet> createScorpion(Player<? extends Bullet> playerTracked, int xPos, int yPos) {
        if (game.getActiveThing(xPos, yPos) instanceof Floor) {
            Scorpion<MiddleBullet> newScorpion = new Scorpion<>(game, playerTracked);
            newScorpion.setBullet(bulletFactory.makeMiddleBullet(newScorpion.getColor(), newScorpion));
            newScorpion.born(xPos, yPos);
            return newScorpion;
        }
        return null;
    }
}

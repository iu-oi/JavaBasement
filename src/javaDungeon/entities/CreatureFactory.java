package javaDungeon.entities;

import asciiPanel.AsciiPanel;
import javaDungeon.blocks.Floor;
import javaDungeon.game.Game;

public class CreatureFactory {

    private volatile int playerCount;

    public synchronized int getPlayerCount() {
        return playerCount;
    }

    public synchronized void incPlayerCount() {
        playerCount++;
    }

    public synchronized void decPlayerCount() {
        if (playerCount > 0) {
            playerCount--;
        }
    }

    private volatile int creatureCount;

    public synchronized int getCreatureCount() {
        return creatureCount;
    }

    public synchronized void incCreatureCount() {
        creatureCount++;
    }

    public synchronized void decCreatureCount() {
        if (creatureCount > 0) {
            creatureCount--;
        }
    }

    private volatile int monsterCount;

    public synchronized int getMonsterCount() {
        return monsterCount;
    }

    public synchronized void incMonsterCount() {
        monsterCount++;
    }

    public synchronized void decMonsterCount() {
        if (monsterCount > 0) {
            monsterCount--;
        }
    }

    protected final Game game;
    protected final BulletFactory bulletFactory;

    public CreatureFactory(Game game, BulletFactory bulletFactory) {
        playerCount = creatureCount = monsterCount = 0;
        this.game = game;
        this.bulletFactory = bulletFactory;
    }

    public Player<? extends Bullet> createPlayer1() {
        Player<BigBullet> player = new Player<>(AsciiPanel.red, game, 4, 20, 4);
        game.putEntity(player, 10, 17);
        player.startAction();
        player.setBullet(bulletFactory.makeBigBullet(player.getColor()));
        return player;
    }

    public Player<? extends Bullet> createPlayer2() {
        Player<SmallBullet> player = new Player<>(AsciiPanel.yellow, game, 4, 30, 4);
        game.putEntity(player, 12, 17);
        player.startAction();
        player.setBullet(bulletFactory.makeSmallBullet(player.getColor()));
        return player;
    }

    public Player<? extends Bullet> createPlayer3() {
        Player<SmallBullet> player = new Player<>(AsciiPanel.brightYellow, game, 8, 20, 4);
        game.putEntity(player, 14, 17);
        player.startAction();
        player.setBullet(bulletFactory.makeSmallBullet(player.getColor()));
        return player;
    }

    public Player<? extends Bullet> createPlayer4() {
        Player<MiddleBullet> player = new Player<>(AsciiPanel.green, game, 4, 20, 4);
        game.putEntity(player, 16, 17);
        player.startAction();
        player.setBullet(bulletFactory.makeMiddleBullet(player.getColor()));
        return player;
    }

    public Player<? extends Bullet> createPlayer5() {
        Player<MiddleBullet> player = new Player<>(AsciiPanel.cyan, game, 4, 20, 4);
        game.putEntity(player, 18, 17);
        player.startAction();
        player.setBullet(bulletFactory.makeMiddleBullet(player.getColor()));
        return player;
    }

    public Player<? extends Bullet> createPlayer6() {
        Player<SmallBullet> player = new Player<>(AsciiPanel.brightBlue, game, 3, 20, 8);
        game.putEntity(player, 20, 17);
        player.startAction();
        player.setBullet(bulletFactory.makeSmallBullet(player.getColor()));
        return player;
    }

    public Player<? extends Bullet> createPlayer7() {
        Player<MiddleBullet> player = new Player<>(AsciiPanel.blue, game, 1, 20, 4);
        game.putEntity(player, 22, 17);
        player.startAction();
        player.setBullet(bulletFactory.makeMiddleBullet(player.getColor()));
        return player;
    }

    public Creature<? extends Bullet> createRat(int xPos, int yPos) {
        if (game.getActiveThing(xPos, yPos) instanceof Floor) {
            Rat<Bullet> newRat = new Rat<>(game);
            newRat.startMoving();
            game.putEntity(newRat, xPos, yPos);
            newRat.startAction();
            return newRat;
        }
        return null;
    }

    public Creature<? extends Bullet> createBat(Player<? extends Bullet> playerTracked, int xPos, int yPos) {
        if (game.getActiveThing(xPos, yPos) instanceof Floor) {
            Bat<Bullet> newBat = new Bat<>(game, playerTracked);
            newBat.startMoving();
            newBat.startAttacking();
            game.putEntity(newBat, xPos, yPos);
            newBat.startAction();
            newBat.setBullet(bulletFactory.makeSmallBullet(newBat.getColor()));
            return newBat;
        }
        return null;
    }

    public Creature<? extends Bullet> createSnake(Player<? extends Bullet> playerTracked, int xPos, int yPos) {
        if (game.getActiveThing(xPos, yPos) instanceof Floor) {
            Snake<Bullet> newSnake = new Snake<>(game, playerTracked);
            newSnake.startMoving();
            game.putEntity(newSnake, xPos, yPos);
            newSnake.startAction();
            return newSnake;
        }
        return null;
    }

    public Creature<? extends Bullet> createScorpion(Player<? extends Bullet> playerTracked, int xPos, int yPos) {
        if (game.getActiveThing(xPos, yPos) instanceof Floor) {
            Scorpion<Bullet> newScorpion = new Scorpion<>(game, playerTracked);
            newScorpion.startMoving();
            newScorpion.startAttacking();
            game.putEntity(newScorpion, xPos, yPos);
            newScorpion.startAction();
            newScorpion.setBullet(bulletFactory.makeMiddleBullet(newScorpion.getColor()));
            return newScorpion;
        }
        return null;
    }
}

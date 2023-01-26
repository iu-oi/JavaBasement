package javaDungeon.game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javaDungeon.Dungeon;
import javaDungeon.blocks.BottomLeftCorner;
import javaDungeon.blocks.BottomRightCorner;
import javaDungeon.blocks.Empty;
import javaDungeon.blocks.HorizontalWall;
import javaDungeon.blocks.TopLeftCorner;
import javaDungeon.blocks.TopRightCorner;
import javaDungeon.blocks.VerticalWall;
import javaDungeon.entities.Bullet;
import javaDungeon.entities.BulletFactory;
import javaDungeon.entities.CreatureFactory;
import javaDungeon.entities.Diamond;
import javaDungeon.entities.Heart;
import javaDungeon.entities.Player;
import javaDungeon.screens.LoseScreen;
import javaDungeon.screens.WinScreen;

public class Game {

    public static void consoleLog(String fmt) {
        System.out.println(fmt);
    }

    private final World sandbox;

    public World getSandbox() {
        return sandbox;
    }

    public Thing getThing(int x, int y) {
        return sandbox.getThing(x, y);
    }

    public void putThing(Thing t, int x, int y) {
        sandbox.putThing(t, x, y);
    }

    public Thing getEntity(int x, int y) {
        return sandbox.getEntity(x, y);
    }

    public void putEntity(Thing t, int x, int y) {
        sandbox.putEntity(t, x, y);
    }

    public Thing getActiveThing(int x, int y) {
        return sandbox.getActiveThing(x, y);
    }

    private Player<? extends Bullet> player;

    public int getPlayerHealth() {
        return player.getHealth();
    }

    public int getPlayerDamage() {
        return player.getBullet().getDamage();
    }

    public void playerStartMoving(Direction direction) {
        player.setDir(direction);
        player.startMoving();
    }

    public void playerStopMoving() {
        player.stopMoving();
    }

    public Direction getPlayerDir() {
        return player.getDir();
    }

    public void playerStartAttacking(Direction direction) {
        player.setAttackDir(direction);
        player.startAttacking();
    }

    public void playerStopAttacking() {
        player.stopAttacking();
    }

    public Direction getPlayerAttackDir() {
        return player.getAttackDir();
    }

    public World initializeArena() {
        World arena = new World();

        for (int x = 1; x < World.WIDTH - 1; x++) {
            arena.putThing(new HorizontalWall(arena), x, 0);
            arena.putThing(new HorizontalWall(arena), x, World.HEIGHT - 2);
        }
        for (int y = 1; y < World.HEIGHT - 2; y++) {
            arena.putThing(new VerticalWall(arena), 0, y);
            arena.putThing(new VerticalWall(arena), World.WIDTH - 1, y);
        }
        arena.putThing(new TopLeftCorner(arena), 0, 0);
        arena.putThing(new TopRightCorner(arena), World.WIDTH - 1, 0);
        arena.putThing(new BottomLeftCorner(arena), 0, World.HEIGHT - 2);
        arena.putThing(new BottomRightCorner(arena), World.WIDTH - 1, World.HEIGHT - 2);
        for (int x = 0; x < World.WIDTH; x++) {
            arena.putThing(new Empty(arena), x, World.HEIGHT - 1);
        }

        return arena;
    }

    public void refreshStatusBar() {
        int currentHealth = getPlayerHealth();
        int currentDamage = getPlayerDamage();

        for (int i = 0; i < World.WIDTH; i++) {
            if (sandbox.getEntity(i, World.HEIGHT - 1) != null) {
                sandbox.removeEntity(i, World.HEIGHT - 1);
            }
        }
        for (int i = 0; i < currentHealth; i++) {
            sandbox.putEntity(new Heart(sandbox), World.WIDTH - 1 - i, World.HEIGHT - 1);
        }
        for (int i = 0; i < currentDamage; i++) {
            sandbox.putEntity(new Diamond(sandbox), i, World.HEIGHT - 1);
        }
    }

    private Dungeon frontend;
    private ScheduledFuture<?> frameHandler;
    public ScheduledExecutorService scheduler;

    public void quit(boolean gameStatus) {
        Game.consoleLog("Game " + this + " is over...");
        frameHandler.cancel(false);

        scheduler.shutdown();
        boolean isTerminated = false;
        do {
            Game.consoleLog("Waiting for mob scheduler " + scheduler + " to shutdown...");
            try {
                isTerminated = scheduler.awaitTermination(2000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                isTerminated = false;
            }
        } while (!isTerminated);
        Game.consoleLog("Mob scheduler " + scheduler + " is terminated...");

        if (gameStatus) {
            frontend.setScreen(new WinScreen(frontend));
        } else {
            frontend.setScreen(new LoseScreen(frontend));
        }
    }

    public final CreatureFactory creatureFactory;

    public void play(int playerId) {
        Game.consoleLog("Game " + this + " is activated...");
        scheduler = Executors.newScheduledThreadPool(128);
        switch (playerId) {
            case 1:
                player = creatureFactory.createPlayer1();
                break;
            case 2:
                player = creatureFactory.createPlayer2();
                break;
            case 3:
                player = creatureFactory.createPlayer3();
                break;
            case 4:
                player = creatureFactory.createPlayer4();
                break;
            case 5:
                player = creatureFactory.createPlayer5();
                break;
            case 6:
                player = creatureFactory.createPlayer6();
                break;
            case 7:
                player = creatureFactory.createPlayer7();
                break;
        }
        refreshStatusBar();
        creatureFactory.createRat(31, 1);
        creatureFactory.createBat(player, 31, 31);
        creatureFactory.createSnake(player, 1, 31);
        creatureFactory.createScorpion(player, 1, 1);
        frameHandler = Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (getPlayerHealth() == 0) {
                    quit(false);
                } else {
                    if (creatureFactory.getMonsterCount() == 0) {
                        quit(true);
                    }
                }
            }
        }, 0, 40, TimeUnit.MILLISECONDS);
    }

    public Game(Dungeon frontend) {
        sandbox = initializeArena();
        creatureFactory = new CreatureFactory(this, new BulletFactory(this));
        this.frontend = frontend;
        Game.consoleLog("New game " + this + " initialized...");
    }

}
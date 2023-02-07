package javaDungeon.game;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javaDungeon.Empty;
import javaDungeon.game.mob.Mob;
import javaDungeon.game.mob.bullet.Bullet;
import javaDungeon.game.mob.creature.Creature;
import javaDungeon.game.mob.creature.CreatureFactory;
import javaDungeon.game.mob.creature.monster.Monster;
import javaDungeon.game.mob.creature.player.Player;
import javaDungeon.game.wall.BottomLeftCorner;
import javaDungeon.game.wall.BottomRightCorner;
import javaDungeon.game.wall.HorizontalWall;
import javaDungeon.game.wall.TopLeftCorner;
import javaDungeon.game.wall.TopRightCorner;
import javaDungeon.game.wall.VerticalWall;
import javaDungeon.screens.PlayScreen;

public class Game implements Runnable, Serializable {

    public static void consoleLog(String fmt) {
        System.out.println(fmt);
    }

    public static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(128);

    private World sandbox;
    private Player<? extends Bullet> player;
    private Collection<Mob> entities;
    private volatile Counter playerCount;
    private volatile Counter creatureCount;
    private volatile Counter monsterCount;
    private transient CreatureFactory creatureFactory;
    private transient PlayScreen frontend;
    private transient ScheduledFuture<?> frameHandler;

    public Game(PlayScreen frontend) {
        this.frontend = frontend;
        creatureFactory = new CreatureFactory(this);
        Game.consoleLog("New game " + this + " initialized...");
    }

    public Thing getItem(int x, int y) {
        return sandbox.getItem(x, y);
    }

    public void putItem(Thing t, int x, int y) {
        sandbox.putItem(t, x, y);
    }

    public void removeItem(int x, int y) {
        sandbox.removeItem(x, y);
    }

    public Thing getEntity(int x, int y) {
        return sandbox.getEntity(x, y);
    }

    public void putEntity(Thing t, int x, int y) {
        sandbox.putEntity(t, x, y);
    }

    public void removeEntity(int x, int y) {
        sandbox.removeEntity(x, y);
    }

    public Thing getActiveThing(int x, int y) {
        return sandbox.getActiveThing(x, y);
    }

    public void playerStartMoving(Direction direction) {
        player.setDirection(direction);
        player.startMoving();
    }

    public void playerStopMoving() {
        player.stopMoving();
    }

    public Direction playerDirection() {
        return player.getDirection();
    }

    public void playerStartShooting(Direction direction) {
        player.setShootDirection(direction);
        player.startShooting();
    }

    public void playerStopShooting() {
        player.stopShooting();
    }

    public Direction playerAttackDirection() {
        return player.getShootDirection();
    }

    public void addMob(Mob mob) {
        entities.add(mob);
        if (mob instanceof Creature) {
            creatureCount.inc();
            if (mob instanceof Player) {
                playerCount.inc();
            } else if (mob instanceof Monster) {
                monsterCount.inc();
            }
        }
    }

    public void removeMob(Mob mob) {
        entities.remove(mob);
        if (mob instanceof Creature) {
            creatureCount.dec();
            if (mob instanceof Player) {
                playerCount.dec();
            } else if (mob instanceof Monster) {
                monsterCount.dec();
            }
        }
    }

    public void initializeArena() {
        sandbox = new World();
        for (int x = 1; x < World.WIDTH - 1; x++) {
            putItem(new HorizontalWall(), x, 0);
            putItem(new HorizontalWall(), x, World.HEIGHT - 2);
        }
        for (int y = 1; y < World.HEIGHT - 2; y++) {
            putItem(new VerticalWall(), 0, y);
            putItem(new VerticalWall(), World.WIDTH - 1, y);
        }
        putItem(new TopLeftCorner(), 0, 0);
        putItem(new TopRightCorner(), World.WIDTH - 1, 0);
        putItem(new BottomLeftCorner(), 0, World.HEIGHT - 2);
        putItem(new BottomRightCorner(), World.WIDTH - 1, World.HEIGHT - 2);
        for (int x = 0; x < World.WIDTH; x++) {
            putItem(new Empty(), x, World.HEIGHT - 1);
        }
    }

    public void refreshStatusBar() {
        int currentHealth = player.currentHealth();
        int currentDamage = player.getBullet().getDamage();
        for (int i = 0; i < World.WIDTH; i++) {
            if (getEntity(i, World.HEIGHT - 1) != null) {
                removeEntity(i, World.HEIGHT - 1);
            }
        }
        for (int i = 0; i < currentHealth; i++) {
            putEntity(new Heart(), World.WIDTH - 1 - i, World.HEIGHT - 1);
        }
        for (int i = 0; i < currentDamage; i++) {
            putEntity(new Diamond(), i, World.HEIGHT - 1);
        }
    }

    public void play(int playerId) {
        initializeArena();
        playerCount = new Counter();
        creatureCount = new Counter();
        monsterCount = new Counter();
        entities = new HashSet<>();
        Game.consoleLog("Game " + this + " started...\nScheduler: " + scheduler);

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

        creatureFactory.createRat(player, 31, 1);
        creatureFactory.createBat(player, 31, 31);
        creatureFactory.createSnake(player, 1, 31);
        creatureFactory.createScorpion(player, 1, 1);

        startFrame();
    }

    public void pause() {
        Game.consoleLog("Game " + this + " paused...");
        try {
            Mob.actionLock.lock();
            for (Mob entity : entities) {
                entity.stopAction();
                if (entity instanceof Creature) {
                    ((Creature) entity).stopAttacking();
                }
            }
            stopFrame();
        } finally {
            Mob.actionLock.unlock();
        }
    }

    public void resume() {
        Game.consoleLog("Game " + this + " resumed...");
        try {
            Mob.actionLock.lock();
            startFrame();
            for (Mob entity : entities) {
                entity.startAction();
                if (entity instanceof Creature) {
                    ((Creature) entity).startAttacking();
                }
            }
        } finally {
            Mob.actionLock.unlock();
        }
    }

    public void resume(PlayScreen frontend) {
        this.frontend = frontend;
        this.creatureFactory = new CreatureFactory(this);
        resume();
    }

    private void startFrame() {
        if (frameHandler == null) {
            frameHandler = scheduler.scheduleAtFixedRate(this, 0, 40, TimeUnit.MILLISECONDS);
        }
    }

    private void stopFrame() {
        if (frameHandler != null) {
            frameHandler.cancel(false);
            frameHandler = null;
        }
    }

    @Override
    public void run() {
        if (player.currentHealth() == 0) {
            frontend.quitGame(false);
        } else {
            if (monsterCount.check()) {
                frontend.quitGame(true);
            }
        }
    }

}
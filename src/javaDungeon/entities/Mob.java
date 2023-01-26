package javaDungeon.entities;

import java.awt.Color;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javaDungeon.blocks.Floor;
import javaDungeon.game.Direction;
import javaDungeon.game.Game;
import javaDungeon.game.Thing;

public class Mob extends Thing implements Runnable {

    public static Lock actionLock = new ReentrantLock();

    private final int speed;

    public static int speedToMilliseconds(int speed) {
        return 2000 / speed;
    }

    public int getSpeed() {
        return speed;
    }

    private volatile int health;

    public synchronized int getHealth() {
        return health;
    }

    public synchronized void loseHealth(int damage) {
        health = health > damage ? health - damage : 0;
    }

    private volatile boolean moving = false;

    public synchronized void startMoving() {
        moving = true;
    }

    public synchronized void stopMoving() {
        moving = false;
    }

    public synchronized boolean isMoving() {
        return moving;
    }

    private volatile Direction direction = Direction.DIR_UP;

    public synchronized void setDir(Direction direction) {
        this.direction = direction;
    }

    public synchronized Direction getDir() {
        return direction;
    }

    public Thing queryAdjacent(Direction request) {
        return world.getActiveThing(getX() + request.getxDir(), getY() + request.getyDir());
    }

    public final Game game;

    public Mob(Color color, char glyph, Game game, int health, int speed) {
        super(color, glyph, game.getSandbox());
        this.game = game;
        this.health = health;
        this.speed = speed;
    }

    public void move() {
        if (moving) {
            Thing thing = queryAdjacent(direction);
            if (thing instanceof Floor) {
                world.removeEntity(getX(), getY());
                world.putEntity(this, thing.getX(), thing.getY());
            }
        }
    }

    public void see() {
    }

    public void die() {
        stopMoving();
        world.removeEntity(getX(), getY());
        stopAction();
    }

    private ScheduledFuture<?> actionHandler;

    public void startAction() {
        actionHandler = game.scheduler.scheduleAtFixedRate(this, 0, Mob.speedToMilliseconds(speed),
                TimeUnit.MILLISECONDS);
    }

    public void stopAction() {
        actionHandler.cancel(false);
    }

    @Override
    public void run() {
        try {
            Mob.actionLock.lock();
            if (health > 0) {
                see();
                move();
            } else {
                die();
            }
        } finally {
            Mob.actionLock.unlock();
        }
    }

}
package javaDungeon.game;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

import javaDungeon.event.*;
import javaDungeon.game.behavior.*;
import javaDungeon.game.entity.Entity;
import javaDungeon.game.entity.creature.Creature;
import javaDungeon.game.entity.creature.enemy.*;
import javaDungeon.game.entity.creature.player.*;
import javaDungeon.game.floor.*;
import javaDungeon.game.wall.*;
import javaDungeon.game.weapon.Weapon;

public class World implements Serializable {

    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;

    public void consoleLog(Object sender, String message) {
        System.out.println(String.format("Log (%s) ", new Date())
                + String.format("%s@%x", sender.getClass().getSimpleName(), sender.hashCode())
                + String.format(": %s (%d)", message, currentFrame));
    }

    private Tile[][] tiles;
    private Set<Entity> entities;
    private Player<? extends Weapon> player;
    private int currentFrame = 0;
    private int enemyCount = 0;

    public World() {
        tiles = new Tile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
        consoleLog(this, "Game initialized.");
    }

    public Thing getBackground(int x, int y) {
        return tiles[x][y].getBackground();
    }

    public void putBackground(Thing thing, int x, int y) {
        tiles[x][y].setBackground(thing);
    }

    public void removeBackground(int x, int y) {
        tiles[x][y].removeBackground();
    }

    public Thing getForeground(int x, int y) {
        return tiles[x][y].getForeground();
    }

    public Thing getForeground(Thing entity, Direction direction) {
        int newX = entity.getX() + direction.diffX();
        int newY = entity.getY() + direction.diffY();
        return getForeground(newX, newY);
    }

    public void putForeground(Thing thing, int x, int y) {
        tiles[x][y].setForeground(thing);
    }

    public void putForeground(Thing thing, Thing entity, Direction direction) {
        int newX = entity.getX() + direction.diffX();
        int newY = entity.getY() + direction.diffY();
        putForeground(thing, newX, newY);
    }

    public boolean testForeground(int x, int y) {
        return tiles[x][y].getForeground() == null;
    }

    public boolean testForeground(Thing entity, Direction direction) {
        int newX = entity.getX() + direction.diffX();
        int newY = entity.getY() + direction.diffY();
        return testForeground(newX, newY);
    }

    public void removeForeground(int x, int y) {
        tiles[x][y].removeForeground();
    }

    public Player<? extends Weapon> getPlayer() {
        return player;
    }

    public void setPlayer(Player<? extends Weapon> player) {
        this.player = player;
    }

    public void newEntity(Entity entity, int x, int y) {
        if (entity instanceof Creature) {
            consoleLog(entity, String.format("Creature created at (%d, %d).", x, y));
            if (entity instanceof Enemy) {
                enemyCount++;
            }
        }
        entities.add(entity);
        putForeground(entity, x, y);
    }

    public void removeEntity(Entity entity) {
        if (entity instanceof Creature) {
            consoleLog(entity, String.format("Creature died at (%d, %d).", entity.getX(), entity.getY()));
            if (entity instanceof Enemy) {
                enemyCount--;
            }
        }
        entities.remove(entity);
        removeForeground(entity.getX(), entity.getY());
    }

    public void newFrame() throws Defeat, Victory {
        currentFrame++;
        if (currentFrame == 1) {
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    putBackground(new Mosaic(), i, j);
                }
            }
            for (int x = 1; x < World.WIDTH - 1; x++) {
                putForeground(new HorizontalWall(), x, 0);
                putForeground(new HorizontalWall(), x, World.HEIGHT - 1);
            }
            for (int y = 1; y < World.HEIGHT - 1; y++) {
                putForeground(new VerticalWall(), 0, y);
                putForeground(new VerticalWall(), World.WIDTH - 1, y);
            }
            putForeground(new TopLeftCorner(), 0, 0);
            putForeground(new TopRightCorner(), World.WIDTH - 1, 0);
            putForeground(new BottomLeftCorner(), 0, World.HEIGHT - 1);
            putForeground(new BottomRightCorner(), World.WIDTH - 1, World.HEIGHT - 1);
            entities = new HashSet<>();
            newEntity(player, 1, 1);
            newEntity(new Dwarf(this, player), 10, 10);
            newEntity(new Dwarf(this, player), 20, 20);
            newEntity(new Crab(this, player), 30, 30);
            consoleLog(this, "Game started.");
        } else {
            for (Entity entity : new LinkedList<>(entities)) {
                if (entity instanceof Passive) {
                    ((Passive) entity).takeDamage(((Passive) entity).detectDamage(), currentFrame);
                }
            }
            for (Entity entity : new LinkedList<>(entities)) {
                if (entity.isObsolete()) {
                    removeEntity(entity);
                }
            }
            if (player.isObsolete()) {
                consoleLog(this, "Game ended. You lost!");
                throw new Defeat();
            }
            if (enemyCount == 0) {
                consoleLog(this, "Game ended. You win!");
                throw new Victory();
            }
            for (Entity entity : new LinkedList<>(entities)) {
                if (entity instanceof Ranged) {
                    ((Ranged) entity).rangedAttack(((Ranged) entity).nextRangedAttack(), currentFrame);
                }
            }
            for (Entity entity : new LinkedList<>(entities)) {
                if (entity instanceof Mobile) {
                    ((Mobile) entity).takeStep(((Mobile) entity).nextStep(), currentFrame);
                }
            }
        }
    }

    public void keyPressed(KeyEvent key) {
        player.keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        player.keyReleased(key);
    }

}

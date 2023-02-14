package javaBasement.game;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javaBasement.event.*;
import javaBasement.game.entity.Entity;
import javaBasement.game.entity.bullet.Bullet;
import javaBasement.game.entity.creature.enemy.*;
import javaBasement.game.entity.creature.player.*;
import javaBasement.game.floor.*;
import javaBasement.game.wall.*;
import javaBasement.game.weapon.Weapon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class World implements Serializable {

    public static final String DATA_SAVE = "world.db";
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;

    public void consoleLog(Object sender, String message) {
        System.out.println(String.format("Log (%s) %s@%x: %s (%d)",
                new Date(), sender.getClass().getSimpleName(), sender.hashCode(), message, currentFrame));
    }

    private Tile[][] tiles;
    private Player<? extends Weapon> player;
    private Set<Enemy> enemies;
    private transient List<Enemy> obsoleteEnemies;
    private Set<Bullet> bullets;
    private transient List<Bullet> obsoleteBullets;
    private int currentFrame = 0;

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
        if (entity instanceof Enemy) {
            consoleLog(entity, String.format("Enemy created at (%d, %d).", x, y));
            enemies.add((Enemy) entity);
        } else if (entity instanceof Player) {
            consoleLog(entity, String.format("Player created at (%d, %d).", x, y));
            player = (Player<?>) entity;
        } else if (entity instanceof Bullet) {
            bullets.add((Bullet) entity);
        }
        putForeground(entity, x, y);
    }

    public void removeEntity(Entity entity) {
        int x = entity.getX();
        int y = entity.getY();
        if (entity instanceof Enemy) {
            consoleLog(entity, String.format("Enemy died at (%d, %d).", x, y));
            enemies.remove((Enemy) entity);
        } else if (entity instanceof Player) {
            consoleLog(entity, String.format("Player died at (%d, %d).", x, y));
        } else if (entity instanceof Bullet) {
            bullets.remove((Bullet) entity);
        }
        removeForeground(x, y);
    }

    public void newFrame() throws Defeat, Victory {
        currentFrame++;
        if (currentFrame == 1) {
            enemies = new HashSet<>();
            bullets = new HashSet<>();

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

            newEntity(player, 1, 1);
            newEntity(new Dwarf(this, player), 11, 11);
            newEntity(new Chopper(this, player), 12, 12);
            newEntity(new Rat(this), 13, 13);
            newEntity(new Crab(this, player), 14, 14);

            consoleLog(this, "Game started.");
        } else {
            obsoleteBullets = new LinkedList<>();
            obsoleteEnemies = new LinkedList<>();

            for (Bullet bullet : bullets) {
                bullet.takeStep(bullet.nextStep(), currentFrame);
            }
            player.takeDamage(player.detectDamage(), currentFrame);
            if (player.isObsolete()) {
                consoleLog(this, "Game ended. You lost!");
                throw new Defeat();
            }
            for (Enemy enemy : enemies) {
                enemy.takeDamage(enemy.detectDamage(), currentFrame);
            }

            for (Bullet bullet : bullets) {
                if (bullet.isObsolete()) {
                    obsoleteBullets.add(bullet);
                }
            }
            for (Enemy enemy : enemies) {
                if (enemy.isObsolete()) {
                    obsoleteEnemies.add(enemy);
                }
            }

            for (Bullet bullet : obsoleteBullets) {
                removeEntity(bullet);
            }
            for (Enemy enemy : obsoleteEnemies) {
                removeEntity(enemy);
            }
            if (enemies.isEmpty()) {
                consoleLog(this, "Game ended. You won!");
                throw new Victory();
            }

            player.rangedAttack(player.nextRangedAttack(), currentFrame);
            for (Enemy enemy : enemies) {
                if (enemy instanceof RangedEnemy) {
                    ((RangedEnemy<?>) enemy).rangedAttack(((RangedEnemy<?>) enemy).nextRangedAttack(), currentFrame);
                }
            }

            player.takeStep(player.nextStep(), currentFrame);
            for (Enemy enemy : enemies) {
                enemy.takeStep(enemy.nextStep(), currentFrame);
            }
        }
    }

    public void keyPressed(int keyCode) {
        player.keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
        player.keyReleased(keyCode);
    }

}

package javaDungeon.screen;

import java.awt.event.KeyEvent;
import java.io.*;

import asciiPanel.AsciiPanel;
import javaDungeon.event.*;
import javaDungeon.game.*;
import javaDungeon.game.entity.creature.player.*;
import javaDungeon.game.weapon.Weapon;

public class PlayScreen implements Screen {

    private static final String dataSave = "game.dat";

    private World world;

    public PlayScreen(int playerNumber) {
        world = new World();
        if (playerNumber == 1) {
            world.setPlayer(new Player1(world));
        } else if (playerNumber == 2) {
            world.setPlayer(new Player2(world));
        } else if (playerNumber == 3) {
            world.setPlayer(new Player3(world));
        } else if (playerNumber == 4) {
            world.setPlayer(new Player4(world));
        } else if (playerNumber == 5) {
            world.setPlayer(new Player5(world));
        } else if (playerNumber == 6) {
            world.setPlayer(new Player6(world));
        } else if (playerNumber == 7) {
            world.setPlayer(new Player7(world));
        }
    }

    public PlayScreen() {
        try {
            FileInputStream fileInputStream = new FileInputStream(dataSave);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            world = (World) (objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveWorld() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dataSave);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(world);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh(AsciiPanel terminal) throws Victory, Defeat {
        try {
            world.newFrame();
        } finally {
            for (int x = 0; x < World.WIDTH; x++) {
                for (int y = 0; y < World.HEIGHT; y++) {
                    Thing front = world.getForeground(x, y);
                    if (front != null) {
                        terminal.write(front.getGlyph(), x, y, front.getColor());
                    } else {
                        Thing back = world.getBackground(x, y);
                        if (back != null) {
                            terminal.write(back.getGlyph(), x, y, back.getColor());
                        }
                    }
                }
            }
            Player<? extends Weapon> currentPlayer = world.getPlayer();
            Weapon currentWeapon = currentPlayer.getWeapon();
            int currentHealth = currentPlayer.getCurrentHealth();
            int currentDamage = currentWeapon.getDamage();
            for (int x = 0; x < World.WIDTH; x++) {
                terminal.write(" ", x, World.HEIGHT);
            }
            for (int i = 0; i < currentHealth; i++) {
                terminal.write(Player.GLYPH, World.WIDTH - 1 - i, World.HEIGHT, currentPlayer.getColor());
            }
            for (int i = 0; i < currentDamage; i++) {
                terminal.write(currentWeapon.getGlyph(), i, World.HEIGHT, currentPlayer.getColor());
            }
        }
    }

    @Override
    public Screen keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
            return new PauseScreen(this);
        } else {
            world.keyPressed(key);
            return this;
        }
    }

    @Override
    public Screen keyReleased(KeyEvent key) {
        world.keyReleased(key);
        return this;
    }

}

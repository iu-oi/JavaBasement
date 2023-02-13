package javaDungeon.screen;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import asciiPanel.AsciiPanel;
import javaDungeon.event.*;
import javaDungeon.game.*;
import javaDungeon.game.entity.creature.player.*;
import javaDungeon.game.weapon.Weapon;

public class PlayScreen extends Screen {

    private World world;
    private Player<? extends Weapon> player;
    private Bar playerStatus;
    private Bar weaponStatus;

    public PlayScreen(int playerNumber, AsciiPanel mainPanel, AsciiPanel subPanel) {
        super(mainPanel, subPanel);

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
        } else {
            world.setPlayer(new Player1(world));
        }

        player = world.getPlayer();
        playerStatus = addBar(player.getGlyph(), player.getColor());
        weaponStatus = addBar(player.getWeapon().getGlyph(), player.getColor());
    }

    public PlayScreen(AsciiPanel mainPanel, AsciiPanel subPanel) {
        super(mainPanel, subPanel);
        try {
            FileInputStream fileInputStream = new FileInputStream(World.DATA_SAVE);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            world = (World) (objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
            world.consoleLog(this, "Game loaded from '" + World.DATA_SAVE + "'.");
        } catch (Exception e) {
            world = new World();
            world.setPlayer(new Player1(world));
            world.consoleLog(this, "Failed to load game from '" + World.DATA_SAVE + "'.");
        } finally {
            player = world.getPlayer();
            playerStatus = addBar(player.getGlyph(), player.getColor());
            weaponStatus = addBar(player.getWeapon().getGlyph(), player.getColor());
        }
    }

    @Override
    public void refresh() throws Victory, Defeat {
        try {
            world.newFrame();
        } finally {
            for (int x = 0; x < World.WIDTH; x++) {
                for (int y = 0; y < World.HEIGHT; y++) {
                    Thing thing = world.getForeground(x, y);
                    if (thing == null) {
                        thing = world.getBackground(x, y);
                    }
                    if (thing != null) {
                        display(thing.getGlyph(), x, y, thing.getColor());
                    }
                }
            }
            int currentHealth = player.getCurrentHealth();
            int damage = player.getWeapon().getDamage();
            playerStatus.setDescription(String.format("x%d", currentHealth));
            weaponStatus.setDescription(String.format("x%d", damage));
            displayStatus();
        }
    }

    @Override
    public Screen keyPressed(int keyCode) {
        world.keyPressed(keyCode);
        return this;
    }

    @Override
    public Screen keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            return new PauseScreen(mainPaniel, subPanel, this, world);
        } else {
            world.keyReleased(keyCode);
        }
        return this;
    }

}

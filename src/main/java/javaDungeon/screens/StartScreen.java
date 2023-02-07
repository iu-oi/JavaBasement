package javaDungeon.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import javaDungeon.Application;
import javaDungeon.game.mob.creature.player.Player;
import javaDungeon.game.mob.creature.player.Player1;
import javaDungeon.game.mob.creature.player.Player2;
import javaDungeon.game.mob.creature.player.Player3;
import javaDungeon.game.mob.creature.player.Player4;
import javaDungeon.game.mob.creature.player.Player5;
import javaDungeon.game.mob.creature.player.Player6;
import javaDungeon.game.mob.creature.player.Player7;

public class StartScreen implements Screen {

    private Application mainFrame;

    public StartScreen(Application mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("-- Java Dungeon --", 15);
        terminal.writeCenter("Choose your character", 16);
        terminal.write(Player.GLYPH, 10, 17, Player1.COLOR);
        terminal.write(Player.GLYPH, 12, 17, Player2.COLOR);
        terminal.write(Player.GLYPH, 14, 17, Player3.COLOR);
        terminal.write(Player.GLYPH, 16, 17, Player4.COLOR);
        terminal.write(Player.GLYPH, 18, 17, Player5.COLOR);
        terminal.write(Player.GLYPH, 20, 17, Player6.COLOR);
        terminal.write(Player.GLYPH, 22, 17, Player7.COLOR);
        terminal.writeCenter("1 2 3 4 5 6 7", 18);
        terminal.writeCenter("0 Load(You must save first)", 19);
    }

    @Override
    public Screen respondToKeyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_0:
                return new PlayScreen("game.dat", mainFrame);
            case KeyEvent.VK_1:
                return new PlayScreen("game.dat", 1, mainFrame);
            case KeyEvent.VK_2:
                return new PlayScreen("game.dat", 2, mainFrame);
            case KeyEvent.VK_3:
                return new PlayScreen("game.dat", 3, mainFrame);
            case KeyEvent.VK_4:
                return new PlayScreen("game.dat", 4, mainFrame);
            case KeyEvent.VK_5:
                return new PlayScreen("game.dat", 5, mainFrame);
            case KeyEvent.VK_6:
                return new PlayScreen("game.dat", 6, mainFrame);
            case KeyEvent.VK_7:
                return new PlayScreen("game.dat", 7, mainFrame);
        }
        return this;
    }

    @Override
    public Screen respondToKeyReleased(KeyEvent key) {
        return this;
    }
}
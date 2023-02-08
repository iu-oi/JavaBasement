package javaDungeon.screen;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import javaDungeon.game.entity.creature.player.*;

public class StartScreen implements Screen {

    @Override
    public void refresh(AsciiPanel terminal) {
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
    public Screen keyPressed(KeyEvent key) {
        return this;
    }

    @Override
    public Screen keyReleased(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_0:
                return new PlayScreen();
            case KeyEvent.VK_1:
            case KeyEvent.VK_2:
            case KeyEvent.VK_3:
            case KeyEvent.VK_4:
            case KeyEvent.VK_5:
            case KeyEvent.VK_6:
            case KeyEvent.VK_7:
                return new PlayScreen(key.getKeyCode() - KeyEvent.VK_0);
            default:
                return this;
        }
    }
}

package javaDungeon;

import javax.swing.JFrame;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
import javaDungeon.game.World;
import javaDungeon.screens.Screen;
import javaDungeon.screens.StartScreen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainFrame extends JFrame implements KeyListener {

    private AsciiPanel terminal;
    private Screen screen;

    public MainFrame() {
        super();
        terminal = new AsciiPanel(World.WIDTH, World.HEIGHT, AsciiFont.CP437_16x16);
        add(terminal);
        pack();
        addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setScreen(new StartScreen(this));
    }

    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    public void keyPressed(KeyEvent e) {
        setScreen(screen.respondToKeyPressed(e));
    }

    public void keyReleased(KeyEvent e) {
        setScreen(screen.respondToKeyReleased(e));
    }

    public void keyTyped(KeyEvent e) {
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                mainFrame.repaint();
            }

        }, 0, 40, TimeUnit.MILLISECONDS);
    }

}
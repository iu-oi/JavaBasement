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

public class Application extends JFrame implements KeyListener, Runnable {

    private AsciiPanel terminal;
    private Screen screen;

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Application() {
        super();
        terminal = new AsciiPanel(World.WIDTH, World.HEIGHT, new AsciiFont("Redjack17.png", 16, 16));
    }

    public void keyPressed(KeyEvent e) {
        setScreen(screen.respondToKeyPressed(e));
    }

    public void keyReleased(KeyEvent e) {
        setScreen(screen.respondToKeyReleased(e));
    }

    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void run() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.add(app.terminal);
        app.pack();
        app.addKeyListener(app);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
        app.setScreen(new StartScreen(app));
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(app, 0, 40, TimeUnit.MILLISECONDS);
    }

}

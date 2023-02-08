package javaDungeon;

import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import asciiPanel.*;

import javaDungeon.event.*;
import javaDungeon.game.*;
import javaDungeon.screen.*;

public class Application extends JFrame implements KeyListener, Runnable {

    private AsciiPanel terminal;
    private Screen screen;

    public Application() {
        super();
        AsciiFont style = new AsciiFont("Redjack17.png", 16, 16);
        terminal = new AsciiPanel(World.WIDTH, World.HEIGHT + 1, style);
    }

    private synchronized void setScreen(Screen screen) {
        this.screen = screen;
    }

    private synchronized void refreshScreen() throws Victory, Defeat {
        terminal.clear();
        screen.refresh(terminal);
    }

    public void repaint() {
        try {
            refreshScreen();
        } catch (Victory event) {
            screen = new WinScreen();
        } catch (Defeat event) {
            screen = new LoseScreen();
        }
        super.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setScreen(screen.keyPressed(e));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setScreen(screen.keyReleased(e));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void run() {
        repaint();
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.add(app.terminal);
        app.pack();
        app.addKeyListener(app);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
        app.setScreen(new StartScreen());
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(app, 0, 50, TimeUnit.MILLISECONDS);
    }

}

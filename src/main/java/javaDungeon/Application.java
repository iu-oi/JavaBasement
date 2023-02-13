package javaDungeon;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import asciiPanel.*;
import javaDungeon.event.*;
import javaDungeon.game.World;
import javaDungeon.screen.*;

public class Application extends JFrame implements KeyListener {

    public static final AsciiFont THEME = new AsciiFont("Redjack17.png", 16, 16);

    private AsciiPanel mainPanel;
    private AsciiPanel subPanel;
    private volatile Screen screen;
    private ScheduledExecutorService threadPool;

    private synchronized void setScreen(Screen screen) {
        this.screen = screen;
    }

    private synchronized void refreshScreen() throws Victory, Defeat {
        mainPanel.clear();
        subPanel.clear();
        screen.refresh();
    }

    private void start() {
        setLayout(new BorderLayout(0, 0));
        add(BorderLayout.CENTER, mainPanel);
        add(BorderLayout.EAST, subPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        addKeyListener(this);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                threadPool.shutdownNow();
                dispose();
                System.exit(0);
            }
        });

        threadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, Screen.REFRESH_RATE, TimeUnit.MILLISECONDS);
    }

    public Application() {
        super("Java Dungeon");
        mainPanel = new AsciiPanel(World.WIDTH, World.HEIGHT, THEME);
        subPanel = new AsciiPanel(Bar.WIDTH, World.HEIGHT, THEME);
        screen = new StartScreen(mainPanel, subPanel);
        threadPool = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void repaint() {
        try {
            refreshScreen();
        } catch (Victory event) {
            setScreen(new WinScreen(mainPanel, subPanel));
        } catch (Defeat event) {
            setScreen(new LoseScreen(mainPanel, subPanel));
        } finally {
            super.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setScreen(screen.keyPressed(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            setScreen(screen.keyReleased(e.getKeyCode()));
        } catch (Quit event) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        new Application().start();
    }

}

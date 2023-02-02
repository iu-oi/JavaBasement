package javaDungeon.game;

import java.io.Serializable;

import javaDungeon.blocks.Floor;

public class World implements Serializable{

    public static final int WIDTH = 33;
    public static final int HEIGHT = 34;

    private Tile<Thing, Thing>[][] tiles;

    public World() {
        tiles = new Tile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                tiles[i][j].setItem(new Floor());
            }
        }
    }

    public Thing getItem(int x, int y) {
        return this.tiles[x][y].getItem();
    }

    public void putItem(Thing t, int x, int y) {
        this.tiles[x][y].setItem(t);
    }

    public void removeItem(int x, int y) {
        this.tiles[x][y].removeThing();
    }

    public Thing getEntity(int x, int y) {
        return this.tiles[x][y].getEntity();
    }

    public void putEntity(Thing t, int x, int y) {
        this.tiles[x][y].setEntity(t);
    }

    public void removeEntity(int x, int y) {
        this.tiles[x][y].removeEntity();
    }

    public Thing getActiveThing(int x, int y) {
        Thing back = getItem(x, y);
        Thing front = getEntity(x, y);
        if (front != null) {
            return front;
        } else {
            return back;
        }
    }

}
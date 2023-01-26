package javaDungeon.blocks;

import javaDungeon.game.World;

public class HorizontalWall extends Wall {

    public HorizontalWall(World world) {
        super((char) 0xcd, world);
    }
}

package javaDungeon.blocks;

import javaDungeon.game.World;

public class VerticalWall extends Wall {

    public VerticalWall(World world) {
        super((char) 0xba, world);
    }
}

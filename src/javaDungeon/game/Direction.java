package javaDungeon.game;

public enum Direction {

    DIR_UP {
        public int getxDir() {
            return 0;
        }

        public int getyDir() {
            return -1;
        }
    },
    DIR_LEFT {
        public int getxDir() {
            return -1;
        }

        public int getyDir() {
            return 0;
        }
    },
    DIR_DOWN {
        public int getxDir() {
            return 0;
        }

        public int getyDir() {
            return 1;
        }
    },
    DIR_RIGHT {
        public int getxDir() {
            return 1;
        }

        public int getyDir() {
            return 0;
        }
    };

    public abstract int getxDir();

    public abstract int getyDir();

    public static Direction getOppositeDir(Direction direction) {
        switch (direction) {
            case DIR_UP:
                return DIR_DOWN;
            case DIR_LEFT:
                return DIR_RIGHT;
            case DIR_DOWN:
                return DIR_UP;
            case DIR_RIGHT:
                return DIR_LEFT;
        }
        return null;
    }

    public static Direction calcApproachDir(int xPos1, int yPos1, int xPos2, int yPos2) {
        int xDiff = xPos1 - xPos2;
        int yDiff = yPos1 - yPos2;

        if (Math.abs(xDiff) < Math.abs(yDiff)) {
            if (yPos1 < yPos2) {
                return DIR_DOWN;
            } else {
                return DIR_UP;
            }
        } else {
            if (xPos1 < xPos2) {
                return DIR_RIGHT;
            } else {
                return DIR_LEFT;
            }
        }
    }

    public static Direction calcEscapeDir(int xPos1, int yPos1, int xPos2, int yPos2) {
        return getOppositeDir(calcApproachDir(xPos1, yPos1, xPos2, yPos2));
    }
}

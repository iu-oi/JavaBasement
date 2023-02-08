package javaDungeon.game;

public enum Direction {

    UP {
        public int diffX() {
            return 0;
        }

        public int diffY() {
            return -1;
        }

        public Direction opposite() {
            return DOWN;
        }
    },
    LEFT {
        public int diffX() {
            return -1;
        }

        public int diffY() {
            return 0;
        }

        public Direction opposite() {
            return RIGHT;
        }
    },
    DOWN {
        public int diffX() {
            return 0;
        }

        public int diffY() {
            return 1;
        }

        public Direction opposite() {
            return UP;
        }
    },
    RIGHT {
        public int diffX() {
            return 1;
        }

        public int diffY() {
            return 0;
        }

        public Direction opposite() {
            return LEFT;
        }
    };

    public abstract int diffX();

    public abstract int diffY();

    public abstract Direction opposite();

}

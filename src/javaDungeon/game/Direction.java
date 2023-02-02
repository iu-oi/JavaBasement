package javaDungeon.game;

public enum Direction {

    DIR_UP {
        public int getxDir() {
            return 0;
        }

        public int getyDir() {
            return -1;
        }

        public Direction opposite() {
            return DIR_DOWN;
        }
    },
    DIR_LEFT {
        public int getxDir() {
            return -1;
        }

        public int getyDir() {
            return 0;
        }

        public Direction opposite() {
            return DIR_RIGHT;
        }
    },
    DIR_DOWN {
        public int getxDir() {
            return 0;
        }

        public int getyDir() {
            return 1;
        }

        public Direction opposite() {
            return DIR_UP;
        }
    },
    DIR_RIGHT {
        public int getxDir() {
            return 1;
        }

        public int getyDir() {
            return 0;
        }

        public Direction opposite() {
            return DIR_LEFT;
        }
    };

    public abstract int getxDir();

    public abstract int getyDir();

    public abstract Direction opposite();

}

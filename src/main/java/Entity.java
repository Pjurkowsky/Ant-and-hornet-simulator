import java.util.Random;
import java.util.logging.XMLFormatter;

abstract class Entity {
    private enum Direction {
        TOPLEFT, TOP, TOPRIGHT,
        LEFT, CENTRE, RIGHT,
        BOTTOMLEFT, BOTTOM, BOTTOMRIGHT;

        public static Direction fromInteger(int x) {
            switch (x) {
                case 0:
                    return TOPLEFT;
                case 1:
                    return TOP;
                case 2:
                    return TOPRIGHT;
                case 3:
                    return LEFT;
                case 4:
                    return CENTRE;
                case 5:
                    return RIGHT;
                case 6:
                    return BOTTOMLEFT;
                case 7:
                    return BOTTOM;
                case 8:
                    return BOTTOMRIGHT;
            }
            return CENTRE;
        }
    }

    Direction direction = Direction.CENTRE;

    protected int x = 0;
    protected int y = 0;
    private Random random = new Random();


    private void changeDir() {
        direction = Direction.fromInteger(random.nextInt(9));
    }

    private void checkBounds() {
        int maxXPos = SimScreen.WIDTH / SimScreen.BLOCKSIZE;
        int maxYPos = SimScreen.HEIGHT / SimScreen.BLOCKSIZE;

        if (x < 0)
            x = 0;
        else if (x >= maxXPos)
            x = maxXPos - 1;
        if (y < 0)
            y = 0;
        else if (y >= maxYPos)
            y = maxYPos - 1;
    }

    public void movement(int map[][]) {

        int prevX = x;
        int prevY = y;
        System.out.println("X" + x);
        System.out.println("Y" + y);
        do {
            x = prevX;
            y = prevY;
            changeDir();
            if (direction == Direction.TOPLEFT || direction == Direction.TOP || direction == Direction.TOPRIGHT)
                y += 1;
            if (direction == Direction.BOTTOMLEFT || direction == Direction.BOTTOM || direction == Direction.BOTTOMRIGHT)
                y -= 1;
            if (direction == Direction.TOPRIGHT || direction == Direction.RIGHT || direction == Direction.BOTTOMRIGHT)
                x += 1;
            if (direction == Direction.TOPLEFT || direction == Direction.LEFT || direction == Direction.BOTTOMLEFT)
                x -= 1;
            checkBounds();
        } while (map[y][x] == 1 || map[y][x] == 2);


    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

import java.util.Random;

abstract class Insect extends Rectangle {
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


    private Random random = new Random();



    Insect(int x, int y){
        super(x,y);
    }

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

    public void movement() {
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
    }


}

import java.util.Random;

abstract class Insect extends Entity {

    Direction direction = Direction.CENTRE;


    private Random random = new Random();



    Insect(int x, int y){
        super(x,y);
    }

    protected void changeDir() {
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

    protected void movement() {
            if (direction == Direction.TOPLEFT || direction == Direction.TOP || direction == Direction.TOPRIGHT)
                y -= 1;
            if (direction == Direction.BOTTOMLEFT || direction == Direction.BOTTOM || direction == Direction.BOTTOMRIGHT)
                y += 1;
            if (direction == Direction.TOPRIGHT || direction == Direction.RIGHT || direction == Direction.BOTTOMRIGHT)
                x += 1;
            if (direction == Direction.TOPLEFT || direction == Direction.LEFT || direction == Direction.BOTTOMLEFT)
                x -= 1;
            checkBounds();
    }


}

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Hornet extends Insect {
    Random random;

    Hornet(int x, int y) {
        super(x, y);
        model.setBackground(Color.RED);
    }

    private Entity attack(ArrayList<Entity> map) {

        for (Entity entity : map) {
            if ((entity.getName() == "Ant" || entity.getName() == "SoliderAnt") && entity.getX() == getX() && entity.getY() == getY()) {
                random = new Random();
                if (random.nextDouble() > 0.5) {
                    return entity;
                } else
                    return null;
            }
        }
        return null;
    }



    @Override
    public Object[] update(ArrayList<Entity> map) {

        Entity flower = checkForClosest(map, "Flower");
        if (flower != null && calcDistance(flower.getX(), flower.getY()) <= 4) {

            int dir = Direction.toInteger(direction);
            System.out.println("dir: " +dir);
            dir += 6;
            dir %= 8;

            direction = Direction.fromInteger(dir);
            System.out.println("dir2: " + Direction.toInteger(direction));
            model.setBackground(Color.CYAN);
        }
        else {
            changeDir();
            model.setBackground(Color.RED);
        }


        movement();
        return new Object[] { attack(map), false};

    }

    @Override
    public String getName() {
        return "Hornet";
    }
}
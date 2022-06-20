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
        for (int i = 0; i < map.size(); i++) {
            Entity entity = map.get(i);
            if ((entity.getName() == "Ant" || entity.getName() == "SoliderAnt") && entity.getX() == getX() && entity.getY() == getY()) {
                random = new Random();
                if (random.nextDouble() > Parameters.getProbOfHornetKillAnt()) {
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
            dir += 6;
            dir %= 8;
            direction = Direction.fromInteger(dir);
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
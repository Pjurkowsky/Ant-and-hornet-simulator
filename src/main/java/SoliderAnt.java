import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SoliderAnt extends Ant {
    Random random;

    SoliderAnt(int x, int y) {
        super(x, y);
        model.setBackground(Color.DARK_GRAY);
    }


    private Entity attack(ArrayList<Entity> map) {

        for (Entity entity : map) {
            Entity hornet = entity;

            if (hornet.getName() == "Hornet" && hornet.getX() == getX() && hornet.getY() == getY()) {
                random = new Random();

                if (random.nextDouble() > 0.5){
                    System.out.println("Hornet");
                    return entity;
                }
                else
                    return null;
            }
        }
        return null;
    }

    @Override
    public Entity update(ArrayList<Entity> map) {
        movement();
        return attack(map);
    }

    @Override
    public String getName() {
        return "SoliderAnt";
    }

}

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Hornet extends Insect implements Entity {
    Random random;
    Hornet(int x, int y) {
        super(x, y);
        model.setBackground(Color.RED);
    }

    private Entity attack(ArrayList<Entity> map) {

        for (Entity entity: map) {
            Rectangle ant = (Rectangle) entity;
            if ((ant.getName() == "Ant" || ant.getName() == "SoliderAnt") && ant.getX() == getX() && ant.getY() == getY()) {

                random = new Random();
                if ( random.nextDouble() > 0.5){
                    System.out.println( ant.getName());
                    return entity;
                }


                else
                    return null;
            }
        }
        return null;
    }

    @Override
    public JPanel draw() {
        model.setBounds(x * SimScreen.BLOCKSIZE + 1, y * SimScreen.BLOCKSIZE + 1 + SimScreen.offsetOfHeight, SimScreen.BLOCKSIZE - 1, SimScreen.BLOCKSIZE - 1);
        return model;
    }

    @Override
    public Entity update(ArrayList<Entity> map) {
        movement();

        return attack( map);
    }

    @Override
    public String getName() {
        return "Hornet";
    }
}
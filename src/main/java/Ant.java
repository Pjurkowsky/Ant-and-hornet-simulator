import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Ant extends Insect implements Entity {


    private boolean hasFood = false;

    Ant(int x, int y) {
        super(x, y);
        model.setBackground(Color.BLACK);


    }

    // 0 0 0
    // 0 1 0
    // 0 0 0
    private Entity eat(ArrayList<Entity> map) {

        for (Entity entity: map) {
            Rectangle food = (Rectangle) entity;
            if (food.getName() == "Food" && food.getX() == getX() && food.getY() == getY()) {
                System.out.println(food.getName());
                return entity;
            }
        }
        return null;
    }


    public boolean hasFood() {
        return hasFood;
    }

    @Override
    public JPanel draw() {
        model.setBounds(x * SimScreen.BLOCKSIZE + 1, y * SimScreen.BLOCKSIZE + 1 + SimScreen.offsetOfHeight, SimScreen.BLOCKSIZE - 1, SimScreen.BLOCKSIZE - 1);
        return model;
    }

    @Override
    public Entity update(ArrayList<Entity> map) {
        movement();
        return eat(map);
    }

    @Override
    public String getName() {
        return "Ant";
    }
}

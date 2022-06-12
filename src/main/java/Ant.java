import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ant extends Insect {

    private int nestPosX, nestPosY;

    private boolean hasFood = false;

    Ant(int x, int y) {
        super(x, y);
        nestPosX = x;
        nestPosY = y;
        model.setBackground(Color.BLACK);
    }

    // 0 0 0
    // 0 1 0
    // 0 0 0
    private Entity eat(ArrayList<Entity> map) {
        for (Entity entity : map) {
            Entity food = entity;
            if (food.getName() == "Food" && food.getX() == getX() && food.getY() == getY() && !hasFood) {
                hasFood = true;
                return entity;
            }
        }
        return null;
    }

    private void toNest() {
        double dx = getX() - nestPosX;
        double dy = getY() - nestPosY;

        System.out.println("dx: " + dx);
        System.out.println("dy: " + dy);

        if (dx > 0 && dy > 0)
            direction = Direction.TOPLEFT;
        else if (dx == 0 && dy > 0)
            direction = Direction.TOP;
        else if (dx < 0 && dy > 0)
            direction = Direction.TOPRIGHT;
        else if (dy == 0 && dx > 0)
            direction = Direction.LEFT;
        else if (dy == 0 && dx < 0)
            direction = Direction.RIGHT;
        else if (dx > 0 && dy < 0)
            direction = Direction.BOTTOMLEFT;
        else if (dx == 0 && dy < 0)
            direction = Direction.BOTTOM;
        else if (dy < 0 && dx < 0)
            direction = Direction.BOTTOMRIGHT;
        else if (dy == 0 && dx == 0)
            direction = Direction.CENTRE;


    }


    @Override
    public JPanel draw() {
        model.setBounds(x * SimScreen.BLOCKSIZE + 1, y * SimScreen.BLOCKSIZE + 1 + SimScreen.offsetOfHeight, SimScreen.BLOCKSIZE - 1, SimScreen.BLOCKSIZE - 1);
        return model;
    }

    @Override
    public Entity update(ArrayList<Entity> map) {
        if (!hasFood) {
            changeDir();
        }
        if (hasFood) {
            toNest();
        }
        movement();

        return eat(map);
    }

    @Override
    public String getName() {
        return "Ant";
    }

    public boolean hasFood() {
        return hasFood;
    }

    public void setFood(boolean food) {
        hasFood = food;
    }


}

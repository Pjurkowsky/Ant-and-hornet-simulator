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

    private Entity eat(ArrayList<Entity> map) {
        for (int i = 0; i < map.size(); i++) {
            Entity entity = map.get(i);
            if (entity.getName() == "Food" && entity.getX() == getX() && entity.getY() == getY() && !hasFood) {
                hasFood = true;
                return entity;
            }
        }
        return null;
    }

    private void goTo(int posX, int posY) {
        int dx = getX() - posX;
        int dy = getY() - posY;

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
    public Object[] update(ArrayList<Entity> map) {
        if (!hasFood) {
            changeDir();
            Entity hornet = checkForClosest(map, "Hornet");
            if(hornet != null && calcDistance(hornet.getX(), hornet.getY()) < 4){
                Entity flower = checkForClosest(map, "Flower");
                goTo(flower.getX(),flower.getY());
                model.setBackground(Color.BLUE);
            }
            else
                model.setBackground(Color.BLACK);


        }
        if (hasFood) {
            goTo(nestPosX, nestPosY);
        }
        movement();
        return new Object[] {eat(map), false};
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

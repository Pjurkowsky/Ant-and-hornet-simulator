import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Nest extends Entity {
    Random random;
    private int foodAmount = 0;

    Nest(int x, int y, int numberOfAnts, ArrayList<Entity> map) {
        super(x, y);
        model.setBackground(Color.GREEN);
        map.add(this);
        for (int i = 0; i < numberOfAnts; i++) {
            map.add(new Ant(x, y));
        }
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    private void spawnAnt(ArrayList<Entity> map) {
        random = new Random();
        if (random.nextDouble() >= 0.5) {
            map.add(new SoliderAnt(getX(), getY()));

        } else {
            map.add(new Ant(getX(), getY()));
        }
        foodAmount -= 10;

    }


    @Override
    public JPanel draw() {
        model.setBounds(x * SimScreen.BLOCKSIZE + 1, y * SimScreen.BLOCKSIZE + 1 + SimScreen.offsetOfHeight, SimScreen.BLOCKSIZE - 1, SimScreen.BLOCKSIZE - 1);
        return model;
    }

    @Override
    public Entity update(ArrayList<Entity> map) {
        for (Entity entity : map) {
            if (entity.getName() == "Ant") {
                Ant ant = (Ant) entity;
                if (ant.hasFood() && ant.getX() == getX() && ant.getY() == getY()) {
                    ant.setFood(false);
                    foodAmount++;
                }
            }
        }
        if (foodAmount >= 10)
            spawnAnt(map);

        return null;
    }

    @Override
    public String getName() {
        return "Nest";
    }
}

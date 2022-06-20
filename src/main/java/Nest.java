import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Nest extends Entity {
    Random random;
    private int foodAmount = 0;
    private int totalFoodAmount = 0;

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

    public int getTotalFoodAmount(){
        return totalFoodAmount;
    }

    private Entity spawnAnt() {
        random = new Random();
        if (random.nextDouble() <= Parameters.getProbBornSAnt())
            return new SoliderAnt(getX(), getY());
        else
            return new Ant(getX(), getY());
    }

    @Override
    public Object[] update(ArrayList<Entity> map) {
        Entity newAnt = null;
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).getName() == "Ant") {
                Ant ant = (Ant) map.get(i);
                if (ant.hasFood() && ant.getX() == getX() && ant.getY() == getY()) {
                    ant.setFood(false);
                    foodAmount++;
                    totalFoodAmount++;
                }
            }
        }
        if (foodAmount >= Parameters.getFoodToBornNewAnt()){
            foodAmount -= Parameters.getFoodToBornNewAnt();
            newAnt = spawnAnt();
        }

        return new Object[]{newAnt, true};
    }

    @Override
    public String getName() {
        return "Nest";
    }
}
    
import javax.swing.*;
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

        for (int i = 0; i < map.size(); i++) {
            Entity entity = map.get(i);
            if (entity.getName() == "Hornet" && entity.getX() == getX() && entity.getY() == getY()) {
                random = new Random();
                if (random.nextDouble() > Parameters.getProbOfSAntKillHornet())
                    return entity;
                else
                    return null;
            }
        }
        return null;
    }

    @Override
    public Object[] update(ArrayList<Entity> map) {
        changeDir();
        movement();
        return new Object[] {attack(map), false};

    }

    @Override
    public String getName() {
        return "SoliderAnt";
    }

}

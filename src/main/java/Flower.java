import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Flower extends Entity {

    Flower(int x, int y) {
        super(x, y);
        model.setBackground(Color.YELLOW);
    }


    @Override
    public Object[] update(ArrayList<Entity> map) {
        return new Object[]{null, false};
    }

    @Override
    public String getName() {
        return "Flower";
    }
}

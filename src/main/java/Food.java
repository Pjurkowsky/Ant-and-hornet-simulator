import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Food extends Entity {
    Food(int x, int y) {
        super(x, y);
        model.setBackground(Color.PINK);
    }



    @Override
    public Object[] update(ArrayList<Entity> map) {
        return new Object[]{null, false};
    }

    @Override
    public String getName() {
        return "Food";
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Nest extends Rectangle implements Entity {

    Nest(int x, int y) {
        super(x, y);
        model.setBackground(Color.GREEN);
    }


    @Override
    public JPanel draw() {
        model.setBounds(x * SimScreen.BLOCKSIZE + 1, y * SimScreen.BLOCKSIZE + 1 + SimScreen.offsetOfHeight, SimScreen.BLOCKSIZE - 1, SimScreen.BLOCKSIZE - 1);
        return model;
    }

    @Override
    public Entity update(ArrayList<Entity> map) {
        return null;
    }

    @Override
    public String getName() {
        return "Nest";
    }
}

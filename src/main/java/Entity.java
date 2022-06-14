import javax.swing.*;
import java.util.ArrayList;

abstract class Entity {
    protected int x = 0;
    protected int y = 0;
    protected JPanel model = new JPanel();

    Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return "Rectangle";
    }

    public JPanel draw() {
        model.setBounds(x * SimScreen.BLOCKSIZE + 1, y * SimScreen.BLOCKSIZE + 1 + SimScreen.offsetOfHeight, SimScreen.BLOCKSIZE - 1, SimScreen.BLOCKSIZE - 1);
        return model;
    }

    abstract Object[] update(ArrayList<Entity> map);


}

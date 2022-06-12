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

    abstract JPanel draw();
    abstract Entity update(ArrayList<Entity> map);


}

import javax.swing.*;

abstract class Rectangle {
    protected int x = 0;
    protected int y = 0;
    protected JPanel model = new JPanel();

    Rectangle(int x, int y) {
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
}

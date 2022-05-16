import javax.swing.*;

public class Simulation extends JFrame {
    public static int WIDTH = 900;
    public static int HEIGHT = 900;
    public static int BLOCKSIZE = 50;


    public Simulation() {
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new Simulation();
        System.out.println("essa");
    }
}

import javax.swing.*;
import java.awt.*;

public class Simulation {

    public static final int WIDTH = 900; // width of window
    public static final int HEIGHT = 900; // width of window
    public static final double simScreenFactor = 0.8; // divide screen 20% for controlScreen and 80% for simScreen
    public static final int offsetOfWidth = (int) Math.round(WIDTH * (1 - simScreenFactor));


    public static double FPS = 10;
    private static double timePerFrame = 1000000000.0 / FPS;
    private boolean running = true;

    private int setSteps = 0;


    SimScreen simScreen = new SimScreen();
    ControlScreen controlScreen = new ControlScreen(simScreen);

    public Simulation() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        controlScreen.setPreferredSize(new Dimension(offsetOfWidth, HEIGHT));
        controlScreen.setLayout(null);

        simScreen.setPreferredSize(new Dimension(WIDTH - offsetOfWidth, HEIGHT));
        simScreen.setLayout(null);


        frame.add(controlScreen, BorderLayout.WEST);
        frame.add(simScreen, BorderLayout.EAST);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        double lastTime = System.nanoTime();
        while (running) {
            double delta = System.nanoTime() - lastTime;
            if (delta >= timePerFrame) {
                if (simScreen.getSteps() >= controlScreen.getSetStepsFromTextField())
                    simScreen.setRunning(false);
                simScreen.update();
                controlScreen.update();

                setFPS(controlScreen.getSetFpsFromTextField());
                lastTime = System.nanoTime();
            }
        }
    }

    private void setFPS(double FPS) {
        this.FPS = FPS;
        this.timePerFrame = 1000000000.0 / this.FPS;
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
    }

}

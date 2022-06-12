import javax.swing.*;
import java.awt.*;

public class Simulation {

    public static final int WIDTH = 900; // width of window
    public static final int HEIGHT = 900; // width of window
    public static final double simScreenFactor = 0.8; // divide screen 20% for controlScreen and 80% for simScreen
    public static final int offsetOfWidth = (int) Math.round(WIDTH * (1 - simScreenFactor));

    public static int FPS = 10;
    private static double timePerFrame = 1000000000.0 / FPS;
    private boolean running = true;


    private String fileNameInput = "data.txt";
    private String fileNameOutput = "output.txt";

    private int setSteps = 30;

    SimScreen simScreen;
    ControlScreen controlScreen;

    public Simulation() {
        Parameters.loadDataFromFile("/" + fileNameInput);

        simScreen = new SimScreen();
        controlScreen = new ControlScreen(this, simScreen);
        controlScreen.updateParameters(Parameters.getParameters(0)); // updates parameters for controlScreen and also for simScreen
        simScreen.init();

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
                if (simScreen.getSteps() >= setSteps) {
                    simScreen.setRunning(false);
                }
                simScreen.update();
                controlScreen.update();
                lastTime = System.nanoTime();
            }
        }
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
        this.timePerFrame = 1000000000.0 / this.FPS;
    }

    public static int getFPS() {
        return FPS;
    }

    public void setSetSteps(int setSteps) {
        this.setSteps = setSteps;
    }

    public int getSetSteps() {
        return setSteps;
    }

    public String getFileNameInput() {
        return fileNameInput;
    }

    public String getFileNameOutput() {
        return fileNameOutput;
    }
}

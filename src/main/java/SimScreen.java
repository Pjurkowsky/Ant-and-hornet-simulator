import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class SimScreen extends JPanel {
    public static final int WIDTH = Simulation.WIDTH - Simulation.offsetOfWidth; // width of simWindow
    public static final int HEIGHT = Simulation.HEIGHT; // height of simWindow
    public static final int BLOCKSIZE = 20; // size of cell that's visible on screen

    private boolean running = false;

    private int map[][];
    private JPanel jPanelMap[][];

    private Random random;
    private int nestPosX;
    private int nestPosY;

    private int hornetPosX;
    private int hornetPosY;

    private int flowerPosX;
    private int flowerPosY;

    private int steps = 0;

    private int numberOfAnts = 0;
    private int numberOfHornets = 0;
    private int numberOfFlowers = 0;

    private ArrayList<Ant> ants;
    private ArrayList<Hornet> hornets;


    public SimScreen() {
        //init map and jPanelMap
        map = new int[HEIGHT / BLOCKSIZE][WIDTH / BLOCKSIZE];
        jPanelMap = new JPanel[HEIGHT / BLOCKSIZE][WIDTH / BLOCKSIZE];
        for (int i = 0; i < HEIGHT / BLOCKSIZE; i++) {
            for (int j = 0; j < WIDTH / BLOCKSIZE; j++) {
                map[i][j] = 0;
                jPanelMap[i][j] = new JPanel();
            }
        }
    }


    //used to init new map
    public void init() {
        //reset steps
        steps = 0;

        //reset map
        for (int i = 0; i < HEIGHT / BLOCKSIZE; i++)
            for (int j = 0; j < WIDTH / BLOCKSIZE; j++)
                map[i][j] = 0;

        // add nest to the map
        random = new Random();
        nestPosY = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
        nestPosX = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
        map[nestPosY][nestPosX] = 2;

        // add flowers
        for(int i = 0; i < numberOfFlowers; i++) {
            random = new Random();
            flowerPosX = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
            flowerPosY = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
            map[flowerPosX][flowerPosY] = 4;
        }

        // add ants to map
        System.out.println(numberOfAnts);
        ants = new ArrayList<>();
        for (int i = 0; i < numberOfAnts; i++) {
            ants.add(new Ant(nestPosX + 1, nestPosY + 1));
            map[ants.get(i).getY()][ants.get(i).getX()] = 1;
        }

        // add hornets to map
        System.out.println(numberOfHornets);
        hornets = new ArrayList<>();
        for (int i = 0; i < numberOfHornets; i++) {
            random = new Random();
            hornetPosY = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
            hornetPosX = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
            hornets.add(new Hornet(hornetPosX, hornetPosY));
            map[hornets.get(i).getY()][hornets.get(i).getX()] = 3;

        }
    }


    //render the game
    private void render(Graphics g) {
        drawGrid(g);

        // draw cells
        for (int i = 0; i < HEIGHT / BLOCKSIZE; i++) {
            for (int j = 0; j < WIDTH / BLOCKSIZE; j++) {

                //clear unused cells
                jPanelMap[i][j].setVisible(false);

                //draw ants
                if (map[i][j] == 1) {
                    jPanelMap[i][j].setVisible(true);
                    jPanelMap[i][j].setBackground(Color.BLACK);
                    jPanelMap[i][j].setBounds(j * BLOCKSIZE + 1, i * BLOCKSIZE + 1, BLOCKSIZE - 1, BLOCKSIZE - 1);
                    this.add(jPanelMap[i][j]);
                }

                //draw nest
                if (map[i][j] == 2) {
                    jPanelMap[i][j].setVisible(true);
                    jPanelMap[i][j].setBackground(Color.GREEN);
                    jPanelMap[i][j].setBounds(j * BLOCKSIZE + 1, i * BLOCKSIZE + 1, BLOCKSIZE - 1, BLOCKSIZE - 1);
                    this.add(jPanelMap[i][j]);
                }

                //draw hornets
                if (map[i][j] == 3) {
                    jPanelMap[i][j].setVisible(true);
                    jPanelMap[i][j].setBackground(Color.red);
                    jPanelMap[i][j].setBounds(j * BLOCKSIZE + 1, i * BLOCKSIZE + 1, BLOCKSIZE - 1, BLOCKSIZE - 1);
                    this.add(jPanelMap[i][j]);
                }

                //draw flowers
                if (map[i][j] == 4) {
                    jPanelMap[i][j].setVisible(true);
                    jPanelMap[i][j].setBackground(Color.yellow);
                    jPanelMap[i][j].setBounds(j * BLOCKSIZE + 1, i * BLOCKSIZE + 1, BLOCKSIZE - 1, BLOCKSIZE - 1);
                    this.add(jPanelMap[i][j]);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    public void update() {
        if (running) {
            steps++;
            for (int i = 0; i < numberOfAnts; i++) {
                map[ants.get(i).getY()][ants.get(i).getX()] = 0;
                ants.get(i).movement(map);
                map[ants.get(i).getY()][ants.get(i).getX()] = 1;
            }

            for (int i = 0; i < numberOfHornets; i++) {
                map[hornets.get(i).getY()][hornets.get(i).getX()] = 0;
                hornets.get(i).movement(map);
                map[hornets.get(i).getY()][hornets.get(i).getX()] = 3;
            }
            this.repaint(); //redraw the screen
        }
    }

    private void drawGrid(Graphics g) {
        //draw vertical lines
        for (int i = 0; i <= WIDTH / BLOCKSIZE; i++)
            g.drawLine(i * BLOCKSIZE, 0, i * BLOCKSIZE, HEIGHT);
        //draw horizontal lines
        for (int i = 0; i <= HEIGHT / BLOCKSIZE; i++)
            g.drawLine(1, i * BLOCKSIZE, WIDTH, i * BLOCKSIZE);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getSteps() {
        return steps;
    }

    public void resetSteps() {
        this.steps = 0;
    }

    public void setNumberOfAnts(int numberOfAnts) {
        this.numberOfAnts = numberOfAnts;
    }

    public void setNumberOfFlowers(int numberOfFlowers) {
        this.numberOfFlowers = numberOfFlowers;
    }

    public void setNumberOfHornets(int numberOfHornets) {
        this.numberOfHornets = numberOfHornets;
    }
}

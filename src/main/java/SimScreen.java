import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class SimScreen extends JPanel {
    public static final int WIDTH = Simulation.WIDTH - Simulation.offsetOfWidth; // width of simWindow
    public static final int HEIGHT = WIDTH; // height of simWindow
    public static final int offsetOfHeight = Simulation.offsetOfWidth / 2;
    public static int BLOCKSIZE = 20; // size of cell that's visible on screen

    private boolean running = false;

    private int map[][];
    private JPanel jPanelMap[][];

    private Random random;


    private int steps = 0;

    private int numberOfAnts = 0;
    private int numberOfHornets = 0;
    private int numberOfFlowers = 0;


    private Nest nest;
    private ArrayList<Ant> ants;
    private ArrayList<Hornet> hornets;
    private ArrayList<Flower> flowers;
    private SoliderAnt soliderAnt; // temporary for tests


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
        int nestPosY = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
        int nestPosX = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
        nest = new Nest(nestPosX, nestPosY);
        map[nestPosY][nestPosX] = 2;

        // add flowers
        flowers = new ArrayList<>();
        for (int i = 0; i < numberOfFlowers; i++) {
            random = new Random();
            int flowerPosX = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
            int flowerPosY = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
            flowers.add(new Flower(flowerPosX, flowerPosY));
            map[flowerPosY][flowerPosX] = 4;
        }

        // add ants to map
        System.out.println(numberOfAnts);
        ants = new ArrayList<>();
        for (int i = 0; i < numberOfAnts; i++) {
            ants.add(new Ant(nestPosX + 1, nestPosY + 1));
            map[ants.get(i).getY()][ants.get(i).getX()] = 1;
        }

        //add solider ant to map temporary
        random = new Random();
        soliderAnt = new SoliderAnt(nestPosX + 1, nestPosY);
        map[soliderAnt.getY()][soliderAnt.getX()] = 5;
        // add hornets to map
        System.out.println(numberOfHornets);
        hornets = new ArrayList<>();
        for (int i = 0; i < numberOfHornets; i++) {
            random = new Random();
            int hornetPosY = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
            int hornetPosX = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
            hornets.add(new Hornet(hornetPosX, hornetPosY));
            map[hornetPosY][hornetPosX] = 3;
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
                    jPanelMap[i][j].setBounds(j * BLOCKSIZE + 1, i * BLOCKSIZE + 1 + offsetOfHeight, BLOCKSIZE - 1, BLOCKSIZE - 1);
                    this.add(jPanelMap[i][j]);
                }

                //draw nest
                if (map[i][j] == 2) {
                    jPanelMap[i][j].setVisible(true);
                    jPanelMap[i][j].setBackground(Color.GREEN);
                    jPanelMap[i][j].setBounds(j * BLOCKSIZE + 1, i * BLOCKSIZE + 1 + offsetOfHeight, BLOCKSIZE - 1, BLOCKSIZE - 1);
                    this.add(jPanelMap[i][j]);
                }

                //draw hornets
                if (map[i][j] == 3) {
                    jPanelMap[i][j].setVisible(true);
                    jPanelMap[i][j].setBackground(Color.RED);
                    jPanelMap[i][j].setBounds(j * BLOCKSIZE + 1, i * BLOCKSIZE + 1 + offsetOfHeight, BLOCKSIZE - 1, BLOCKSIZE - 1);
                    this.add(jPanelMap[i][j]);
                }

                //draw flowers
                if (map[i][j] == 4) {
                    jPanelMap[i][j].setVisible(true);
                    jPanelMap[i][j].setBackground(Color.YELLOW);
                    jPanelMap[i][j].setBounds(j * BLOCKSIZE + 1, i * BLOCKSIZE + 1 + offsetOfHeight, BLOCKSIZE - 1, BLOCKSIZE - 1);
                    this.add(jPanelMap[i][j]);
                }
                //draw solider ant
                if (map[i][j] == 5) {
                    jPanelMap[i][j].setVisible(true);
                    jPanelMap[i][j].setBackground(Color.DARK_GRAY);
                    jPanelMap[i][j].setBounds(j * BLOCKSIZE + 1, i * BLOCKSIZE + 1 + offsetOfHeight, BLOCKSIZE - 1, BLOCKSIZE - 1);
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
            //update ants
            for (int i = 0; i < numberOfAnts; i++) {
                map[ants.get(i).getY()][ants.get(i).getX()] = 0;
                ants.get(i).movement(map);
                map[ants.get(i).getY()][ants.get(i).getX()] = 1;
            }
            //update solider ants
            map[soliderAnt.getY()][soliderAnt.getX()] = 0;
            soliderAnt.movement(map);
            map[soliderAnt.getY()][soliderAnt.getX()] = 5;


            //update hornets
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
            g.drawLine(i * BLOCKSIZE, offsetOfHeight, i * BLOCKSIZE, HEIGHT + offsetOfHeight);
        //draw horizontal lines
        for (int i = 0; i <= HEIGHT / BLOCKSIZE; i++)
            g.drawLine(1, i * BLOCKSIZE + offsetOfHeight, WIDTH, i * BLOCKSIZE + offsetOfHeight);
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

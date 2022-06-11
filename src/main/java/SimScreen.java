import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

public class SimScreen extends JPanel {
    public static final int WIDTH = Simulation.WIDTH - Simulation.offsetOfWidth; // width of simWindow
    public static final int HEIGHT = WIDTH; // height of simWindow
    public static final int offsetOfHeight = Simulation.offsetOfWidth / 2;
    public static int BLOCKSIZE = 20; // size of cell that's visible on screen

    private boolean running = false;

    private ArrayList<Entity> map;

    private Random random;


    private int steps = 0;


    private int setNumberOfAnts = 0;
    private int setNumberOfHornets = 0;
    private int setNumberOfFlowers= 0;
    private int setNumberOfFood = 9;
    private int setNumberOfSoliderAnts = 10;

    private int numberOfAnts = setNumberOfAnts;
    private int numberOfHornets = setNumberOfHornets;
    private int numberOfFlowers = setNumberOfFlowers;
    private int numberOfFood = setNumberOfFood;
    private int numberOfSoliderAnts = setNumberOfSoliderAnts;

    private Nest nest;


    public SimScreen() {
        //init map and jPanelMap
        map = new ArrayList<>();
    }

    //used to init new map
    public void init() {
        //reset steps
        steps = 0;

        //reset entities
        numberOfHornets = setNumberOfHornets;
        numberOfFlowers =setNumberOfFlowers;
        numberOfFood = setNumberOfFood;
        numberOfAnts = setNumberOfAnts;
        numberOfSoliderAnts = setNumberOfSoliderAnts;


        //reset map
        for (Iterator<Entity> itr = map.iterator(); itr.hasNext(); )
            this.remove(itr.next().draw());
        map.clear();

        // add nest to the map
        random = new Random();
        int nestPosY = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
        int nestPosX = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
        nest = new Nest(nestPosX, nestPosY);
        map.add(nest);

//        // add flowers

        for (int i = 0; i < setNumberOfFlowers; i++) {
            random = new Random();
            int flowerPosX = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
            int flowerPosY = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
            map.add(new Flower(flowerPosX, flowerPosY));
        }

//        // add food
        for (int i = 0; i < setNumberOfFood; i++) {
            random = new Random();
            int foodPosX = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
            int foodPosY = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
            map.add(new Food(foodPosX, foodPosY));
        }

        // add ants to map
        for (int i = 0; i < setNumberOfAnts; i++) {
            map.add(new Ant(nestPosX + 1, nestPosY + 1));
        }
        for (int i = 0; i < setNumberOfSoliderAnts; i++) {
            map.add(new SoliderAnt(nestPosX + 1, nestPosY + 1));
        }
//        // add hornets to map
        for (int i = 0; i < setNumberOfHornets; i++) {
            random = new Random();
            int hornetPosY = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
            int hornetPosX = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
            map.add(new Hornet(hornetPosX, hornetPosY));
        }
    }

    //render the game
    private void render(Graphics g) {
        drawGrid(g);

        // draw cells
        for (int i = 0; i < map.size(); i++) {
            this.add(map.get(i).draw());
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    public void update() {
        if (running) {
            steps++;
            ArrayList<Entity> entitiesToDelete = new ArrayList<>();
            //updates all entities
            for (Iterator<Entity> itr = map.iterator(); itr.hasNext(); ) {
                Entity entity = itr.next().update(map);
                if (entity != null)
                    entitiesToDelete.add(entity);
            }

            // delete dead entities
            for (Entity entity : entitiesToDelete) {
                if(((Rectangle) entity).getName() == "Ant")
                    numberOfAnts--;
                else if(((Rectangle) entity).getName() == "SoliderAnt")
                    numberOfSoliderAnts--;
                else if(((Rectangle) entity).getName() == "Food")
                    numberOfFood--;
                else if(((Rectangle) entity).getName() == "Hornet")
                    numberOfHornets--;
                this.remove(entity.draw());
                map.remove(entity);

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

    public void setNumberOfAnts(int numberOfAnts) {
        this.setNumberOfAnts = numberOfAnts;
    }

    public void setNumberOfFlowers(int numberOfFlowers) {
        this.setNumberOfFlowers = numberOfFlowers;
    }

    public void setNumberOfHornets(int numberOfHornets) {
        this.setNumberOfHornets = numberOfHornets;
    }

    public int getNumberOfAnts() {
        return numberOfAnts;
    }

    public int getNumberOfHornets() {
        return numberOfHornets;
    }

    public int getNumberOfFlowers() {
        return numberOfFlowers;
    }

    public int getNumberOfSoliderAnts() {
        return numberOfSoliderAnts;
    }

    public int getNumberOfFood() {
        return numberOfFood;
    }
}


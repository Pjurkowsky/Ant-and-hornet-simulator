import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.Iterator;
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
    private int setNumberOfFlowers = 0;
    private int setNumberOfFood = 0;
    private int setNumberOfSoliderAnts = 0;

    private int numberOfAnts = setNumberOfAnts;
    private int numberOfHornets = setNumberOfHornets;
    private int numberOfFlowers = setNumberOfFlowers;
    private int numberOfFood = setNumberOfFood;
    private int numberOfSoliderAnts = setNumberOfSoliderAnts;

    private Nest nest;

    ArrayList<ArrayList<Integer>> listOfOutputData = new ArrayList<>();
    private int counter = 0;
    private int init_counter = 0;

    public SimScreen() {
        //init map and jPanelMap
        map = new ArrayList<>();
        for (int i = 0; i < Parameters.getSteps() / Parameters.getHowOftenLogging(); i++) {
            listOfOutputData.add(new ArrayList<>());
            listOfOutputData.get(i).add(0);
            listOfOutputData.get(i).add(0);
            listOfOutputData.get(i).add(0);
            listOfOutputData.get(i).add(0);
            listOfOutputData.get(i).add(0);
            listOfOutputData.get(i).add(0);
            listOfOutputData.get(i).add(0);
        }

    }

    //used to init new map
    public void init() {
        //reset steps
        steps = 0;

        //counter resets
        counter = 0;

        //init counter
        init_counter++;


        //reset entities
        numberOfHornets = setNumberOfHornets;
        numberOfFlowers = setNumberOfFlowers;
        numberOfFood = setNumberOfFood;
        numberOfAnts = setNumberOfAnts;
        numberOfSoliderAnts = setNumberOfSoliderAnts;





        //reset map
        for (Iterator<Entity> itr = map.iterator(); itr.hasNext(); )
            this.remove(itr.next().draw());
        map.clear();

        // add nest and ants to the map
        random = new Random();
        int nestPosY = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
        int nestPosX = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
        nest = new Nest(nestPosX, nestPosY, setNumberOfAnts, map); // nest adds himself to the map


        // add flowers
        for (int i = 0; i < setNumberOfFlowers; i++) {
            random = new Random();
            int flowerPosX = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
            int flowerPosY = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
            map.add(new Flower(flowerPosX, flowerPosY));
        }

        // add food
        for (int i = 0; i < setNumberOfFood; i++) {
            random = new Random();
            int foodPosX = random.nextInt(HEIGHT - BLOCKSIZE) / BLOCKSIZE;
            int foodPosY = random.nextInt(WIDTH - BLOCKSIZE) / BLOCKSIZE;
            map.add(new Food(foodPosX, foodPosY));
        }

        // add hornets to map
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
        if (!Simulation.testMode)
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
            ArrayList<Entity> entitiesToAdd = new ArrayList<>();
            //updates all entities
            for (int i = 0; i < map.size(); i++) {
                Object[] entityHandler = map.get(i).update(map);
                if (entityHandler[0] != null) {
                    if (!((boolean) entityHandler[1]))
                        entitiesToDelete.add((Entity) entityHandler[0]);
                    else if ((boolean) entityHandler[1])
                        entitiesToAdd.add((Entity) entityHandler[0]);
                }

            }

            // delete dead entities
            for (Entity entity : entitiesToDelete) {
                if (entity.getName() == "Ant")
                    numberOfAnts--;
                else if (entity.getName() == "SoliderAnt")
                    numberOfSoliderAnts--;
                else if (entity.getName() == "Food")
                    numberOfFood--;
                else if (entity.getName() == "Hornet")
                    numberOfHornets--;
                this.remove(entity.draw());
                map.remove(entity);
            }

            // add new entities
            for (Entity entity : entitiesToAdd) {
                if (entity.getName() == "Ant")
                    numberOfAnts++;
                else if (entity.getName() == "SoliderAnt")
                    numberOfSoliderAnts++;
                else if (entity.getName() == "Food")
                    numberOfFood++;
                else if (entity.getName() == "Hornet")
                    numberOfHornets++;
                if (!Simulation.testMode)
                this.add(entity.draw());
                map.add(entity);
            }

            if ((steps % Parameters.getHowOftenLogging()) == 0 && Simulation.testMode) {
                getOutputData();
                counter++;
            }


            this.repaint(); //redraw the screen
        }
        if (Simulation.currentNumberOfSimulation >= Simulation.numberOfSimulations)
            Parameters.createOutputFile("/" + Simulation.getFileNameOutput(), listOfOutputData, init_counter);
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

    public void setNumberOfSoliderAnts(int numberOfSoliderAnts) {
        this.numberOfSoliderAnts = numberOfSoliderAnts;
    }

    public void setNumberOfFood(int numberOfFood) {
        this.setNumberOfFood = numberOfFood;
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

    public int getNumberOfFoodInNest() {
        return nest.getFoodAmount();
    }

    public void getOutputData() {

        listOfOutputData.get(counter).set(0, listOfOutputData.get(counter).get(0) + getSteps());
        listOfOutputData.get(counter).set(1, listOfOutputData.get(counter).get(1) + numberOfAnts);
        listOfOutputData.get(counter).set(2, listOfOutputData.get(counter).get(2) + numberOfSoliderAnts);
        listOfOutputData.get(counter).set(3, listOfOutputData.get(counter).get(3) + numberOfHornets);
        listOfOutputData.get(counter).set(4, listOfOutputData.get(counter).get(4) + numberOfFlowers);
        listOfOutputData.get(counter).set(5, listOfOutputData.get(counter).get(5) + numberOfFood);
        listOfOutputData.get(counter).set(6, listOfOutputData.get(counter).get(6) + nest.getTotalFoodAmount());
    }

}


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Parameters {

    private static ArrayList<ArrayList<Double>> listOfListsOfParameters = new ArrayList<>();

    private static double steps = 0.;
    private static double numberOfAnts = 0.;
    private static double numberOfHornets = 0.;
    private static double numberOfFlowers = 0.;
    private static double numberOfFood = 0.;
    private static double howOftenLogging = 0.;
    private static double probOfHornetKillAnt = 0.;
    private static double probOfSAntKillHornet = 0.;
    private static double foodToBornNewAnt = 0.;
    private static double probBornSAnt = 0.;

    private static int numberOfParameters = 10;
    private static int numberOfListsOfParameters = 0;

    public static void loadDataFromFile(String fileName) {
        try {
            //reset if new file
            listOfListsOfParameters.clear();
            numberOfListsOfParameters = 0;

            fileName = "src/main/resources/" + fileName;
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int counter = 0;
            listOfListsOfParameters.add(new ArrayList<>());
            while (scanner.hasNext()) {
                if (counter >= numberOfParameters) {
                    counter = 0;
                    listOfListsOfParameters.add(new ArrayList<>());
                    numberOfListsOfParameters++;
                    scanner.nextLine();
                }
                listOfListsOfParameters.get(numberOfListsOfParameters).add(Double.parseDouble(scanner.next()));
                counter++;
            }
            scanner.close();

            steps = getParameters(0).get(0);
            numberOfAnts = getParameters(0).get(1);
            numberOfHornets = getParameters(0).get(2);
            numberOfFlowers = getParameters(0).get(3);
            numberOfFood = getParameters(0).get(4);
            howOftenLogging = getParameters(0).get(5);
            probOfHornetKillAnt = getParameters(0).get(6);
            probOfSAntKillHornet = getParameters(0).get(7);
            foodToBornNewAnt = getParameters(0).get(8);
            probBornSAnt = getParameters(0).get(9);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        System.out.println(listOfListsOfParameters);
    }

    public static void createOutputFile(String fileName, ArrayList<ArrayList<Integer>> outputData, int counter) {
        try {
            fileName = "src/main/resources/" + fileName;
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("step,ants,soliderAnts,hornets,flowers,foodOnMap,foodCollected");

            for (int i = 0; i < outputData.size(); i++) {
                for (int j = 0; j < outputData.get(i).size(); j++) {
                    pw.print(outputData.get(i).get(j)/(counter -1  ));
                    if (j < outputData.get(i).size() - 1)
                        pw.print(',');
                }
                pw.println();
            }

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Double> getParameters(int i) {
        return listOfListsOfParameters.get(i);
    }

    public static double getSteps() {
        return steps;
    }

    public static int getNumberOfAnts() {
        return (int) numberOfAnts;
    }

    public static int getNumberOfHornets() {
        return (int) numberOfHornets;
    }

    public static int getNumberOfFlowers() {
        return (int) numberOfFlowers;
    }

    public static int getNumberOfFood() {
        return (int) numberOfFood;
    }

    public static int getHowOftenLogging() {
        return (int) howOftenLogging;
    }

    public static double getProbOfHornetKillAnt() {
        return probOfHornetKillAnt;
    }

    public static double getProbOfSAntKillHornet() {
        return probOfSAntKillHornet;
    }

    public static int getFoodToBornNewAnt() {
        return (int) foodToBornNewAnt;
    }

    public static double getProbBornSAnt() {
        return probBornSAnt;
    }

    public static int getNumberOfParameters() {
        return numberOfParameters;
    }
}

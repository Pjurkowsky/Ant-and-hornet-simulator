import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Parameters {

    private static ArrayList<ArrayList<Integer>> listOfListsOfParameters = new ArrayList<>();

//    private int numberOfAnts = 0;
//    private int numberOfHornets = 0;
//    private int numberOfFlowers = 0;

    private static int numberOfParameters = 3;
    private static int numberOfListsOfParameters = 0;

    public static void loadDataFromFile(String fileName) {
        try {
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
                listOfListsOfParameters.get(numberOfListsOfParameters).add(Integer.parseInt(scanner.next()));
                counter++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println(listOfListsOfParameters);
    }

    public static ArrayList<Integer> getParameters(int i) {
        return listOfListsOfParameters.get(i);
    }

}

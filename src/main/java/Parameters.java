import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Parameters {

    private static ArrayList<ArrayList<Integer>> listOfListsOfParameters = new ArrayList<>();

//    private static int numberOfAnts = 10;
//    private static int numberOfHornets = 10;
//    private static int numberOfFlowers = 3;

    private static int numberOfParameters = 3;

    private static int numberOfListsOfParameters = 0;

    public static ArrayList<ArrayList<Integer>> loadDataFromFile(String fileName){
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int counter = 0;
            listOfListsOfParameters.add(new ArrayList<>());

            while (scanner.hasNext()) {
                if(counter >= numberOfParameters){
                    counter = 0;
                    listOfListsOfParameters.add(new ArrayList<>());
                    numberOfListsOfParameters++;
                    scanner.nextLine();
                }
                listOfListsOfParameters.get(numberOfListsOfParameters).add(Integer.parseInt(scanner.next()));
                counter++;
            }
            scanner.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
        }


        System.out.println(listOfListsOfParameters);
        return listOfListsOfParameters;
    }
}

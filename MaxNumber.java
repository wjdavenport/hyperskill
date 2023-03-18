
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

class MaxNumber {
    public final static double NEGATIVE_INFINITY = -1.0/0.0;
    public static void main(String[] args) throws FileNotFoundException {
        String pathToFile = "../dataset_91007.txt";
        File file = new File(pathToFile);
        double max = NEGATIVE_INFINITY;
        int counter = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextDouble()) {
                counter++;
                double newNum = scanner.nextDouble();
                max = (newNum > max) ? newNum : max;
                }
            }
        catch (FileNotFoundException e) {
            System.out.println("No file found: " + pathToFile);
        }
        System.out.print("Tested " + counter + " numbers; ");
        System.out.println("the largest number is: " + max);
    }
}

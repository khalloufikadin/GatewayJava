import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * Car Data Analysis: Project 3 Starter Code.
 *
 * Program that reads plain text files, outputs amount 
 * of times brand is repeated, two highest priced cars, 
 * average price within criteria, and best "value" car from an equation.
 * Kadin Khalloufi
 * kkhallo1 / 653EDA
 * 3/9/23
 * Gateway Programming: Java
 * Johns Hopkins University
 * Spring 2023
 */
 
public class CarDataAnalysis {

   // menu options
   static final int BRAND_QUERY = 1;
   static final int TWO_HIGHEST_PRICES_QUERY = 2;
   static final int RANGE_QUERY = 3;
   static final int BEST_VALUE_QUERY = 4;
   static final int QUIT = 5;

   // column index constants for car data file
   static final int BRAND = 2;
   static final int YEAR = 4;
   static final int MILEAGE = 6;
   static final int PRICE = 1;

   /**
    * Counts the number of lines in a given plain-text file.
    * @param filename The file whose lines are to be counted.
    * @return the number of lines in the file.
    * @throws IOException
    */
    
   public static int countFileLines(String filename)
                                    throws IOException {
      // Makes input stream based on filename given.
      FileInputStream fileByteStream = new FileInputStream(filename);
      
      // Initializes a scanner based on the input stream.
      Scanner inFS = new Scanner(fileByteStream);
      
      // Counts the amount of lines within the file. 
      int lineCount = 0;
      while (inFS.hasNextLine()) {
         String currentLine = inFS.nextLine();
         ++lineCount;
      }
      
      // Closes input stream and returns amount of lines. 
      fileByteStream.close();
      return lineCount;
   }

   /**
    * Print the program menu to the console.
    */
    
   public static void printMenu() {
   
      System.out.printf("[%d]: Average price of brand.\n", BRAND_QUERY);
      System.out.printf("[%d]: Two highest prices.\n",
             TWO_HIGHEST_PRICES_QUERY);
      System.out.printf("[%d]: Average price in year and mileage range.\n",
             RANGE_QUERY);
      System.out.printf("[%d]: Best value.\n", BEST_VALUE_QUERY);
      System.out.printf("[%d]: Quit.\n", QUIT);
      System.out.print("Please select an option: ");
   
   }
   
   /**
    * Makes string array with which column is inputted.
    * @param filename The file whose contents are going to be imported.
    * @param columnFinder The column which is going in the array.
    * @param rowCount The length of the array.
    * @param currentArray The array with all the contents.
    * @return String array that has the contents from columnFinder.
    * @throws IOException
    */

   public static String[] makeStringArray(String filename, int columnFinder, 
      int rowCount, String[] currentArray) throws IOException {
      
      // Initializes input stream and scanner. 
      FileInputStream fileByteStream = new FileInputStream(filename);
      Scanner inFS = new Scanner(fileByteStream);
      
      // Gets rid of the criteria line and makes empty string.
      String throwAway = inFS.nextLine();
      String currentString = "";
      
      // Makes empty array with amount of criteria in each line. 
      String[] grabberArray = new String[13];
      
      // Repeats for every line in the file but first.
      for (int i = 0; i < rowCount; i++) {
      
         // Line is made into string and put into array split by commas.
         currentString = inFS.nextLine();
         grabberArray = currentString.split(",");
         
         // Put into returning array with the index that is wanted. 
         currentArray[i] = grabberArray[columnFinder];
      }
      
      // Closes input stream and returns array with the criteria wanted. 
      fileByteStream.close();
      return currentArray;
   }
   
   /**
    * Makes integer array with which column is inputted.
    * @param filename The file whose contents are going to be imported.
    * @param columnFinder The column which is going in the array.
    * @param rowCount The length of the array.
    * @param currentArray The array with all the contents.
    * @return Integer array that has the contents from columnFinder.
    * @throws IOException
    */
    
   public static int[] makeIntArray(String filename, int columnFinder, 
      int rowCount, int[] currentArray) throws IOException {
      
      // Initializes input stream and scanner. 
      FileInputStream fileByteStream = new FileInputStream(filename);
      Scanner inFS = new Scanner(fileByteStream);
      
      // Gets rid of the criteria line and makes empty string.
      String throwAway = inFS.nextLine();
      String currentString = "";
      
      // Makes empty array with amount of criteria in each line. 
      String[] grabberArray = new String[13];
      
      // Repeats for every line in the file but first.
      for (int i = 0; i < rowCount; i++) {
      
         // Line is made into string and put into array split by commas.
         currentString = inFS.nextLine();
         grabberArray = currentString.split(",");
         
         // Put into returning array as an int with the index that is wanted. 
         currentArray[i] = Integer.parseInt(grabberArray[columnFinder]);
      }
      
      // Closes input stream and returns array with the criteria wanted. 
      fileByteStream.close();
      return currentArray;
   }
   
   /**
    * Makes double array with which column is inputted.
    * @param filename The file whose contents are going to be imported.
    * @param columnFinder The column which is going in the array.
    * @param rowCount The length of the array.
    * @param currentArray The array with all the contents.
    * @return String array that has the contents from columnFinder.
    * @throws IOException
    */
    
   public static double[] makeDoubleArray(String filename, int columnFinder, 
      int rowCount, double[] currentArray) throws IOException {
      
      // Initializes input stream and scanner. 
      FileInputStream fileByteStream = new FileInputStream(filename);
      Scanner inFS = new Scanner(fileByteStream);
      
      // Gets rid of the criteria line and makes empty string.
      String throwAway = inFS.nextLine();
      String currentString = "";
      
      // Makes empty array with amount of criteria in each line. 
      String[] grabberArray = new String[13];
      
      // Repeats for every line in the file but first.
      for (int i = 0; i < rowCount; i++) {
      
         // Line is made into string and put into array split by commas.
         currentString = inFS.nextLine();
         grabberArray = currentString.split(",");
         
         // Put into returning array as a double with the index that is wanted.
         currentArray[i] = Double.parseDouble(grabberArray[columnFinder]);
      }
      
      // Closes input stream and returns array with the criteria wanted. 
      fileByteStream.close();
      return currentArray;
   }

   /**
    * Drive the Car Data Analysis program.
    * @param args This program does not take commandline arguments.
    * @throws IOException
    */
    
   public static void main(String[] args) throws IOException {
   
      // output purpose
      System.out.println("Welcome to the car dataset analysis program.");
   
      // get input filename (e.g. "USA_cars_datasets.csv")
      System.out.print("Please enter input csv filename: ");
      Scanner keyboard = new Scanner(System.in);
      String filename = keyboard.nextLine();
   
      // count the number of rows in the file (ignore headers line)
      int rowCount = countFileLines(filename) - 1;
      System.out.println("File has " + rowCount + " entries.");
      System.out.println();
   
      // Declares parallel arrays for each column of interest
      String[] carBrand = new String[rowCount];
      int[] carYear = new int[rowCount];
      double[] carMileage = new double[rowCount];
      int[] carPrice = new int[rowCount];
   
      // Loads columns from file
      carBrand = makeStringArray(filename, BRAND, rowCount, carBrand);
      carYear = makeIntArray(filename, YEAR, rowCount, carYear);
      carPrice = makeIntArray(filename, PRICE, rowCount, carPrice);
      carMileage = makeDoubleArray(filename, MILEAGE, rowCount, carMileage);
   
      // while the user doesn't choose to quit...
      int option = 0;
      while (option != QUIT) {
      
         // display the menu and get an option
         printMenu();
         option = keyboard.nextInt();
      
         //Chosen option
         switch (option) {
            case BRAND_QUERY:
               getBrand(keyboard, carBrand, carYear, carPrice, carMileage);
               break;
               
            case TWO_HIGHEST_PRICES_QUERY:
               twoHighestPrices(carPrice);
               break;
               
            case RANGE_QUERY:
               rangeQuery(keyboard, carYear, carPrice, carMileage);
               break;
               
            case BEST_VALUE_QUERY:
               bestValueQuery(keyboard, carYear, carPrice, carMileage, 
                  carBrand);
               break;
               
            case QUIT:
               System.out.println("Thank you for using the program!");
               break;
               
            default:
               System.out.println("Invalid option.");
         }
         // leave empty line for next printing of menu
         System.out.println();
      }
   }

   /**
    * Prompts for car brand and output file, scans array for brand, 
    * outputs index, brand, year, mileage, and price to outfile. 
    * Prints average and amount of cars from brand.
    * @param keyboard Gets input from user. 
    * @param carBrand The array that holds the car brands.
    * @param carYear The array that holds the car years. 
    * @param carPrice The array that holds the car prices.
    * @param carMileage The array that holds the car mileages.
    * @throws IOException
    */                
   public static void getBrand(Scanner keyboard, String[] carBrand, 
      int[] carYear, int[] carPrice, double[] carMileage) throws IOException {
   
      // Input car brand and file name to be outputted. 
      System.out.print("Please enter a car brand: ");
      String carChoice = keyboard.next();
      System.out.print("Please enter an output filename: ");
      String outputFileName = keyboard.next();
      
      // Initializes input stream and scanner. 
      FileOutputStream fileStream = new FileOutputStream(outputFileName);
      PrintWriter outFS = new PrintWriter(fileStream);
      
      // Initializes array that will hold the location that fits criteria.
      int[] locationArray = new int[carBrand.length];
      
      // Initializes occurance count.
      int occurance = 0;
      
      // Goes through the brand array and checks if match with userinput.
      for (int i = 0; i < carBrand.length; i++) {
         // If brand match then will store index and update occurance.
         if (carBrand[i].compareToIgnoreCase(carChoice) == 0) {
            locationArray[occurance] = i;
            occurance += 1;
         }
      }
      
      // Outprints the index, brand, year, mileage, and price in the out file.
      for (int i = 0; i < occurance; i++) {
         outFS.println(locationArray[i] + ", " + carChoice.toLowerCase() + ", " 
            + carYear[locationArray[i]] + ", " + carMileage[locationArray[i]] 
            + ", " + carPrice[locationArray[i]]);
      }
      // Closes outprint stream. 
      outFS.close();
      
      // Finds the total price of all cars.
      double total = 0;
      for (int i = 0; i < occurance; i++) {
         total += carPrice[locationArray[i]];
      }
      
      // Finds the average car price.
      double averageOfCars = total / occurance;
   
      // Prints if there are no occurances/ 
      if (occurance == 0) {
         System.out.println("There are no matching entries for brand " 
            + carChoice + ".");
      }
      
      // Prints the average and amount of occurance for car brand. 
      else {
         System.out.println("There are " + occurance + 
            " matching entries for brand " + carChoice + " with an average ");
         System.out.printf("price of $%.2f.\n", averageOfCars);
      }
   }  
   
   /**
    * Prints cars within two highest prices in the index.
    * @param carPrice The array that holds the car prices.
    */     
   public static void twoHighestPrices(int[] carPrice) {
      
      // Initializes the first and second highest car prices.
      double highestNum = 0;
      double secondHighestNum = 0;
      
      // Goes through every car price in the array.
      for (int i = 0; i < carPrice.length; ++i) {
         
         // If the car price in the index is higher then will update.
         if (carPrice[i] > highestNum) {
         
            // Updates the second highest with past highest. 
            secondHighestNum = highestNum;
            highestNum = carPrice[i];
         }
         
         // If the current price is bigger than the second highest will update.
         if ((carPrice[i] < highestNum) && carPrice[i] > secondHighestNum) {
            secondHighestNum = carPrice[i];
         }
      }     
      
      // Prints the two highest price cars. 
      System.out.printf("The two highest prices are $%.2f and $%.2f.\n", 
         highestNum, secondHighestNum);
   }
   
   /**
    * Prints cars within range and gives average price.
    * @param keyboard Gets input from user.
    * @param carYear The array that holds the car years. 
    * @param carPrice The array that holds the car prices.
    * @param carMileage The array that holds the car mileages.
    */   
   public static void rangeQuery(Scanner keyboard, int[] carYear, 
      int[] carPrice, double[] carMileage) {
      
      // Gets bounds from user (inclusive).
      System.out.print("Please enter the year lower bound: ");
      int lowerYearBound = keyboard.nextInt();
      System.out.print("Please enter the year upper bound: ");
      int upperYearBound = keyboard.nextInt();
      System.out.print("Please enter the mileage lower bound: ");
      int lowerMileBound = keyboard.nextInt();
      System.out.print("Please enter the mileage upper bound: ");
      int upperMileBound = keyboard.nextInt();
      
      // Initializes the total and amount of cars matched.
      int criteriaMatch = 0;
      double totalCost = 0.0;
      
      // Checks through entire array.
      for (int i = 0; i < carYear.length; i++) {
         
         // If within the bounds.
         if (((carYear[i] >=  lowerYearBound) && (carYear[i] <=  
            upperYearBound)) && ((carMileage[i] >=  lowerMileBound) && 
            (carMileage[i] <=  upperMileBound))) {
            
            // Updates total and amount of cars matched.
            criteriaMatch += 1;
            totalCost += carPrice[i];
         }
      }
      
      // Gets average cost of cars. 
      double averageCarCost = totalCost / criteriaMatch;
   
      // Prints the bounds, criteria met, and the average price of car. 
      System.out.println("There are " + criteriaMatch + 
         " matching entries for year range [" + lowerYearBound 
         + ", " + upperYearBound + "] and ");
      System.out.printf("mileage range [" + lowerMileBound + ", " 
         + upperMileBound + "] with an average price of $%.2f.\n", 
         averageCarCost);
   }
   
   /**
    * Prints the best value car in the index of the file.
    * @param keyboard Gets input from user.
    * @param carYear The array that holds the car years. 
    * @param carPrice The array that holds the car prices.
    * @param carMileage The array that holds the car mileages.
    * @param carBrand The array that holds the car brands.
    */
   public static void bestValueQuery(Scanner keyboard, int[] carYear, 
      int[] carPrice, double[] carMileage, String[] carBrand) {
      
      // Gets bounds (exclusive). 
      System.out.print("Please enter lower mileage threshold: ");
      double lowerMileageThreshold = keyboard.nextDouble();
      System.out.print("Please enter lower price threshold: ");
      int lowerPriceThreshold = keyboard.nextInt();
      
      // Initializes the index, best price, and compared price. 
      int bestCarIndex = 0;
      double bestPrice = 0.0;
      double comparePrice = 0.0;
      
      // Goes throgh the entire array. 
      for (int i = 0; i < carPrice.length; ++i) {
      
         // Checks to see if the price and miles meet criteria. 
         if ((carPrice[i] > lowerPriceThreshold) && 
            (carMileage[i] > lowerMileageThreshold)) {
               
               // Computes the "value" of the car. 
            comparePrice = carYear[i] - (carMileage[i] / 13500.0) 
                  - (carPrice[i] / 1900.0);
         }
         
         // If the price is better than the previous best value, will update. 
         if (comparePrice > bestPrice) {
            bestPrice = comparePrice;
            bestCarIndex = i;
         }
      }
      
      // Prints bounds, year, price, brand, mileage of the best value car.
      System.out.println("The best-value entry with more than " + 
         lowerMileageThreshold + " miles and a price\n" + "higher than $" 
         + lowerPriceThreshold + " is a " + carYear[bestCarIndex] + " " 
         + carBrand[bestCarIndex] + " with " + carMileage[bestCarIndex] 
         + " miles for a price \nof $" + carPrice[bestCarIndex] + ".");
   }
}
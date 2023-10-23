import java.util.Scanner;

/** Starter file for Project A, EN.500.112 Gateway Computing: Java.
* Makes Mondrian Art (!) based on height and width of 4 inputs
* Spring 2023
* @author Joanne Selinski
* Kadin Khalloufi 653EDA 
*/
public class Project1 {

   /** Main method.
   * @param args not used
   */
   public static void main(String[] args) {
   
      Scanner scnr = new Scanner(System.in);  // this allows us to collect input
   
      // variable declarations
      double blueBlockWidth = 0;
      double blueBlockHeight = 0;
      double topLeftBlockHeight = 0;
      double bottomRightBlockWidth = 0;
      
      // Prompt for and collect parameters from the user
      System.out.print("Enter percent for blue block width: ");
      blueBlockWidth = scnr.nextDouble();
      
      System.out.print("Enter percent for blue block height: ");
      blueBlockHeight = scnr.nextDouble();
       
      System.out.print("Enter percent for top left block height: ");
      topLeftBlockHeight = scnr.nextDouble();
      
      System.out.print("Enter percent for bottom right block width: ");
      bottomRightBlockWidth = scnr.nextDouble();
      
      // Make the output based on the inputs
      // Makes the blocks a percentage
      blueBlockWidth = blueBlockWidth / 100;
      blueBlockHeight = blueBlockHeight / 100;
      topLeftBlockHeight = topLeftBlockHeight / 100;
      bottomRightBlockWidth = bottomRightBlockWidth / 100;
      
      // Draws the left bottom blue rectangle
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.filledRectangle((blueBlockWidth / 2), (blueBlockHeight / 2),
         (blueBlockWidth / 2), (blueBlockHeight / 2));
   
      // Draws the right bottom yellow rectangle
      StdDraw.setPenColor(StdDraw.YELLOW);
      StdDraw.filledRectangle((1 - (bottomRightBlockWidth / 2)), 
         (blueBlockHeight / 4), (bottomRightBlockWidth / 2),
         (blueBlockHeight / 4));
      
      // Set up black pen and radius (.02 )for lines
      StdDraw.setPenColor();
      StdDraw.setPenRadius(0.02);
      
      // Draws the horizontal line above yellow block
      StdDraw.line((1 - bottomRightBlockWidth), (blueBlockHeight / 2), 1,
         (blueBlockHeight / 2));
      
      // Draws the vertical line left of yellow block
      StdDraw.line((1 - bottomRightBlockWidth), 0, (1 - bottomRightBlockWidth),
         blueBlockHeight);
      
      // Sets red color and draws top right red rectangle
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.filledRectangle((((1 - blueBlockWidth) / 2) + blueBlockWidth),
         (((1 - blueBlockHeight) / 2) + blueBlockHeight),
         ((1 - blueBlockWidth) / 2), ((1 - blueBlockHeight) / 2));
   
      // Sets white color and draws white circle 
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledCircle(blueBlockWidth, (1 - topLeftBlockHeight),
         (topLeftBlockHeight / 3));
      
      //Sets blue color, pen radius (.01), and draws top left arc of circle
      StdDraw.setPenRadius(0.01);
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.arc(blueBlockWidth, (1 - topLeftBlockHeight), 
         (topLeftBlockHeight / 3), 90, 180);
         
      // Sets yellow color and draws bottom left arc of circle
      StdDraw.setPenColor(StdDraw.YELLOW);
      StdDraw.arc(blueBlockWidth, (1 - topLeftBlockHeight), 
         (topLeftBlockHeight / 3), 180, 270);
         
      // Sets black color and draws right arc of circle
      StdDraw.setPenColor();
      StdDraw.arc(blueBlockWidth, (1 - topLeftBlockHeight), 
         (topLeftBlockHeight / 3), -90, 90);
      
      // Draws left vertical black line
      StdDraw.line(blueBlockWidth, 0, blueBlockWidth, 1);
      
      // Draws horizontal black line by red and blue block
      StdDraw.line(0, blueBlockHeight, 1, blueBlockHeight);
      
      // Draws horizontal black line intersecting circle
      StdDraw.filledRectangle((blueBlockWidth / 2), (1 - topLeftBlockHeight),
         (blueBlockWidth / 2), .015);
         
   }
}

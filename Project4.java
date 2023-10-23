import java.io.PrintWriter;
import java.io.IOException;
import java.awt.Color;
import java.util.Scanner; 
import java.io.FileInputStream; 

/** 
 * Makes rectangle shapes in STDDraw window in checkerboard, snake, and
 * diagonal pattern.
 * Kadin Khalloufi
 * 653EDA/kkhallo1
 * 3/30/23
 */
public class Project4 {

   /** 
    * Makes the menu and goes into methods.
    * @param args not used
    * @throws IOException
    */ 
   public static void main(String[] args) throws IOException {
   
      // Initializes Scanner
      Scanner scnr = new Scanner(System.in);
      
      // Asks and saves number of rows and columns
      System.out.print("Enter Checkerboard size: ");
      int checkerColumnAndRow = scnr.nextInt();
      
      // Asks for color values
      System.out.print("Enter RGB values, each [0,255]: ");
      
      // Stores numbers in a int array
      int[] colorValues = new int[3];
      for (int i = 0; i < 3; i++) {
         colorValues[i] = scnr.nextInt();
      }
      
      // Makes the checkerboard with column/row number and color
      checkerboard(checkerColumnAndRow, colorValues);
      
      // Gets the name of the file
      System.out.print("Enter Snake input filename: ");
      String filename = scnr.next();
      
      // Makes an array with column and row numbers in file
      int[] columnAndRow  = new int[2];
      columnAndRow = getColumnAndRow(filename);
      
      // Makes an Rectangle array with values in the file
      Rectangle[][] snakeArray = 
         new Rectangle[columnAndRow[0]][columnAndRow[1]];
      snakeArray = getArray(filename);
      
      // Prints the array of squares
      printSnake(snakeArray, columnAndRow);
      
      // Gets the name of the file
      System.out.print("Enter Diagonals input filename: ");
      filename = scnr.next();
      
      // Makes an array with column and row numbers in file
      columnAndRow = getColumnAndRow(filename);
      
      // If the number of columns and rows are the same number
      if (columnAndRow[0] == columnAndRow[1]) {
         
         // Makes an Rectangle array with values in the file
         Rectangle[][] diagonalsArray = 
            new Rectangle[columnAndRow[0]][columnAndRow[1]];
         diagonalsArray = getArray(filename);
         
         // Prints the array of squares
         printDiagonals(diagonalsArray, columnAndRow);
      }
      
      // Doesn't meet the criteria
      else {
         System.out.print("Specified pattern is not square so cannot be " + 
            "displayed by Diagonals operation.");
      }
   }
   
   /**
    * Makes the checkerboard from the inputted grid from left to right 
    * column to column down. Outprints row/column number, and square info to
    * new file with checkerboard# as name.
    * @param checkerColumnAndRow The amount of rows and columns.
    * @param colorValues Stores the three numbers for the RGB value.
    * @throws IOException
    */ 
   public static void checkerboard(int checkerColumnAndRow, int[] colorValues) 
      throws IOException {
 
      // Initalizes the output file name and printwriter 
      PrintWriter outfile = new PrintWriter("checkerboard" 
         + checkerColumnAndRow + ".txt");
      outfile.println(checkerColumnAndRow + " " + checkerColumnAndRow);
      
      // Initial values that will help us organize the Rectangles
      // on the drawing canvas
      double row = 1.0 / checkerColumnAndRow;  //row of grid cell
      double height = 1.0 / checkerColumnAndRow; //height of grid cell
      double xCenter = row / 2.0;   //x center offset
      double yCenter = height / 2.0;  //y center offset
      boolean filled = true;
      
      // Create a 2D array (grid) that can hold Rectangles
      Rectangle[][] grid = 
         new Rectangle[checkerColumnAndRow][checkerColumnAndRow];
      
      // Makes the window gray
      setGrayboard();
      
      // Iterate over the rows and columns of the 2D array, and
      // populate it with randomly-generated Rectangles      
      for (int r = 0; r < checkerColumnAndRow; r++) {
         for (int c = 0; c < checkerColumnAndRow; c++) {
         
            // Create the rectangle using the values above, and store
            // it in the 2D array at the current location
            // This will fill (odd rows and even columns) or vice versa
            // with the color desired.  
            if ((r % 2 == 0) && (c % 2 == 1) || (r % 2 == 1) && (c % 2 == 0)) {
               grid[r][c] = new Rectangle(new Color(colorValues[0], 
                  colorValues[1], colorValues[2]), row, height, filled,
                  (c * row) + xCenter, 1 - ((r * height) + yCenter));
            }
            
            // Fills the remaining squares with the white color
            else {
               grid[r][c] = new Rectangle(new Color(255, 255, 255), 
                  row, height, filled, (c * row) + xCenter,
                  1 - ((r * height) + yCenter));
            }
            
            // Draw the current rectangle on the screen with pause and
            // outprints the information 
            grid[r][c].draw();
            StdDraw.pause(200);
            outfile.println(grid[r][c]);
         }
      }
      
      // Flushes and closes the printwriter
      outfile.flush();
      outfile.close();
   }
   
   /**
    * Grabs the first two numbers in the file, which are rows and columns.
    * @param filename The name of the file which numbers are looking for.
    * @return Integer array with the two numbers in it.
    * @throws IOException
    */
   public static int[] getColumnAndRow(String filename) throws IOException {
   
      // Initializes file input and scanner
      FileInputStream fileByteStream = new FileInputStream(filename);
      Scanner inFS = new Scanner(fileByteStream);
      
      // Makes array to store values
      int[] columnAndRow = new int[2];
      
      // Get column and row numbers
      columnAndRow[0] = inFS.nextInt();
      columnAndRow[1] = inFS.nextInt(); 
     
      //return values
      return columnAndRow;
   }
   
   /**
    * Makes the rectangle array which contains all information for each square.
    * @param filename The name of the file which information is searched.
    * @return Rectangle array with all square information. 
    * @throws IOException
    */
   public static Rectangle[][] getArray(String filename) throws IOException {
      
      // Makes the window gray
      setGrayboard();
      
      // Makes input stream and scanner
      FileInputStream fileByteStream = new FileInputStream(filename);
      Scanner inFS = new Scanner(fileByteStream);
      
      // Grabs the number or columns and rows
      int column = inFS.nextInt();
      int row = inFS.nextInt(); 
      
      // Initalizes the array with the square info
      Rectangle[][] grid = new Rectangle[column][row];
      
      // Skips the line
      String throwawayLine = inFS.nextLine();
      
      // Stores the information into a rectangle array
      for (int r = 0; r < column; r++) {
         for (int c = 0; c < row; c++) {
            grid[r][c] = new Rectangle(inFS.nextLine());
         }
      }
      
      // Closes the input stream            
      fileByteStream.close();
      
      // returns the array filled with info
      return grid;
   }
   
   /**
    * Prints the rectangle array in a snake format.
    * @param snakeArray Square information stored in an array.
    * @param columnAndRow Number of rows and columns.
    */
   public static void printSnake(Rectangle[][] snakeArray, int[] columnAndRow) {

      // If the row is even then it goes up to down
      for (int row = 0; row < columnAndRow[1]; row++) {
         
         // Checks even
         if (row % 2 == 0) {
         
            // Draws squares up to down with pause
            for (int column = 0; column < columnAndRow[0]; column++) {
               snakeArray[column][row].draw();
               StdDraw.pause(200);
            }  
         }
         
         // If the row is odd
         else if (row % 2 == 1) {
            
            // Draws squares down to up with pause
            for (int column = (columnAndRow[0] - 1); column >= 0; column--) { 
               snakeArray[column][row].draw();
               StdDraw.pause(200);
            }
         }
      }
   }
   
   /**
    * Prints the rectangle array in a diagonal format.
    * @param diagonalsArray Square information stored in an array.
    * @param columnAndRow Number of rows and columns.
    */
   public static void printDiagonals(Rectangle[][] diagonalsArray, 
      int[] columnAndRow) {
   
      // Makes an updater value
      int update = 0;
      
      // Repeats row/column value
      for (int i = 0; i < columnAndRow[0]; i++) {
         // Repeats row/column value
         for (int j = 0; j < columnAndRow[0]; j++) { 
            
            // If it is the first diagonal
            if (i == 0) {
               // Draw the shape
               diagonalsArray[j][j].draw();
               StdDraw.pause(200);
            }
         }
         
         // Every other Diagonal line   
         if (i != 0) {
            
            // Decreases the amount of times repeated until zero
            for (int w = 0; w < columnAndRow[0] - update; w++) {
               
               // Draws the top diagonal line
               if (w + update == w + i) {
                  diagonalsArray[w][i + w].draw();
                  StdDraw.pause(200);
               }
            }
            
            // Decreases the amount of times repeated until zero
            for (int k = 0; k < columnAndRow[0] - update; k++) {
            
               // Draws the bottom diagonal line
               if ((k + update == k + i)) {
                  diagonalsArray[i + k][k].draw();
                  StdDraw.pause(200);
               }
            }
         }
         
         // Increments the update value
         update += 1;   
      }
   }
   
   /**
    * Makes the STDdraw window gray.
    */
   public static void setGrayboard() {
   
      // creates filled value and draws the gray rectangle
      boolean filled = true;
      Rectangle[][] newB = new Rectangle[1][1];
      newB[0][0] = new Rectangle(Color.LIGHT_GRAY, 1.0, 1.0, filled, .5, .5);
      newB[0][0].draw();
   }
  
}

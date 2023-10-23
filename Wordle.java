import java.util.Scanner;
import java.util.Random;

/**
 * Project 2: Wordle
 *
 * This project has you create a text based version of Wordle
 * (https://www.nytimes.com/games/wordle/index.html). Wordle is a word guessing
 * game in which you have 6 tries to guess a 5-letter word. You are told whether
 * each letter of your guess is in the word and in the right position, in the
 * word but in the wrong position, or not in the word at all.
 *
 * Some key differences in our version are:
 *
 * - Text menu based with no grid. Players have to scroll up to see their
 * previous guesses.
 *
 * - Support for 4, 5, or 6 letter words
 *
 * - We don't check for whether a guess is a valid word or not. Players can
 * guess anything they want (of the correct length).
 *
 * Fun facts: The original Wordle was developed by Josh Wardle. Wardle's wife
 * chose the official word list for the game.
 *
 * 500.112 Gateway Computing: Java Spring 2023
 * 
 * Wordle game that lets any combination of letters pass, 4-6 letter words,
 * and gives 2 hints for the characters.
 * Kadin Khalloufi 
 * 653EDA / kkhallo1
 * 2/23/23
 *
 */
public class Wordle {
   /**
    * The number of guesses the player starts with.
    */
   static final int MAX_GUESSES = 6;
   
   /**
    * The maximum number of hints available to the player.
    */
   static final int MAX_HINTS = 2;

   /**
    * The entry point for the program.
    *
    * @param args commandline args
    */
   public static void main(String[] args) {
      printIntro();
      
      //Initialize the game state.
      Random random = new Random();
      String word = newWord(random);
      String[] guesses = new String[MAX_GUESSES];
      for (int i = 0; i < guesses.length; i++) {
         guesses[i] = "";
      }
      char lastHint = '\0';
      int numGuesses = 0;
      int numHints = 0;
      Scanner scanner = new Scanner(System.in);
      boolean gameOver = false;
   
      //Run the game loop.
      while (!gameOver) {
         String command = printMenu(guesses, word, scanner);
         if ("h".equalsIgnoreCase(command)) {
            //Check whether the player has any hints left.
            if (numHints < MAX_HINTS) {
               lastHint = giveHint(word, lastHint, random);
               numHints++;
               printNumHintsRemaining(numHints);
            } else {
               System.out.println("Sorry, you're out of hints!");
            }
         } else if ("g".equalsIgnoreCase(command)) {
            printNumGuessesRemaining(numGuesses);
            String guess = getGuess(scanner);
            //Ensure the player's guess was valid and should count 
            //against the number of guesses taken.
            if (validateGuess(guess, word.length())) {
               String result = processGuess(word, guess);
               guesses[numGuesses++] = result;
               gameOver = checkGameOver(guess, word, numGuesses);
            }
         } else if ("e".equalsIgnoreCase(command)) {
            gameOver = true;
         } else {
            System.out.println("Invalid option! Try again!");
         }
      }
   }
   
   /**
    * Prints the introduction.
    */
   public static void printIntro() {
      System.out.println("Welcome to Wordle! Menu options:");
      System.out.println("g/G: Make a guess");
      System.out.println("h/H: Get a hint");
      System.out.println("e/E: Exit");
   }
      
   /**
    * Prints the current game state and menu options, and prompts the
    * player for their choice.
    * 
    * @param guesses An array of the player's guesses.
    * @param word The correct word.
    * @param scanner a Scanner used to prompt the player for a menu choice.
    * @return The player's menu choice.
    */
    
   public static String printMenu(String[] guesses, String word,
                                  Scanner scanner) {
      //Iterate through all of the user's guess "slots" and print them. 
      for (int i = 0; i < guesses.length; i++) {
         printGuess(guesses[i], word.length());
      }
      System.out.print("Enter a choice (G/H/E): ");
      return scanner.nextLine();
   }
   
   /**
    * Prints:
    * 
    * "Enter a guess: " 
    * 
    * (NOT followed by a newline) and reads the guess from a scanner.
    *
    * @param scanner The Scanner used to read the player's guess.
    * @return The player's guess.
    * 
    * [2 points]
    */
    
   public static String getGuess(Scanner scanner) {
      System.out.print("Enter a guess: ");
      // Gets the input and returns as variable guess.
      String guessWord = scanner.nextLine();  
      return guessWord;
   }
   
   /**
    * Chooses a random word of length 4, 5, or 6 using 
    * WordProvider.getWord() and the provided random number generator.
    *
    * @param random A random number generator.
    * @return The randomly chosen word.
    *
    * [3 points]
    */
    
   public static String newWord(Random random) {
      // Gives random number between 0 and 2.
      int wordLength = random.nextInt(3);
      String realWord = ""; 
      // If 0 was chosen then word was 4 letters.
      if (wordLength == 0) {
         realWord += WordProvider.getWord(4);
      }
      // If 1 was chosen then word was 5 letters.
      else if (wordLength == 1) {
         realWord += WordProvider.getWord(5);
      }
      // If 2 was chosen then word was 6 letters.
      else {
         realWord += WordProvider.getWord(6);
      }
      // Returns the word to guess.
      return realWord;
   }

   /**
   * Prints a player's guess, or, if the guess is the empty string, a series of
   * underscores (one per character in the correct word). All printed
   * characters are separated by a space. The very last thing printed should
   * be a newline.
   *
   * @param guess A player's guess or the empty string.
   * @param wordLength the number of character in the correct word.
   * 
   * [6 points]
   */
   
   public static void printGuess(String guess, int wordLength) {
      // Determines the word is not empty string.
      if (!("".equals(guess))) {
         /* Prints out every character of the guess word 
            with space after except last char. */
         for (int i = 0; i < (wordLength - 1); i++) {   
            System.out.print(guess.charAt(i) + " ");
         }
         // Prints last char (with no space after) and a new line.
         System.out.println(guess.charAt(wordLength - 1));
      }
      // Determines guess is empty.
      else {
         int k = 0;
         String underlineOnly = "";
         // Stores underscore and space for every char of the actual word.
         while (k < wordLength) {
            underlineOnly += "_ ";
            k += 1;
         }
         // Prints the String but takes out last space and makes new line.
         System.out.println(underlineOnly.substring(0, 
            underlineOnly.length() - 1));
      }    
   }
   
   /**
    * Prints the number of guesses remaining using correct plurality. 
    * For example, if the player has 1 guesses remaining, this function 
    * prints:
    * 
    * "You have 1 guess remaining." 
    * 
    * If the player has 0 guesses remaining, this function prints:
    * 
    * "You have 0 guesses remaining."
    *
    * @param numGuesses the number of guesses that the player has made
    * so far.
    *
    * [4 points]
    */
    
   public static void printNumGuessesRemaining(int numGuesses) {
      int difference = 6;
      /* If the number of guesses are less than 5, then it prints out 
         the absolute value of a 6 minus the number of guess used.
         This is with pluaral guesses remaining. */
      if (numGuesses < 5) {
         numGuesses -= difference;
         System.out.println("You have " + Math.abs(numGuesses) + 
            " guesses remaining.");
      }
      /* If on last guess number (6), then will print singular,
         hint (last hint before game is over) */
      else {
         System.out.println("You have 1 guess remaining.");
      }
   }
   
   /**
    * Prints the number of hints remaining using correct plurality. 
    * For example, if the player has 1 hint remaining, this function 
    * prints:
    * 
    * "You have 1 hint remaining." 
    * 
    * If the player has 0 hints remaining, this function prints:
    * 
    * "You have 0 hints remaining."
    *
    * @param numHints the number of hints that the player has received 
    * so far.
    *
    * [4 points]
    */
    
   public static void printNumHintsRemaining(int numHints) {
      // No prompt for 2 hints becuase only runs when hint is chosen.
      // Gives 1 hint output.
      if (numHints == 1) {
         System.out.println("You have 1 hint remaining.");
      }
      // Gives output for no hints left.
      else {
         System.out.println("You have 0 hints remaining.");
      }
   }
   
  /**
    * Prints 
    * 
    * "Hint: the word contains the letter: _." 
    * 
    * followed by a newline, where _ is a randomly chosen letter in the word 
    * parameter. IMPORTANT: the hint that you provide must not be the same
    * as the previous hint given (if there is one).
    *
    * @param word The word to give a hint for.
    * @param lastHint The previous hint given to the player or '\0' (the null 
    *   character literal) if no hint was given yet.
    * @param random A random number generator.
    * @return The hint character.
    *
    * [5 points]
    */
    
   public static char giveHint(String word, char lastHint, Random random) {
      // Stores a random number between 0 and length of word - 1.
      int indexChar = random.nextInt(word.length());
      //Gets the char at that random number.
      char hintLetter = word.charAt(indexChar);
      /* For 2nd hint, while loop that will run until hint letter
         is not the same as first. */
      while (hintLetter == lastHint) {
         indexChar = random.nextInt(word.length());
         hintLetter = word.charAt(indexChar);
      }
      // Prints the hint letter and returns it as a char.
      System.out.println("Hint: the word contains the letter: " + 
         hintLetter +  ".");
      return hintLetter;
   }

   /**
    * Checks the player's guess for validity. We define a valid guess as one 
    * that is the correct length and contains only lower case letters and 
    * upper case  letters. If the guess length is incorrect, this function
    * prints:
    * 
    * "You must enter a guess of length _." 
    * 
    * Where _ is the correct length. If the guess contains anything other 
    * than a lower or upper case  letter, this function prints:
    *
    * "Your guess must only contain upper case letters and lower case letters."
    *  
    * This function prints BOTH messages when appropriate. Each sentence 
    * printed by this funtion is terminated by a newline.
    *
    * @param guess  The guess that the player has entered.
    * @param correctLength The length of the correct word.
    * @return true if the guess is of the correct length and contains only valid
    * characters, otherwise false.
    *
    * [8 points]
    */
    
   public static boolean validateGuess(String guess, int correctLength) {
      //Establish 3 conditions for length, letters, and if both are correct.
      boolean guessLength = true;
      boolean guessCharacters = true;
      boolean validGuess = true;
      /* Checks to see if the length is correct, if not 
         will mark length as false. */
      if (guess.length() != correctLength) {
         System.out.println("You must enter a guess of length " + 
            correctLength + ".");
         guessLength = false;
      }
      // Checks every char in the String and determines if letter.
      int indexChar = 0;
      for (indexChar = 0; indexChar < guess.length(); indexChar++) {
         if (!(Character.isLetter(guess.charAt(indexChar)))) {
            // Marks false if nonletter char is used.
            guessCharacters = false;
         }
      }
      // Prints failed letter requirement.
      if (!guessCharacters) {
         System.out.println("Your guess must only contain upper case letters"
            + " and lower case letters.");
      }
      // Checks to see if either criteria wasn't met.
      // If not met will fail the valid guess check.
      if ((!guessLength) || (!guessCharacters)) {
         validGuess = false;
      }
      // Returns either met or unmet criteria as boolean.
      return validGuess;
   }

   /**
    * Checks the player's guess against the current word (capitalization is
    * IGNORED for this comparison). Then returns a string corresponding to 
    * the player's guess. A ? indicates a letter that isn't in the word at
    * all. A lower case letter indicates that the letter is in the word but
    * not in the correct position. An upper case letter indicates a correct
    * letter in the correct position. Example:
    *
    * SPLINE (the correct word)
    *
    * SPEARS (the player's guess)
    *
    * SPe??s (the resulting String returned by this function)
    *
    * @param word The correct word that the player is trying to guess.
    * @param guess The guess that a player has entered.
    * @return The formatted guess result (see above).
    *
    * [12 points]
    */
    
   public static String processGuess(String word, String guess) {
      // Makes both strings lowercase (could use IgnoreCase)
      word = word.toLowerCase();
      guess = guess.toLowerCase();
      // Creates empty string, index number, and compared char variable.
      String outputWord = "";
      int indexChar = 0;
      char processLetter;
      // Will run until the output word is the same length as actual word.
      while ((outputWord.length() < word.length())) {
         // Gets the letter at the index of the guess.
         processLetter = guess.charAt(indexChar);
         // If the guess letter is not in word, produces ? in its place.
         if (word.indexOf(processLetter) == -1) {
            outputWord += '?';
         }
         /* If the letter starting at the index number is in the right place,
            then it will produce Upper case letter. */
         /* Avoids issue of multiple letters in same word getting lowercase,
            even in right place. */
         else if (word.indexOf(processLetter, indexChar) == 
            guess.indexOf(processLetter, indexChar)) {
            outputWord += Character.toUpperCase(processLetter);
         }
         // If in the word but not correct spot, puts a lowercase letter.
         else {
            outputWord += processLetter;
         }
         // Goes to next letter.
         indexChar += 1;
      }
      // Returns analyzed word.
      return outputWord;
   }
   
   /**
    * Checks whether the game is over due to the guess matching the word or 
    * the player being out of guesses. Prints either 
    * 
    * "Congrats, you guessed the word!"
    * 
    * or 
    * 
    * "Sorry, you ran out of guesses!" 
    * 
    * as appropriate.
    * 
    * @param guess The player's most recent guess.
    * @param word The correct word.
    * @param numGuesses The number of guesses the player has tried so far.
    * @return true if the game should end (due to a win or loss), otherwise
    * false.
    * 
    * [4 points]
    */
    
   public static boolean checkGameOver(String guess, String word, 
                                      int numGuesses) {
      // Makes both strings lowercase (could use IgnoreCase)
      guess = guess.toLowerCase();
      word = word.toLowerCase();
      // Makes three conditions based on guesses, correct word, and endgame.
      boolean gameOver = false;
      boolean correctWord = false;
      boolean endGame = false;
      // If guess is the word then prints statement and makes true.
      if (guess.equals(word)) {
         System.out.println("Congrats, you guessed the word!");
         correctWord = true;
      }
      // If all guesses are used (6) prints statement and makes true.
      if (numGuesses == 6) {
         System.out.println("Sorry, you ran out of guesses!");
         gameOver = true;
      }
      // If either condition is true, makes end game true.
      if (gameOver || correctWord) {
         endGame = true;
      }
      // returns boolean, false if game continues, true to end the game.
      return endGame;
   }
}
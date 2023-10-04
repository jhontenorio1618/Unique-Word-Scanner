import java.net.URL;
import java.util.Scanner;

public class UniqueWords {

  public static void main(String[] args) {
    showRunner("https://www.gutenberg.org/cache/epub/1/pg1.txt");

  }

  /**
   * Constant to determine size of array to hold words from book
   */
  private static final int EXPECTED_WORDS = 10;
  private static DynamicArray demo = new DynamicArray(EXPECTED_WORDS);
  private static String[] words = demo.getData();

  /**
   * Show runner method: attempts to connect to a book, scan its words,
   * and save each unique word in an array that it returns.
   *
   * @param link to the book we wish to scan
   * @return array with the words in the book containing no duplicates
   */
  public static String[] showRunner(String link) {
    // Make sure that the link provided is neither null nor empty
    if (link != null && link.length() > 0) {
      // Make sure that you can connect a Scanner to that link.
      Scanner book = scanURL(link);
      // We wrote method scanURL to return a null if connection is not possible.
      if (book != null) {
        // Scanner connection successful. Start at the beginning of the array
        // Pull one word out at a time and add it to the array
        while (book.hasNext()) {
          String word = book.next();
          demo.addUnique(word);
        } // end while for book scanner
      }
    }
    demo.print();
    return words; // Return the array
  }

  /**
   * Determine if a word is in the array. The method searches array
   * wordsFromBook up to and including the mostRecentElement. We
   * assume that mostRecentElement < wordsFromBook.length.
   *
   * @param //String word to search from in array wordsFromBook
   * @return true if word is found among the use portion of the array (up to
   * and including [mostRecentElement]), otherwise false.
   * UPDATE(METHOD USAGE DISCONTINUED)
   */


  /**
   * Resize an array. WRITE YOUR STRAGEGY AS JAVADOC COMMENTS HERE:
   * create a new array with a larger size
   * add 200 more "spots" element from old array
   * copy element from the old array to new array
   * done with a for loop
   *
   * @param //old array String[] to copy into new array
   * @return new String[] array with an increase length size (old length + 200) and elements of the old array copied into the new array
   * WRITE YOUR METHOD BELOW.
   *UPDATE(METHOD USAGE DISCONTINUED)
   */

  /**
   * Convert a string with a website link to a URL object that can be processed
   * by a Scanner. If the string does not point to a valid website, the method
   * returns a null pointer.
   *
   * @param link String to the website that we want to connect to.
   * @return a URL object to that website, or null if connection not possible.
   *
   */
  public static URL connectToLink(String link) {
    URL connection;
    try {
      // If the website is good, convert it to a URL object
      connection = new URL(link);
    } catch (Exception e) {
      // If we can't connect to the website, prepare to return null.
      connection = null;
    }
    return connection;
  } // method connectToLink

  public static Scanner scanURL(String link) {
    // Assume attempt will fail, and we'll return null
    Scanner scanner = null;
    // Convert string with link to a URL object
    URL connection = connectToLink(link);
    // If conversion was not successful and we got a null, skip to return
    if (connection != null) {
      // Conversion was successful; try to pass it to Scanner
      try {
        scanner = new Scanner(connection.openStream());
      } catch (Exception e) {
        // If Scanner cannot open the URL, scanner remains null
        scanner = null;
      }
    }
    return scanner;
  } // method scanURL
}

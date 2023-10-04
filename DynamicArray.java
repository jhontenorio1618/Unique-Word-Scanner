import java.util.Arrays;

/**
 * An object with dynamic storage capability for Strings.
 * Desired behavior:
 * - Detect when full and size up by a given factor;
 * - Capable of reporting how much data store;
 * - index addressable (random access)
 * - a contains method
 * - data to be added to the end of existing data
 * - ability to delete data and re-compact the storage
 */
public class DynamicArray {


    // Constant with array default size
    private static final int DEFAULT_SIZE = 2;

    //Resize factor for enlarging a full array

    private static final int RESIZE_FACTOR = 2;

    //Underlying array with strings to store
    private String[] data;

    //Next available position to accept data
    private int nextAvailable;


    // Basic constructor
    public DynamicArray(int size) {
        // Make sure size is a legit value
        if (size < 1)
            size = DEFAULT_SIZE;
        this.data = new String[size];
        this.nextAvailable = 0;
    } // basic constructor

    //Default constructor
    public DynamicArray() {
        // Call basic constructor with size = DEFAULT_SIZE
        this(DEFAULT_SIZE);
    } // default constructor

    /**
     * Method to add a string to the object
     *
     * @param string String to add to the object's underying array
     */
    public void add(String string) {
        // If array is full resize first then add String to underlying array
        insert(this.nextAvailable, string);
        // Advance to the next available position
        nextAvailable++;
        // Keep count of how many elements are in the array
    } // method add

    /**
     * Inserts a string 'what' at a specified position 'where' in the data array.
     * @param where The position at which to insert the string.
     * @param what The string to be inserted.
     */
    public void insert(int where, String what) {
        /* Specific to the add method, removes redundancy of 'add' method AND "insert"
          may also be used as an add method AS WELL
         */
        if (where == this.nextAvailable) {
            if (this.nextAvailable == this.data.length) {
                resize();
            }
            this.data[this.nextAvailable] = what;
        }
        if (where >= 0 && where < this.nextAvailable) {
            // position legit; is array full?
            // Room assured.
            for (int i = this.nextAvailable; i != where; i--) {
                this.data[i] = this.data[i - 1];
            }
            // Shifting complete. Overwrite position [where]
            this.data[where] = what;
            this.nextAvailable++;
        }
    }


    /**
     * Method to resize the array when full
     */
    public void resize() {
        // Create a larger array
        String[] temp = new String[RESIZE_FACTOR * this.data.length];
        // copy strings from current array to larger array
        for (int i = 0; i < this.data.length; i++) {
            temp[i] = this.data[i];
        }
        // Replace underlying array with the new larger array
        this.data = temp;
    } // method resize

    /**
     * Retrieve an element by location index.
     *
     * @param index int with the position of element we want to retrieve
     * @return the string a position [index] or null if position is invalind or empty
     */
    public String retrieve(int index) {
        String retrieved = null;
        if (index >= 0 && index < this.nextAvailable) {
            retrieved = this.data[index];
        }
        return retrieved;
    } // method retrieve

    /**
     * Remove an element by location index and rearrange the remaining elements
     * to fill the gap.
     *
     * @param index int with the position of element we want to retrieve
     * @return the string a positiion [index] or null if position is invalind or empty
     */
    public String remove(int index) {
        String removed = this.retrieve(index);
        if (removed != null) {
            for (int i = index; i < this.nextAvailable - 1; i++) {
                this.data[i] = this.data[i + 1];
            }
            this.data[nextAvailable - 1] = null;
        }
        nextAvailable--;
        return removed;
    } // method remove

    /**
     * Check if the array contains a given word.
     *
     * @param word String to search for in the array
     * @return true if the word is found, false otherwise
     * //[The, The, There
     */
    public boolean contains(String word) {
        boolean found = false; // We'll start assuming the word is unique
        int i = 0;
        while (!found && i < this.nextAvailable && word != null) {
            if (this.data[i].equals(word)) {
                found = true;
            }
            i++;
        }
        return found;
    } // method contains//

    /**
     * Find the index of the first occurrence of a given word.
     *
     * @param word String to search for in the array
     * @return the index of the word, or -1 if not found
     */
    public int indexOf(String word) {
        boolean stop = false;
        int index = -1;
        int i = 0;
        while (!stop && i < this.nextAvailable && word != null) {
            if (this.data[i].equals(word)) {
                index = i;
                stop = true;
            }
            i++;
        }
        return index;
    } // method indexOf

    /**
     * Count how many times a given word appears in the array.
     *
     * @param word String to count occurrences of
     * @return the number of occurrences of the word
     */
    public int count(String word) {
        int counter = 0;
        int i = 0;
        while (i < this.nextAvailable) {
            // In the case scenario that word is found, "counter" will increase by one.
            if (this.data[i].equals(word)) {
                counter++;
            }
            i++;
        }
        return counter;
    } // method count

    /**
     * Insert a string at a specified position in the array.
     *
     * @param position int with the position to insert at
     * @param string String to insert
     */


    /**
     * Add a string only if it doesn't already exist in the array.
     *
     * @param word String to add if it doesn't already exist
     */
    public void addUnique(String word) {
        if (!this.contains(word)) {
            this.add(word);
        }
    } // method addUnique

    /**
     * Remove the first element in the array.
     */
    public String removeFront() {
        return this.remove(0);
    } // method removeFront

    public String removeLast() {
        return remove(this.nextAvailable - 1);
    }


    /**
     * Print the contents of the array.
     */
    public void print() {
        System.out.println(Arrays.toString(this.data));
    } // method print

    public void addFront(String string) {
        insert(0, string);
    }


    public int getNextAvailable() {
        return this.nextAvailable;
    }


    /**
     * String representation of a DynamicArray object.
     */
    public String toString() {
        // Demonstrate the use of StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Number of items currently stored: %d", this.nextAvailable));
        sb.append(String.format("\nCurrent capacity: %d", this.data.length));
        sb.append(String.format("\nContents:"));
        for (int i = 0; i < this.nextAvailable; i++) {
            sb.append(String.format("\n\t%s", this.data[i]));
        }
        return sb.toString();
    } // method toString

    public int size() {
        return this.getNextAvailable();
    }

    //Will allow access to data array since it's originally private
    public String[] getData() {
        return this.data;
    }

    //Comparison of two arrays.
    public int compareTo(DynamicArray other) { // Other is another array
        return (this.nextAvailable) - (other.nextAvailable);
    }

} // class DynamicArray

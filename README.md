# Unique-Word-Scanner
# Code Purpose

## `UniqueWords.java`

### Purpose:
The `UniqueWords` class contains methods to read a book from a given URL, process the text, and return an array with unique words.

### Challenges:
- Handling potential errors during the connection process.
- Efficiently processing and storing potentially large amounts of text.
- Ensuring that the array of words contains only unique entries.

## `DynamicArray.java`

### Purpose:
The `DynamicArray` class is a custom implementation of a dynamic array capable of storing strings. It dynamically resizes itself as needed and provides methods for adding, removing, and manipulating elements.

### Challenges:
- Managing dynamic resizing to maintain efficiency.
- Implementing insertions and deletions while preserving order.
- Ensuring the uniqueness of elements when adding.

## DISCLAIMER 

The code provided in `UniqueWords.java` is designed to work with a specific type of URL: one that points to a plain text file. This is evident in the method `scanURL`, which attempts to create a `Scanner` object to read from the URL.

If the URL points to a text file (commonly with a `.txt` extension), the code should work as intended. However, if the URL points to a different type of resource, such as an HTML page or a binary file, the code may not function correctly. It's designed specifically for text-based content.

In a real-world scenario, you might want to add additional checks and handling to ensure that the URL points to a compatible text file before attempting to process it.

---


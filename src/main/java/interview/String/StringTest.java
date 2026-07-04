/**
 * @author phucle-compass
 */
package String;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class StringTest {

  public static void main(String[] args) throws IOException, URISyntaxException {

    // 1. How do you reverse a string in Java?
    //reverseString();

    // 2. How do you swap two numbers without using a third variable in Java?
    //swapTwoNumbers();

    // 3. Write a Java program to check if a vowel is present in a string
    //checkVowelPresentInString();

    // 4. Write a Java program to check if the given number is a prime number
    //checkGivenNumberIsPrimeNumber();

    // 5. Write a Java program to print a Fibonacci sequence using recursion
    //printFibonacciSequence(10);

    // 6. How do you check if a list of integers contains only odd numbers in Java?
    //checkListNumberContainsOnlyOddNumbers();

    // 7. How do you check whether a string is a palindrome in Java?
    //checkStringIsPalindrome("abcba");

    // 8. How do you remove spaces from a string in Java?
    //removeSpacesInString();

    // 9. How do you remove leading and trailing spaces from a string in Java?
    //removeLeadingAndTrailingSpacesFromString();

    // 10. How do you sort an array in Java?
    //sortArray();

    // 11. How can you find the factorial of an integer in Java?
    //findFactorialOfInteger(5);

    // 12. How can you find a string in a text file in Java?
    //findAStringInTextFile();

    // 13. How do you remove all occurrences of a given character from an input string in Java?
    //removeAllOccurrencesOfGivenCharacterFromString();

    // 14. How do you get distinct characters and their count in a string in Java?
    //getDistinctCharactersAndTheirCountInString();

    // 15. Can you prove that a String object in Java is immutable programmatically?

    // 16. How do you create text blocks in Java?

    // 17. Show an example of switch expressions and multi-label case statements in Java

    // 18. How do you compile and run a Java class from the command line?

    // 19. Read and print the content of file
    //readSmallFile();
    //readMediumFile();
    //readLargeFile();

    // 20. Reverse words without using split.
    reverseWords("Hello Java World");

    // 21. Find all occurrences of "aba"
    findPattern("abababa", "aba");
  }

  private static void reverseString() {
    String str = "Hello World";

    String reversed = IntStream.rangeClosed(1, str.length())
        .mapToObj(i -> String.valueOf(str.charAt(str.length() - i)))
        .reduce("", String::concat);

    System.out.println(reversed);
  }

  private static void swapTwoNumbers() {
    int a = 10;
    int b = 20;

    a = a + b; // a = 10 + 20 = 30
    b = a - b; // b = 30 - 20 = 10;
    a = a - b ; // a = 30 - 10 = 20
  }

  private static boolean checkVowelPresentInString() {
    String inputStr = "Hello";
    inputStr = inputStr.toLowerCase();

    for (char c : inputStr.toCharArray()) {
      if (c == 'a' || c == 'e' || c == 'i' || c == 'u' || c == 'o') {
        return true;
      }
    }

    return false;
  }

  private static boolean checkGivenNumberIsPrimeNumber() {
    int number = 10;
    if (number <= 1) {
      return false;
    }

    for (int i = 2; i <= Math.sqrt(number); i++) {
      if (number % i == 0) {
        return false;
      }
    }

    return true;
  }

  private static int printFibonacciSequence(int n) {
    if (n <= 1) {
      return n;
    }

    return printFibonacciSequence(n - 1) + printFibonacciSequence(n - 2);
  }

  private static boolean checkListNumberContainsOnlyOddNumbers() {
    List<Integer> numbers = Arrays.asList(0, 2, 4, 6, 8);
    return numbers.stream()
        .allMatch(n -> n != null && n %2 != 0);
  }

  private static boolean checkStringIsPalindrome(String s) {
    String normalizedStr = s.replaceAll("\\s+", "");
    return IntStream.range(1, normalizedStr.length() / 2)
        .allMatch(c -> s.charAt(c) == s.charAt(normalizedStr.length() - 1 - c));
  }

  private static void removeSpacesInString() {
    String s = "abc ef gh";
    String result = s.chars()
        .filter(c -> c != ' ')
        .mapToObj(c -> String.valueOf((char)c))
        .collect(Collectors.joining());
    System.out.println(result);
  }

  private static void removeLeadingAndTrailingSpacesFromString() {

    String s = " Hello Java ";
    int left = 0;
    int right = s.length() - 1;

    while (left <= right && Character.isWhitespace(s.charAt(left))) {
      left++;
    }

    while (right >= left && Character.isWhitespace(s.charAt(right))) {
      right--;
    }

    String result = s.substring(left, right + 1)
        .chars()
        .mapToObj(c -> String.valueOf((char) c))
        .collect(Collectors.joining());
    System.out.println(result);
  }

  private static void sortArray() {}

  private static void findFactorialOfInteger(int number) {
    if (number < 0) {
      throw new IllegalArgumentException("Number must be non-negative");
    }

    long reduce = LongStream.rangeClosed(1, number)
        .reduce(1, (a, b) -> a * b);
    System.out.println(reduce);
  }

  private static void findAStringInTextFile() throws IOException, URISyntaxException {

    String targetStr = "Java";
    Path path = Path.of("src/main/java/interview/String/sample.txt");
    try (var lines = Files.lines(path)) {
      lines.filter(l -> l.contains(targetStr))
          .forEach(System.out::println);
    }
  }

  private static void removeAllOccurrencesOfGivenCharacterFromString() {
    String s = "abcbdefgeab";
    char c = 'b';
    String result = s.chars()
        .filter(c1 -> c1 != c)
        .mapToObj(c1 -> String.valueOf((char)c1))
        .collect(Collectors.joining())
        .toString();
    System.out.println(result);
  }

  private static void getDistinctCharactersAndTheirCountInString() {
    String s = "abcbdefgeab";
    Map<Character, Long> maps = s.chars()
        .mapToObj(c -> (char) c)
        .collect(
            Collectors.groupingBy(
                Function.identity(), Collectors.counting()
            )
        );
    System.out.println(maps);
  }

  private static void readSmallFile() {
    String content = null;
    try {
      content = Files.readString(Path.of("src/main/java/interview/String/sample.txt"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println(content);
  }

  /*
    ** Advantages
    **** Memory efficient
    **** Lazy loading
    **** Supports Stream API
    **** Automatically buffered
    ** Time Complexity: O(n)
    ** Memory: O(1)
   */

  private static void readMediumFile() {
    Path path = Path.of("src/main/java/interview/String/sample.txt");
    try (var lines = Files.lines(path)) {
      lines.forEach(System.out::println);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /*
    ** Best Performance for Large Text Files - BufferedReader
    ** Why it's fast:
    **** Uses an internal buffer (8 KB by default)
    **** Avoids reading one character at a time
    **** Minimal memory usage
   */
  private static void readLargeFile() {
    try (BufferedReader buffer = new BufferedReader(new FileReader("src/main/java/interview/String/sample.txt"))) {
      String line;
      while ((line = buffer.readLine()) != null) {
        System.out.println(line);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static String reverseWords(String s){

    StringBuilder result = new StringBuilder();

    int end = s.length()-1;

    while(end>=0){

      while(end>=0 && s.charAt(end)==' ')
        end--;

      if(end<0)
        break;

      int start=end;

      while(start>=0 && s.charAt(start)!=' ')
        start--;

      result.append(s,start+1,end+1);

      result.append(" ");

      end=start-1;
    }

    return result.toString().trim();
  }

   static List<Integer> findPattern(String text, String pattern){

    List<Integer> result = new ArrayList<>();

    for(int i=0;i<=text.length()-pattern.length();i++){

      if(text.substring(i,i+pattern.length()).equals(pattern)){
        result.add(i);
      }
    }

    return result;
  }}

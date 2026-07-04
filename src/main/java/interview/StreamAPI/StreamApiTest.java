/**
 * @author phucle-compass
 */
package StreamAPI;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiTest {

  public static void main(String[] args) {
    // 1. Find all even numbers from a list
    // 2. Convert a list of strings to uppercase
    // 3. Find the sum of all numbers in a list
    // 4. Find the maximum number
    // 5. Find the minimum number
    // 6. Count strings starting with a specific letter
    // 7. Remove duplicates from a list
    // 8. Sort in ascending order
    // 9. Sort in descending order
    // 10. Find the first element
    // 11. Remove empty/blank strings
    // 12. Find the second-largest number
    // 13. Find union of two lists
    //findUnionTwoList();
    // 14. Find Pairs with Target Sum
    //findPairsWithTargetSum();
    // 15. Find Common Elements Between Two Lists
    //findCommonElementsBetweenTwoList();
    // 16. Find All Elements That Appear More Than Once in a List
    //findAllElementsAppearMoreThanOnceInList();
    // 17. Find the first non-repeating character in a String using Java Streams.
    //findFirstNonRepeatingCharacter();
    // 18. Find Even Numbers in a List
    //findEvenNumbers();
    // 19. Convert List of Strings to Uppercase
    //convertListOfStreamToUppercase();
    // 20. Find the First Element in a Stream
    //findFirstElement();
    // 21. Find the Maximum Number in a List
    //findMaxNumber();
    // 22. Count Elements in a Stream
    //countElements();
    // 23. Remove Duplicates from a List
    //removeDuplicateNumbers();
    // 24. Sort a List Using Stream API
    //sortList();
    // 25. Check If Any String in the List Starts with “A”
    //checkStringStartWithCharacter();
    // 26. Group Elements by Their Length
    //groupElementByLength();
    // 27. Convert a List to a Map (Using Streams)
    //convertListToMap();
  }

  private static void findUnionTwoList() {
    List<Integer> number1s = Arrays.asList(1, 2, 3, 4, 5, 6);
    List<Integer> number2s = Arrays.asList(4, 5, 6, 7, 8, 9);

    List<Integer> unionDescNumbers = Stream.of(number1s, number2s)
        .flatMap(Collection::stream)
        .distinct()
        .sorted(Comparator.reverseOrder())
        .toList();
    System.out.println(unionDescNumbers);
  }

  private static void findPairsWithTargetSum() {
    List<Integer> number1s = Arrays.asList(1, 2, 3, 6, 17, 1, 10, 8);
    List<Integer> number2s = Arrays.asList(1, 17, 12, 12, 2, 4, 6);
    int targetSum = 18;

    List<String> pairsTartSum = number1s.stream()
        .flatMap(n1 -> number2s.stream()
            .filter(n2 -> (n1 + n2) == targetSum)
            .map(n -> n1 + "," + n)
        )
        .toList();
    System.out.println(pairsTartSum);
  }

  private static void findCommonElementsBetweenTwoList() {

    List<Integer> number1s = Arrays.asList(10, 20, 30, 40, 50);
    List<Integer> number2s = Arrays.asList(30, 40, 50, 60, 70);

    List<Integer> finalNumbers = number1s.stream()
        .filter(number2s::contains)
        .toList();
    System.out.println(finalNumbers);
  }

  private static void findAllElementsAppearMoreThanOnceInList() {

    List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6);
    numbers.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet()
        .stream()
        .filter(a -> a.getValue() > 1)
        .map(Map.Entry::getKey)
        .forEach(System.out::println);
  }

  private static void findFirstNonRepeatingCharacter() {

    String input = "swiss";
    Character result = input.chars()
        .mapToObj(c -> (char)c)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet()
        .stream()
        .filter(e -> e.getValue() == 1)
        .map(Map.Entry::getKey)
        .findFirst()
        .orElse('N');
    System.out.println(result);
  }

  static void findEvenNumbers() {
    List<Integer> numbers = Arrays.asList(1, 2, 4, 4, 5, 6, 7, 8);
    List<Integer> evenNumbers = numbers.stream()
        .filter(n -> n % 2 == 0)
        .toList();
    System.out.println(evenNumbers);
  }

  static void convertListOfStreamToUppercase() {
    List<String> fruits = Arrays.asList("banana", "apple", "orange");
    List<String> upperFruits = fruits.stream()
        .map(String::toUpperCase)
        .toList();
    System.out.println(upperFruits);
  }

  static void findFirstElement() {
    List<String> programmings = Arrays.asList("Java", "Python", "Golang");
    String firstPro = programmings.stream()
        .findFirst()
        .orElse("No Programming");
    System.out.println(firstPro);
  }

  static void findMaxNumber() {
    List<Integer> numbers = Arrays.asList(1, 8, 3, 2, 10);
    int maxNumber = numbers.stream()
        .max(Integer::compareTo)
        .orElse(-1);
    System.out.println(maxNumber);
  }

  static void countElements() {
    List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");
    long count = fruits.stream().count();
    System.out.println(count);
  }

  static void removeDuplicateNumbers() {
    List<Integer> numbers = Arrays.asList(5, 3, 9, 3, 5, 7, 9);
    List<Integer> distinctNumbers = numbers.stream()
        .distinct()
        //.sorted(Integer::compareTo) // sort ascending
        .sorted(Comparator.reverseOrder()) // sort descending
        .toList();
    System.out.println(distinctNumbers);
  }

  static void sortList() {
    List<Integer> numbers = Arrays.asList(4, 2, 7, 1, 9);
    List<Integer> ascNumbers = numbers.stream()
        .sorted()
        .toList();
    List<Integer> descNumbers = numbers.stream()
        .sorted(Comparator.reverseOrder())
        .toList();
    System.out.println(ascNumbers);
    System.out.println(descNumbers);

    List<Integer> descendingNumbers = numbers.stream()
        .sorted((a, b) -> b - a)
        .toList();
    System.out.println(descendingNumbers);

    List<Integer> ascendingNumbers = numbers.stream()
        .sorted((a, b) -> a - b)
        .toList();
    System.out.println(ascendingNumbers);
  }

  static void checkStringStartWithCharacter() {
    List<String> names = Arrays.asList("John", "Alice", "Bob");
    boolean findPrefix = names.stream()
        .anyMatch(s -> s.startsWith("A"));
    System.out.println(findPrefix);

    List<String> prefixNumbers = names.stream()
        .filter(s -> s.startsWith("A"))
        .toList();
    System.out.println(prefixNumbers);
  }

  static void groupElementByLength() {
    List<String> words = Arrays.asList("apple", "banana", "cherry", "dog", "cat");
    Map<Integer, List<String>> groupNumbers = words.stream()
        .collect(Collectors.groupingBy(String::length));
    System.out.println(groupNumbers);
  }

  static void convertListToMap() {
    List<Product> products = Arrays.asList(
        new Product(1, "Laptop"),
        new Product(2, "Phone"),
        new Product(3, "Tablet")
    );

    Map<Integer, String> maps = products.stream()
        .collect(Collectors.toMap(p -> p.id, p -> p.name));
    System.out.println(products);
    System.out.println(maps);
  }
}

class Product {
  int id;
  String name;
  public Product(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}

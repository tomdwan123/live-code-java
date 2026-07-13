/**
 * @author phucle-compass
 */
package epam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Interview {

  public static void main(String[] args) {

    System.out.println(solution("111"));
    //isPalindrome("kaYak");
    //removeDuplicates({2,2,3,4,4});
    //validWordAbbreviation("tnginixqipjkhn", "t10khn18");
    //isPalindrome2("KLMNOPQRSTUVgVUTSRQPONMLKL");
    //findLongestSubstring("abcdbea");
    //longestPalindrome("babad");
    //isValid("()[]{}");
    String[] input = {"AD-RO", "ME-BE", "RO-ME"};
    order(input);
  }

  private static int solution(String s) {

    int count = 0;
    int lastIndex = -1;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '1') {
        count++;
        lastIndex = i;
      }
    }

    if (count == 0) return 0;

    return count + lastIndex;
  }

  public static boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;

    while (left < right) {
      while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
        left++;
      }

      while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
        right--;
      }

      if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
        return false;
      }

      left++;
      right--;
    }

    return true;
  }

  public static int removeDuplicates(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    int write = 0;

    for (int read = 1; read < nums.length; read++) {
      if (nums[read] != nums[write]) {
        write++;
        nums[write] = nums[read];
      }
    }

    return write + 1;
  }

  public static boolean validWordAbbreviation(String word, String abbr) {

    // Replace the following return statement with your code
    int pw = 0;
    int pa = 0;
    String jump = "0";
    while (pw < word.length() && pa < abbr.length()) {
      while (pa < abbr.length() && Character.isDigit(abbr.charAt(pa))) {
        if (abbr.charAt(pa) != '0') { // not start with zero
          jump += abbr.charAt(pa);
        } else { // check this current num is start with zero or not
          if (pa == 0 ||
              (pa > 0 && !Character.isDigit(abbr.charAt(pa-1)))
          ) {
            return false; // num start with zero
          }
          jump += abbr.charAt(pa);
        }

        pa++;
      }

      if (jump.equals("0") && word.charAt(pw) != abbr.charAt(pa)) {
        return false;
      }

      pw += jump.equals("0") ? 1 : Integer.parseInt(jump);
      pa += jump.equals("0") ? 1 : 0;
      jump = "0";
    }

    return pw == word.length() && pa == abbr.length();
  }

  public static boolean isPalindrome2(String string) {

    // Replace this placeholder return statement with your code
    int left = 0, right = string.length() - 1;
    int count = 0;
    while (left < right) {

      if (string.charAt(left) != string.charAt(right)) {
        return isPalindromeHelper(string, left + 1, right)
            || isPalindromeHelper(string, left, right - 1);
      }

      left++;
      right--;
    }

    return true;
  }

  static boolean isPalindromeHelper(String s, int left, int right) {
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        return false;
      }

      left++;
      right--;
    }

    return true;
  }

  public int removeElement(int[] nums, int val) {

    // Replace this placeholder return statement with your code
    int count = nums.length;
    int left = 0;
    int right = nums.length - 1;
    while (left < nums.length) {
      if (nums[left] == val) {
        while (right >= 0) {
          if (nums[right] != val) {
            nums[left] = nums[right];
            right--;
            break;
          }

          right--;
        }

        count--;
      }
    }

    left++;

    return count;
  }

  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int lSum = target - nums[i];
      if (map.containsKey(lSum)) {
        return new int[]{map.get(lSum), i};
      }

      map.put(nums[i], i);
    }

    return null;
  }

  public static int findLongestSubstring(String str) {

    // Replace this placeholder return statement with your code
    int max = 0;
    int left = 0;
    Set<Character> set = new HashSet<>();
    for (int right = 0; right < str.length(); right++) {
      while (set.contains(str.charAt(right))) {
        set.remove(str.charAt(left));
        left++;
      }

      set.add(str.charAt(right));
      max = Math.max(max, right - left + 1);
    }

    return max;
  }

  public static String longestPalindrome(String s) {
    if (s == null || s.length() < 2) {
      return s;
    }

    int start = 0;
    int end = 0;

    for (int i = 0; i < s.length(); i++) {

      int len1 = expand(s, i, i);       // odd
      int len2 = expand(s, i, i + 1);   // even

      int len = Math.max(len1, len2);

      if (len > end - start + 1) {
        start = i - (len - 1) / 2;
        end = i + len / 2;
      }
    }

    return s.substring(start, end + 1);
  }

  private static int expand(String s, int left, int right) {

    while (left >= 0 &&
        right < s.length() &&
        s.charAt(left) == s.charAt(right)) {

      left--;
      right++;
    }

    return right - left - 1;
  }

  private static List<String> order(String[] input) {

    Map<String, String> map = new HashMap<>();
    Set<String> fromSet = new HashSet<>();
    Set<String> toSet = new HashSet<>();

    for (int i = 0; i < input.length; i++) {
      String node = input[i];
      String from = node.split("-")[0];
      String to = node.split("-")[1];

      fromSet.add(from);
      toSet.add(to);
      map.put(from, node);
    }

    // find the start node in the grap
    String start = null;
    for (String from : fromSet) {
      if (!toSet.contains(from)) {
        start = from;
        break;
      }
    }
    
    // if the graph is cycle, pick randum first node
    if (start == null) {
      start = input[0].split("-")[0];
    }

    List<String> result = new ArrayList<>();
    Set<String> visitedFrom = new HashSet<>();
    while (map.containsKey(start) && !visitedFrom.contains(start)) {
      visitedFrom.add(start);
      result.add(map.get(start));
      start = map.get(start).split("-")[1];
    }

    return result;
  }
}

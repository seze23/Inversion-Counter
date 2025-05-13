/**
 * @author Sydney Eze
 * Programming Assignment 2 - Recursion exercises
 * COMS W3134
 *
 * Note: All methods must be written recursively. No credit will be given for
 * methods written without recursion, even if they produce the correct output.
 */
public class Recursion {

    /**
     * Returns the value of x * y, computed via recursive addition.
     * x is added y times. Both x and y are non-negative.
     * @param x  non-negative integer multiplicand 1
     * @param y  non-negative integer multiplicand 2
     * @return   x * y
     */
    public static int recursiveMultiplication(int x, int y) {
        //If x * y = 0
        if (y==0){
            return 0;
        }
        //Actual method body
        return x + recursiveMultiplication(x, y-1);
    }

    /******************************************************************************/
    /**
     * Reverses a string via recursion.
     * @param s  the non-null string to reverse
     * @return   a new string with the characters in reverse order
     */
    public static String reverse(String s) {
        int length = s.length();
        //Base case: if string is empty
        if (s.isEmpty()){
            return s;
        }
        //Base case: if string length is 1
        if (length == 1){
            return s;
        }
        //Actual method body
        //return last character and create new substring
        return s.charAt(length - 1) + reverse(s.substring(0, length - 1));
    }

    /******************************************************************************/
    private static int maxHelper(int[] array, int index, int max) {
        //Base case: array has been traversed
        if (index >= array.length){
            return max;
        }
        //Base case: if array length is 1
        if (array.length == 1){
            return array[index];
        }
        //Compare values at index
        if (array[index] > max){
            max = array[index];
            return maxHelper (array, index + 1, max);
        }
        return maxHelper (array, index + 1, max);
    }

    /**
     * Returns the maximum value in the array.
     * Uses a helper method to do the recursion.
     * @param array  the array of integers to traverse
     * @return       the maximum value in the array
     */
    public static int max(int[] array) {
        return maxHelper(array, 0, Integer.MIN_VALUE);
    }

    /******************************************************************************/

    /**
     * Returns whether or not a string is a palindrome, a string that is
     * the same both forward and backward.
     * @param s  the string to process
     * @return   a boolean indicating if the string is a palindrome
     */
    public static boolean isPalindrome(String s) {
        int length = s.length();
        //Base case: string is empty or length = 1
        if (s.isEmpty() || length == 1){
            return true;
        }
        //If letters at opposing indices are not the same
        if (s.charAt(0) != s.charAt(length - 1)){
            return false;
        } else{ //Parse through rest of string
            return isPalindrome(s.substring(1, length - 1));
        }
    }

    /******************************************************************************/
    private static boolean memberHelper(int key, int[] array, int index) {
        //Base case: array has been traversed
        if (index >= array.length){
            return false;
        }
        //Base case: if array length is 1
        if (array.length == 1){
            if (array[index] != key){
                return false;
            }
            if (array[index] == key){
                return true;
            }
        } else{ //Check for key
            if (array[index] == key){
                return true;
            }
        }
        return memberHelper(key, array, index + 1);
    }

    /**
     * Returns whether or not the integer key is in the array of integers.
     * Uses a helper method to do the recursion.
     * @param key    the value to seek
     * @param array  the array to traverse
     * @return       a boolean indicating if the key is found in the array
     */
    public static boolean isMember(int key, int[] array) {
        return memberHelper(key, array, 0);
    }

    /******************************************************************************/
    /**
     * Returns a new string where identical chars that are adjacent
     * in the original string are separated from each other by a tilde '~'.
     * @param s  the string to process
     * @return   a new string where identical adjacent characters are separated
     *           by a tilde
     */
    public static String separateIdentical(String s) {
        int length = s.length();
        //Base case: string is empty or length = 1
        if (s.isEmpty() || length == 1){
            return s;
        }
        //Check if characters are the same
        if (s.charAt(0) == s.charAt(1)){
            return s.charAt(0) + "~" + separateIdentical(s.substring(1));
        } else {
            return s.charAt(0) + separateIdentical(s.substring(1));
        }
    }
}

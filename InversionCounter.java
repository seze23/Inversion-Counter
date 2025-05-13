import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Class with two different methods to count inversions in an array of integers.
 * @author Sydney Eze
 * @version 1.0.0 November 17, 2022
 */
public class InversionCounter {

    /**
     * Returns the number of inversions in an array of integers.
     * This method uses nested loops to run in Theta(n^2) time.
     * @param array the array to process
     * @return the number of inversions in an array of integers
     */
    public static long countInversionsSlow(int[] array) {
        long slowCount = 0;
        int elements = array.length;

        //Slow approach uses nested for loops
        for (int i = 0; i < elements; i++) {
            //Iterate through the array
            for (int j = i + 1; j < elements; j++){
                //"Next" comparison to front element i in the array
                if (array[i] > array[j]){
                    slowCount++;
                }
            }
        }

        return slowCount;
    }

    /**
     * Returns the number of inversions in an array of integers.
     * This method uses mergesort to run in Theta(n lg n) time.
     * @param array the array to process
     * @return the number of inversions in an array of integers
     */
    public static long countInversionsFast(int[] array) {
        long fastCount = 0;
        int fastElements = array.length;
        // Make a copy of the array so you don't actually sort the original
        // array.
        int[] arrayCopy = new int[fastElements];
        int [] scratch =  new int[fastElements];

        System.arraycopy(array, 0, arrayCopy, 0, fastElements);

        for (int length = 1; length < fastElements; length *= 2){
            //Split array
            for (int low = 0; low + (2 * length) <= fastElements; low += 2 * length){
                int mid = Math.min(low + length - 1, fastElements - 1);
                int high = Math.min((low + 2 * length) - 1, fastElements - 1);

            int i = low;
            int j = mid + 1;
            int m = low;

            //Mergesort
            for (int k = low; k <= high; k++){
                if (i <= mid && (j > high || arrayCopy[i] <= arrayCopy[j])){
                    scratch[m++] = arrayCopy[i++];
                } else{
                    scratch[m++] = arrayCopy[j++];

                    fastCount += (mid - i + 1);
                }
            }
            for (int k = low; k <= high; k++){
                arrayCopy[k] = scratch[k];
            }
            }
        }
        return fastCount;
    }

    /**
     * Reads an array of integers from stdin.
     * @return an array of integers
     * @throws IOException if data cannot be read from stdin
     * @throws NumberFormatException if there is an invalid character in the
     * input stream
     */
    private static int[] readArrayFromStdin() throws IOException,
                                                     NumberFormatException {
        List<Integer> intList = new LinkedList<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        int value = 0, index = 0, ch;
        boolean valueFound = false;
        while ((ch = reader.read()) != -1) {
            if (ch >= '0' && ch <= '9') {
                valueFound = true;
                value = value * 10 + (ch - 48);
            } else if (ch == ' ' || ch == '\n' || ch == '\r') {
                if (valueFound) {
                    intList.add(value);
                    value = 0;
                }
                valueFound = false;
                if (ch != ' ') {
                    break;
                }
            } else {
                throw new NumberFormatException(
                        "Error: Invalid character '" + (char)ch +
                        "' found at index " + index + " in input stream.");
            }
            index++;
        }

        int[] array = new int[intList.size()];
        Iterator<Integer> iterator = intList.iterator();
        index = 0;
        while (iterator.hasNext()) {
            array[index++] = iterator.next();
        }
        return array;
    }

    public static void main(String[] args) {
        try {
            if (args.length > 1) {
                System.err.println("Usage: java InversionCounter [slow]");
                System.exit(1);
            }

            if (args.length == 1 && !args[0].equals("slow")){
                System.err.println("Error: Unrecognized option '" + args[0] + "'.");
                System.exit(1);
            }

            System.out.print("Enter sequence of integers, each followed by a space: ");
            int[] ourNumbers = readArrayFromStdin();

            if (ourNumbers.length == 0){
                System.err.println("Error: Sequence of integers not received.");
                System.exit(1);
            }

            long inversionCount;
            if (args.length == 1 && args[0].equals("slow")){
                inversionCount = countInversionsSlow(ourNumbers);
            } else{
                inversionCount = countInversionsFast(ourNumbers);
            }
            System.out.println("Number of inversions: " + inversionCount);

        } catch (NumberFormatException | IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}



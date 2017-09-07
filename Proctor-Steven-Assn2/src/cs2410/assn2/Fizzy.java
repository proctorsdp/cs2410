package cs2410.assn2;

/**
 * Prints the numbers 1 through 100.<br>
 * Every number that is a multiple of 3 is replaced with the word "Fizz".<br>
 * Numbers that are a multiple of 5 are replaced with the word "Buzz".<br>
 * Any number that is a multiple of both 3 and 5 are replaced with "FizzBuzz".
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class Fizzy {

    /**
     * Private integer used to store the current value of an iteration
     */
    private static int counter;

    /**
     * Iterates from 0 to 100 using the variable counter.<br>
     * For each iteration, counter is passed into isFizz() and isBuzz() methods.
     * <p>
     * Prints the following to the console:<br>
     * - "Fizz" if counter is a multiple of 3<br>
     * - "Buzz" if counter is a multiple of 5<br>
     * - "FizzBuzz" if counter is a multiple of 3 and 5<br>
     * - Otherwise prints the current value of counter
     *
     * @param args not used in this program
     */
    public static void main(String[] args) {

        for (counter = 1; counter <= 100; counter++) {
            if (isFizz(counter) && isBuzz(counter)) {
                System.out.println("FizzBuzz");
            } else if (isBuzz(counter)) {
                System.out.println("Buzz");
            } else if (isFizz(counter)) {
                System.out.println("Fizz");
            } else {
                System.out.println(counter);
            }
        }
    }

    /**
     * Accepts an integer and returns true if the integer is a multiple of 3.
     *
     * @param val some integer value
     * @return true or false
     */
    private static boolean isFizz(int val) {
        return ((val % 3) == 0);
    }

    /**
     * Accepts an integer and returns true if the integer is a multiple of 5.
     *
     * @param val some integer value
     * @return true or false
     */
    private static boolean isBuzz(int val) {
        return (val % 5) == 0;
    }
}

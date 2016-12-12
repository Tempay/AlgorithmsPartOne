import edu.princeton.cs.algs4.StdIn;

/**
 * Created by Tempa on 12/10/2016.
 */
public class Subset {
    public static void main(String[] args) {
        String[] input = StdIn.readAllStrings();
        int outputSize = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        for (String s : input) {
            randomizedQueue.enqueue(s);
        }
        while (outputSize > 0) {
            System.out.println(randomizedQueue.dequeue());
            outputSize--;
        }
    }
}

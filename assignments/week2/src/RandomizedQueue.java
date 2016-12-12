import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Tempa on 12/9/2016.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    private class Node {
        Item item;
        Node next;
    }

    public RandomizedQueue() {
        this.first = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.next = first;
        first = newFirst;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            Item item = first.item;
            size--;
            first = null;
            return item;
        }
        int random = StdRandom.uniform(size);
        Node removedNode = first;
        while (random-- > 1) {
            removedNode = removedNode.next;
        }
        Item item = removedNode.next.item;
        removedNode.next = removedNode.next.next;
        size--;
        return item;
    }

    private class RqIterator implements Iterator<Item> {
            Node cur = copyNode();
            int iterSize = size;

            public boolean hasNext() {
                return iterSize != 0;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public Item next() {
                if (iterSize == 0) {
                    throw new NoSuchElementException();
                }
                if (iterSize == 1) {
                    Item item = cur.item;
                    cur = null;
                    iterSize = 0;
                    return item;
                }
                int random = StdRandom.uniform(iterSize);
                Node removedNode = cur;
                while (random-- > 1) {
                    removedNode = removedNode.next;
                }
                Item item = removedNode.next.item;
                removedNode.next = removedNode.next.next;
                iterSize--;
                return item;
            }
    }

    public Iterator<Item> iterator() {
        Iterator<Item> iterator = new RqIterator();
        return iterator;
    }

    private Node copyNode() {
        if (isEmpty()) {
            return null;
        }
        Node copyFirst = first;
        Node copy = new Node();
        Node head = copy;
        while (copyFirst != null) {
            Node node = new Node();
            node.item = copyFirst.item;
            copy.next = node;
            copy = copy.next;
            copyFirst = copyFirst.next;
        }
        return head.next;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int random = StdRandom.uniform(size);
        Node sample = first;
        while (random-- > 0) {
            sample = sample.next;
        }
        return sample.item;
    }

    public static void main(String[] args) {
        System.out.println("running random");
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        //System.out.println(randomizedQueue.isEmpty());
        //randomizedQueue.enqueue(314);
        //System.out.println(randomizedQueue.size());
        randomizedQueue.sample();
    }
}

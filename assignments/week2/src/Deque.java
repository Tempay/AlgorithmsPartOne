import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Tempa on 12/9/2016.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    // return the number of items on the deque
    public int size() {
        return size;
    }
    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            first = node;
            last = node;
        }
        else {
            node.next = first;
            first.previous = node;
            first = node;
        }
        size++;
    }
    // add the item to the end
    public  void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node();
        node.item = item;
        if (last == null) {
            last = node;
            first = node;
        }
        else {
            node.previous = last;
            last.next = node;
            last = node;
        }
        size++;
    }
    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        Item res = first.item;
        if (size == 1) {
            first = null;
            last = null;
            size--;
            return res;
        }
        first = first.next;
        first.previous = null;
        size--;
        return res;
    }

    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        Item res = last.item;
        if (size == 1) {
            first = null;
            last = null;
            size--;
            return res;
        }
        last = last.previous;
        last.next = null;
        size--;
        return res;
    }

    @Override
    public Iterator<Item> iterator() {
        Iterator<Item> iterator = new Iterator<Item>() {
            Node current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Item next() {
                if (current == null) {
                    throw  new NoSuchElementException();
                }
                Item res = current.item;
                current = current.next;
                return res;
            }
        };
        return iterator;
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeFirst();
        System.out.print(deque.isEmpty());

    }
}

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
        if (first == null) {
            Node newFirst = new Node();
            newFirst.item = item;
            first = newFirst;
            last = newFirst;
            size = 1;
            return;
        }
        else {
            Node temp = first;
            Node newFirst = new Node();
            newFirst.item = item;
            first = newFirst;
            first.next = temp;
            size++;
            return;
        }
    }
    // add the item to the end
    public  void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (last == null) {
            Node newLast = new Node();
            newLast.item = item;
            first = newLast;
            last = newLast;
            size = 1;
            return;
        }
        else {
            Node newLast = new Node();
            newLast.item = item;
            last.next = newLast;
            last = newLast;
            size++;
            return;
        }

    }
    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        Item res = first.item;
        first = first.next;
        size--;
        return res;
    }

    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        Item res = last.item;
        last = null;
        return res;
    }

    @Override
    public Iterator<Item> iterator() {
        Iterator<Item> iterator = new Iterator<Item>() {
            Node current = first;
            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public Item next() {
                Item res = current.next.item;
                current = current.next;
                return res;
            }
        };
        return iterator;
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(5);
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());
        System.out.println(deque.removeLast());
    }
}

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first, last;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private boolean[] iterated = new boolean[size()];

        public boolean hasNext() {
            for (boolean isIterated : iterated) {
                if (!isIterated) {
                    return true;
                }
            }

            return false;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int randomN = StdRandom.uniform(size);

            while (iterated[randomN]) {
                randomN = StdRandom.uniform(size);
            }

            Node current = first;

            for (int n = 0; n < randomN; n++) {
                current = current.next;
            }

            iterated[randomN] = true;

            return current.item;
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (first == null);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.prev = oldLast;
            oldLast.next = last;
        }

        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int randomN = StdRandom.uniform(size);
        Node current = first;

        for (int n = 0; n < randomN; n++) {
            current = current.next;
        }

        if (first == last) {
            first = null;
            last = null;
        }
        else if (current == first) {
            first = current.next;
            first.prev = null;
        }
        else if (current == last) {
            last = current.prev;
            last.next = null;
        }
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;

        return current.item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int randomN = StdRandom.uniform(size);
        Node current = first;

        for (int n = 0; n < randomN; n++) {
            current = current.next;
        }

        return current.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        System.out.print("Is Empty? ");
        System.out.print(randomizedQueue.isEmpty());
        System.out.print("\n");

        for (int i = 1; i <= 10; i++) {
            randomizedQueue.enqueue(i);
        }

        System.out.print("Size of randomized queue is ");
        System.out.print(randomizedQueue.size());
        System.out.print("\n");

        System.out.print("Numbers in randomized queue\n");
        for (int i : randomizedQueue) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.print("\n");

        System.out.print("Numbers in randomized queue\n");
        for (int i : randomizedQueue) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.print("\n");

        System.out.print("Is Empty? ");
        System.out.print(randomizedQueue.isEmpty());
        System.out.print("\n");

        System.out.print("Deque random number ");
        for (int i = 1; i <= 3; i++) {
            System.out.print(randomizedQueue.dequeue());
            System.out.print(" ");
        }

        System.out.print("\n");

        System.out.print("Is Empty? ");
        System.out.print(randomizedQueue.isEmpty());
        System.out.print("\n");

        System.out.print("Numbers in randomized queue\n");
        for (int i : randomizedQueue) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.print("\n");

        System.out.print("Size of randomized queue is ");
        System.out.print(randomizedQueue.size());
        System.out.print("\n");

        System.out.print("Sample random number ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(randomizedQueue.sample());
            System.out.print(" ");
        }
    }
}

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first, last;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != last.next;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;

            return item;
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

        int N = StdRandom.uniform(size);
        Node current = first;

        for (int n = 0; n < N; n++) {
            current = current.next;
        }

        if (current == first) {
            first = current.next;
        }
        else if (current == last) {
            last = current.prev;
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
        int N = StdRandom.uniform(size);
        Node current = first;

        for (int n = 0; n < N; n++) {
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

        for(int i = 1; i <= 20; i++) {
            randomizedQueue.enqueue(i);
        }

        System.out.print("Size of randomized queue is ");
        System.out.print(randomizedQueue.size());
        System.out.print("\n");

        System.out.print("Numbers in randomized queue\n");
        for(int i : randomizedQueue) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.print("\n");

        System.out.print("Deque random number ");
        for(int i = 1; i <= 10; i++) {
            System.out.print(randomizedQueue.dequeue());
            System.out.print(" ");
        }

        System.out.print("\n");

        System.out.print("Numbers in randomized queue\n");
        for(int i : randomizedQueue) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.print("\n");

        System.out.print("Size of randomized queue is ");
        System.out.print(randomizedQueue.size());
        System.out.print("\n");

        System.out.print("Sample random number ");
        for(int i = 1; i <= 10; i++) {
            System.out.print(randomizedQueue.sample());
            System.out.print(" ");
        }
    }
}

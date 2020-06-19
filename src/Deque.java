import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size = 0;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    private class DequeIterator implements Iterator<Item> {
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

    // add item for the first time
    private void addFirstItem(Item item) {
        first = new Node();
        first.item = item;
        last = first;
    }


    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (first == null);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            addFirstItem(item);
        }
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.prev = first;
        }

        size++;
    }


    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            addFirstItem(item);
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

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.item;
        first = first.next;

        if (!isEmpty()) {
            first.prev = null;
        }

        size--;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = last.item;
        last = last.prev;

        if (!isEmpty()) {
            last.next = null;
        }

        size--;

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();

        String test = "I will be back";

        System.out.print("Is Empty? ");
        System.out.print(deque.isEmpty());
        System.out.print("\n");

        deque.addLast("be");
        deque.addLast("back");
        deque.addFirst("will");
        deque.addFirst("I");

        System.out.print("Is Empty? ");
        System.out.print(deque.isEmpty());
        System.out.print("\n");

        for (String word : deque) {
            System.out.print(word + " ");
        }

        System.out.print("\n");
        System.out.print(deque.size());
        System.out.print("\n");

        System.out.print(deque.removeFirst() + "\n");
        System.out.print(deque.removeLast() + "\n");

        System.out.print(deque.size());
        System.out.print("\n");

        System.out.print("Is Empty? ");
        System.out.print(deque.isEmpty());
        System.out.print("\n");

        System.out.print(deque.removeLast() + "\n");
        System.out.print(deque.removeFirst() + "\n");

        System.out.print(deque.size());
        System.out.print("\n");

        System.out.print("Is Empty? ");
        System.out.print(deque.isEmpty());
        System.out.print("\n");
    }
}

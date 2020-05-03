import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] itemArray;
    private int size;
    private int head;

    // construct an empty randomized queue
    public RandomizedQueue() {
        itemArray = (Item[]) new Object[2];
        size = 2;
        head = 0;
    }


    // is the randomized queue empty?
    public boolean isEmpty() {
        return head == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return head;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        if (head >= 0) System.arraycopy(itemArray, 0, temp, 0, head);
        itemArray = temp;
        size = capacity;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        itemArray[head++] = item;
        if (head == size) {
            resize(size + 1);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (head == 0)
            throw new NoSuchElementException();
        int random = StdRandom.uniform(head);
        Item temp = itemArray[random];
        itemArray[random] = itemArray[head - 1];
        itemArray[--head] = null;
        /*if (head < size / 4) {
            resize(size / 2);
        }*/
        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (head == 0)
            throw new NoSuchElementException();
        int random = StdRandom.uniform(head);
        return itemArray[random];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    private class ItemIterator implements Iterator<Item> {
        int i;

        ItemIterator() {
            i = 0;
        }

        @Override
        public boolean hasNext() {
            return head != i;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return itemArray[i++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        StdOut.println("iterator");
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext())
            StdOut.print(iterator.next());
        StdOut.println("\n dqueue");
        for (int i = 0; i < 10; i++) {
            StdOut.print(queue.dequeue());
        }

    }

}
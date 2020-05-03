import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item value;
        Node next;
        Node previous;
    }

    private Node head;
    private Node tail;
    private int size;

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if(item == null)
            throw new IllegalArgumentException();
        Node node = new Node();
        node.value = item;
        node.next = head;
        node.previous = null;
        if(head != null)
            head.previous = node;
        head = node;
        if (tail == null)
            tail = head;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if(item == null)
            throw new IllegalArgumentException();
        Node node = new Node();
        node.value = item;
        node.next = null;
        node.previous = tail;
        if(tail != null)
            tail.next = node;
        tail = node;
        if (head == null)
            head = tail;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(isEmpty())
            throw new NoSuchElementException();
        Node n = head;
        Item i = n.value;
        head = head.next;
        if (head == null)
            tail = null;
        size--;
        return i;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if(isEmpty())
            throw new NoSuchElementException();
        Item i = tail.value;
        if(tail.previous != null)
            tail.previous.next = null;
        tail = tail.previous;
        size--;
        if (tail == null)
            head = null;
        return i;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    private class ItemIterator implements Iterator<Item> {
        Node n;
        public ItemIterator(){
            n = head;
        }

        @Override
        public boolean hasNext() {
            return n != null;
        }

        @Override
        public Item next() {
            if(!hasNext())
                throw new NoSuchElementException();
            Item i = n.value;
            n = n.next;
            return i;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super Item> action) {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();
        for(int i = 0; i < 10; i++){
            dq.addFirst(i);
        }
        Iterator<Integer> it = dq.iterator();
        StdOut.println("iterator add first");
        while(it.hasNext())
            StdOut.print(it.next());
        StdOut.println("\n remove first:");
        for(int i = 0; i < 10; i++){
            StdOut.print(dq.removeFirst());
        }
        for(int i = 0; i < 10; i++){
            dq.addLast(i);
        }
        it = dq.iterator();
        StdOut.println("\n iterator add last");
        while(it.hasNext())
            StdOut.print(it.next());
        StdOut.println("\n remove last:");
        for(int i = 0; i < 10; i++){
            StdOut.print(dq.removeLast());
        }
    }

}
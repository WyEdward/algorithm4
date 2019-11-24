package cn.wyedward.algorithms.chapter1_3;


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item>{
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = head;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    private class Node{
        public Item item;
        public Node prev;
        public Node next;
    }
    public boolean isEmpty(){
        return head == null;
    }
    public int size(){
        return size;
    }
    public void pushLeft(Item item){
        Node node = new Node();
        node.item = item;
        node.prev = null;
        if(isEmpty()){
            head = tail = node;
            node.next = null;
        }else {
            head.prev = node;
            node.next = head;
            head = node;
        }
        size++;
    }
    public void pushRight(Item item){
        Node node = new Node();
        node.item = item;
        node.next = null;
        if(isEmpty()){
            head = tail = node;
            node.prev = null;
        }else{
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }
    public Item popLeft(){
        if (isEmpty()){
            return null;
        }else{
            Item e = head.item;
            if(size() == 1){
                head = tail = null;
            }else{
                head = head.next;
                head.prev.next = null;
                head.prev = null;
            }
            size--;
            return e;
        }
    }
    public Item popRight(){
        if(isEmpty()){
            return null;
        }else{
            Item item = tail.item;
            if(size() == 1){
                head = tail = null;
            }else{
                tail = tail.prev;
                tail.next.prev = null;
                tail.next = null;
            }
            size--;
            return item;
        }

    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.pushLeft(1);
        deque.pushLeft(2);
        deque.pushLeft(3); //321456
        deque.pushRight(4);
        deque.pushRight(5);
        deque.pushRight(6);
        for (int i : deque) {
            StdOut.print(i+" ");
        }
        StdOut.print(deque.size());
        StdOut.println();
        StdOut.print(deque.popLeft()+" ");
        StdOut.print(deque.popRight()+" ");
        for (int i : deque) {
            StdOut.print(i+" ");
        }
        StdOut.print(deque.size());
    }
}


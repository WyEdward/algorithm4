package cn.wyedward.algorithms.chapter1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stacktwo<Item> implements Iterable<Item>{
    private Node first;
    private int N;
    private int operates;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements  Iterator<Item>{
        private Node current = first;
        private int count = operates;
        @Override
        public boolean hasNext() {
            if(count != operates){
                throw new ConcurrentModificationException();
            }
            return current != null;
        }

        @Override
        public Item next() {
            if(count != operates){
                throw new ConcurrentModificationException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    private class Node{
        Item item;
        Node next;

        Node(){};
        Node(Node x){
            item = x.item;
            if(x.next != null){
                next = new Node(x.next);
            }
            N++;
        }
    }
    public Stacktwo(){
        first = null;
        N = 0;
    }
    public Stacktwo(Stacktwo<Item> s){
        first = new Node(s.first);
    }
    public boolean isEmpty(){
        return first == null;
    }
    public int size(){
        return N;
    }
    public void push(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
        operates++;
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        operates++;
        return item;
    }
    public Item peek(){
        if(isEmpty()){
            throw new NoSuchElementException("stack underflow");
        }
        return first.item;
    }

    /**
     * 1.3.47
     * 可连接的队列、栈或steque
     * @param stack
     */
    public void catenation(Stacktwo<Item> stack){
        if(stack.first != null){
            Stacktwo<Item> temp = new Stacktwo<>(stack);
            Node last = temp.first;
            while(last.next != null){
                last = last.next;
            }
            last.next = first;
            first = temp.first;
        }
    }

    public static void main(String[] args) {
        Stacktwo<String> s = new Stacktwo<>();
        Stacktwo<String> s2 = new Stacktwo<>();
        s.push("1");
        s.push("2");
        s2.push("3");
        s2.push("4");
        s.catenation(s2);
        for (String s1 : s) {
            System.out.println(s1);
        }
       /* while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")){
                s.push(item);
            }else if(!s.isEmpty()){
                StdOut.print(s.pop() + " ");
            }
        }*/
       /* StdOut.println("(" + s.size() + " left on stack)");*/
    }
}

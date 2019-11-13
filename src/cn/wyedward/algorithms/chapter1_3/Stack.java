package cn.wyedward.algorithms.chapter1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{

    private Node first;//栈顶
    private int N; //元素数量

    private class Node{
        Item item;
        Node next;
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
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item =  current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<String>();
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")){
                s.push(item);
            }else if (!s.isEmpty()) {
                StdOut.print(s.pop()+" ");
            }
        }
        StdOut.println("("+s.size()+" left on stack)");
        for (String s1 : s) {
            StdOut.print(s1+" ");
        }
    }
}

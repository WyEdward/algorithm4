package cn.wyedward.algorithms.chapter1_3;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item>{
    private Node first; //指向最早添加的结点的链接
    private Node last; //指向最近添加的结点的链接
    private int N; //队列中的元素数量

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
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
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

    /**
     * 入队列
     * @param item
     */
    public void enqueue(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else  oldlast.next = last;
        N++;
    }

    /**
     *
     * 出队列
     * @return
     */
    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        N--;
        return item;
    }

    /**
     * 打印第i个元素
     * @param args
     */
    public Item getnums(int num){
        if(num > N || num <= 0){
            throw new NoSuchElementException();
        }
        Node i = null;
        for(i = first; i != null; i = i.next){
            if(--num == 0){
                break;
            }
        }
        return i.item;
    }

    public static void main(String[] args) {
        Queue<String> q = new Queue<String>();
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")){
                q.enqueue(item);
            }else if(!q.isEmpty()){
                StdOut.print(q.dequeue()+" ");
            }
        }
        StdOut.println("("+q.size()+" left on queue)");
        for (String q1 : q) {
            StdOut.print(q1+" ");
        }
    }
}

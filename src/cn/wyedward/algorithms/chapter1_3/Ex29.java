package cn.wyedward.algorithms.chapter1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 用环形链表实现Queue
 * 环形链表也是一条链表，只是没有任何结点的链接为空，
 * 且只要链表非空则last.next的值为first。只能使用一个Node类型的实例变量(last)
 * * @param <Item>
 */
public class Ex29<Item> implements Iterable<Item>{
    private int N;
    private Node last;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private int n = N;
        private Node current = last;
        @Override
        public boolean hasNext() {
            return n > 0;
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            Item item = current.next.item;
            current = current.next;
            n--;
            return item;
        }

        @Override
        public void remove() {
            throw  new UnsupportedOperationException();
        }
    }

    private class Node{
        private Item item;
        private Node next;
    }

    /**
     * 创建空的队列
     */
    public Ex29(){
        last = null;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return last == null;
    }
    /**
     * 返回列表元素个数
     */
    public int size(){
        return N;
    }
    /**
     * 返回最近添加的队列元素
     * 如果为空抛出异常
     */
    public Item peek(){
        if(isEmpty()) throw new RuntimeException("Queue underflow");
        return last.next.item;
    }
    /**
     * 添加元素进列表
     */
    public void enqueue(Item item){
        Node x = new Node();
        x.item = item;
        if (isEmpty()){
            x.next = x;
        }else{
            x.next = last.next;
            last.next = x;
        }
        last = x;
        N++;
    }
    /**
     * 移除并返回队列中最先添加的元素 队列的特性(先进先出)
     * 如果为空抛出异常
     */
    public Item dequeue(){
        if(isEmpty()) throw new RuntimeException("Queue underflow");
        Item item = last.next.item;
        if(last.next == last)
            last = null;
        else
            last.next = last.next.next;
        N--;
        return item;
    }
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    public static void main(String[] args) {
        Ex29<String> q = new Ex29<String>();
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")) q.enqueue(item);
            else if(!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue: [ " + q + "])");
    }
}

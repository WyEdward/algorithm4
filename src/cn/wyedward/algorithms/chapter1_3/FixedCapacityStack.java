package cn.wyedward.algorithms.chapter1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class FixedCapacityStack<Item> implements Iterable<Item>{
    private Item[] a; // stack entries
    private int N; // size
    public FixedCapacityStack(int cap){
        a = (Item[]) new Object[cap];
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        return N == 0;
    }

    /**
     * 数组长度
     * @return
     */
    public int size(){

        return N;
    }

    /**
     * 推元素入栈
     * @param item
     */
    public void push(Item item){
        if(N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    /**
     * 出栈
     * @return
     */
    public Item pop(){
        Item item = a[--N];
        a[N] = null; //防止对象游离
        if(N > 0 && N == a.length/4) resize(a.length/2); //防止数组太大
        return item;
    }

    /**
     * 将大小为N <= max 的栈移动到一个新的大小的max的数组中
     * @param max
     */
    private void resize(int max){
        Item[] temp = (Item[]) new Object[max];
        for(int i = 0; i < N; i++){
            temp[i] = a[i];
        }
        a = temp;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }


    public static void main(String[] args) {
        FixedCapacityStack<String> s = new FixedCapacityStack<String>(100);
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")){
                s.push(item);
            }else if (!s.isEmpty()) {
               StdOut.print(s.pop()+" ");
            }
        }
        StdOut.println("(" + s.size() + "left on stack");
    }


}


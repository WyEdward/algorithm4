package cn.wyedward.algorithms.chapter1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item>{
    private Item[] a = (Item[]) new Object[1];//栈元素
    private int N = 0; //元素数量
    public boolean isEmpty(){
        return N == 0;
    }
    public int Size(){
        return N;
    }

    /**
     * 将栈移动到一个大小为max的数组
     * @param max
     */
    private void resize(int max){
        Item[] temp = (Item[])new Object[max];
        for(int i = 0; i < N; i++){
            temp[i] = a[i];
        }
        a = temp;
    }

    /**
     * 将元素添加到栈顶
     * @param item
     */
    public void push(Item item){
        if(N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    /**
     * 从栈顶删除元素
     * @return
     */
    public Item pop(){
        Item item = a[--N];
        a[N] = null; //避免对象游离
        if(N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * 支持先进后出的迭代
     */
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

        @Override
        public void remove() {

        }
    }
    public static void main(String[] args) {
        FixedCapacityStackOfStrings s = new FixedCapacityStackOfStrings(100);
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

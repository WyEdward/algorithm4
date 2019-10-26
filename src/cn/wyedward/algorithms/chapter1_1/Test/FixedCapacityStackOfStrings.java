package cn.wyedward.algorithms.chapter1_1.Test;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStrings {

    private String[] stack;
    private int N;

    FixedCapacityStackOfStrings(int cap){
        stack = new String[cap];
    }

    public String pop(){
        return stack[--N];
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }
    public void push(String s){
        stack[N++] = s;
    }
    public static void main(String[] args){
        FixedCapacityStackOfStrings s = new FixedCapacityStackOfStrings(100);
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")){
                s.push(item);
            }else if(!s.isEmpty()){
                StdOut.print(s.pop() + " ");
            }
        }
        StdOut.println("("+s.size()+" left"+")");
    }

}


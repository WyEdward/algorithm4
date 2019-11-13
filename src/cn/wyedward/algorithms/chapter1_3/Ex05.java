package cn.wyedward.algorithms.chapter1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex05 {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        Stack<Integer> stack = new Stack<>();
        while(N > 0){
            stack.push(N % 2);
            N = N / 2;
        }
        for (Integer d : stack) {
            StdOut.print(d);
        }
        StdOut.println();
    }
}

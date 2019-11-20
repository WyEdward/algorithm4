package cn.wyedward.algorithms.chapter1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex15 {
    public static void main(String[] args) {
        Queue<String> q = new Queue<String>();
        int num = StdIn.readInt();
        while(!StdIn.isEmpty()){
            String s = StdIn.readString();
            q.enqueue(s);
        }
        int length = q.size();
        StdOut.println("第key个元素是:"+q.getnums(num));
        StdOut.println("倒数第key个元素是:"+q.getnums(length - num + 1));
    }
}

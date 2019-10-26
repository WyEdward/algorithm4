package cn.wyedward.algorithms.chapter1_1;

import edu.princeton.cs.algs4.StdIn;

public class Ex09 {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        String s = "";
        for(int n = N;n > 0; n /= 2)
            s = (n % 2) + s;
        System.out.println(s);


        //java库中现有的方法
        String s1 = Integer.toBinaryString(N);
        System.out.println(s1);
    }
}

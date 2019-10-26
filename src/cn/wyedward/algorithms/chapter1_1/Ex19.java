package cn.wyedward.algorithms.chapter1_1;

import edu.princeton.cs.algs4.StdIn;

public class Ex19 {
    public static long[] F(int N){
        long[] fibonacci = new long[N+1];
        if(N == 0)
            return fibonacci;
        fibonacci[1] = 1;
        if(N == 1)
            return fibonacci;
        for(int i = 2; i <= N; i++){
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
        }
        return fibonacci;
    }
    public static void main(String[] args) {
        int N = StdIn.readInt();
        long[] f = F(N);
        for(int i = 0; i<f.length; i++){
            System.out.println(f[i]);
        }
    }
}

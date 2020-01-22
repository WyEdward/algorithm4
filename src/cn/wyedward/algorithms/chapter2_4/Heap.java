package cn.wyedward.algorithms.chapter2_4;

import edu.princeton.cs.algs4.StdOut;

public class Heap {
    public static void sort(Comparable[] a){
        int n = a.length;
        for(int k = n/2; k >= 1; k--)
            sink(a, k, n);
        while(n > 1){
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private static void sink(Comparable[] pq, int k, int n){
        while(2 * k <= n){
            int j = 2 * k;
            if(j < n && less(pq, j, j+1)) j++;
            if(!less(pq, k, j)){
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }
    private static boolean less(Comparable[] pq, int i, int j){
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j){
        Object temp = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = temp;
    }

    private static void show(Comparable[] pq){
        for(int i = 0; i < pq.length; i++){
            StdOut.print(pq[i]);
        }
    }
    public static void main(String[] args) {
        String[] a = {"S","O","R","T","E","X","A","M","P","L","E"};
        sort(a);
        show(a);
    }
}

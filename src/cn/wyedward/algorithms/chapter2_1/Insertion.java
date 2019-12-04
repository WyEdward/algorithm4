package cn.wyedward.algorithms.chapter2_1;

import edu.princeton.cs.algs4.StdOut;

public class Insertion {
    public static void sort(Comparable[] a){
        //将a[]按升序排列
        int N = a.length;
        for(int i = 1; i < N; i++){
            //将a[i]插入到a[i-1]、a[i-2]、a[i-3]...之中
            for(int j = i; j > 0 && less(a[j],a[j-1]); j--){
                exch(a, j, j-1);
            }
        }
    }
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static boolean isSorted(Comparable[] a){
        int N = a.length;
        for(int i = 1; i < a.length; i++){
            if(less(a[i],a[i-1])) return false;
        }
        return true;
    }
    private static void show(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
    public static void main(String[] args) {
        String[] a = {"S","O","R","T","E","X","A","M","P","L","E"};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

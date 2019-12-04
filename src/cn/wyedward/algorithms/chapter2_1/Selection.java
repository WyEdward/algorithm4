package cn.wyedward.algorithms.chapter2_1;

import edu.princeton.cs.algs4.StdOut;

public class Selection {
    public static void sort(Comparable[] a){
        //将a[]按升序排列
        int N = a.length; //数组长度
        for(int i = 0; i < N; i++){
            int min = i;
            for(int j = i+1; j < N; j++){
                if(less(a[j],a[min])) min = j;
            }
            exch(a, i , min);
        }
    }
    private static boolean less(Comparable v, Comparable m){
        return v.compareTo(m) < 0;
    }
    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i++){
            if(less(a[i],a[i-1])) return false;
        }
        return true;
    }
    private static void show(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            StdOut.print(a[i]+" ");
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

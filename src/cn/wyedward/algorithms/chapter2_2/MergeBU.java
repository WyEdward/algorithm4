package cn.wyedward.algorithms.chapter2_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 自底向上的归并排序
 */
public class MergeBU {
    private static Comparable[] aux; //归并所需的辅助数组

    public static void sort(Comparable[] a){
        //进行lgN次两两归并
        int N = a.length;
        aux = new Comparable[a.length];
        for(int sz = 1; sz < N; sz = sz + sz)       //sz子数组大小
            for(int lo = 0; lo < N - sz; lo += sz+sz) //lo子数组索引
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi){
        //将a[lo..mid]和a[mid+1..hi]归并
        int i = lo, j = mid+1;
        for(int k = lo; k <= hi; k++){ //将a[lo..hi]复制到aux[lo..hi]
            aux[k] = a[k];
        }
        for(int k = lo; k <= hi; k++){    //归并回到a[lo..hi]
            if      (i > mid)          a[k] = aux[j++];
            else if (j > hi)           a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                       a[k] = aux[i++];
        }
    }

    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i++){
            if(less(a[i],a[i-1])) return false;
        }
        return true;
    }

    public static void show(Comparable[] a){
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

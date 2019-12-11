package cn.wyedward.algorithms.chapter2_2;

import edu.princeton.cs.algs4.StdOut;

public class Ex10 {
    private static Comparable[] aux;
    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo, j = hi;
        for(int k = lo; k <= mid; k++){
            aux[k] = a[k];
        }
        for(int k = mid + 1; k <= hi; k++){
            aux[k] = a[hi + mid + 1 - k];
        }
        for(int k = lo; k <= hi; k++){
            if(less(aux[i], aux[j])){
                a[k] = aux[i++];
            }else{
                a[k] = aux[j--];
            }
        }
    }
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i++){
            if(less(a[i], a[i-1])){
                return false;
            }
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

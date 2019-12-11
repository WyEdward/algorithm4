package cn.wyedward.algorithms.chapter2_2;

import edu.princeton.cs.algs4.StdOut;

public class Ex09 {
    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a,0,a.length-1,aux);
    }

    private static void sort(Comparable[] a, int lo, int hi, Comparable[] aux){
        if(hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, aux);
        sort(a, mid+1, hi, aux);
        merge(a, lo, mid, hi, aux);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux){
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }
        for(int k = lo; k <= hi; k++){
            if (i > mid)                   {a[k] = aux[j++];}
            else if (j > hi)               {a[k] = aux[i++];}
            else if (less(aux[j],aux[i]))  {a[k] = aux[j++];}
            else                           {a[k] = aux[i++];}
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

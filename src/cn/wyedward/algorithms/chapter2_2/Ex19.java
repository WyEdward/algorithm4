package cn.wyedward.algorithms.chapter2_2;

import edu.princeton.cs.algs4.StdOut;

public class Ex19 {
    private static Comparable[] aux;
    private static int merge(Comparable[] a, int lo, int mid, int hi, int inversions){
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }
        for(int k = lo; k <= hi; k++){
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(less(aux[j], aux[i])) {
                a[k] = aux[j++];
                inversions += j - k - 1;
            }
            else a[k] = aux[i++];
        }
        return inversions;
    }
    public static int count(Comparable[] a){
        aux = new Comparable[a.length];
        return count(a, 0, a.length - 1, 0);
    }
    private static int count(Comparable[] a, int lo, int hi, int inversions){
        if(hi <= lo){
            return inversions;
        }
        int mid = lo + (hi - lo) / 2;
        inversions = count(a, lo, mid, inversions);
        inversions = count(a, mid + 1,hi,inversions);
        return merge(a, lo, mid, hi, inversions);
    }
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public static boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i++){
            if(less(a[i], a[i-1])){
                return false;
            }
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
        int count = count(a);
        System.out.println(count);
        assert isSorted(a);
        show(a);
    }
}

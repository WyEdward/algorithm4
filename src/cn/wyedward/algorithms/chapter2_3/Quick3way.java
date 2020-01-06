package cn.wyedward.algorithms.chapter2_3;

import edu.princeton.cs.algs4.StdOut;

public class Quick3way {
    public static void sort(Comparable[] a){
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return;
        int lt = lo, i = lo+1, gt = hi;
        Comparable v = a[lo];
        while(i <= gt){
            int cmp = a[i].compareTo(v);
            if (cmp < 0){
                exch(a, lt++, i++);
            }else if (cmp > 0){
                exch(a, i, gt--);
            }else{
                i++;
            }
        }//a[lo .. lt-1] < v=a[lt .. gt] < a[gt+1 .. hi]
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable v = a[i];
        a[i] = a[j];
        a[j] = v;
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

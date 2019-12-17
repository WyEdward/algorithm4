package cn.wyedward.algorithms.chapter2_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 改进三项
 * 1、通过把辅助参数传参 可以一个对象排序多个数组
 * 2、在递归中检验是否有序
 * 3、对小数组的排序 用插入排序加快排序速度
 */
public class Ex11 {
    private static int CUTOFF = 15;
    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        //Comparable[] aux = a.clone();
        sort(a, aux, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if(hi - lo < CUTOFF){
            insertionSort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if(less(a[mid + 1], a[mid]))  //在递归中检验是否有序
        merge(a, aux, lo, mid, hi);
    }
    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }
        for(int k = lo; k <= hi; k++){
            if(i > mid) {a[k] = aux[j++];}
            else if (j > hi) {a[k] = aux[i++];}
            else if (less(aux[i], aux[j])) {a[k] = aux[i++];}
            else {a[k] = aux[j++];}
        }
    }

    public static void insertionSort(Comparable[] a, int lo, int hi){
        for(int i = lo; i <= hi; i++){
            for(int j = i; j > lo && less(a[j], a[j-1]); j--){
                exch(a, j, j - 1);
            }
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

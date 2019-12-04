package cn.wyedward.algorithms.chapter2_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 排序算法类的模板
 */
public class Example {
    public static void sort(Comparable[] a){}
    /**
     * 比较v 和 w
     * 如果v < w 返回true
     * 其他 返回false
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    /**
     * 交换数组中的两个值
     * @param a
     * @param i
     * @param j
     */
    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 打印数组元素
     * @param a
     */
    private static void show(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    /**
     * 确认排序后数组元素都是有序的
      * @param a
     * @return
     */
    private static boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i++){
            if(less(a[i],a[i-1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a); //确认排序后数组元素都是有序的
        show(a);
    }
}

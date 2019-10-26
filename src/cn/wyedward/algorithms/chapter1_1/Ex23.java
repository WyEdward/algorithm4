package cn.wyedward.algorithms.chapter1_1;

import edu.princeton.cs.algs4.StdIn;

public class Ex23 {
    public static int rank(int key, int[] a){
        return rank(key, a, 0, a.length - 1);
    }
    public static int rank(int key, int[] a, int lo, int hi){
        if(lo > hi){
            return -1;
        }
        int mid = lo + (hi - lo)/2;
        if(key < a[mid]) {
            return rank(key, a, lo, mid-1);
        }else if(key > a[mid]){
            return rank(key, a, mid + 1, hi);
        }else{
            return mid;
        }
    }
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8,9,10};
        char arg = StdIn.readChar();
        StdIn.readLine();//读取该行的其余内容
        String[] keys = StdIn.readLine().split(" ");
        for (String key : keys) {
            if(arg == '+' && rank(Integer.parseInt(key), a) == -1){
                System.out.println(key);
            }else if(arg == '-' && rank(Integer.parseInt(key), a) > -1){
                System.out.println(key);
            }
        }
    }
}

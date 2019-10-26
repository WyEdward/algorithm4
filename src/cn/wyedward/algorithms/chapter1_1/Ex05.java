package cn.wyedward.algorithms.chapter1_1;

import edu.princeton.cs.algs4.StdIn;

public class Ex05 {
    public static void main(String[] args) {
        double a = StdIn.readDouble();
        double b = StdIn.readDouble();
        if(a>0 && a<1 && b>0 && b<1){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}

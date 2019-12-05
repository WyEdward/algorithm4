package cn.wyedward.algorithms.chapter2_1;

import edu.princeton.cs.algs4.StdIn;

public class Ex18 {
    public static void main(String[] args) {
        String[] a = {"S","O","R","T","E","X","A","M","P","L","E"};
        String sort = StdIn.readString();
        if(sort.equals("selection")) {
            Selection.drawSort(a);
        } else if (sort.equals("insertion")){
            Insertion.drawSort(a);
        }
    }
}

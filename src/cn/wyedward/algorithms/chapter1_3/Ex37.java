package cn.wyedward.algorithms.chapter1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex37 {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int m = StdIn.readInt();
        Queue<Integer> q = new Queue<>();
        for(int i = 0; i < n; i++){
            q.enqueue(new Integer(i));
        }
        int k = 0;
        while(!q.isEmpty()){
            int x = q.dequeue();
            if(++k % m == 0){
                StdOut.print(x + " ");
            }else{
                q.enqueue(x);
            }
        }
        StdOut.println();
    }
}

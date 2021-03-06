package cn.wyedward.algorithms.chapter2_4;

import edu.princeton.cs.algs4.*;

/**
 * 优先队列的用例
 */
public class TopM {
    public static void main(String[] args) {
        //打印输入流中最大的M行
        int M = StdIn.readInt();
        MinPQ<Transaction> pq = new MinPQ<>(M + 1);
        while(StdIn.hasNextLine()){
            pq.insert(new Transaction(StdIn.readLine()));
            if(pq.size() > M)
                pq.delMin(); //如果优先队列中存在M+1个元素删除其中最小的元素
        } //最大的M个有元素全部都在优先队列中
        Stack<Transaction> stack = new Stack<>();
        while(!pq.isEmpty()){
            stack.push(pq.delMin());
        }
        for (Transaction t : stack) {
            StdOut.println(t);
        }
    }
}

package cn.wyedward.algorithms.chapter2_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 * 使用优先队列的多向归并
 */
public class Multiway {
    public static void merge(In[] streams){
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<>(N);
        for(int i = 0; i < N; i++){
            if(!streams[i].isEmpty()){
                pq.insert(i, streams[i].readString());
            }
        }
        while(!pq.isEmpty()){
            //StdOut.println(pq.min());
            int i = pq.delMin();
            if(!streams[i].isEmpty()){
                pq.insert(i,streams[i].readString());
            }
        }
    }

    public static void main(String[] args) {
        int N = args.length;
        In[] streams = new In[N];
        for(int i = 0; i < N; i++){
            streams[i] = new In(args[i]);
        }
        merge(streams);
    }
}

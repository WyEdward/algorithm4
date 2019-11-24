package cn.wyedward.algorithms.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
    private int[] id;  //父链接数组 (由触点索引)
    private int[] sz;  //(由触点索引的)各个根节点所对应的分量的大小
    private int count; //连通分量的数量
    public WeightedQuickUnionUF(int N){
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
        }
        sz = new int[N];
        for(int i = 0; i < N; i++){
            sz[i] = 1;
        }
    }

    /**
     * 返回连通分量的个数
     * @return
     */
    public int count(){
        return count;
    }

    /**
     * 判断两个触点是不是在同一个连通分量里
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    /**
     * 返回触点p所在连通分量的根节点
     * @param p
     * @return
     */
    public int find(int p){
        while(p != id[p]) p = id[p];
        return p;
    }

    /**
     * 链接两个触点
     * 分别找到两个触点所在连通分量的根节点
     * 根据每棵树的节点大小比 将小树指向大树
     * @param p
     * @param q
     */
    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if(i == j) return;
        if(sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }else{
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }
    public static void main(String[] args) {
        int N = StdIn.readInt();
        Quickunion uf = new Quickunion(N);
        while(!StdIn.isEmpty()){
            int p =StdIn.readInt();
            int q = StdIn.readInt();
            if(uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + "components");
    }
}

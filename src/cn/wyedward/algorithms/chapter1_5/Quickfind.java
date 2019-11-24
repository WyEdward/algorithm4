package cn.wyedward.algorithms.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Quickfind {
    private int[] id; //分量id(以触点作为索引)
    private int count; //分量数量

    /**
     * 以整数标识(0到N-1) 初始化N个触点
     * @param N
     */
    public Quickfind(int N){
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    /**
     * 返回连通分量的数量
     * @return
     */
    public int count(){
        return count;
    }

    /**
     * 判断p和q是否存在一个分量中
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    /**
     * 找p触点所在分量的标识符
     * @param p
     * @return
     */
    public int find(int p){
        return id[p];
    }

    /**
     * 在p和q之间添加一条连接
     * @param p
     * @param q
     */
    public void union(int p, int q){
        int pID = find(p);
        int qID = find(q);
        if(pID == qID) return;
        for(int i = 0; i < id.length; i++){
            if(id[i] == pID) id[i] = qID;
        }
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Quickfind uf = new Quickfind(N);
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

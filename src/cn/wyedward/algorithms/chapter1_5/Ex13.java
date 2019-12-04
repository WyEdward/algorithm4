package cn.wyedward.algorithms.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 使用路径压缩的加权quick-union算法
 * 1.5.13
 */
public class Ex13 {
    private int[] id; //父链接
    private int[] sz; //树的大小
    private int count; //分量的个数
    public Ex13(int N){
        count = N;
        id = new int[N];
        sz = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }
    public int count(){
        return count;
    }
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    public int find(int p){
        int root = p;
        while(root != id[root]){
            root = id[root];
        }
        while(id[p] != root){
            int temp = p;
            p = id[p];
            id[temp] = root;
        }
        return root;
    }
    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) return;
        if(sz[q] < sz[p]){
            id[rootQ] = rootP;
            sz[p] += sz[q];
        }else{
            id[rootP] = rootQ;
            sz[q] += sz[p];
        }
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Ex13 uf = new Ex13(N);
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(uf.connected(p, q)){
                continue;
            }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + "components");
    }
}

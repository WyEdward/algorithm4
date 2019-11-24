package cn.wyedward.algorithms.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 小知识:
 * 字节数组和整形数组的区别
 * 1、byte数组每个元素只有一个字节，而整形数组每个元素约4个字节
 * 2、byte类型和整形表示的整数范围是不一样的 byte里的最大数字为127
 */
public class UF {
    private int[] parent; //父链接
    private byte[] rank; //树的高度  最大值不能超过31
    private int count;  //分量的个数
    public UF(int n){
        if(n < 0) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }
    public int find(int p){
        validate(p);
        while(p != parent[p]){
            parent[p] = parent[parent[p]]; //路径压缩减半
            p = parent[p];
        }
        return p;
    }
    public int count(){
        return count;
    }
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) return;
        if(rank[rootP] < rank[rootQ]){
            parent[rootP] = rootQ;
        }else if(rank[rootP] > rank[rootQ]){
            parent[rootQ] = rootP;
        }else{
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }

    /**
     * 验证p是否是一个有效的索引
     * @param p
     */
    public void validate(int p){
        int n = parent.length;
        if(p < 0 || p >= n){
            throw new IllegalArgumentException("index " + p + " is not between 0 and "+ (n-1));
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        UF uf = new UF(n);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}

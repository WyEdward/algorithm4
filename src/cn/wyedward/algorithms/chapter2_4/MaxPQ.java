package cn.wyedward.algorithms.chapter2_4;

/**
 * 基于堆的优先队列
 * @param <Key>
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;  //基于堆的完全二叉树
    private int N = 0; //存储在pq[1..N]中 ,pq[0]没有使用
    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN + 1];
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }
    public Key delMax(){
        Key max = pq[1];  //从根结点得到最大元素
        exch(1, N--);     //将其和最后一个结点交换
        pq[N+1] = null;   //防止对象游离
        sink(1);          //恢复堆的有序性
        return max;
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    /**
     * 维持堆的有序性 上浮
     * @param k
     */
    private void swim(int k){
        while(k > 1 && less(k/2, k)){ //如果该节点大于它的父结点
            exch(k/2, k);             //交换该节点和它的父结点
            k = k/2;                    //然后k指向交换后的结点 再判断
        }
    }

    /**
     * 维持堆的有序性 下沉
     * @param k
     */
    private void sink(int k){
        while(2*k <= N){
            int j = 2*k;
            if(j < N && less(j, j+1)){
                j++;
            }
            if(!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
}

package cn.wyedward.algorithms.chapter4_2;

import cn.wyedward.algorithms.chapter1_3.Stack;

/**
 * 有向环检测
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle; //有向环的所有顶点 (如果存在)
    private boolean[] onStack;      //递归调用的栈上的所有顶点
    public DirectedCycle(Digraph G){     //数据结构默认不赋值 就为null了
        onStack = new boolean[G.V()];     //初始化的时候 不new的话 为null
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++){   //一个图 可能是非连通的 ，但是它包含多个连通图
            if(!marked[v]){
                dfs(G, v);
            }
        }
    }
    private void dfs(Digraph G, int v){
        onStack[v] = true;
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if (this.hasCycle()) return;
            else if(!marked[w]){
                edgeTo[w] = v;
                dfs(G, w);
            }else if(onStack[w]){
                cycle = new Stack<Integer>();
                for(int x = v; x != w; x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false; //调用完后  就恢复原始状态 ，因为不在递归调用栈里面了  为了下一个连通图的遍历
    }

    public boolean hasCycle(){
        return cycle != null;
    }
    public Iterable<Integer> cycle(){
        return cycle;
    }

}

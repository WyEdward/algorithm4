package cn.wyedward.algorithms.chapter3_2;

import cn.wyedward.algorithms.chapter1_3.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root; //二叉查找树的根结点

    private class Node{
        private Key key;   //键
        private Value val; //值
        private Node left, right; //指向子树的链接
        private int N; //以该节点为根的子树的结点总数
        public Node(Key key, Value val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size(){
        return size(root);
    }
    private int size(Node x){
        if(x == null) return 0;
        else          return x.N;
    }

    public Value get(Key key){
        return get(root, key);
    }
    private Value get(Node x, Key key){
        //以x为根节点的子树中查找并返回key所对应的值;
        //如果找不到则返回null
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val){
        //查找key,找到则更新它的值，否则为它创建一个新的结点
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val){
        //如果key存在于x的根节点的子树中则更新它的值；
        //否则将以key和val为键值对的新结点插入到该子树中
        if(x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node x){
        if(x.left == null) return x;
        return min(x.left);
    }

    public Key max(){
        return max(root).key;
    }
    private Node max(Node x){
        if(x.right == null) return x;
        return max(x.right);
    }
    public Key floor(Key key){
        Node x = floor(root, key);
        if(x == null) return null;
        return x.key;
    }
    private Node floor(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        if(cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if(t != null) return t;
        else return x;
    }
    public Key ceiling(Key key){
        Node x = ceiling(root, key);
        if(x == null) return null;
        return x.key;
    }
    private Node ceiling(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        if(cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if(t != null){
            return t;
        }else{
            return x;
        }
    }

    public Key select(int k){
        return select(root, k).key;
    }
    private Node select(Node x, int k){
        if(x == null) return null;
        int t = size(x.left);
        if(t > k){
            return select(x.left, k);
        }else if(t < k){
            return select(x.right, k - t - 1);
        }else{
            return x;
        }
    }

    public int rank(Key key){
        return rank(key, root);
    }
    private int rank(Key key, Node x){
        //返回以x为根结点的子树中小于x.key的键的数量
        if(x == null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return rank(key, x.left);
        else if(cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node x){
        if(x.left == null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void deleteMax(){
        root = deleteMax(root);
    }
    private Node deleteMax(Node x){
        if(x.right == null){
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void delete(Key key){
        root = delete(root, key);
    }
    private Node delete(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) {
            x.left = delete(x.left, key);
        }else if(cmp > 0){
            x.right = delete(x.right, key);
        }else{
            if(x.right == null){
                return x.left;
            }
            if(x.left == null){
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
        if(x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0) keys(x.left, queue, lo, hi);
        if(cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if(cmphi > 0) keys(x.right, queue, lo, hi);
    }

    //Ex3.2.32
    private boolean isBST(){
        return isBST(root, null, null);
    }
    private boolean isBST(Node x, Key min, Key max){
        if(x == null) return true;
        if(min != null && x.key.compareTo(min) <= 0){
            return false;
        }
        if(max != null && x.key.compareTo(max) >= 0 ){
            return false;
        }
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }


    //Ex3.2.33
    public boolean isRankConsistent(){
        for(int i = 0; i < size(); i++){
            if(i != rank(select(i))){
                return false;
            }
        }
        for (Key key : keys()) {
            if(!key.equals(select(rank(key)))){
                return false;
            }
        }
        return true;
    }


}

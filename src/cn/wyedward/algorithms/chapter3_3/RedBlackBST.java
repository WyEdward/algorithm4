package cn.wyedward.algorithms.chapter3_3;

import cn.wyedward.algorithms.chapter1_3.Queue;

/**
 * 基于红黑二叉查找树的符号表
 * @param <Key>
 * @param <Value>
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        private Key key;  //键
        private Value val;  //相关联的值
        private Node left, right; //左右子树
        private int N;    //这颗子树中的结点总数
        private boolean color;    //由其父结点指向它的链接的颜色

        public Node(Key key, Value val, int N, boolean color){
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    /**
     * 判断由其父结点指向它的链接的颜色是不是为红色
     * @param x
     * @return
     */
    private boolean isRed(Node x){
        if(x == null){
            return false;
        }
        return x.color == RED;
    }

    /**
     * 左旋转
     * @param h
     * @return
     */
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 右旋转
     * @param h
     * @return
     */
    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 颜色变换
     * @param h
     */
    private void flipColors(Node h){
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    /**
     * 测试树的高度
     * @return
     */
    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x == null){
            return 0;
        }else{
            return x.N;
        }
    }

    /**
     * 在树中插入元素
     * @param key
     * @param val
     */
    public void put(Key key, Value val){
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val){
        if(h == null){
            return new Node(key, val, 1, RED);
        }
        int cmp = key.compareTo(h.key);
        if(cmp < 0){
            h.left = put(h.left, key, val);
        }else if(cmp > 0){
            h.right = put(h.right, key, val);
        }else {
            h.val = val;
        }

        if(isRed(h.right) && !isRed(h.left)){
            h = rotateLeft(h);
        }
        if(isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h);
        }
        if(isRed(h.left) && isRed((h.right))){
            flipColors(h);
        }

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    /**
     * 得到某个键的值
     * @param key
     * @return
     */
    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node x, Key key){
        if(x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return get(x.left, key);
        }else if(cmp > 0){
            return get(x.right, key);
        }else{
            return x.val;
        }
    }

    /**
     * 寻找最大的键
     * @return 键
     */
    public Key max(){
        return max(root).key;
    }

    /**
     * 寻找最大的键的结点
     * @return 最大的键的结点结点
     */
    private Node max(Node x){
        if(x.right == null){
            return x;
        }
        return max(x.right);
    }

    /**
     * 寻找最小的键
     * @return
     */
    public Key min(){
        return min(root).key;
    }

    /**
     * 寻找最小的键的结点
     * @param x
     * @return
     */
    private Node min(Node x){
        if(x.left == null){
            return x;
        }
        return min(x.left);
    }

    /**
     * 不大于key值得最大键
     * @param key
     * @return
     */
    public Key floor(Key key){
        Node x = floor(root, key);
        if(x == null){
            return null;
        }
        return x.key;
    }
    /**
     * 不大于key值得最大键的结点
     * @param key
     * @return
     */
    private Node floor(Node x, Key key){
        if(x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            return x;
        }
        if(cmp < 0){
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if(t != null){
            return t;
        }else{
            return x;
        }
    }

    /**
     * 不小于key最小的键
     * @param key
     * @return
     */
    public Key ceiling(Key key){
        Node x = ceiling(root, key);
        if(x == null){
            return null;
        }
        return x.key;
    }

    /**
     * 不小于key最小的键的结点
     * @param x
     * @param key
     * @return
     */
    private Node ceiling(Node x, Key key){
        if(x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            return x;
        }
        if(cmp > 0){
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if(t != null) {
            return t;
        }else{
            return x;
        }
    }

    /**
     * 查找升序索引为第k的键
     * @param k
     * @return
     */
    public Key select(int k){
        return select(root, k).key;
    }

    /**
     * 查找升序索引为k的结点
     * @param x
     * @param k
     * @return
     */
    private Node select(Node x, int k){
        if(x == null){
            return null;
        }
        int t = size(x.left);
        if (t > k){
            return select(x.left, k);
        } else if(t < k){
            return select(x.right, k - t - 1);
        } else{
            return x;
        }
    }

    /**
     * 查找键排行第几 也就是升序时索引为几
     * @param key
     * @return
     */
    public int rank(Key key){
        return rank(key, root);
    }

    private int rank(Key key, Node x){
        if(x == null){
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return rank(key, x.left);
        }else if(cmp > 0){
            return 1 + size(x.left) + rank(key, x.right);
        }else{
            return size(x.left);
        }
    }

    /**
     * 将
     * @return
     */
    public Iterable<Key> keys(){
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
        if(x == null){
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0){
            keys(x.left, queue, lo, hi);
        }
        if(cmplo <= 0 && cmphi >= 0){
            queue.enqueue(x.key);
        }
        if(cmphi > 0){
            keys(x.right,queue, lo, hi);
        }
    }

    // Exercise 3.2.32
    private boolean isBST(){
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max){
        if(x == null){
            return true;
        }
        if(min != null && x.key.compareTo(min) <= 0){
            return false;
        }
        if(max != null && x.key.compareTo(max) >= 0){
            return false;
        }
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    //Ex 3.3.33
    private boolean is23(){
        return is23(root);
    }

    /**
     * 判断是否为23树
     * @param x
     * @return
     */
    private boolean is23(Node x){
        if(x == null) {
            return true;
        }
        if(isRed(x.right)){
            return false;
        }
        if(x != root && isRed(x) && isRed(x.left)){
            return false;
        }
        return is23(x.left) && is23(x.right);
    }

    /**
     * 检查从根结点到所有空链接的路径上的黑链接的数量是否相同
     * @return
     */
    private boolean isBalanced(){
        int black = 0;
        for(Node x = root; x != null; x = x.left){
            if(!isRed(x)){
                black++;
            }
        }
        return isBalanced(root, black);
    }

    private boolean isBalanced(Node x, int black){
        if(x == null){
            return black == 0;
        }
        if(!isRed(x)){
            black--;
        }
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    private boolean isRedBlackBST(){
        return is23() && isBalanced() && isBST();
    }

    // Exercise 3.3.39
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if (root != null) {
            root.color = BLACK;
        }
    }

    private Node deleteMin(Node h) {
        if (h.left == null) {
            return null;
        }
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node balance(Node h) {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    // Exercise 3.3.40
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
        }
        return h;
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (root != null) {
            root.color = BLACK;
        }
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left)) {
            h = rotateRight(h);
        }
        if (h.right == null) {
            return null;
        }
        if (!isRed(h.right) && !isRed(h.right.left)) {
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }

    // Exercise 3.3.41
    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (root != null) {
            root.color = BLACK;
        }
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) {
                h = rotateRight(h);
            }
            if (key.compareTo(h.key) == 0 && h.right == null) {
                return null;
            }
            if (!isRed(h.right) && !isRed(h.right.left)) {
                h = moveRedRight(h);
            }
            if (key.compareTo(h.key) == 0) {
                h.val = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

}

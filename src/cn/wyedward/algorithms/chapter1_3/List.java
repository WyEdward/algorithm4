package cn.wyedward.algorithms.chapter1_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class List<Item> implements Iterable<Item>{
    private int N;
    private Node first;
    private Node last;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node{
        private Item item;
        private Node next;
    }

    public List(){
        first = null;
        last = null;
    }
    public List(Item[] a){
        for (Item t : a) {
            append(t);
        }
    }
    public List(Iterable<Item> coll){
        for(Item t : coll){
            append(t);
        }
    }
    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

    /**
     * 打印集合类(链表 栈 队列)
     * @return
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Item item : this){
            s.append(item+" ");
        }
        return s.toString();
    }
    /**
     * 返回链表首
     * @return
     */
    public Item first(){
        if(isEmpty()) throw new RuntimeException("List is Empty");
        return first.item;
    }

    /**
     * 返回链表尾
     * @return
     */
    public Item last(){
        if(isEmpty()) throw new RuntimeException("List is Empty");
        return last.item;
    }

    /**
     * 移除链表首部
     * @return
     */
    public Item removeFirst(){
        if(isEmpty()) throw new RuntimeException("List is Empty");
        Item item = first.item;
        first = first.next;
        N--;
        if(isEmpty()) last = null;//避免游离
        return item;
    }

    /**
     * 在链表首部插入元素
     * @param item
     */
    public void prepend(Item item){
        Node x = new Node();
        x.item = item;
        if(isEmpty()) {first = x; last = x;}
        else          {x.next = first; first = x;}
        N++;
    }

    /**
     * 在链表尾部插入元素
     * @param item
     */
    public void append(Item item){
        Node x = new Node();
        x.item = item;
        if(isEmpty()) {first = x; last = x;}
        else          {last.next = x; last = x;}
        N++;
    }

    /**
     * 移除链表尾部元素 1.3.19
     * @return
     */
   public Item removeLast(){
        if(isEmpty()) throw new RuntimeException("List is Empty");
        if(first == last) return removeFirst();
         Item item = last.item;
         Node prev = null,
              curr = first;
         while(curr.next != null){
             prev = curr;
             curr = curr.next;
         }
         prev.next = null;
         last = prev;
         N--;
         return item;
   }

    /**
     * 删除链表的第k个元素(如果它存在的话)
     * @param k
     * @return
     */
   public Item delete(int k){
       if(k < 1) return null;

       int i = 1;
        Node prev = null,
             curr = first;
        while(i < k && curr != null){
            prev = curr;
            curr = curr.next;
            i++;
        }
        if(curr != null){
            if(prev == null){
                first = curr.next;
            }
            else
                prev.next = curr.next;
            if(curr.next == null){
                last = prev;
            }
            N--;
            return curr.item;
        }
        else{
            return null;
        }
   }
    /**
     * 查看链表是否包含某值 1.3.21
     */
    public boolean contains(Item item){
        Node curr = first;
        while(curr != null && !curr.item.equals(item)){
            curr = curr.next;
        }
        return curr != null;
    }
    /**
     * 接受一个链表结点作为参数并删除该结点的后续结点 1.3.24
     * (如果参数结点或参数结点的后续结点为空则什么也不做)
     */
    public void removeAfter(Node node){
        if (node != null && node.next != null){
            if(node.next.next == null)
                last = node;
            node.next = node.next.next;
            N--;
        }
    }
    /**
     * 接受两个链表结点作为参数，将第二个结点插入链表并使之成为第一个结点的后续结点
     * (如果两个参数为空则什么也不做) 1.3.25
     */
    public void insertAfter(Node a,Node b){
        if(a != null && b != null){
            if(last == a)
                last = b;
            b.next = a.next;
            a.next = b;
            N++;
        }
    }
    /**
     * 接受一条链表和一个字符串key作为参数,删除链表中所有item域为key的结点
     * 1.3.26
     * 非递归解法
     */
    public void remove(Item item){
        List<Integer> idx = new List<Integer>();
        int i = 1;
        for (Item x : this) {
            if(x.equals(item)){
                idx.prepend(i);
            }
            i++;
        }
        for(int k : idx){
            delete(k);
        }
    }
    /**
     * 接受一条链表和一个字符串key作为参数,删除链表中所有item域为key的结点
     * 1.3.26
     * 递归解法
     */
    public void removeRec(Item item){
        first = remove_Node(first,item);
        setLastAndN();
    }
    private Node remove_Node(Node node,Item item){
        if(node != null){
            Node rest = remove_Node(node.next, item);
            if(node.item.equals(item))
                return rest;
            else{
                node.next = rest;
                return node;
            }
        }
        else{
            return null;
        }
    }
    private void setLastAndN(){
        last = first;
        N = 0;
        if(first != null){
            N++;
            while(last.next != null){
                last = last.next;
                N++;
            }
        }
    }

    /**
     * 接受一个链表的首结点作为参数，返回链表中键最大的结点的值。
     * 假设所有键均为正整数，如果链表为空则返回0
     * 1.3.27
     */
    public Item max(Node node){
        if(node == null) throw new RuntimeException("List is empty");
        return max(node, null);
    }
    public Item max(Node node,Item def){
        if(node == null){
            return def;
        }
        Item max = node.item;
        Node curr = node;
        while(curr.next != null){
            curr = curr.next;
            if(((Comparable)max).compareTo(curr.item) < 0)
                max = curr.item;
        }
        return max;
    }
    /**
     * 接受一个链表的首结点作为参数，返回链表中键最大的结点的值。
     * 假设所有键均为正整数，如果链表为空则返回0
     * 递归解法
     * 1.3.28
     */
    public Item maxRec(Node node,Item def){
        if(node == null){
            return def;
        }
        else{
            return maxRec(node);
        }
    }
    public Item maxRec(Node node){
        if(node == null) throw new RuntimeException("List is empty");
        if(node.next == null){
            return node.item;
        }else{
            Item maxTail = maxRec(node.next);
            return ((Comparable)node.item).compareTo(maxTail) > 0 ? node.item : maxTail;
        }
    }

    /**
     * 将链表逆序
     * 1.3.30
     * (迭代解法)
     */
     public void reverse(){
            first = reverse(first);
            setLastAndN();
     }

     public Node reverse(Node node){
         Node first = node,
              reverse = null;
         while(first != null){
             Node second = first.next;
             first.next = reverse;
             reverse = first;
             first = second;
         }
         return reverse;
     }

    /**
     * 将链表逆序
     * 1.3.30
     * (递归解法)
     */
    public void reverseRec(){
         first = reverseRec(first);
    }

    public Node reverseRec(Node root){
        if (root == null) return null;
        if(root.next == null) return root;
        Node second = root.next;
        Node rest = reverseRec(second);
        second.next = root;
        root.next = null;
        return rest;
    }



    /*********************
     * Operations on nodes
     *********************/

    /**
     * 返回第k个元素的值
     * @param k
     * @return
     */
    public Node node(int k){
       if(k < 1) return null;
          int i = 1;
          Node curr = first;
          while(i < k && curr != null){
              curr = curr.next;
               i++;
       }
        return curr;
     }

    /**
     * 创建一个值为item的新结点
     * @param item
     * @return 这个新结点
     */
    public Node createNode(Item item){
        Node node = new Node();
        node.item = item;
        return node;
    }



    /************
     * Unit tests
     ************/

    private static void testBaseMethods()
    {
        int[] a = { 2, 4, 6, 8, 10, 12 };

        List<Integer> lst = new List<Integer>();
        for (int i = 0; i < a.length; i++)
            lst.append(a[i]);
        showList(lst);

        lst = new List<Integer>();
        for (int i = 0; i < a.length; i++)
            lst.prepend(a[i]);
        showList(lst);

        StdOut.println("removeFirst: " + lst.removeFirst());
        showList(lst);
    }

    private static void testRemoveLast()
    {
        List<Integer> lst = new List<Integer>(new Integer[] { 6, 8, 10, 12 });
        showList(lst);

        while (!lst.isEmpty())
        {
            StdOut.println("removeLast: " + lst.removeLast());
            showList(lst);
        }
    }

    private static void testDelete()
    {
        List<Integer> lst = new List<Integer>(new Integer[] { 2, 4, 6, 8, 10, 12 });
        showList(lst);

        StdOut.printf("delete(%d): %s\n", 5, lst.delete(5));
        showList(lst);

        StdOut.printf("delete(%d): %s\n", 1, lst.delete(1));
        showList(lst);

        StdOut.printf("delete(%d): %s\n", 4, lst.delete(4));
        showList(lst);

        StdOut.printf("delete(%d): %s\n", 8, lst.delete(8));
        showList(lst);

        StdOut.printf("delete(%d): %s\n", 0, lst.delete(0));
        showList(lst);

        while (!lst.isEmpty())
        {
            StdOut.printf("delete(%d): %s\n", 1, lst.delete(1));
            showList(lst);
        }
    }

    private static void testContains()
    {
        Integer[] a = { 4, 6, 10, 12 };
        List<Integer> lst = new List<Integer>(a);
        showList(lst);

        StdOut.printf("contains(%d): %s\n", 0, lst.contains(0));

        for (int i = 0; i < a.length; i++)
            StdOut.printf("contains(%d): %s\n", a[i], lst.contains(a[i]));
    }

    private static void testRemove()
    {
        for (int k = 0; k < 8; k++)
        {
            List<Integer> lst1 = randomList(20, 0, 5);
            List<Integer> lst2 = new List<Integer>(lst1);
            StdOut.println(lst1);
            StdOut.println();

            int n = StdRandom.uniform(0, 5);

            StdOut.printf("remove(%d):\n", n);
            lst1.remove(n);
            showList(lst1);

            StdOut.printf("removeRec(%d):\n", n);
            lst2.removeRec(n);
            showList(lst2);
            StdOut.println();
        }
    }

    private static void testReverse()
    {
        int n = 10;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++)
            a[i] = 2 * (i + 1);

        testReverse(new List<Integer>(a));
        StdOut.println();

        testReverse(randomList(20, 0, 10));
        StdOut.println();

        testReverse(new List<Integer>(new Integer[] { 37 }));
        StdOut.println();

        testReverse(new List<Integer>(new Integer[] { }));
        StdOut.println();
    }
    private static void testReverse(List<Integer> lst)
    {
        List<Integer> lst1 = lst;
        List<Integer> lst2 = new List<Integer>(lst1);
        StdOut.println(lst1);

        StdOut.println("reverse():");
        lst1.reverse();
        StdOut.println(lst1);

        StdOut.println("reverseRec():");
        lst2.reverseRec();
        StdOut.println(lst2);
    }

    private static void testNode()
    {
        List<Integer> lst = new List<Integer>(new Integer[] { 2, 6, 12 });
        showList(lst);

        for (int i = -1; i <= 4; i++)
            StdOut.printf("node(%d): %s\n", i, lst.node(i) != null ? lst.node(i).item : null);
    }

    private static void testRemoveAfter()
    {
        List<Integer> lst = new List<Integer>(new Integer[] { 2, 6, 8, 10, 12 });
        showList(lst);

        int[] k = { 0, 2, 1, 5, 3, 2, 1 };

        for (int i = 0; i < k.length; i++)
        {
            StdOut.printf("removeAfter(node(%d)):\n", k[i]);
            lst.removeAfter(lst.node(k[i]));
            showList(lst);
        }
    }

    private static void testInsertAfter()
    {
        List<Integer> lst = new List<Integer>(new Integer[] { 2, 6, 10, 12 });
        showList(lst);

        StdOut.printf("insertAfter(node(%d), null):\n", 1);
        lst.insertAfter(lst.node(1), null);
        showList(lst);

        int ia = 1,
                b = 3;
        StdOut.printf("insertAfter(node(%d), createNode(%d)):\n", ia, b);
        lst.insertAfter(lst.node(ia), lst.createNode(b));
        showList(lst);

        ia = 5;
        b = 25;
        StdOut.printf("insertAfter(node(%d), createNode(%d)):\n", ia, b);
        lst.insertAfter(lst.node(ia), lst.createNode(b));
        showList(lst);
    }

    private static void testMax()
    {
        for (int k = 0; k < 8; k++)
        {
            List<Integer> lst = randomList(10, 100, 1000);
            StdOut.println(lst);

            StdOut.printf("max():    %d\n", lst.max(lst.node(1)));
            StdOut.printf("maxRec(): %d\n\n", lst.maxRec(lst.node(1)));
        }
    }



    public static void main(String[] args)
    {
        testBaseMethods();
        StdOut.println();

        testRemoveLast();
        StdOut.println();

        testDelete();
        StdOut.println();

        testContains();
        StdOut.println();

        testNode();
        StdOut.println();

        testRemoveAfter();
        StdOut.println();

        testInsertAfter();
        StdOut.println();

        testRemove();
        StdOut.println();

        testMax();
        StdOut.println();

        testReverse();
        StdOut.println();
    }
    /*******************
     * Unit test helpers
     *******************/

    public static void showList(List lst)
    {
        StdOut.println(lst);
        if (!lst.isEmpty())
            StdOut.printf("Size: %d, First: %s, Last: %s\n\n", lst.size(), lst.first(), lst.last());
        else
            StdOut.printf("Size: %d\n\n", lst.size());
    }

    private static List<Integer> randomList(int n, int a, int b)
    {
        Integer[] r = new Integer[n];
        for (int i = 0; i < n; i++)
            r[i] = StdRandom.uniform(a, b);
        return new List<Integer>(r);
    }


}

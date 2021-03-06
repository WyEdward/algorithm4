package cn.wyedward.algorithms.chapter3_2;

import edu.princeton.cs.algs4.StdIn;

/**
 * 测试基于二叉查找树的符号表
 */
public class TestBST {

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            bst.put(key, i);
        }
        for (String s : bst.keys()) {
            System.out.println(s + " " + bst.get(s));
        }
        System.out.println("min(): " + bst.min());
        System.out.println("max(): " + bst.max());
        System.out.println("floor(\"F\"): " + bst.floor("F"));
        System.out.println("ceiling(\"C\"): " + bst.ceiling("C"));
        System.out.println("select(1): " + bst.select(1));
        System.out.println("rank(\"R\"): " + bst.rank("R"));
        System.out.println("delete(\"D\"):");
        bst.delete("D");
        for (String s : bst.keys()) {
            System.out.println(s + " " + bst.get(s));
        }
        System.out.println("deleteMin():");
        bst.deleteMin();
        for (String s : bst.keys()) {
            System.out.println(s + " " + bst.get(s));
        }
        System.out.println("deleteMax():");
        bst.deleteMax();
        for (String s : bst.keys()) {
            System.out.println(s + " " + bst.get(s));
        }
    }
}
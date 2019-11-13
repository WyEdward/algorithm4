package cn.wyedward.algorithms.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex04 {
    public static boolean isBalanced(String s){
        Stack<Character> open = new Stack<Character>();
        int n = s.length();
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(c =='('|| c =='['|| c=='{'){
                open.push(c);
            }else if ((c == ')' && open.pop() != '(')
                    || (c == ']' && open.pop() != '[')
                    || (c == '}' && open.pop() != '{')){
                return false;
            }
        }
        return open.isEmpty();
    }

    public static void main(String[] args) {
        String s = StdIn.readAll().trim();
        StdOut.println(isBalanced(s));
    }
}

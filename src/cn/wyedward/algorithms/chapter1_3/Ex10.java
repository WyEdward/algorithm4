package cn.wyedward.algorithms.chapter1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex10 {
    public static void main(String[] args)
    {
        Stack<String> ops  = new Stack<String>();
        Stack<String> vals = new Stack<String>();

        while (!StdIn.isEmpty())
        {
            String s = StdIn.readString();

            if      (s.equals("("))               ;
            else if (s.equals("+") ||
                    s.equals("-") ||
                    s.equals("*") ||
                    s.equals("/") ||
                    s.equals("sqrt")) ops.push(s);
            else if (s.equals(")"))
            {
                String op = ops.pop();
                String v = vals.pop();

                if (op.equals("+") ||
                        op.equals("-") ||
                        op.equals("*") ||
                        op.equals("/"))
                    v = String.format("%s %s %s", vals.pop(), v, op);
                else if (op.equals("sqrt"))
                    v = String.format("%s %s", v, op);

                vals.push(v);
            }
            else vals.push(s);
        }

        StdOut.println(vals.pop());
    }
}

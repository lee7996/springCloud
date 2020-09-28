package com.javbus.server;

import javax.swing.tree.TreeNode;
import java.util.Stack;
import java.util.function.Function;

public class LambdaTest {
    public static void main(String[] args) {
        Function<Integer, String> function = x -> Integer.toBinaryString(10);
        System.out.println(function.apply(10));

        Function<Integer, String> function2 = Integer::toBinaryString;
        System.out.println(function2.apply(10));

        String x = "Hello";

        Function<String,String> func1 = y -> {/*x="a";*/ return y + " "+ x ;};
        System.out.println(func1.apply("w3cschool.cn"));
        Stack<TreeNode> toVisit = new Stack<>();
    }


}


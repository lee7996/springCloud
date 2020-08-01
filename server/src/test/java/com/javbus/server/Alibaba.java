package com.javbus.server;

public class Alibaba {
    public static int k = 0;
    public static Alibaba t1 = new Alibaba("t1");
    public static Alibaba t2 = new Alibaba("t2");
    public static int i = print("i");
    public static int n = 99;
    private int a = 0;
    public int j = print("j");
    {
        print("构造块");
    }
    static {
        print("静态块");
    }

    public Alibaba(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String args[]) {
        Alibaba t = new Alibaba("init");
    }
}
// 1:j i=0 n=0 (i=1, n=1, j=1)
// 2:构造块 i=1 n=1 (2,2)
// 3:t1 i=2 n=2 (3,3)
// 4:j i=3 n=3 (4,4,4)
// 5:构造块 i=4 n=4 (5,5)
// 6:t2 i=5 n=5 (6,6)
// 7:i i=6 n=6 (7,7) 99
// 8:静态块 i=7 n=99 (8,100)
// 9:j i=8 n=100 (9,101)
// 10:构造块 i=9 n=101 (10,102)
// 11:init i=10 n=102

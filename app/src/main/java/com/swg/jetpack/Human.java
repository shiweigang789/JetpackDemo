package com.swg.jetpack;

import java.util.ArrayList;

public class Human {
    private String name;
    private long age;
    protected int no;
    public static long score;
    public static final String real_name = "Sand哥";

    public Human() {
    }

    public int greet(String var1) {
        System.out.println(var1);
        ArrayList var2 = new ArrayList();
        StringBuilder var3 = new StringBuilder();
        var3.append("Hello java asm StringBuilder");
        long var4 = System.nanoTime();
        return 10 + 11;
    }

    public static void staticMethod(String var0) {
        System.out.println("Hello Java Asm!");
    }
}
package com.habitree;

import org.junit.Test;

import java.text.SimpleDateFormat;


public class ExampleUnitTest {

    private static final SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void addition_isCorrect() throws Exception {
        dps();
        sps();
// System.out.println(String.valueOf(a60));
//        System.out.println(String.valueOf(a65));
//        System.out.println(String.valueOf(a70));
//        System.out.println(String.valueOf(a80));
//        System.out.println(String.valueOf(a90));
    }

    public static void dps() {
        int[] dps = new int[]{1, 2, 3, 4, 5, 6, 8, 10, 12, 14, 15, 16, 18, 20, 22, 24, 25, 26, 28, 30, 32, 34, 35, 36, 38, 40, 42, 44, 45, 46, 48, 50, 52, 54, 55, 56, 58, 60, 65, 70, 75, 80, 85, 90, 95, 100, 110, 120, 130, 140, 150, 160, 180, 200, 250, 300};
        for (int dp : dps) {
            float ddd = (float) (dp*0.75);
            String demo = "<dimen name=\"#dp\">@dp</dimen>";
            demo = demo.replace("#", "_" + dp).replace("@", "" + ddd);
            System.out.println(demo);
        }
    }

    public static void sps() {
        int[] sps = new int[]{10, 12, 14, 15, 16, 18, 20, 22, 24, 25, 26, 28, 30, 32, 34, 35, 36, 38, 40, 42, 44, 45};
        for (int sp : sps) {
            float ddd = (float) (sp*0.75);
            String demo = "<dimen name=\"#sp\">@sp</dimen>";
            demo = demo.replace("#", "_" + sp).replace("@", "" + ddd);
            System.out.println(demo);
        }
    }
}
package com.sunmi.yzk.demo;

public class Utils {

    // 在安全可控范围内,处理下sleep方法的异常
    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 封装日志方法
    public static void log(String message) {
        System.out.println(message);
    }
}

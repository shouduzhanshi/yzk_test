package com.sunmi.yzk.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***
 * 商店实现类,维护两条线程,分别用于进货,以及销售
 */
public class Shop extends Thread {

    // 商店名
    private String shopName;

    // 本商店进货用的卡车
    private Truck truck;

    // 随机购买时间提供者.
    private Random random;

    // 商店库存
    private List<Bicycle> bicycles = new ArrayList<>();

    // 是否已经开业
    boolean isOpen;

    public Shop(String shopName, Truck truck) {
        this.shopName = shopName;
        this.truck = truck;
        this.random = new Random();
    }

    @Override
    public void run() {
        super.run();
        if (isOpen) {
            return;
        }
        isOpen = true;
        // 先启动进货任务
        startPurchaseThread();
        // 在当前线程进行销售任务
        sell();
    }

    private void sell() {
        while (true) {
            // 售货线程
            int i = random.nextInt(10);
            Utils.sleep(i * 1000);
            synchronized (this.bicycles) {
                if (bicycles.size() > 0) {
                    // 出售货物
                    Bicycle bicycle = this.bicycles.get(0);
                    this.bicycles.remove(bicycle);
                    Utils.log(shopName + "售出单车一辆" + bicycle.id);
                }
            }
        }
    }


    // 启动进货线程
    private void startPurchaseThread() {
        new Thread(() -> {
            while (true) {
                Utils.sleep(10000);
                purchase();
            }
        }).start();
    }

    private void purchase() {
        // 使用卡车进货,不关心货从哪来.
        List<Bicycle> bicycles = truck.getBicycles();
        synchronized (this.bicycles) {
            this.bicycles.addAll(bicycles);
        }
        Utils.log(shopName + "进货" + bicycles.size());
    }
}

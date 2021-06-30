package com.sunmi.yzk.demo;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 获取工厂 对象
        BicycleFactory bicycleFactory = BicycleFactory.getInstance();
        //启动生产线
        bicycleFactory.start();
        // 工厂运行8秒后启动商店
        Utils.sleep(8000);

        // 商店A 开业
        Opened("商店A", bicycleFactory);

        // 商店B 开业
        Opened("商店B", bicycleFactory);

    }

    private static void Opened(String shopName, BicycleFactory bicycleFactory) {
        // 用卡车隔离商店和工厂的关系,商店只关注进货的类型即可,无需关注货物从哪来.
        new Shop(shopName, new Truck() {
            @Override
            public List<Bicycle> getBicycles() {
                return bicycleFactory.getBicycles();
            }
        }).start();
    }

}

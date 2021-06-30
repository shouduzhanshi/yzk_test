package com.sunmi.yzk.demo;
import java.util.ArrayList;
import java.util.List;

/***
 * 生产工厂
 */
public class BicycleFactory extends Thread {

    /***
     * 工厂库存
     */
    private List<Bicycle> sBicycles = new ArrayList<>();

    /***
     * 工厂单例
     */
    private static BicycleFactory factory = new BicycleFactory();

    boolean produceState;

    public static BicycleFactory getInstance(){
        return factory;
    }

    //私有构造,保障单例模式
    private BicycleFactory(){

    }

    @Override
    public void run() {
        super.run();
        // 防止其他人再开一条生产线.
        if (produceState) {
            return;
        }
        produceState = true;
        produce();
    }


    private void produce() {
        while (true) {
            //每两秒生产一辆单车
            Utils.sleep(2000);
            synchronized (sBicycles){
                sBicycles.add(new Bicycle());
                Utils.log("完成单车生产");
            }
        }
    }

    // 出货方法,给下游提供货品
    public List<Bicycle> getBicycles() {
        List<Bicycle> resultBicycle = new ArrayList<>();
        // 多线程操作共享变量,必须得给它锁上.
        synchronized (sBicycles){
            if (sBicycles.size()<=5){
                resultBicycle.addAll(sBicycles);
            }else{
                for (int i=0;i<5;i++){
                    resultBicycle.add(sBicycles.get(i));
                }
            }
            sBicycles.removeAll(resultBicycle);
        }
        return resultBicycle;
    }



}

package com.sunmi.yzk.demo;

import java.util.List;

// 卡车接口,用于隔离工厂与商店的关系.
public interface Truck {
  List<Bicycle> getBicycles();
}

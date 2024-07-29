package com.example.home_car.DataSender;

public class HardwareData {
    private int H_temperature;
    private boolean H_switchState;

    // 构造函数
    public HardwareData(int temperature, boolean switchState) {
        this.H_temperature = temperature;
        this.H_switchState = switchState;
    }

    // 获取温度
    public int getTemperature() {
        return H_temperature;
    }

    // 设置温度
    public void setTemperature(int temperature) {
        this.H_temperature = temperature;
    }


    // 获取开关状态
    public boolean isSwitchState() {
        return H_switchState;
    }

    // 设置开关状态
    public void setSwitchState(boolean switchState) {
        this.H_switchState = switchState;
    }

    // 获取硬件数据的接口函数
    public static HardwareData fetchHardwareData() {
        // 模拟从硬件获取数据
        int temperature = 25; // 示例数据
        boolean switchState = true; // 示例数据
        return new HardwareData(temperature, switchState);
    }
}

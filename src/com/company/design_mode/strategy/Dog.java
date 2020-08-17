package com.company.design_mode.strategy;

/**
 * @description:
 * @author: wjk
 * @date: 2020/8/17 14:02
 **/
public class Dog {
    private int foodAmount;
    public Dog(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "foodAmount=" + foodAmount +
                '}';
    }
}

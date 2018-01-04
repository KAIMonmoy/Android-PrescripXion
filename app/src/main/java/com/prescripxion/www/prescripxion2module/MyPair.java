package com.prescripxion.www.prescripxion2module;

/**
 * Created by Tasin Ishmam on 12/14/2017.
 */

public class MyPair {

    public Medicine medicine;
    public int amount;

    public MyPair(Medicine medicine, int amount) {
        this.medicine = medicine;
        this.amount = amount;

    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

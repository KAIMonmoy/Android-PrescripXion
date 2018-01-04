package com.prescripxion.www.prescripxion2module;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Tasin Ishmam on 12/11/2017.
 */

public class Medicine implements Serializable, Comparable<Medicine> {
    public String Name;
   public String Details;
   public Double Price;

    public Medicine(String name, String details, Double price) {
        Name = name;
        Details = details;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    @Override
    public int hashCode() {
        String temp = Name + Details;

        return temp.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof Medicine))
            return false;

        Medicine rhs = (Medicine) obj;

        if(this.Name.equals(rhs.Name) && this.Details.equals(rhs.Details)) {
            return true;
        }
        else return false;

    }

    public void setName(String name) {
        Name = name;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    @Override
    public int compareTo(@NonNull Medicine medicine) {
        return this.Name.compareTo(medicine.Name);
    }
}

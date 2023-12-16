package org.example.model;

import java.io.Serializable;

//描述结果数组
public class ResultArr implements Serializable {

    private int[] arr;

    public int[] get() {
        return arr;
    }

    public void set(int[] arr) {
        this.arr = arr;
    }
}

package org.example.model;

import java.io.Serializable;

//描述用户输入的字符串
public class InputStr implements Serializable {
    private String str;

    public String get() {
        return str;
    }

    public void set(String str) {
        this.str = str;
    }
}

package org.example.model;

import java.io.Serializable;
import java.util.List;

//描述排序过程列表
public class SortList implements Serializable {

    private List<int[]> list;

    public List<int[]> get() {
        return list;
    }

    public void set(List<int[]> list) {
        this.list = list;
    }
}

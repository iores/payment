package com.itpay.core.model.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by feng on 2017/8/13 0013.
 * 分页数据
 */
public class ListPage<T> implements Serializable {

    private static final long serialVersionUID = -123011478945074383L;

    /**
     * 总大小
     */
    private int allSize = 0;

    /**
     * 页大小
     */
    private int size = 0;

    /**
     * 当前页号
     */
    private int number = 1;


    /**
     * 页内容
     */
    private List<T> list = new ArrayList<>();
    
    public ListPage() {
        
    }
    public ListPage(int allSize, int size, int number, List<T> list) {
        this.allSize = allSize;
        this.size = size;
        this.number = number;
        this.list = list;
    }


    public int getAllSize() {
        return allSize;
    }

    public void setAllSize(int allSize) {
        this.allSize = allSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

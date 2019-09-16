package com.lx.seckill.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：leesom
 * @date ：Created in 2019/9/6 20:13
 * @description：分页实体类
 * @modified By：
 * @version: $
 */
public class PageBean implements Serializable {

    //当前页
    private long total;

    //当前页记录
    private List row;

    public PageBean(long total,List row){
        this.total=total;
        this.row=row;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRow() {
        return row;
    }

    public void setRow(List row) {
        this.row = row;
    }
}

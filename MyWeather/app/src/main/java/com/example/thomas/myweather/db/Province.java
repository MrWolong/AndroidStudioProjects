package com.example.thomas.myweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Thomas on 2017/12/5.
 */

public class Province extends DataSupport {
    private int id;
    private String name;
    private int code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

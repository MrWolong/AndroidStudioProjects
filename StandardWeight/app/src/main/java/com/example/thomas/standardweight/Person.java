package com.example.thomas.standardweight;
import java.io.Serializable;
public class Person implements Serializable{
    private String sex;
    private Double height;
    private Double weight;
    public Person(){
    }
    public Person(String sex,Double height,Double weight){
        this.sex=sex;
        this.height=height;
        this.weight=weight;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Double getHeight() {
        return height;
    }
    public void setHeight(Double height) {
        this.height = height;
    }
    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
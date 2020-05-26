package com.house.back.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "sale")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    //卖家的姓名
    String username;
    //卖方的标题
    String title;
    //房屋的地点
    String loaction;
    //几室
    String  shi;
    //几厅
    String  ting;
    //几卫
    String  wei;
    //朝向
    String direction;
    //房屋所在层数
    String layernumber;
    //房屋总共层数
    String layertotal;
    //装修情况
    String decoration;
    //供暖情况
    String heat;
    //电梯情况
    String elevator;
    //房屋类型
    String housetype;
    //产权类型
    String propertyrightstype;
    //产权年限
    String propertyrightsyear;
    //房屋面积
    String square;
    //售价
    String price;
    //房屋的特色
    String feature;
    //图片数量
    String picnumber;
    //图片路径
    String picpath;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLoaction() {
        return loaction;
    }

    public void setLoaction(String loaction) {
        this.loaction = loaction;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getTing() {
        return ting;
    }

    public void setTing(String ting) {
        this.ting = ting;
    }

    public String getWei() {
        return wei;
    }

    public void setWei(String wei) {
        this.wei = wei;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLayernumber() {
        return layernumber;
    }

    public void setLayernumber(String layernumber) {
        this.layernumber = layernumber;
    }

    public String getLayertotal() {
        return layertotal;
    }

    public void setLayertotal(String layertotal) {
        this.layertotal = layertotal;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getHeat() {
        return heat;
    }

    public void setHeat(String heat) {
        this.heat = heat;
    }

    public String getElevator() {
        return elevator;
    }

    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    public String getHousetype() {
        return housetype;
    }

    public void setHousetype(String housetype) {
        this.housetype = housetype;
    }

    public String getPropertyrightstype() {
        return propertyrightstype;
    }

    public void setPropertyrightstype(String propertyrightstype) {
        this.propertyrightstype = propertyrightstype;
    }

    public String getPropertyrightsyear() {
        return propertyrightsyear;
    }

    public void setPropertyrightsyear(String propertyrightsyear) {
        this.propertyrightsyear = propertyrightsyear;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getPicnumber() {
        return picnumber;
    }

    public void setPicnumber(String picnumber) {
        this.picnumber = picnumber;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }


}

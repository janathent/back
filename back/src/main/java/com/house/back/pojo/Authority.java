package com.house.back.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "authority")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    String username;
    String houseupload;
    String uploadmanage;
    String collectionrecord;
    String browserrecord;
    String mymessage;
    String infomodify;


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

    public String getHouseupload() {
        return houseupload;
    }

    public void setHouseupload(String houseupload) {
        this.houseupload = houseupload;
    }

    public String getUploadmanage() {
        return uploadmanage;
    }

    public void setUploadmanage(String uploadmanage) {
        this.uploadmanage = uploadmanage;
    }

    public String getCollectionrecord() {
        return collectionrecord;
    }

    public void setCollectionrecord(String collectionrecord) {
        this.collectionrecord = collectionrecord;
    }

    public String getBrowserrecord() {
        return browserrecord;
    }

    public void setBrowserrecord(String browserrecord) {
        this.browserrecord = browserrecord;
    }

    public String getMymessage() {
        return mymessage;
    }

    public void setMymessage(String mymessage) {
        this.mymessage = mymessage;
    }

    public String getInfomodify() {
        return infomodify;
    }

    public void setInfomodify(String infomodify) {
        this.infomodify = infomodify;
    }
}

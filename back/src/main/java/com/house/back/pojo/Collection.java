package com.house.back.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "collection")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    String username;
    String collectionid;

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

    public String getCollectionid() {
        return collectionid;
    }

    public void setCollectionid(String collectonid) {
        this.collectionid = collectonid;
    }
}

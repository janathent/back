package com.house.back.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;



//用于记录用户的收藏

@Entity
@Table(name = "chathistory")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class ChatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    String messagefrom;
    String messageto;
    String record;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessagefrom() {
        return messagefrom;
    }

    public void setMessagefrom(String messagefrom) {
        this.messagefrom = messagefrom;
    }

    public String getMessageto() {
        return messageto;
    }

    public void setMessageto(String messageto) {
        this.messageto = messageto;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}

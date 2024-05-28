package com.wad.firstmvc.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Repository {
    @Id
    @GeneratedValue
    public Long id;
    public String name;

    public Repository(String name) {
        this.name = name;
    }

    public Repository() {}

    //public void appendFile(long rootId) {
    //    this.rootId = rootId;
    //}

    public void setId(Long id) {
        this.id = id;
    }

}
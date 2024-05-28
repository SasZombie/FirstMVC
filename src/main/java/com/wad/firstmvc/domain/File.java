package com.wad.firstmvc.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
public class File {
    @Getter
    @Id
    @GeneratedValue
    public Long id;
    public String name;
    public String category;
    public Long parentId;
    public LocalDate finishedOn;
    public String type;
    public String content;

    public File(String name, String category, Long parentId, LocalDate finishedOn, String type, String content) {
        this.name = name;
        this.category = category;
        this.parentId = parentId;
        this.finishedOn = finishedOn;
        this.type = type;
        this.content = content;
    }

    public File(String name, Long parentId) {
        this.name = name;
        this.category = "createdOn";
        this.parentId = parentId;
        this.finishedOn = LocalDate.now();
        this.type = "file";
        this.content = "";
    }

    public File() {}

    //public void appendFile(long rootId) {
    //    this.rootId = rootId;
    //}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public LocalDate getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(LocalDate finishedOn) {
        this.finishedOn = finishedOn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setParentId(long l) {
        this.parentId = l;
    }
}
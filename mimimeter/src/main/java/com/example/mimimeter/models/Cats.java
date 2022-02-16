package com.example.mimimeter.models;

import org.springframework.web.bind.annotation.SessionAttribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Comparator;

@Entity
@Table(name = "cats")
public class Cats {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "catposition")
    private int position;

    @Column(name = "catname")
    private String name;

    public long getId(){
        return id;
    }

    public int getPosition(){
        return position;
    }

    public String getName(){
        return name;
    }

    public void setPosition(int position) { this.position = position; }
}


package com.example.mimimeter.models;

import java.util.Comparator;

public class CatComporator implements Comparator<Cats> {
    public int compare(Cats a, Cats b) {
        return b.getPosition() - a.getPosition();
    }
}

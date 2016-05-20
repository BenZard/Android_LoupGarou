package com.cryocrystal.exampleproject.models;

/**
 * Created by benoit on 20/05/2016.
 */
public class Word {
    private String name;

    public Word(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public  void setName(String name){
        this.name = name;
    }
}

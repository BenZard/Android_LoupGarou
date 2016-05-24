package com.cryocrystal.exampleproject.models;

import java.util.ArrayList;

/**
 * Created by Riper on 20/05/2016.
 */
public class ListMots {
    ArrayList<Word> words = new ArrayList<Word>();

    public ListMots(){

    }

    public ArrayList<Word> getMots() {
        return words;
    }

    public void addMot(Word mot) {
        words.add(mot);
    }

    public void deleteMot(int pos){
        words.remove(pos);
    }

}

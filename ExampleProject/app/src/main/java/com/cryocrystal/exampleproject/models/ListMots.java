package com.cryocrystal.exampleproject.models;

import java.util.ArrayList;

/**
 * Created by Riper on 20/05/2016.
 */
public class ListMots {
    ArrayList<String> mots = new ArrayList<String>();

    public ListMots(){
        mots.add("Leonardo Di Caprio");
        mots.add("Céline Dion");
        mots.add("Barack Obama");
        mots.add("Donald Trump");
        mots.add("Zizou");
        mots.add("Jamel Debbouze");
    }

    public void addMot(String mot) {
        mots.add(mot);
    }

    public void deleteMot(int pos){
        mots.remove(pos);
    }

}

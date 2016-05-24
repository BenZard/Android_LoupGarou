package com.cryocrystal.exampleproject.models;

import com.cryocrystal.exampleproject.R;

public enum Team {
    BLUE(R.drawable.blue),
    YELLOW(R.drawable.yellow),
    NEUTRE(R.drawable.neutral);

    private int score;
    private final int drawableRes;

    Team(int drawableRes){
        this.drawableRes = drawableRes;
    }

    public int getDrawableRes() {
        return drawableRes;
    }
}

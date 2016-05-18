package com.cryocrystal.exampleproject.models;

import com.cryocrystal.exampleproject.R;

public enum Role {
    WEREWOLF(R.drawable.loup_garou),
    SEER(R.drawable.voyante),
    VILLAGER(R.drawable.villageois);

    private final int drawableRes;

    Role(int drawableRes){
        this.drawableRes = drawableRes;
    }

    public int getDrawableRes() {
        return drawableRes;
    }
}

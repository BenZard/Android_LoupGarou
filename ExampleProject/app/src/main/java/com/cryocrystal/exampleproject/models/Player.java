package com.cryocrystal.exampleproject.models;

public class Player {
    private String name;
    private Team team;

    public Player(String name) {
        this.name = name;
        team = Team.NEUTRE;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }
}

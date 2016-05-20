package com.cryocrystal.exampleproject.models;

public final class Player {
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

    public  void setName(String name){
        this.name = name;
    }
    public  void setTeam(Team team){
        this.team = team;
    }
}

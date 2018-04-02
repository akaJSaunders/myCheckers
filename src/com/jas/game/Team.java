package com.jas.game;

import java.util.ArrayList;

public class Team {


    private ArrayList <Pawn> pawnList;
    private int score;
    private int colour;
    private int dimension;
    private int teamID;
    private int startPoint;

    public Team(int colour, int dimension){
        this.dimension = dimension;
        this.colour = colour;
        pawnList = new ArrayList<>();
        score = 0;
    }

    public Team (int colour){
        this.colour = colour;
        pawnList = new ArrayList<>();
        dimension = 8;
        score = 0;

    }


}

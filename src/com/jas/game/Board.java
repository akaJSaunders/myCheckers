package com.jas.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map <String, Pawn> pieceLoc = new HashMap();

    public Board(){
    }

    public void setUp(){
        for (int x = 0; x < 10; x = x + 2){
            pieceLoc.put(x + ",0",
                         new Pawn(1));
            pieceLoc.put(x + ",2",
                    new Pawn(1));

            pieceLoc.put(x + ",8",
                    new Pawn(2));
        }
        for (int x = 1; x < 10; x = x + 2){
            pieceLoc.put(x + ",1",
                    new Pawn(1));

            pieceLoc.put(x + ",9",
                    new Pawn(2));
            pieceLoc.put(x + ",7",
                    new Pawn(2));
        }
    }

    public void printBoard(){
        System.out.println(" :0 1 2 3 4 5 6 7 8 9");
        for(int y = 0; y < 10; y++){
            System.out.print(y + ":");
            for(int x = 0 ; x < 10; x++) {
               String loc = x + "," + y;
                if(pieceLoc.containsKey(loc)){
                    System.out.print(pieceLoc.get(loc).getTeam());
                }
                else{
                    System.out.print("_");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void move(String curLoc, String newLoc){
        Pawn pawn = pieceLoc.get(curLoc);
        System.out.println(pieceLoc.keySet());
        pieceLoc.remove(curLoc, pawn);
        pieceLoc.put(newLoc, pawn);
    }
}

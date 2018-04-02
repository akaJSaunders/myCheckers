package com.jas.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map <Coordinate, Pawn> pieceLoc = new HashMap<Coordinate, Pawn>();

    public Board(){
    }

    public void setUp(){
        for (int x = 0; x < 10; x = x + 2){
            pieceLoc.put(new Coordinate(x, 0),
                         new Pawn(1));
            pieceLoc.put(new Coordinate(x, 2),
                    new Pawn(1));

            pieceLoc.put(new Coordinate(x, 8),
                    new Pawn(2));
        }
        for (int x = 1; x < 10; x = x + 2){
            pieceLoc.put(new Coordinate(x, 1),
                    new Pawn(1));

            pieceLoc.put(new Coordinate(x, 9),
                    new Pawn(2));
            pieceLoc.put(new Coordinate(x, 7),
                    new Pawn(2));
        }
    }

    public void printBoard(){
        System.out.println(" :0 1 2 3 4 5 6 7 8 9");
        for(int y = 0; y < 10; y++){
            System.out.print(y + ":");
            for(int x = 0 ; x < 10; x++) {
                Coordinate loc = new Coordinate(x, y);
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

    public boolean isValidMove(Coordinate loc, Coordinate newLoc){
        int X = loc.getX();
        int Y = loc.getY();
        int newX = newLoc.getX();
        int newY = newLoc.getY();

        if(pieceLoc.containsKey(loc)){

            if(pieceLoc.containsKey(newLoc)){
                int curTeam = pieceLoc.get(loc).getTeam();
                int newTeam = pieceLoc.get(newLoc).getTeam();
                if(curTeam == newTeam){
                    System.out.println("ERROR: Friendly pawn in the way");
                    return false;
                }
            }

            else if(((newX == X + 1) && (newY == Y + 1)) || ((newX == X + 1) && (newY == Y - 1))
                                                || ((newX == X - 1) && (newY == Y + 1))
                                                || ((newX == X - 1) && (newY == Y - 1))){
                return true;

            }
            System.out.println("ERROR: Pawn cant make such move");
            return false;
        }
        System.out.println("ERROR: No such pawn exists");
        return false;
    }

    public void move(Coordinate loc, Coordinate newLoc){
        if(isValidMove(loc, newLoc)){
            Pawn pawn = pieceLoc.get(loc);
            System.out.println(pieceLoc.keySet());
            pieceLoc.remove(loc, pawn);
            pieceLoc.put(newLoc, pawn);
            return;
        }
    }
}

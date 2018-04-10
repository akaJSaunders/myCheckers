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
            //friendly pawn in way?
            if(pieceLoc.containsKey(newLoc)){
                int curTeam = pieceLoc.get(loc).getTeam();
                int newTeam = pieceLoc.get(newLoc).getTeam();
                if(curTeam == newTeam){
                    System.out.println("ERROR: Friendly pawn in the way");
                    return false;
                }
            }
            //valid range?
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

    public boolean isValidEat(Coordinate loc, Coordinate newLoc) {
        int X = loc.getX();
        int Y = loc.getY();
        int targetX = newLoc.getX();
        int targetY = newLoc.getY();

        if (pieceLoc.containsKey(loc) && pieceLoc.containsKey(newLoc)) {
            int curTeam = pieceLoc.get(loc).getTeam();
            int newTeam = pieceLoc.get(newLoc).getTeam();
            //Check teams
            if (curTeam == newTeam) {
                System.out.println("ERROR: Friendly pawn in the way");
                return false;
            }

            //Check range
            if (((targetX == X + 1) && (targetY == Y + 1)) || ((targetX == X + 1) && (targetY == Y - 1))
                    || ((targetX == X - 1) && (targetY == Y + 1))
                    || ((targetX == X - 1) && (targetY == Y - 1))) {
            }
            else{
                System.out.println("ERROR: Attack not in range");
                return false;
            }

            //Check if free space available
            String direction = getDirection(loc, newLoc);
            boolean piecePresent = false;
            if(direction.equals("NW")){
                Coordinate newC = new Coordinate(newLoc.getX() - 1, newLoc.getY() - 1);
                piecePresent = pieceLoc.containsKey(newC);
            }
            else if(direction.equals("SW")){
                Coordinate newC = new Coordinate(newLoc.getX() - 1, newLoc.getY() + 1);
                piecePresent = pieceLoc.containsKey(newC);
            }
            else if(direction.equals("NE")){
                Coordinate newC = new Coordinate(newLoc.getX() + 1, newLoc.getY() - 1);
                piecePresent = pieceLoc.containsKey(newC);
            }
            else if(direction.equals("SE")){
                Coordinate newC = new Coordinate(newLoc.getX() + 1, newLoc.getY() + 1);
                piecePresent = pieceLoc.containsKey(newC);
            }

            if (piecePresent){
                System.out.println("No empty space behind token");
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean move(Coordinate loc, Coordinate newLoc){
        if(isValidMove(loc, newLoc)){
            Pawn pawn = pieceLoc.get(loc);
            System.out.println(pieceLoc.keySet());
            pieceLoc.remove(loc, pawn);
            pieceLoc.put(newLoc, pawn);
            return true;
        }
        return false;
    }

    public boolean eat(Coordinate loc, Coordinate newLoc){
        if(isValidEat(loc, newLoc)){
            Pawn pawn = pieceLoc.get(loc);
            Pawn enemy = pieceLoc.get(newLoc);
            String direction = getDirection(loc, newLoc);

            pieceLoc.remove(loc, pawn);
            pieceLoc.remove(newLoc, enemy);

            if(direction.equals("NW")){
                Coordinate newC = new Coordinate(newLoc.getX() - 1, newLoc.getY() - 1);
                pieceLoc.put(newC, pawn);
            }
            else if(direction.equals("SW")){
                Coordinate newC = new Coordinate(newLoc.getX() - 1, newLoc.getY() + 1);
                pieceLoc.put(newC, pawn);
            }
            else if(direction.equals("NE")){
                Coordinate newC = new Coordinate(newLoc.getX() + 1, newLoc.getY() - 1);
                pieceLoc.put(newC, pawn);
            }
            else if(direction.equals("SE")){
                Coordinate newC = new Coordinate(newLoc.getX() + 1, newLoc.getY() + 1);
                pieceLoc.put(newC, pawn);
            }
            return true;
        }
        return false;
    }

    public String getDirection(Coordinate loc, Coordinate newLoc){
        String direction;
        if (loc.getX() == newLoc.getX() + 1){
            if(loc.getY() == newLoc.getY() + 1){
                direction = "NW";
            }
            else{
                direction = "SW";
            }
        }
        else{
            if(loc.getY() == newLoc.getY() + 1){
                direction = "NE";
            }
            else{
                direction = "SE";
            }
        }
        return direction;
    }
}

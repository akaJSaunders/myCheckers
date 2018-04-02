package com.jas.game;

public class Game {
    public static void main(String [] arg){
        Board board = new Board();
        board.setUp();
        board.printBoard();
        Coordinate loc = new Coordinate(0,0);
        Coordinate newLoc = new Coordinate(1,1);
        board.move(loc, newLoc);

        System.out.println("");
        System.out.println("new Board");
        board.printBoard();
    }


}

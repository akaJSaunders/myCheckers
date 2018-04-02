package com.jas.game;

public class Game {
    public static void main(String [] arg){
        Board board = new Board();
        board.setUp();
        board.printBoard();
        board.move("2,8", "5,5");

        System.out.println("");
        System.out.println("new Board");
        board.printBoard();
    }


}

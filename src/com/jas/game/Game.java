package com.jas.game;


import java.util.Scanner;

public class Game {
    public static void main(String [] arg){

        Scanner scan = new Scanner(System.in);

        System.out.println("Player is team 1, computer is team 2");
        Board board = new Board();
        board.setUp();

        boolean active = true;
        while(active){
            board.printBoard();
            System.out.println("Move(1) or eat(2)?");
            int answer = scan.nextInt();
            if(answer == 1){
                System.out.println("Player turn, type x and then y of token to move");
                int tokenX = scan.nextInt();
                int tokenY = scan.nextInt();
                System.out.println("type x and then y of destination");
                int targetX = scan.nextInt();
                int targetY = scan.nextInt();

                Coordinate loc = new Coordinate(tokenX,tokenY);
                Coordinate newLoc = new Coordinate(targetX,targetY);
                boolean move = board.move(loc, newLoc);
                if(!move){
                    System.out.println("Not valid move, try again");
                }
            }
            else if(answer == 2){
                System.out.println("Player turn, type x and then y of token to move");
                int tokenX = scan.nextInt();
                int tokenY = scan.nextInt();
                System.out.println("type x and then y of target");
                int targetX = scan.nextInt();
                int targetY = scan.nextInt();

                Coordinate loc = new Coordinate(tokenX,tokenY);
                Coordinate newLoc = new Coordinate(targetX,targetY);

                boolean eat = board.eat(loc, newLoc);
                if(!eat){
                    System.out.println("Not valid eat, try again");
                }
            }

        }
    }


}

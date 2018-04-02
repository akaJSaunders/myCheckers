package com.jas.game;

public class Coordinate {
    private int x, y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode(){
        int hashCode = 1;
        hashCode = hashCode * 37 + x;
        hashCode = hashCode * 37 + y;

        return hashCode;
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof Coordinate)){
            return false;
        }
        Coordinate loc = (Coordinate) other;
        return (this.x == loc.x && this.y == loc.y);
    }
}

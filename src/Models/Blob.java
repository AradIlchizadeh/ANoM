package Models;

import Interfaces.IBlob;

public class Blob implements IBlob {

    private float X;
    private float Y;
    private int mass;

    public Blob(){}

    public void addPixel(int x, int y){
        X += x;
        Y += y;
        mass++;
    }

    public int mass(){
        return mass;
    }

    public double distance(Blob other){
        float deltaX = other.getCenterX() - this.getCenterX();
        float deltaY = other.getCenterY() - this.getCenterY();

        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    public float getCenterX(){
        return X / mass;
    }

    public float getCenterY(){
        return Y / mass;
    }

    @Override
    public String toString() {

        return mass + " " + "( " + X + ", " + Y + ")";
    }
}

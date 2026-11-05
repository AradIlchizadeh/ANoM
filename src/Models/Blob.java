package Models;

import java.util.Locale;

import Interfaces.IBlob;

public class Blob implements IBlob {

    private double X;
    private double Y;
    private int mass = 0;

    public Blob(){}

    public void addPixel(int x, int y){
        X += x;
        Y += y;
        mass++;
    }

    public int mass(){
        return mass;
    }

    public double distance(IBlob other){
        double deltaX = other.getCenterX() - this.getCenterX();
        double deltaY = other.getCenterY() - this.getCenterY();

        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    public double getCenterX(){
        return X / mass;
    }

    public double getCenterY(){
        return Y / mass;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%d (%.4f, %.4f)", mass, getCenterX(), getCenterY());
    }

}

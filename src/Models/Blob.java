package Models;

import java.util.ArrayList;

import Interfaces.IBlob;
import Interfaces.IBlobTracker;

public class Blob implements IBlob, IBlobTracker {

    private float X;
    private float Y;
    private int mass = 0;
    private ArrayList<IBlob> neighborsByDist;
    private IBlob closestNeighbor;

    public Blob(){
        neighborsByDist = new ArrayList<>();
        closestNeighbor = null;
    }

    public void addPixel(int x, int y){
        X += x;
        Y += y;
        mass++;
    }

    public int mass(){
        return mass;
    }

    public double distance(IBlob other){
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

        return mass + " " + "( " + getCenterX() + ", " + getCenterY() + ")";
    }
    public IBlob getClosestNeighbor(){
        return closestNeighbor;
    }
    
    public void setClosestNeighbor(IBlob neighbor){
        closestNeighbor = neighbor;
    }

    public ArrayList<IBlob> getNeighborsByDist(){
        return neighborsByDist;
    }
}

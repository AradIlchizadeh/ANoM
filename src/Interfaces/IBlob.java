package Interfaces;
import Interfaces.IBlobTracker;

public interface IBlob {

    public void addPixel(int x, int y);

    public int mass();

    public double distance(IBlob bead);

    public float getCenterX();

    public float getCenterY();

    public String toString();

    public IBlob getClosestNeighbor();


    
}

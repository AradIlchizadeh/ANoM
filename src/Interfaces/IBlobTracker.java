package Interfaces;
import java.util.ArrayList;


public interface IBlobTracker {
    public IBlob getClosestNeighbor();
    public void setClosestNeighbor(IBlob closest);
    public ArrayList<IBlob> getNeighborsByDist();
    public double distance(IBlob bead);

}
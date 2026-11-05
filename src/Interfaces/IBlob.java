package Interfaces;

public interface IBlob {

    public void addPixel(int x, int y);

    public int mass();

    public double distance(IBlob bead);

    public double getCenterX();

    public double getCenterY();

    public String toString();

}

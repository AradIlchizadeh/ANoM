package Interfaces;

import Models.Blob;

public interface IBlob {

    public void addPixel(int x, int y);

    public int mass();

    public double distance(Blob other);

    public float getCenterX();

    public float getCenterY();

    public String toString();
}

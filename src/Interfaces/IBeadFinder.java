package Interfaces;

import Models.Blob;

import java.util.ArrayList;

public interface IBeadFinder {

    public ArrayList<IBlob> getBeads(int minPixels);

    public void findBlobs();

}

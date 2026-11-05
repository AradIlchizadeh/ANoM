package Interfaces;

import Models.Blob;
import Models.Picture;
import Utilities.*;

import java.util.ArrayList;

public interface IFactory {

    public static IBlob createBlob() {
        return new Blob();
    }

    public static IPicture createPicture(String address){
        return new Picture(address);
    }

    public static IDataCleaner createCleaner(IPicture input, double tau){
        return new DataCleaner(input, tau);
    }

    public static IBeadFinder createBeadFinder(boolean[][] input){
        return new BeadFinder(input);
    }

    public static IBeadTracker createBeadTracker(ArrayList<IBlob> beadsF1, ArrayList<IBlob> beadsF2){
        return new BeadTracker(beadsF1, beadsF2);
    }
}

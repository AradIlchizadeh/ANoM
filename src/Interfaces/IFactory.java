package Interfaces;

import Models.Blob;
import Models.Picture;
import Utilities.*;

public interface IFactory {

    public static IBlob createBlob() {
        return new Blob();
    }

    public static IPicture createPicture(String address){
        return new Picture(address);
    }

    public static IDataCleaner createCleaner(IPicture input, int tau){
        return new DataCleaner(input, tau);
    }

    public static IBeadFinder createBeadFinder(boolean[][] input){
        return new BeadFinder(input);
    }
}

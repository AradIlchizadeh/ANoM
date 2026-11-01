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

    public static IDataRefiner createRefiner(IPicture input, int tau){
        return new DataRefiner(input, tau);
    }

    public static IBeadFinder createBeadFinder(boolean[][] input){
        return new BeadFinder(input);
    }
}

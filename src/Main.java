import Interfaces.*;
import Models.Blob;
import Models.Picture;
import Utilities.DataRefiner;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int minPixel = Integer.parseInt(args[0]);
        int tau = Integer.parseInt(args[1]);
        IPicture picture = IFactory.createPicture(args[2]);

        IDataRefiner dataRefiner = IFactory.createRefiner(picture, tau);
        dataRefiner.Fill();
        IBeadFinder beadFinder = IFactory.createBeadFinder(dataRefiner.getResults());
        beadFinder.findBlobs();
        ArrayList<IBlob> beads = beadFinder.getBeads(minPixel);

        for (IBlob blob : beads) {
            System.out.println(blob.toString());
        }

    }
}

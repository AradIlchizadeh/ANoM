import Interfaces.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        if(args.length != 3)
            throw new Exception("input is invalid.");

        int minPixel = Integer.parseInt(args[0]);
        int tau = Integer.parseInt(args[1]);
        IPicture picture = IFactory.createPicture(args[2]);

        IDataCleaner Cleaner = IFactory.createCleaner(picture, tau);
        Cleaner.clean();
        IBeadFinder beadFinder = IFactory.createBeadFinder(Cleaner.getCleanedData());
        beadFinder.findBlobs();
        ArrayList<IBlob> beads = beadFinder.getBeads(minPixel);

        for (IBlob blob : beads) {
            System.out.println(blob);
        }

    }
}

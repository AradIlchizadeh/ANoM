import Interfaces.*;
import Utilities.BeadTracker;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        if(args.length != 4)
            throw new Exception("input is invalid.");

        int minPixel = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);
        String folderAddress = args[3];

        File folder = new File(folderAddress);
        File[] allFiles = folder.listFiles();
        if(allFiles == null)
            throw new Exception("folder is invalid.");

        ArrayList<File> files = new ArrayList<>();
        for (File f : allFiles) {
            if(f.getName().toLowerCase().endsWith(".jpg"))
                files.add(f);
        }
        if(files.size() < 2)
            throw new Exception("folder has less than 2 pictures.");

        File[] sorted = files.toArray(new File[0]);
        Arrays.sort(sorted);

        for (int i = 0; i < sorted.length - 1; i++) {

            ArrayList<IBlobTracker> beadsI = findBeads(sorted[i].getPath(), minPixel, tau);
            ArrayList<IBlobTracker> beadsNext = findBeads(sorted[i + 1].getPath(), minPixel, tau);

            BeadTracker tracker = new BeadTracker(beadsNext, beadsI);
            tracker.trackBeads(delta);
        }
    }

    private static ArrayList<IBlobTracker> findBeads(String address, int minPixel, double tau) {

        IPicture picture = IFactory.createPicture(address);

        IDataCleaner cleaner = IFactory.createCleaner(picture, tau);
        cleaner.clean();
        IBeadFinder beadFinder = IFactory.createBeadFinder(cleaner.getCleanedData());
        beadFinder.findBlobs();
        ArrayList<IBlob> beads = beadFinder.getBeads(minPixel);

        ArrayList<IBlobTracker> result = new ArrayList<>();
        for (IBlob blob : beads) {
            result.add((IBlobTracker) blob);
        }
        return result;
    }
}

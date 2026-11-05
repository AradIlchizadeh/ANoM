package Core;

import Interfaces.IBeadFinder;
import Interfaces.IBeadTracker;
import Interfaces.IBlob;
import Interfaces.IDataCleaner;
import Interfaces.IFactory;
import Interfaces.IPicture;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Manager {

    public static void run(int minPixel, double tau, double delta, String folderAddress, PrintWriter writer) throws Exception {

        File[] pictures = getSortedPictures(folderAddress);

        for (int i = 0; i < pictures.length - 1; i++) {

            ArrayList<IBlob> beadsI = findBeads(pictures[i].getPath(), minPixel, tau);
            ArrayList<IBlob> beadsNext = findBeads(pictures[i + 1].getPath(), minPixel, tau);

            IBeadTracker tracker = IFactory.createBeadTracker(beadsNext, beadsI);
            tracker.trackBeads(delta, writer);
        }
    }

    private static File[] getSortedPictures(String folderAddress) throws Exception {

        File[] allFiles = new File(folderAddress).listFiles();
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
        return sorted;
    }

    private static ArrayList<IBlob> findBeads(String address, int minPixel, double tau) {

        IPicture picture = IFactory.createPicture(address);
        IDataCleaner cleaner = IFactory.createCleaner(picture, tau);
        IBeadFinder beadFinder = IFactory.createBeadFinder(cleaner.getCleanedData());

        return beadFinder.getBeads(minPixel);
    }
}

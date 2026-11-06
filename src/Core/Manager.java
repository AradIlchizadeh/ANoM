package Core;

import Interfaces.IAvogadro;
import Interfaces.IBeadFinder;
import Interfaces.IBeadTracker;
import Interfaces.IBlob;
import Interfaces.IDataCleaner;
import Interfaces.IFactory;
import Interfaces.IPicture;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class Manager {

    public static void getAvogadro(int minPixel, double tau, double delta, String folderAddress) throws Exception {

        String outputFile = "displacements-" + new File(folderAddress).getName() + ".txt";

        try (PrintWriter writer = new PrintWriter(outputFile)) {
            trackPictures(minPixel, tau, delta, folderAddress, writer);
        }

        IAvogadro avogadro = IFactory.createAvogadro();
        double boltzmannConstant = avogadro.calculateBoltzmann(outputFile);
        double avogadroNumber = avogadro.calculateAvogadro(outputFile);

        System.out.println(String.format(Locale.US, "Boltzmann = %.4e", boltzmannConstant));
        System.out.println(String.format(Locale.US, "Avogadro = %.4e", avogadroNumber));
    }

    private static void trackPictures(int minPixel, double tau, double delta, String folderAddress, PrintWriter writer) throws Exception {

        ArrayList<File> pictures = getSortedPictures(folderAddress);

        for (int i = 0; i < pictures.size() - 1; i++) {

            ArrayList<IBlob> beadsI = findBeads(pictures.get(i).getPath(), minPixel, tau);
            ArrayList<IBlob> beadsNext = findBeads(pictures.get(i + 1).getPath(), minPixel, tau);

            IBeadTracker tracker = IFactory.createBeadTracker(beadsNext, beadsI);
            tracker.trackBeads(delta, writer);
        }
    }

    private static ArrayList<File> getSortedPictures(String folderAddress) throws Exception {

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

        files.sort(Comparator.comparing(File::getName));

        return files;
    }

    private static ArrayList<IBlob> findBeads(String address, int minPixel, double tau) {

        IPicture picture = IFactory.createPicture(address);
        IDataCleaner cleaner = IFactory.createCleaner(picture, tau);
        IBeadFinder beadFinder = IFactory.createBeadFinder(cleaner.getCleanedData());

        return beadFinder.getBeads(minPixel);
    }
}

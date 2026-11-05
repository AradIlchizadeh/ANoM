package Utilities;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;

import Interfaces.IBeadTracker;
import Interfaces.IBlob;

public class BeadTracker implements IBeadTracker {

    private ArrayList<IBlob> beadsF1;
    private ArrayList<IBlob> beadsF2;

    public BeadTracker(ArrayList<IBlob> b1, ArrayList<IBlob> b2){
        beadsF1 = b1;
        beadsF2 = b2;
    }

    public void trackBeads(double delta, PrintWriter writer){
        for (IBlob beadF1 : beadsF1) {
            double minDistance = Double.MAX_VALUE;

            for (IBlob beadF2 : beadsF2) {
                double distance = beadF1.distance(beadF2);
                if(distance <= delta && distance < minDistance)
                    minDistance = distance;
            }

            if(minDistance <= delta)
                writer.println(String.format(Locale.US, "%.4f", minDistance));
        }
    }
}
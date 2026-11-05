package Utilities;
import java.util.ArrayList;
import java.util.Locale;
import Interfaces.IBlob;
import Interfaces.IBlobTracker;
public class BeadTracker  {
    private ArrayList<IBlobTracker> beadsF1;
    private ArrayList<IBlobTracker> beadsF2;


    public BeadTracker(ArrayList<IBlobTracker> b1, ArrayList<IBlobTracker> b2){
        beadsF1 = b1;
        beadsF2 = b2;
    }

    public void trackBeads(double delta){

        for (IBlobTracker beadF1 : beadsF1) {
            for (IBlobTracker beadF2 : beadsF2) {
                double distance = beadF1.distance((IBlob) beadF2);
                if(distance <= delta)
                    beadF1.getNeighborsByDist().add((IBlob) beadF2);
            }
            sort(beadF1);
        }

        for (IBlobTracker beadF1 : beadsF1) {
            IBlobTracker current = beadF1;

            while (current != null) {

                if(current.getNeighborsByDist().isEmpty()){
                    current.setClosestNeighbor(null);
                    current = null;
                    continue;
                }

                IBlobTracker closest = (IBlobTracker) current.getNeighborsByDist().get(0);

                if(closest.getClosestNeighbor() == null){
                    current.setClosestNeighbor((IBlob) closest);
                    closest.setClosestNeighbor((IBlob) current);
                    current = null;
                }
                else{
                    current = resetClosestNeighbors(current, closest);
                }
            }
        }

        for (IBlobTracker beadF1 : beadsF1) {
            IBlob partner = beadF1.getClosestNeighbor();
            if(partner != null)
                System.out.println(String.format(Locale.US, "%.4f", beadF1.distance(partner)));
        }
    }

    private void sort(IBlobTracker blob){
        ArrayList<IBlob> list = blob.getNeighborsByDist();
        for(int i = 0; i < list.size() - 1; i++){
            for(int j = 0; j < list.size() - 1 - i; j++){
                if(blob.distance(list.get(j)) > blob.distance(list.get(j + 1))){
                    IBlob temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    private IBlobTracker resetClosestNeighbors(IBlobTracker bead1, IBlobTracker closest){
        IBlobTracker neighbor = (IBlobTracker) closest.getClosestNeighbor();
        double firstDistance = bead1.distance((IBlob) closest);
        double secondDistance = neighbor.distance((IBlob) closest);

        if (firstDistance < secondDistance){
            neighbor.getNeighborsByDist().remove(0);
            neighbor.setClosestNeighbor(null);
            closest.setClosestNeighbor((IBlob) bead1);
            bead1.setClosestNeighbor((IBlob) closest);
            return neighbor;
        }
        else{
            bead1.getNeighborsByDist().remove(0);
            return bead1;
        }
    }

}
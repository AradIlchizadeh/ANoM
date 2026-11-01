package Utilities;


import Interfaces.IDataCleaner;
import Interfaces.IPicture;

public class DataCleaner implements IDataCleaner {

    private IPicture picture;
    private boolean[][] cleanedData;
    private int width;
    private int height;
    private int tau;

    public DataCleaner(IPicture input, int tau) {
        picture = input;
        width = picture.width();
        height = picture.height();
        cleanedData = new boolean[width][height];
        this.tau = tau;

    }

    public void clean() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (picture.getLuminance(i, j) >= tau) {
                    cleanedData[i][j] = true;
                }
            }
        }
    }

    public boolean[][] getCleanedData() {
        return cleanedData;
    }

}

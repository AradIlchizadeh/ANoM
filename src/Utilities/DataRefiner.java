package Utilities;


import Interfaces.IDataRefiner;
import Interfaces.IPicture;

public class DataRefiner implements IDataRefiner {

    private IPicture picture;
    private boolean[][] result;
    private int width;
    private int height;
    private int tau;

    public DataRefiner(IPicture input, int tau) {
        picture = input;
        width = picture.width();
        height = picture.height();
        result = new boolean[width][height];
        this.tau = tau;

    }

    public void Fill() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (picture.getAlpha(i, j) >= tau) {
                    result[i][j] = true;
                }
            }
        }
    }

    public boolean[][] getResults() {
        return result;
    }

}

package Utilities;

import Interfaces.IBeadFinder;
import Interfaces.IBlob;
import Interfaces.IFactory;

import java.util.ArrayList;

public class BeadFinder implements IBeadFinder {

    private ArrayList<IBlob> blobs;
    private boolean[][] cleanedData;
    private boolean[][] visited;
    private int width;
    private int height;
    private IBlob current;

    public BeadFinder(boolean[][] input) {
        cleanedData = input;
        width = cleanedData.length;
        height = cleanedData[0].length;
        visited = new boolean[width][height];
        blobs = new ArrayList<>();
    }

    public void findBlobs() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (cleanedData[i][j] && !visited[i][j]) {
                    current = IFactory.createBlob();
                    blobs.add(current);
                    DFS_Search(i, j);

                }
            }
        }
    }

    private void DFS_Search(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return;
        if (!cleanedData[x][y]) return;
        if (visited[x][y]) return;

        visited[x][y] = true;
        current.addPixel(x, y);

        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if (k == 0 && l == 0)
                    continue;
                DFS_Search(x + k, y + l);
            }

        }


    }


    public ArrayList<IBlob> getBeads(int minPixels) {
        ArrayList<IBlob> beads = new ArrayList<>();
        for (IBlob blob : blobs) {
            if (blob.mass() >= minPixels)
                beads.add(blob);
        }
        return beads;
    }
}

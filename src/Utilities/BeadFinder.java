package Utilities;

import Interfaces.IBeadFinder;
import Interfaces.IBlob;
import Interfaces.IFactory;
import Models.Blob;

import java.util.ArrayList;

public class BeadFinder implements IBeadFinder {

    private ArrayList<IBlob> blobs;
    private boolean[][] result;
    private boolean[][] visited;
    private int width;
    private int height;
    private IBlob current;

    public BeadFinder(boolean[][] input) {
        result = input;
        width = result.length;
        height = result[0].length;
        visited = new boolean[width][height];
    }

    public void findBlobs() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (result[i][j] && !visited[i][j]) {
                    current = IFactory.createBlob();
                    blobs.add(current);
                    dfs(i, j);

                }
            }
        }
    }

    private void dfs(int x, int y) {
        if (x < 0 && x >= width && y < 0 && y >= height) return;
        if (!result[x][y]) return;
        if (visited[x][y]) return;

        visited[x][y] = true;
        current.addPixel(x, y);

        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if (k == x && l == y)
                    continue;
                dfs(x + k, y + l);
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

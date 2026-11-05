package Core;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {

        if(args.length != 4)
            throw new Exception("input is invalid.");

        int minPixel = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);
        String folderAddress = args[3];

        PrintWriter writer = new PrintWriter("displacements-" + new File(folderAddress).getName() + ".txt");

        Manager.run(minPixel, tau, delta, folderAddress, writer);

        writer.close();
    }
}
package Core;

public class Main {
    public static void main(String[] args) throws Exception {

        if(args.length != 4)
            throw new Exception("usage: Main <minPixel> <tau> <delta> <folderAddress>");

        int minPixel = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);
        String folderAddress = args[3];

        Manager.getAvogadro(minPixel, tau, delta, folderAddress);
    }
}

package Utilities;

import Interfaces.IAvogadro;

import java.io.BufferedReader;
import java.io.FileReader;

public class Avogadro implements IAvogadro {

    public double calculateBoltzmann(String address) throws Exception {
        double variance = calculateVariance(address);
        double diffusionConstant = calculateDiffusionConstant(variance);

        return (diffusionConstant * 6 * Math.PI * Config.VISCOSITY * Config.BEAD_RADIUS) / Config.TEMPERATURE;
    }

    public double calculateAvogadro(String address) throws Exception {
        double boltzmann = calculateBoltzmann(address);

        return Config.R / boltzmann;
    }

    private double calculateVariance(String address) throws Exception {
        double sum = 0;
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(address))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;

                double distance = Double.parseDouble(line);
                sum += distance * distance;
                count++;
            }
        }

        if (count == 0)
            throw new Exception("file has no displacement.");

        return sum * Config.PIXEL_SIZE * Config.PIXEL_SIZE / (2 * count);
    }

    private double calculateDiffusionConstant(double variance) {
        return variance / (2 * Config.DELTA_T);
    }
}

package org.example;

import java.util.*;

public class Main {


    private static List<Instrument> findCombination(Instrument[] instruments, int payloadCapacity, int volumeCapacity) {
        // Sort instruments by a ratio of base scientific value to weight in descending order
        Arrays.sort(instruments, new Comparator<Instrument>() {
            @Override
            public int compare(Instrument o1, Instrument o2) {
                double ratio1 = (double) o1.baseScientificValue / o1.weight;
                double ratio2 = (double) o2.baseScientificValue / o2.weight;
                return Double.compare(ratio2, ratio1); // Sort in descending order
            }
        });

        List<Instrument> selectedInstruments = new ArrayList<>();
        int totalWeight = 0;
        int totalVolume = 0;
        for (Instrument instrument : instruments) {
            if (totalWeight + instrument.weight <= payloadCapacity && totalVolume + instrument.volume <= volumeCapacity) {
                selectedInstruments.add(instrument);
                totalWeight += instrument.weight;
                totalVolume += instrument.volume;
            }
        }
        return selectedInstruments;
    }

    private static int getTotalWeight(List<Instrument> instruments) {
        int totalWeight = 0;
        for (Instrument instrument : instruments) {
            totalWeight += instrument.weight;
        }
        return totalWeight;
    }

    private static int getTotalVolume(List<Instrument> instruments) {
        int totalVolume = 0;
        for (Instrument instrument : instruments) {
            totalVolume += instrument.volume;
        }
        return totalVolume;
    }

    private static int getTotalScientificValue(List<Instrument> instruments) {
        int totalScientificValue = 0;
        for (Instrument instrument : instruments) {
            totalScientificValue += instrument.baseScientificValue;
        }
        return totalScientificValue;
    }
    public static void main(String[] args) {



        Instrument[] instruments = {
                new Instrument(3, 2, 10), // Instrument 1
                new Instrument(4, 3, 15), // Instrument 2
                new Instrument(2, 1, 8),  // Instrument 3
                new Instrument(5, 4, 20)  // Instrument 4
        };
        int payloadCapacity = 10; // kg
        int volumeCapacity = 7;   // m^3


        // Find the optimal combination of instruments
        List<Instrument> optimalCombination = findCombination(instruments, payloadCapacity, volumeCapacity);

        // Output the results
        System.out.println("Selected instruments:");
        for (Instrument instrument : optimalCombination) {
            System.out.println("- Instrument with weight: " + instrument.weight + " kg, volume: " +
                    instrument.volume + " m^3, and scientific value: " + instrument.baseScientificValue);
        }
        System.out.println("Total weight: " + getTotalWeight(optimalCombination) + " kg");
        System.out.println("Total volume: " + getTotalVolume(optimalCombination) + " m^3");
        System.out.println("Total scientific value: " + getTotalScientificValue(optimalCombination));


        // Time Complexity:  O(n log n)

        // Space Complexity: O(n), where n is the number of instruments.

    }
}
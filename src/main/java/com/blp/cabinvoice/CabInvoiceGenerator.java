package com.blp.cabinvoice;

import com.blp.model.Ride;

import java.util.HashMap;
import java.util.Map;

public class CabInvoiceGenerator {
    private static final double COST_PER_KM = 10;
    private static final double COST_PER_MIN = 1;
    private static final double MIN_FARE = 5.0;

    public static double calculateFare(double distance, int time) {
        double totalFare = distance * COST_PER_KM + time * COST_PER_MIN;
        return Math.max(totalFare, MIN_FARE);
    }

    public double calculateTotalFare(Ride[] rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare = calculateFare(ride.getDistance(), ride.getTime());
        }
        return totalFare;

    }

    public static InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.getDistance(), ride.getTime());
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public static InvoiceSummary getInvoice(int userId) {
        Map<Integer, Ride[]> map = new HashMap<>();
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)};
        Ride[] ride1 = {new Ride(2.0, 5),
                new Ride(0.1, 1)};
        map.put(3, rides);
        map.put(1, ride1);
        for (Map.Entry<Integer, Ride[]> entry : map.entrySet()) {
            if (userId == entry.getKey()) {
                Ride[] ridesArray = entry.getValue();
                return calculateFare(ridesArray);
            }
        }
        return null;
    }
}

package Assignment2.part1;
import Assignment2.part1.Aircraft.*;
import Assignment2.part1.Ferry.Ferry;
import Assignment2.part1.Metro.Metro;
import Assignment2.part1.Mono.Monowheel;
import Assignment2.part1.Train.*;
import Assignment2.part1.Wheeled.WheeledTransportation;

// -----------------------------------------------------
// Assignment 2
// Part 1
// Written by: Noa Chayer 40223439
// Due: Febuary 24 2023
// -----------------------------------------------------

public class A2_part1{

    // Sieves through a passed Object array and determines the least and most expensive aircraft, if there are any.
    static void findLeastAndMostExpensiveAircraft(Object[] vehicles){
        Aircraft mostExpensive = new Aircraft();
        Aircraft leastExpensive = new Aircraft();

        double maxPrice = -1;
        double minPrice = -1;
        // Compares object class name to the wanted class name
        for (int i = 0; i < vehicles.length; i++){
            if (vehicles[i].getClass().getSimpleName().equalsIgnoreCase("Aircraft")){
                Aircraft aircraft = ((Aircraft)vehicles[i]);
                if (maxPrice == -1){
                    maxPrice = aircraft.getPrice();
                    mostExpensive = aircraft;
                } else if(maxPrice < aircraft.getPrice()){
                    maxPrice = aircraft.getPrice();
                    mostExpensive = aircraft;
                }
    
                if (minPrice == -1){
                    minPrice = aircraft.getPrice();
                    leastExpensive = aircraft;
                } else if(minPrice > aircraft.getPrice()){
                    minPrice = aircraft.getPrice();
                    leastExpensive = aircraft;
                }
            }
        }

        // No aircraft check
        if (maxPrice == -1 || minPrice == -1){
            System.out.println("There are no aircrafts in this list.");
            return;
        }

        System.out.println("Least expensive aircraft: " + leastExpensive.toString() + "\nMost expensive aircraft: " + mostExpensive.toString());
    }
    public static void main(String[] args){
        Ferry ferry1 = new Ferry();
        Ferry ferry2 = new Ferry(120, 800);
        System.out.println(ferry1.toString()); 
        System.out.println(ferry2.toString()); 

        Aircraft plane1 = new Aircraft();
        Aircraft plane2 = new Aircraft(29000, 1800);
        System.out.println(plane1.toString());
        System.out.println(plane2.toString());

        WW2Airplane bomber = new WW2Airplane(8000, 800, true);
        Aircraft fighter = new WW2Airplane();
        System.out.println(bomber.toString());
        System.out.println(fighter.toString());

        WheeledTransportation wheeled1 = new WheeledTransportation();
        WheeledTransportation wheeled2 = new WheeledTransportation(16, 80);
        System.out.println(wheeled1.toString());
        System.out.println(wheeled2.toString());
        
        Monowheel mono1 = new Monowheel();
        Monowheel mono2 = new Monowheel(mono1);
        System.out.println(mono1.toString());
        System.out.println(mono2.toString());

        Train train1 = new Train(12, 80, 10, "San Fransico", "Portland");
        WheeledTransportation train2 = new Train(train1);
        System.out.println(train1.toString());
        System.out.println(train2.toString());

        Metro orange = new Metro(31, 8, 30, 10, "Montmorency", "Cote Vertu");
        Metro green = new Metro(29, 8, 30, 10, "Angrigon", "Honore Beaugrand");
        System.out.println(orange.toString());
        System.out.println(green.toString());

        WheeledTransportation tram1 = new Tram();
        Tram tram2 = new Tram(1999, 8, 6, 20, 2, "NDG", "Westmount");
        System.out.println(tram1.toString());
        System.out.println(tram2.toString());

        // Equality testing
        System.out.println("\nThe idea that Monowheel 'mono1' and 'mono2' are equal is " + mono1.equals(mono2));
        System.out.println("The idea that Metro 'orange' and 'green' are equal is " + orange.equals(green));
        System.out.println("The idea that Ferry 'ferry2' and Aircraft 'plane1' are equal is " + ferry2.equals(plane1));
        System.out.println("The idea that Train 'train1' and WheeledVehicle 'train2' are equal is " + train1.equals(train2));
        
        // Find least and most expensive for two different arrays
        Object[] arr1 = {ferry1, ferry2, plane1, plane2, bomber, fighter, wheeled1, wheeled2, mono1, mono2, train1, train2, orange, green, tram1, tram2};
        Object[] arr2 = {ferry1, ferry2, new Metro(), new Tram(), new Monowheel(), new Ferry(), wheeled1, wheeled2, mono1, mono2, train1, train2, orange, green, tram1, tram2};

        System.out.println("\nWill now attempt to find the most and least expensive Aircrafts within a list of the previously mentionned vehicles...");
        findLeastAndMostExpensiveAircraft(arr1);
        System.out.println("\nWill now attempt to find the most and least expensive Aircrafts within a list of most of the previous vehicles, without the aircrafts...");
        findLeastAndMostExpensiveAircraft(arr2);
    }
}
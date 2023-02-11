package Assignment2.part2;
import Assignment2.part2.Aircraft.*;
import Assignment2.part2.Ferry.Ferry;
import Assignment2.part2.Metro.Metro;
import Assignment2.part2.Mono.Monowheel;
import Assignment2.part2.Train.*;
import Assignment2.part2.Wheeled.WheeledTransportation;
import Assignment2.part2.Aircraft.Aircraft;

// -----------------------------------------------------
// Assignment 2
// Part 2
// Written by: Noa Chayer 40223439
// Due: Febuary 24 2023
// -----------------------------------------------------

public class A2_part2{

    // Returns a copy of the passed array, using class constructors
    static Object[] copyTheObjects(Object[] vehicles){
        Object[] copyArr = new Object[vehicles.length];

        // Tries to construct passed objects in inverted fashion (See doc.txt)
        for (int i = 0; i < vehicles.length; i++){
            try {copyArr[i] = new Ferry(vehicles[i]); continue;} catch(Exception e){}
            try {copyArr[i] = new WW2Airplane(vehicles[i]); continue;} catch(Exception e){}
            try {copyArr[i] = new Aircraft(vehicles[i]); continue;} catch(Exception e){}
            try {copyArr[i] = new Monowheel(vehicles[i]); continue;} catch(Exception e){}
            try {copyArr[i] = new Tram(vehicles[i]); continue;} catch(Exception e){}
            try {copyArr[i] = new Metro(vehicles[i]); continue;} catch(Exception e){}
            try {copyArr[i] = new Train(vehicles[i]); continue;} catch(Exception e){}
            try {copyArr[i] = new WheeledTransportation(vehicles[i]); continue;} catch(Exception e){}
        }

        return copyArr;
    }

    public static void main(String[] args){
        Ferry ferry1 = new Ferry();
        Ferry ferry2 = new Ferry(120, 800);

        Aircraft plane1 = new Aircraft();
        Aircraft plane2 = new Aircraft(29000, 1800);

        WW2Airplane bomber = new WW2Airplane(8000, 800, true);
        Aircraft fighter = new WW2Airplane();

        WheeledTransportation wheeled1 = new WheeledTransportation();
        WheeledTransportation wheeled2 = new WheeledTransportation(16, 80);
        
        Monowheel mono1 = new Monowheel();
        Monowheel mono2 = new Monowheel(mono1);

        Train train1 = new Train(12, 80, 10, "San Fransico", "Portland");
        WheeledTransportation train2 = new Train(train1);

        Metro orange = new Metro(31, 8, 30, 10, "Montmorency", "Cote Vertu");
        Metro green = new Metro(29, 8, 30, 10, "Angrigon", "Honore Beaugrand");

        WheeledTransportation tram1 = new Tram();
        Tram tram2 = new Tram(1999, 8, 6, 20, 2, "NDG", "Westmount");

        Object[] arr = {ferry1, ferry2, plane1, plane2, bomber, fighter, wheeled1, wheeled2, mono1, mono2, train1, train2, orange, green, tram1, tram2};
        //Object[] arr = {wheeled1, orange, train1};

        System.out.println("\nExisting Object Array toString:\n");
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i].toString());
        }
        System.out.println("\nArray Identifier: " + arr);
        
        System.out.println("\n\nCopy of Said Array toString:\n");
        Object[] copiedArr = copyTheObjects(arr);
        for (int i = 0; i < copiedArr.length; i++){
            System.out.println(copiedArr[i].toString());
        }
        System.out.println("\nArray Identifier: " + copiedArr);

        // Additional Equality Testing
        System.out.println("\n\nChecking All Equalities (Expected: 'n' true returns):");
        for (int i = 0; i < copiedArr.length; i++){
            System.out.println(arr[i].equals(copiedArr[i]));
        }
    }
}
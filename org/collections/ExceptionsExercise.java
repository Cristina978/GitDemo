package org.collections;

import java.util.ArrayList;
import java.util.List;

public class ExceptionsExercise extends Exception {
    public ExceptionsExercise() {
        super();
    }
    public ExceptionsExercise(String message) {
        super(message);
    }

    public static void main(String[] args) {
        List<Integer> newList = new ArrayList<Integer>();
        newList.add(14);
        newList.add(3);
        newList.add(78);
        List<String> nullList = null;

        try {
            System.out.println(newList.get(5)); }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Caught an IndexOutOfBoundsException");
            try {
                throw new ExceptionsExercise("Caught my custom exception");
            } catch (ExceptionsExercise ex) {
                System.out.println("Custom exception caught: " + ex.getMessage());
            }
        } finally {
            System.out.println("Rest of the code");
        }

        try {
            System.out.println(nullList.get(5));}
        catch (NullPointerException e) {
            System.out.println("Caught a NullPointerException");}
         finally {
            System.out.println("Rest of the code part2");
        }
    }

}

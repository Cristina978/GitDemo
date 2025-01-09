package org.collections;

import java.util.HashSet;
import java.util.Iterator;

public class PrimeNumbers {
    public static void main(String[] args) {
        int n = 10;
        HashSet<Integer> primes = new HashSet<>();
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        HashSet<Integer> allNumbers = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            allNumbers.add(i);
        }

        System.out.println("Prime numbers less than " + n + ":");
        Iterator<Integer> primeIterator = primes.iterator();
        while (primeIterator.hasNext()) {
            System.out.print(primeIterator.next() + " ");
        }
        System.out.println();

        System.out.println("Number of prime numbers found: " + primes.size());

        allNumbers.removeAll(primes);

        System.out.println("Non-prime numbers from 1 to " + n + ": " + allNumbers);
        System.out.println("Number of non-prime numbers: " + allNumbers.size());
    }

    public static boolean isPrime(int num) {
        if (num <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}

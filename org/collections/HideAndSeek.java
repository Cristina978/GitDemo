package org.collections;

import java.util.ArrayList;
import java.util.List;

public class HideAndSeek {
    public static void main(String[] args) {
        int n = 7;
        int p = 3;

        List<Integer> people = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            people.add(i);
        }

        int i = 0;
        while (people.size() > 1) {
            i = (i + (p - 1)) % people.size(); //using modulo operation to wrap around
            people.remove(i);
        }

        System.out.println("The last person is: " + people.get(0));
    }
}


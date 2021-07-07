package com.davidhagar.population;

import com.davidhagar.StringGuess;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

public class ListPopulation implements Population {

    private HackedPriorityQueue<StringGuess> population;
    private int size;

    public ListPopulation(int size) {
        this.size = size;

        Comparator<? super StringGuess> comp = new Comparator<StringGuess>() {
            @Override
            public int compare(StringGuess o1, StringGuess o2) {
                return o2.compareTo(o1); // reverse order
            }
        };
        population = new HackedPriorityQueue<StringGuess>(size, comp);
    }

    @Override
    public StringGuess getNextMutationParent() {
        int index = ThreadLocalRandom.current().nextInt(0, population.size());
        return population.get(index);
    }

    @Override
    public StringGuess[] getNextCrossoverParents() {
        return new StringGuess[0];
    }

    @Override
    public void add(StringGuess guess) {

        population.add(guess);
        if( population.size() > size)
            population.poll(); // removes highest/worst score
    }

    @Override
    public void logPopulation() {
        System.out.println();
        for (StringGuess sg : population) {
            System.out.println(sg);
        }
    }
}

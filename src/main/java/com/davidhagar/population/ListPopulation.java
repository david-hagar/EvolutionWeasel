package com.davidhagar.population;

import com.davidhagar.StringGuess;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ListPopulation implements Population {

    private HackedPriorityQueue<StringGuess> population;
    private int size;

    public ListPopulation(int size) {
        this.size = size;

        Comparator<? super StringGuess> comp = new Comparator<>() {
            @Override
            public int compare(StringGuess o1, StringGuess o2) {
                return o2.compareTo(o1); // reverse order
            }
        };
        population = new HackedPriorityQueue<>(size, comp);
    }

    @Override
    public StringGuess getNextMutationParent() {
        int index = ThreadLocalRandom.current().nextInt(0, population.size());
        return population.get(index);
    }

    @Override
    public void intializePopulation(StringGuess[] pop) {

        population.addAll(List.of(pop));
        if (population.size() > size)
            population.poll(); // removes highest/worst score
    }

    @Override
    public void addAndRemoveLeast(StringGuess guess) {

        population.add(guess);
        if (population.size() > size)
            population.poll(); // removes highest/worst score
    }

    @Override
    public int getSize() {
        return size;
    }
}

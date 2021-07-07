package com.davidhagar.population;

import com.davidhagar.StringGuess;

public interface Population {
    StringGuess getNextMutationParent();

    StringGuess[] getNextCrossoverParents();

    void add(StringGuess guess);

    void logPopulation();
}

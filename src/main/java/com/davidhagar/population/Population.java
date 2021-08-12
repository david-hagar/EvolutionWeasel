package com.davidhagar.population;

import com.davidhagar.StringGuess;

public interface Population {
    StringGuess getNextMutationParent();

    void intializePopulation(StringGuess[] population);

    int getSize();

    void addAndRemoveLeast(StringGuess guess);
}

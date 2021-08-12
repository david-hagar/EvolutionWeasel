package com.davidhagar.population;

import com.davidhagar.StringGuess;

import java.util.concurrent.ThreadLocalRandom;

public class CompetitionPopulation implements Population {

    private int size;
    private StringGuess[] list;

    public CompetitionPopulation(int size) {
        this.size = size;
        list = new StringGuess[size];
    }

    private static int getSecondRandomIndex(int firstIndex, int size) {
        int i;

        do {
            i = ThreadLocalRandom.current().nextInt(0, size);
        }
        while (i == firstIndex);
        return i;
    }

    @Override
    public StringGuess getNextMutationParent() {
//        int i = ThreadLocalRandom.current().nextInt(0, size);
//        int j = getSecondRandomIndex(i, size);
//
//
//        int winnerIndex;
//        if( list[i].score < list[j].score ){
//            winnerIndex = i;
//            replaceIndex = j;
//        } else if( list[i].score > list[j].score ){
//            winnerIndex = j;
//            replaceIndex = i;
//        }
//        else if (ThreadLocalRandom.current().nextBoolean() ){ // equal case flip a coin
//            winnerIndex = j;
//            replaceIndex = i;
//        } else {
//            winnerIndex = i;
//            replaceIndex = j;
//        }

        return list[ThreadLocalRandom.current().nextInt(0, size)];
    }

    @Override
    public void intializePopulation(StringGuess[] pop) {
        list = pop;
    }
    @Override
    public void addAndRemoveLeast(StringGuess guess) {
        int i = ThreadLocalRandom.current().nextInt(0, size);
        if( guess.score < list[i].score)
            list[i] = guess;
    }

    @Override
    public int getSize() {
        return size;
    }
}

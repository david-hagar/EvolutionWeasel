package com.davidhagar;

import java.util.concurrent.ThreadLocalRandom;

public class StringGuess implements Comparable<StringGuess> {
    public String guess;
    public float score;
    private int firstDecreasingIndex;

    public StringGuess(String guess, float score, int firstDecreasingIndex) {
        this.guess = guess;
        this.score = score;
        this.firstDecreasingIndex = firstDecreasingIndex;
    }

    public String mutate(StringGoalGA stringGoalGA) {

        StringBuilder str = new StringBuilder(guess);

        if( guess.length() > 0) {
            int numberCharToMutate = (int) Math.max(stringGoalGA.getMutationRate() * guess.length(), 1);
            // System.out.println(numberCharToMutate);
            for (int i = 0; i < numberCharToMutate; i++) {
                int index = ThreadLocalRandom.current().nextInt(0, Math.min(guess.length(), firstDecreasingIndex));

                char c = (char) ThreadLocalRandom.current().nextInt(stringGoalGA.getMinChar(), stringGoalGA.getMaxChar());
                //char c = (char) (ThreadLocalRandom.current().nextBoolean() ? str.charAt(index)+1:str.charAt(index)-1);

                str.setCharAt(index, c);
            }
        }

        if(ThreadLocalRandom.current().nextFloat() <= stringGoalGA.getLengthMutationRate())
            if( ThreadLocalRandom.current().nextBoolean() ) {
                str.setLength(str.length()+1);
                str.setCharAt(str.length()-1, ' ');
            }
            else
                str.setLength(Math.max(1, str.length()-1));

        return str.toString();
    }

    @Override
    public int compareTo(StringGuess o) {
        return Float.compare(score, o.score);
    }

    @Override
    public String toString() {
        return score + ", " + firstDecreasingIndex + ", \"" + guess +'\"';
    }
}

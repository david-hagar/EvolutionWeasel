package com.davidhagar;

import com.davidhagar.population.Population;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

public class StringGoalGA {

    public static final int LOG_RATE_MS = 5;
    private float lengthMutationRate = 0.05f;
    //private int characterAdjustMaxOffset = 10;
    private String goal = "Me thinks it looks like a weasel!";
    private String initialGuess = "Something to start";
    private int maxGenerations = 5000000;
    private char minChar = ' ';
    private char maxChar = '~';
    private int lengthPenaltyPerChar = maxChar-minChar;
    private int endIteration = -1;
    private File logFile;
    private Population population;

    public StringGoalGA(File logFile, Population population) {
        this.logFile = logFile;
        this.population = population;
    }

    public float getLengthMutationRate() {
        return lengthMutationRate;
    }

    public StringGoalGA setLengthMutationRate(float lengthMutationRate) {
        this.lengthMutationRate = lengthMutationRate;
        return this;
    }

    public String getGoal() {
        return goal;
    }

    public StringGoalGA setGoal(String goal) {
        this.goal = goal;
        return this;
    }

    public int getMaxGenerations() {
        return maxGenerations;
    }

    public StringGoalGA setMaxGenerations(int maxGenerations) {
        this.maxGenerations = maxGenerations;
        return this;
    }

    public char getMinChar() {
        return minChar;
    }

    public StringGoalGA setMinChar(char minChar) {
        this.minChar = minChar;
        return this;
    }

    public char getMaxChar() {
        return maxChar;
    }

    public StringGoalGA setMaxChar(char maxChar) {
        this.maxChar = maxChar;
        return this;
    }

    public int getLengthPenaltyPerChar() {
        return lengthPenaltyPerChar;
    }

    public StringGoalGA setLengthPenaltyPerChar(char lengthPenaltyPerChar) {
        this.lengthPenaltyPerChar = lengthPenaltyPerChar;
        return this;
    }



    public float score(String guess) {

        int diffTotal = 0;

        int minLength = Math.min(goal.length(), guess.length());

        for (int i = 0; i < minLength; i++) {
            diffTotal += Math.abs(goal.charAt(i) - guess.charAt(i));
        }
        diffTotal+= (Math.max(goal.length(), guess.length()) - minLength) * lengthPenaltyPerChar;

        return diffTotal; // low score is a better match
    }


    public int firstDecreasingIndex(String guess) {
        int minLength = Math.min(goal.length(), guess.length());
        int lastDiff = 0;

        for (int i = 0; i < minLength; i++) {
            int diff = Math.abs(goal.charAt(i) - guess.charAt(i));
            if( diff < lastDiff )
                return i;
            lastDiff = diff;
        }

        return minLength;
    }


    public void run() throws IOException {

        float initialScore = score(initialGuess);
        StringGuess[] g = new StringGuess[population.getSize()];

        for (int i = 0; i < population.getSize(); i++) {
            g[i] = new StringGuess(initialGuess, initialScore, 10);
        }
        population.intializePopulation(g);

        StringGuess best = new StringGuess("", Float.MAX_VALUE, 10);

        long lastLog = System.currentTimeMillis();

        try (FileOutputStream fos = new FileOutputStream(logFile);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter iterationWriter = new BufferedWriter(osw)
        ) {
            iterationWriter.write("Iteration, Best Score, CAMO, Guess");
            iterationWriter.newLine();

            for (int i = 0; i < maxGenerations; i++) {
                StringGuess p = population.getNextMutationParent();
                String childStr = p.mutate(this);
                float score = score(childStr);
                int cAMO = Math.max(1, p.characterAdjustMaxOffset + (ThreadLocalRandom.current().nextBoolean() ? +1:-1));

                StringGuess guess = new StringGuess(childStr, score(childStr), cAMO);
                population.addAndRemoveLeast(guess);

                if (best.compareTo(guess) > 0)
                    best = guess;

                if (score == 0) {
                    System.out.println("Success in " + i + " generations: " + best);
                    endIteration = i;
                    break;
                }

                if (System.currentTimeMillis() - lastLog > LOG_RATE_MS) {
                    System.out.println(i + ") best = " + best);
                    lastLog = System.currentTimeMillis();
                    //population.logPopulation();
                }
                iterationWriter.write(i + ", " + best.score + ", " + best.characterAdjustMaxOffset + ", " + best.guess);
                iterationWriter.newLine();
            }
        }
        if(best.score == 0)
            System.out.println("Finished with success");
        else
        {
            System.out.println("Finished without finding. best = " + best);
            endIteration = maxGenerations;
        }
    }

    public int getEndIteration() {
        return endIteration;
    }

//    public int getCharacterAdjustMaxOffset() {
//        return characterAdjustMaxOffset;
//    }
//
//    public void setCharacterAdjustMaxOffset(int characterAdjustMaxOffset) {
//        this.characterAdjustMaxOffset = characterAdjustMaxOffset;
//    }

}

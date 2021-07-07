package com.davidhagar;

import com.davidhagar.population.ListPopulation;
import com.davidhagar.population.Population;

public class StringGoalGA {

    private final String name;
    private int populationSize = 1;
    private float mutationRate = 0.01f;
    private float lengthMutationRate = 0.05f;
    private String goal = "Me thinks it looks like a weasel!";
    private String initialGuess = "Something to start";
    private int maxGenerations = 50000;
    private char minChar = ' ';
    private char maxChar = '~';
    private int lengthPenaltyPerChar = (int) (maxChar-minChar);
    private Population population;

    public StringGoalGA(String name) {
        this.name = name;
    }


    public StringGoalGA setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public float getMutationRate() {
        return mutationRate;
    }

    public StringGoalGA setMutationRate(float mutationRate) {
        this.mutationRate = mutationRate;
        return this;
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

    public String getName() {
        return name;
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

    public void run() {
        population = new ListPopulation(populationSize);

        float initialScore = score(initialGuess);
        for (int i = 0; i < populationSize; i++) {
            StringGuess g = new StringGuess(initialGuess, initialScore);
            population.add(g);
        }
        StringGuess best = new StringGuess("", Float.MAX_VALUE);

        for (int i = 0; i < maxGenerations; i++) {
            StringGuess p = population.getNextMutationParent();
            String childStr = p.mutate(this);
            float score = score(childStr);

            StringGuess guess = new StringGuess(childStr, score);
            population.add(guess);

            if( best.compareTo(guess) > 0)
                best = guess;

            if(score == 0) {
                System.out.println("Success in " + i + " generations: " + best);
                break;}

            System.out.println(i + ") best = " + best);
            //population.logPopulation();
        }
        if(best.score == 0)
            System.out.println("Finished with success");
        else
            System.out.println("Finished without finding. best = " + best);
    }

}

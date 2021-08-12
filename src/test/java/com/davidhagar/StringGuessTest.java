package com.davidhagar;

import com.davidhagar.population.ListPopulation;

import java.io.File;
import java.util.PriorityQueue;

class StringGuessTest {

    public void test(){
        StringGoalGA stringGoalGA = new StringGoalGA(new File("test"), new ListPopulation(5));
        for (int i = 0; i < 30; i++) {
            StringGuess sg = new StringGuess("Me thinks it looks like a weasel!", 123.0f);
            String child = sg.mutate(stringGoalGA);
            System.out.println(child);
            System.out.println("firstDecreasingIndex = " + stringGoalGA.firstDecreasingIndex(child));
        }


        PriorityQueue<Integer> pQueue = new PriorityQueue<>();

        // Adding items to the pQueue using add()
        pQueue.add(10);
        pQueue.add(20);
        pQueue.add(15);

        // Printing the top element of PriorityQueue
        System.out.println(pQueue.peek());
        System.out.println(pQueue.size());

        // Printing the top element and removing it
        // from the PriorityQueue container
        System.out.println(pQueue.poll());
        System.out.println(pQueue.size());

        // Printing the top element again
        System.out.println(pQueue.peek());
        System.out.println(pQueue.size());


    }

    public static void main(String[] args) {
        new StringGuessTest().test();
    }

}
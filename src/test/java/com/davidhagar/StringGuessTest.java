package com.davidhagar;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class StringGuessTest {

    public void test(){
        for (int i = 0; i < 30; i++) {
            StringGuess sg = new StringGuess("1234567890", 123.0f);
            System.out.println(sg.mutate(new StringGoalGA("test").setMutationRate(0.2f)));
        }


        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();

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
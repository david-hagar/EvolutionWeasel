package com.davidhagar;

public class EvolutionWeasel {


    public static void main(String[] args) {
        try {
            new StringGoalGA("test").run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

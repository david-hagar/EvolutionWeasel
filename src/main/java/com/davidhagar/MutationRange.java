package com.davidhagar;

import com.davidhagar.population.CompetitionPopulation;
import com.davidhagar.population.ListPopulation;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MutationRange {

    private final float start;
    private final float stop;
    private final int steps;
    private File out;
    private File logDir;

    public MutationRange(float start, float stop, int steps, File out, File logDir) {
        this.start = start;
        this.stop = stop;
        this.steps = steps;
        this.out = out;
        this.logDir = logDir;
    }


    public void run() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(out);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)
        ) {
            writer.write(  "Max Character Change, End Iteration" );
            writer.newLine();

            float step = (stop - start) / steps;

            for (int i = 0; i < steps; i++) {
                float x = start + i * step;
                int characterAdjustMaxOffset = (int)x;
                StringGoalGA ga = new StringGoalGA(new File(logDir, "test-" + characterAdjustMaxOffset + ".csv"), new CompetitionPopulation(3));
//                ga.setMutationRate(mutationsRate);
                //ga.setCharacterAdjustMaxOffset(characterAdjustMaxOffset);
                ga.run();
                writer.write(characterAdjustMaxOffset + ", " + ga.getEndIteration());
                writer.newLine();

                System.out.println( i + " of " + steps + " complete for mutation rate = " + characterAdjustMaxOffset);
                System.out.println();
            }
            System.out.println("Done.");
        }
    }
}

package com.davidhagar;

import com.davidhagar.population.CompetitionPopulation;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class EvolutionWeasel {


    public static void main(String[] args) {
        try {
            File logDir = new File("./out");
            if(logDir.exists())
                FileUtils.deleteDirectory(logDir);
            if(! logDir.mkdirs() )
                throw new Exception("Can't make log directory:" + logDir);

            new StringGoalGA(new File("./out/out1.csv"), new CompetitionPopulation(4)).run();
//            new MutationRange(1, 25, 300, new File("./out/out.csv"), logDir).run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

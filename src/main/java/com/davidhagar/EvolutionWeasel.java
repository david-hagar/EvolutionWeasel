package com.davidhagar;

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

//            new StringGoalGA("test").run();
            new MutationRange(1, 25, 300, new File("./out.csv"), logDir).run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package com.gmerien.app;

public class Run{
    public static void exec(String qPath, String dPath, String df, String rPath, String run){
        long startTime;
        long stopTime;
        long elapsedTime;

        if (run.compareTo("run_0") == 0){

            startTime = System.currentTimeMillis();

            XmlOpener opener = new XmlOpener(qPath);
        
            String path = dPath;

            Queries testQueries = new Queries();

            testQueries = opener.getQueries("no");

            WeightMatrix testWM = new WeightMatrix(df);

            testWM.setTerms(testQueries);

            testWM.setDocuments(path, df, testQueries);

            testWM.initWeightMatrix();

            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            System.out.println("Time 1 : " + elapsedTime);

            testWM.computeWeightMatrix();

            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            System.out.println("Time 2 : " + elapsedTime);

            Results testRes = new Results(run);

            testRes.computeResults(testQueries, testWM);
            testRes.save(rPath);
            
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            System.out.println("Time 3 : " + elapsedTime);
        }
        else if (run.compareTo("run_1") == 0){
            System.out.println("to implement");
            
        }
        else{
            System.out.println("Not a recognized run name, try run_0 or run_1");
        }

    }
}
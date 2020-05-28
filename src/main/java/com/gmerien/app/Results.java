package com.gmerien.app;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Results{
    HashMap<String, Result> res;
    String run;

    public Results(String r){
        res = new HashMap<String, Result>();
        run = r;
    }

    public void computeResults(Queries queries, WeightMatrix weightMatrix){
        String currentQuery;
        String currentDoc;
        Double currentResult;
        Result result;

        ArrayList<SimRank> tmp;

        for (Map.Entry<String, Query> q : queries.getMap().entrySet()){
            currentQuery = q.getKey();
            result = new Result();
            tmp = new ArrayList<SimRank>();

            for (Map.Entry<String, Integer> d : weightMatrix.getDocuments().entrySet()){
                if (!queries.getMap().containsKey(d.getKey())){
                    currentDoc = d.getKey();

                    currentResult = weightMatrix.computeSimilarity(currentQuery, currentDoc);

                    tmp.add(new SimRank(currentDoc, currentResult));
                }
            }

            tmp.sort(new SimComparator());

            result.makeResult(1000, tmp);
            res.put(currentQuery, result);
        }
    }

    public void save(String name){
        try{
            FileWriter fileWriter = new FileWriter(name);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            String qid;
            String docno;
            Integer rank;
            Double sim;
            String runid = run;

            System.out.println("Write results in file " + name );
            for (Map.Entry<String, Result> r : res.entrySet()){
                qid = r.getKey();

                for ( int i = 0; i < r.getValue().res.size(); i ++){
                    docno = r.getValue().res.get(i).getValue0();
                    rank = r.getValue().res.get(i).getValue1();
                    sim = r.getValue().res.get(i).getValue2();

                    printWriter.println(qid + " 0 " + docno + " " + rank + " " + sim + " " + runid);
                }
            }

            printWriter.close();
            
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public void print(){
        for (Map.Entry<String, Result> c : res.entrySet()){
            c.getValue().print(c.getKey(), run);
        }
    }
}
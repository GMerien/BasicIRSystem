package com.gmerien.app;

import java.util.ArrayList;
import org.javatuples.Triplet;

public class Result{
    // docno, rank, similarity
    ArrayList<Triplet<String, Integer, Double>> res;

    public Result(){
        res = new ArrayList<Triplet<String, Integer, Double>>();
    }

    public void addResult(String docno, Integer rank, Double similarity){
        Triplet<String, Integer, Double> tmp = new Triplet<String,Integer,Double>(docno, rank, similarity);

        res.add(tmp);
    }

    public void makeResult(Integer num, ArrayList<SimRank> l){
        if (num > l.size()){
            num = l.size();
            System.out.println("More results requested than number of documents");
        }
        
        for (int i = 0; i < num; i ++){
            this.addResult(l.get(i).getVal().getValue0(), i + 1, l.get(i).getVal().getValue1());
        }
    }

    public void getDocno(){}

    public void print(String qid, String run){
        String docno;
        Integer rank;
        Double sim;

        for (int i = 0; i < res.size(); i ++){
            docno = res.get(i).getValue0();
            rank = res.get(i).getValue1();
            sim = res.get(i).getValue2();

            System.out.println(qid + " 0 " + docno + " " + rank + " " + sim + " " + run);
        }
    }
}
package com.gmerien.app;

import org.javatuples.Pair;

public class SimRank{
    protected Pair<String, Double> val;

    public SimRank(String docno, Double sim){
        val = new Pair<String,Double>(docno, sim);
    }

    public Pair<String, Double> getVal(){
        return val;
    }
}
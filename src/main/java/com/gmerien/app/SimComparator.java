package com.gmerien.app;

import java.util.Comparator;

public class SimComparator implements Comparator<SimRank>{
    public int compare(SimRank s1, SimRank s2){
        int comp = Double.compare(-s1.val.getValue1(), -s2.val.getValue1());
        
        return comp;
    }
}
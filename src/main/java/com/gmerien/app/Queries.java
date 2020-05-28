package com.gmerien.app;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Queries{
    HashMap<String, Query> queriesMap;

    public Queries(){

        queriesMap = new HashMap<String, Query>();
    }

    public boolean addQuery(String name, Query query){
        if(!queriesMap.containsKey(name)){
            queriesMap.put(name, query);
            
            return true;
        }
        else{
            System.out.println("The query " + name + " was already added in the query list");
        
            return false;
        }
    }

    public HashMap<String, Query> getMap(){

        return queriesMap;
    }

    public void print(){
        Iterator<Map.Entry<String, Query>> it = queriesMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Query> pair = (Map.Entry<String, Query>)it.next();
            System.out.println("Query " + pair.getKey() + ":");
            ((Query)pair.getValue()).print();
        }
    }
}
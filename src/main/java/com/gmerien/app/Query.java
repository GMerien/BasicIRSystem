package com.gmerien.app;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Query{
    ArrayList<String> terms;

    public Query(String s, String l){
        terms = new ArrayList<String>();

        Matcher m;
        Pattern pattern = Pattern.compile("[^\\s,;:\\(\\)\\[\\]\\{\\}\\.\\/#']+");

        m = pattern.matcher(s);

        while(m.find()){
            
            terms.add(m.group());
        }
    }

    public ArrayList<String> getQuery(){

        return terms;
    }

    public void print(){
        for(int i = 0; i < terms.size(); i ++){
            System.out.print(" [ " + terms.get(i) + " ] ");
        }
        System.out.println();
    }
}
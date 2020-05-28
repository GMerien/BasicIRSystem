package com.gmerien.app;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocTokens {
    protected int id;
    protected String docId;
    protected ArrayList<String> tokens;
    protected int tokenNumber;

    public DocTokens(DocText d){
        String current;
        String token;

        Matcher m;
        Pattern pattern = Pattern.compile("[^\\s,;:\\(\\)\\[\\]\\{\\}\\.\\/#]+");

        id = d.getId();
        docId = d.getDocId();
        tokens = new ArrayList<String>();
        tokenNumber = 0;
    
        for (int i = 0; i < d.size(); i++){
            current = d.getParagraphs().get(i);
            m = pattern.matcher(current);

            while (m.find()){
                token = m.group();
                tokens.add(token);
                tokenNumber ++;
            }
        }
    }

    public ArrayList<String> getTokens(){

        return tokens;
    }

    public int getId(){

        return id;
    }
    
    public String getDocId(){

        return docId;
    }


    public void print(){
        System.out.println("\nFrom document id : " + id);
        for(int i = 0; i < tokens.size(); i ++){
            System.out.print("||  " + tokens.get(i) + "  ");
        }
    }
}